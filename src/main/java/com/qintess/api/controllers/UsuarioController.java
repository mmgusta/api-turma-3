package com.qintess.api.controllers;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qintess.api.controllers.exception.UsuarioNaoEncontradoException;
import com.qintess.api.entidades.Usuario;
import com.qintess.api.repositorios.UsuarioRepository;

@RestController
public class UsuarioController {

	@Autowired
	private UsuarioRepository repo;
	
	@GetMapping("/usuarios")
	public Iterable<Usuario> getTodos() {
		return repo.findAll();
	}
	
	@GetMapping("/usuarios/{id}")
//	public ResponseEntity<?> get(@PathVariable int id) {
	public Usuario get(@PathVariable int id) {
		
		return repo.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException(id));
		
//		var usuario = repo.findById(id);
//		
//		if(usuario.isPresent())
//			return ResponseEntity.ok(usuario);
//		
//		return ResponseEntity.notFound().build();
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Menino não encontrado com o id: " + id);
	}
	
	@PostMapping("/usuarios")
	public ResponseEntity<?> add(@Valid @RequestBody Usuario usuario, BindingResult result) {
		
		if(result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getAllErrors());
		}
		
		var senhaCripto = encodaSenha(usuario.getSenha());
		usuario.setSenha(senhaCripto);
		
		repo.save(usuario);
		
		return ResponseEntity.ok(usuario);
	}
	
	private String encodaSenha(String senha) {
		return new BCryptPasswordEncoder().encode(senha);
	}
}
