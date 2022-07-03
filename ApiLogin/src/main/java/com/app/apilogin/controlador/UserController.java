package com.app.apilogin.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/peliculas")
	public String peliculas() {
		return "peliculas/peliculas";
	}
	
	@GetMapping("/nuevaPelicula")
	public String nuevaPelicula() {
		return "admin/nuevasPeliculas";
	}
	
	@GetMapping("/login/user")
	public String iniciarSesion() {
		return "admin/login";
	}
	
	@GetMapping("/iniciarPerfil")
	public String perfilInicio() {
		return "admin/index";
	}
	
	@GetMapping("/administrarPelicula")
	public String administrarPelicula() {
		return "admin/administrarPeliculas";
	}

}