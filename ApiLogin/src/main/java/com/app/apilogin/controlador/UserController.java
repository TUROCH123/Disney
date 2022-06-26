package com.app.apilogin.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
@Controller
public class UserController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
//
	@GetMapping("/peliculas")
	public String peliculas() {
		return "peliculas/peliculas";
	}
}