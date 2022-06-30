package com.app.apilogin.controlador;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.app.apilogin.excepciones.ResourceNotFoundException;
import com.app.apilogin.modelo.Usuario;
import com.app.apilogin.repositorio.UsuarioRepositorio;
import com.app.apilogin.service.ApiLoginService;
import com.app.apilogin.util.Constantes;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/api/v1/login")
@CrossOrigin(origins = "http://localhost:8080")
public class LoginControlador {
	private static final Logger logger = LoggerFactory.getLogger(LoginControlador.class);
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	@Autowired
	private ApiLoginService service;
    
	@GetMapping("/listarUsuarios")
	public List<Usuario> listarUsuarios() {
		return usuarioRepositorio.findAll();
	}

	@PostMapping("/guardarUsuario")
	public ResponseEntity<?> guardarUsuario(@RequestBody Usuario usuario) throws JsonProcessingException {
        return service.guardarUsuario(usuario);
	}

	@GetMapping("/obtenerUsuarioPorID/{id}")
	public ResponseEntity<?> obtenerUsuarioPorID(@PathVariable Integer id) throws JsonProcessingException {
		return service.obtenerUsuarioPorID(id);
	}
	

	@PutMapping("/actualizarUsuarioPorID")
	public ResponseEntity<?> actualizarUsuarioPorID(@RequestParam(value = "id",required = false) Integer id,@RequestBody Usuario usuarioAct) throws JsonProcessingException {
		return service.actualizarUsuarioPorID(id, usuarioAct);
	}
	
	//este metodo sirve para eliminar un empleado
	@DeleteMapping("/eliminarUsuario/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarUsuario(@PathVariable Integer id){
		
			Usuario usuario = usuarioRepositorio.findById(id)
					            .orElseThrow(() -> new ResourceNotFoundException("No existe el usuario con el ID : " + id));
			
			usuarioRepositorio.delete(usuario);
			Map<String, Boolean> respuesta = new HashMap<>();
			respuesta.put("eliminar",Boolean.TRUE);
			return ResponseEntity.ok(respuesta);
	 }

	@GetMapping("/validarDatos")
	public ResponseEntity<?> validarDatos(@RequestParam(value = "email",required = true) String email, @RequestParam(value = "pass",required = true) String pass) throws JsonProcessingException {
		logger.info(Constantes.MENSAJE2,"[validarDatos][user] ", email);
		logger.info(Constantes.MENSAJE2,"[validarDatos][pass] ", pass);
		return service.validarDatos(email, pass);
	}


}