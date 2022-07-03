package com.app.apilogin.controlador;

import java.util.List;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.app.apilogin.excepciones.WSException;
import com.app.apilogin.integration.UsuarioWs;
import com.app.apilogin.modelo.Contenido;
import com.app.apilogin.modelo.Genero;
import com.app.apilogin.modelo.Pelicula;
import com.app.apilogin.modelo.Usuario;
import com.app.apilogin.repositorio.ContenidoRepositorio;
import com.app.apilogin.repositorio.GeneroRepositorio;
import com.app.apilogin.repositorio.PeliculaRepositorio;
import com.app.apilogin.service.imp.AlmacenServicioImpl;
import com.app.apilogin.util.Constantes;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
@RequestMapping("/admin")
public class AdminControlador {
	private static final Logger logger = LoggerFactory.getLogger(AdminControlador.class);
	@Autowired
	private ContenidoRepositorio contenidoRepositorio;

	@Autowired
	private GeneroRepositorio generoRepositorio;

	@Autowired
	private AlmacenServicioImpl servicio;
	
	@Autowired
	private UsuarioWs usuariows;

	
	@GetMapping("")
	public ModelAndView verPaginaDeInicio(@PageableDefault(sort = "nombre", size = 5) Pageable pageable) {
		Page<Contenido> peliculas = contenidoRepositorio.findAll(pageable);
		return new ModelAndView("admin/administrarPeliculas").addObject("peliculas", peliculas);
	}

	@GetMapping("/peliculas/nuevo")
	public ModelAndView mostrarFormularioDeNuevaPelicula() {
		List<Genero> generos = generoRepositorio.findAll(Sort.by("tipo"));
		logger.info(Constantes.MENSAJE2,"[mostrarFormularioDeNuevaPelicula][generos] ", generos.size());

		return new ModelAndView("admin/nuevaPelicula")
				.addObject("pelicula", new Contenido())
				.addObject("generos",generos);
	}
	
	@PostMapping("/peliculas/nuevo")
	public ModelAndView registrarPelicula(@Validated Contenido contenido, BindingResult bindingResult) {

		logger.info(Constantes.MENSAJE2,"[registrarPelicula][pelicula][descripcionGeneral] ", contenido.getDescripcionGeneral());
		logger.info(Constantes.MENSAJE2,"[registrarPelicula][pelicula][nombre] ", contenido.getNombre());
		logger.info(Constantes.MENSAJE2,"[registrarPelicula][pelicula][pGeneral] ", contenido.getPgeneral());
		logger.info(Constantes.MENSAJE2,"[registrarPelicula][pelicula][Portada] ", contenido.getPortada());
		logger.info(Constantes.MENSAJE2,"[registrarPelicula][pelicula][TrailerId] ", contenido.getYoutubeTrailerId());
		if(bindingResult.hasErrors() || contenido.getPortada().isEmpty()) {
			if(contenido.getPortada().isEmpty()) {
				bindingResult.rejectValue("portada","MultipartNotEmpty");
			}
			
			List<Genero> generos = generoRepositorio.findAll(Sort.by("tipo"));

			logger.info(Constantes.MENSAJE2,"[registrarPelicula][generos] ", generos);

			return new ModelAndView("admin/nuevaPelicula")
					.addObject("pelicula",contenido)
					.addObject("generos",generos);
		}

		String rutaPortada = servicio.almacenarArchivo(contenido.getPortada());//iamgen del contenido
		contenido.setRutaPortada(rutaPortada);//RUTA del contenido
		logger.info(Constantes.MENSAJE2,"[registrarPelicula][pelicula][RutaPortada] ", contenido.getRutaPortada());
		contenidoRepositorio.save(contenido);
		return new ModelAndView("redirect:/admin");
	}
	
	@GetMapping("/peliculas/{id}/editar")
	public ModelAndView mostrarFormilarioDeEditarPelicula(@PathVariable Integer id) {
		Contenido pelicula = contenidoRepositorio.getOne(id);
		List<Genero> generos = generoRepositorio.findAll(Sort.by("titulo"));
		
		return new ModelAndView("admin/editarPelicula")
				.addObject("pelicula",pelicula)
				.addObject("generos",generos);
	}
	
//	@PostMapping("/peliculas/{id}/editar")
//	public ModelAndView actualizarPelicula(@PathVariable Integer id,@Validated Pelicula pelicula,BindingResult bindingResult) {
//		if(bindingResult.hasErrors()) {
//			List<Genero> generos = generoRepositorio.findAll(Sort.by("titulo"));
//			return new ModelAndView("admin/editar-pelicula")
//					.addObject("pelicula",pelicula)
//					.addObject("generos",generos);
//		}
//		
//		Pelicula peliculaDB = peliculaRepositorio.getOne(id);
//		peliculaDB.setTitulo(pelicula.getDescripcion());//titulo del contenido
//		peliculaDB.setDescripcion(pelicula.getDescripcion());//descripcion del contenido
//		peliculaDB.setFechaEstreno(pelicula.getFechaEstreno());//fecha debe de ser pelicula o serie o documental
//		peliculaDB.setYoutubeTrailerId(pelicula.getYoutubeTrailerId());//idvideo trailer
//		peliculaDB.setGeneros(pelicula.getGeneros());//generos del contenido
//		
//		if(!pelicula.getPortada().isEmpty()) {//portada del contenido
//			servicio.eliminarArchivo(peliculaDB.getRutaPortada());//ruta del contenido
//			String rutaPortada = servicio.almacenarArchivo(pelicula.getPortada());//portada del contenido
//			peliculaDB.setRutaPortada(rutaPortada); //ruta del contenido
//		}
//		
//		peliculaRepositorio.save(peliculaDB);
//		return new ModelAndView("redirect:/admin");
//	}
	
//	@PostMapping("/peliculas/{id}/eliminar")
//	public String eliminarPelicula(@PathVariable Integer id) {
//		Pelicula pelicula = peliculaRepositorio.getOne(id);
//		peliculaRepositorio.delete(pelicula);
//		servicio.eliminarArchivo(pelicula.getRutaPortada());//portada del contenido
//		
//		return "redirect:/admin";
//	}
	
	@PostMapping("/iniciarSesion")
	public ModelAndView iniciarSesionLog(String email,String pass) throws WSException, JsonProcessingException {
		logger.info(Constantes.MENSAJE2,"[iniciarSesionLog][email] ", email);
		logger.info(Constantes.MENSAJE2,"[iniciarSesionLog][pass] ", pass);
		Usuario usuario = usuariows.validarDatos(email,pass);
		String obj = Constantes.printPrettyJSONString(usuario);
		logger.info(Constantes.MENSAJE2, "[iniciarSesionLog] ", obj);

		return new ModelAndView("admin/perfil")
				.addObject("usuario",usuario).addObject("perfiles",usuario.getPerfiles());
	}
	
	
}
