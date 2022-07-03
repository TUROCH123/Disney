package com.app.apilogin.controlador;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.app.apilogin.entity.Respuesta;
import com.app.apilogin.excepciones.ResourceNotFoundException;
import com.app.apilogin.modelo.Perfiles;
import com.app.apilogin.modelo.Usuario;
import com.app.apilogin.repositorio.PerfilesRepositorio;
import com.app.apilogin.repositorio.UsuarioRepositorio;
import com.app.apilogin.util.Constantes;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/api/v1/ajustes")
@CrossOrigin(origins = "http://localhost:8080")
public class AjustesControlador {
	private static final Logger logger = LoggerFactory.getLogger(AjustesControlador.class);
	@Autowired
	private PerfilesRepositorio perfilesRepositorio;
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
    

	
	@PutMapping("/editarPerfilPorID")
	public ResponseEntity<Perfiles> actualizarPerfilPorID(@RequestParam(value = "id",required = true) Integer id,@RequestBody Perfiles perfilAct) throws JsonProcessingException {
		Perfiles perfilActualizado = new Perfiles();
		
		String obj = Constantes.printPrettyJSONString(perfilAct);
		logger.info(Constantes.MENSAJE2, "[actualizarPerfilPorID] ", obj);
		logger.info(Constantes.MENSAJE2, "[actualizarPerfilPorID][id] ", id);
		Usuario usuario = usuarioRepositorio.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No exite el Usuario"));
		if (usuario != null) {
			logger.info(Constantes.MENSAJE2, "[actualizarPerfilPorID] ", usuario.getPerfiles().size());
			for (Perfiles perfilUser : usuario.getPerfiles()) {
				if (perfilUser.getId().equals(perfilAct.getId())) {
					Perfiles perfil = perfilesRepositorio.findById(perfilAct.getId())
							.orElseThrow(() -> new ResourceNotFoundException("No exite el perfil"));
					perfil.setAlias(perfilAct.getAlias());
					perfil.setAvatar(perfilAct.getAvatar());
					perfil.setIdioma(perfilAct.getIdioma());
					perfil.setPing(perfilAct.getPing());
					perfil.setReproduccionAutomatica(perfilAct.isReproduccionAutomatica());
					perfil.setControlParental(perfilAct.isControlParental());
					
					perfil.getMiListas().clear();
					perfil.getMiListas().addAll(perfilAct.getMiListas());
					
					perfilActualizado = perfilesRepositorio.save(perfil);
					break;
				}
			}
		}
		return ResponseEntity.ok(perfilActualizado);
	}


}