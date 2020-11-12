package com.qintess.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qintess.api.entidades.Usuario;
import com.qintess.api.repositorios.UsuarioRepository;

@Configuration
public class CarregarDatabase {

	private static final Logger log = LoggerFactory.getLogger(CarregarDatabase.class);
	
	@Bean
	CommandLineRunner iniciarDatabase(UsuarioRepository repo) {
		return args -> {
			log.info(repo.save(new Usuario("gustavo", "$2a$10$aMm0uo4v.ke..bRRE60jVOe5aGmBUz2HFWdDI2r.icK01VaOjepy2")).toString());
			log.info(repo.save(new Usuario("ivanzao", "$2a$10$aMm0uo4v.ke..bRRE60jVOe5aGmBUz2HFWdDI2r.icK01VaOjepy2")).toString());
			log.info(repo.save(new Usuario("bruno", "$2a$10$aMm0uo4v.ke..bRRE60jVOe5aGmBUz2HFWdDI2r.icK01VaOjepy2")).toString());
			log.info(repo.save(new Usuario("igor", "$2a$10$aMm0uo4v.ke..bRRE60jVOe5aGmBUz2HFWdDI2r.icK01VaOjepy2")).toString());
		};
	}
}
