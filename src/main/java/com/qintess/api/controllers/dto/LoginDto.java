package com.qintess.api.controllers.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginDto {

	@NotNull(message = "O nome não pode ser nulo.")
	private String nome;
	
	@NotNull(message = "A senha não pode ser nulo.")
	@Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
	private String senha;
	
	public LoginDto(String nome, String senha) {
		this.nome = nome;
		this.senha = senha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(this.nome, this.senha);
	}
	
	
}
