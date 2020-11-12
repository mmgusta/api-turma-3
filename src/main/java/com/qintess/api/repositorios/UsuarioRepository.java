package com.qintess.api.repositorios;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.qintess.api.entidades.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

	Optional<Usuario> findByNome(String username);

}
