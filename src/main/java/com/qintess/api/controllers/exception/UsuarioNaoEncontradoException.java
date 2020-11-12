package com.qintess.api.controllers.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {

	public UsuarioNaoEncontradoException(int id) {
		super("Usuario nao encontrado com o id: " + id);
	}
}
