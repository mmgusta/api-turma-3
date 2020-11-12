package com.qintess.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qintess.api.controllers.dto.LoginDto;
import com.qintess.api.controllers.dto.TokenDto;
import com.qintess.api.security.config.TokenService;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<?> autentica(@RequestBody @Valid LoginDto dto) {
		
		var authentication = dto.converter();
		
		try {
			
			var autenticacao = authenticationManager.authenticate(authentication);
			
			// gerar o token
			String token = tokenService.gerarToken(autenticacao);
			
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		// retornar o token
		
	}
}
