package com.app.apilogin.controlador;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.app.apilogin.modelo.Contenido;
import com.app.apilogin.modelo.Genero;

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
	@GetMapping("/nuevaPelicula")
	public String nuevaPelicula() {
		return "admin/nuevasPeliculas";
	}
	
//    @GetMapping("/nuevaPelicula")
//    ModelAndView nuevaPelicula() {
//        List<Genero> generos = generoRepository.findAll(Sort.by("tipo"));
//
//        return new ModelAndView("admin/nuevasPeliculas")
//                .addObject("pelicula", new Contenido())
//                .addObject("generos", generos);
//    }
}