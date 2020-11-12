package com.qintess.api.security.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.qintess.api.repositorios.UsuarioRepository;

public class AutenticacaoViaTokenFiltro extends OncePerRequestFilter {

	private TokenService tokenService;
	private UsuarioRepository usuarioRepository;

	public AutenticacaoViaTokenFiltro(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = recuperarToken(request);
		
		boolean valido = tokenService.isTokenValido(token);
		
		if(valido)
			autenticarUsuario(token);
		
		filterChain.doFilter(request, response);
	}

	private void autenticarUsuario(String token) {
		int idUsuario = tokenService.getIdUsuario(token);
		
		var usuario = usuarioRepository.findById(idUsuario).get();
		
		var autenticacao = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		
		SecurityContextHolder
			.getContext()
			.setAuthentication(autenticacao);
	}

	private String recuperarToken(HttpServletRequest request) {
		
		var token = request.getHeader("Authorization");
		
		if(token == null || token.isEmpty() || !token.startsWith("Bearer "))
			return null;
		
		return token.substring(7, token.length());
	}

	

}
