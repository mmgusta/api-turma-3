package com.qintess.api.security.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.qintess.api.entidades.Usuario;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${api.turma3.token.expiracao}")
	private String expiracao;
	
	@Value("${api.turma3.chave}")
	private String chave;

	public String gerarToken(Authentication autenticacao) {
		
		var usuario = (Usuario)autenticacao.getPrincipal();
		var hoje = new Date();
		var dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiracao));
		
		return Jwts
					.builder()
						.setIssuer("Api do treinamento Turma 3")
						.setSubject(usuario.getId().toString())
						.setIssuedAt(hoje)
						.setExpiration(dataExpiracao)
						.signWith(SignatureAlgorithm.HS256, this.chave)
						.compact();
		
	}

	public boolean isTokenValido(String token) {
		try {
			Jwts
				.parser()
					.setSigningKey(this.chave)
					.parseClaimsJws(token);
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public int getIdUsuario(String token) {
		var claims = Jwts
						.parser()
							.setSigningKey(this.chave)
							.parseClaimsJws(token)
							.getBody();
		
		return Integer.parseInt(claims.getSubject());
	}

}
