package com.qintess.api.controllers.advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.qintess.api.controllers.exception.UsuarioNaoEncontradoException;

@ControllerAdvice
public class UsuarioNaoEncontradoAdvice {

	@ResponseBody
	@ExceptionHandler(UsuarioNaoEncontradoException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	String usuarioNaoEncontradoHandler(UsuarioNaoEncontradoException ex) {
		return ex.getMessage();
	}
}
