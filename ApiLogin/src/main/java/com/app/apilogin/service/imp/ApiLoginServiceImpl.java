package com.app.apilogin.service.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.app.apilogin.entity.Respuesta;
import com.app.apilogin.excepciones.IDFException;
import com.app.apilogin.excepciones.ResourceNotFoundException;
import com.app.apilogin.modelo.Usuario;
import com.app.apilogin.repositorio.UsuarioRepositorio;
import com.app.apilogin.service.ApiLoginService;
import com.app.apilogin.util.Constantes;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class ApiLoginServiceImpl implements ApiLoginService {
	private static final Logger logger = LoggerFactory.getLogger(ApiLoginServiceImpl.class);

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Override
	public ResponseEntity<?> validarDatos(String email, String pass) throws JsonProcessingException {

		Respuesta respuesta = new Respuesta();
		try {
			if (email.isEmpty() || pass.isEmpty()) {
				throw new IDFException("1", "Los campos ingresados son nulos o vacios");
			} else {

				logger.info(Constantes.MENSAJE2, "[validarDatos][user] ", email);
				logger.info(Constantes.MENSAJE2, "[validarDatos][pass] ", pass);

				Usuario usuario = usuarioRepositorio.validarDatos(email, pass);

				if (usuario != null) {
					respuesta.setCodigo("0");
					respuesta.setMensaje("Proceso Exitoso");
					String obj = Constantes.printPrettyJSONString(respuesta);
					logger.info(Constantes.MENSAJE2, "[guardarUsuario] ", obj);
					return ResponseEntity.status(HttpStatus.OK).body(usuario);
				} else {
					throw new IDFException("1", "Los campos ingresados no son los correctos");
				}

			}
		} catch (IDFException idf) {
			respuesta.setCodigo(idf.getCode());
			respuesta.setMensaje(idf.getMessage());
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		} catch (Exception e) {
			respuesta.setCodigo("-3");
			respuesta.setMensaje(e.getMessage());
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		}
	}

	@Override
	public ResponseEntity<?> actualizarUsuarioPorID(Integer id, Usuario usuarioAct) throws JsonProcessingException {

		String obj = Constantes.printPrettyJSONString(usuarioAct);
		logger.info(Constantes.MENSAJE2, "[actualizarUsuarioPorID] ", obj);
		logger.info(Constantes.MENSAJE2, "[actualizarUsuarioPorID][id] ", id);
		Respuesta respuesta = new Respuesta();
		Usuario usuarioActualizado = new Usuario();
		try {
			Usuario usuario = usuarioRepositorio.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("No exite el Usuario"));
			if (usuario != null) {
				usuario.setNombre(usuarioAct.getNombre());
				usuario.setApellido(usuarioAct.getApellido());
				usuario.setEmail(usuarioAct.getEmail());
				usuario.setCelular(usuarioAct.getCelular());
				usuario.setPassword(usuarioAct.getPassword());
				usuario.setFechaInscripcion(usuarioAct.getFechaInscripcion());
				usuario.setFechaVencimiento(usuarioAct.getFechaVencimiento());

				usuario.getPerfiles().clear();

				usuario.getPerfiles().addAll(usuarioAct.getPerfiles());
				usuario.getMedioPago().clear();
				
				usuario.getMedioPago().addAll(usuarioAct.getMedioPago());
				String objaa = Constantes.printPrettyJSONString(usuario);
				logger.info(Constantes.MENSAJE2, "[actualizarUsuarioPorID][usuario][id3] ", objaa);
				usuario.setRoles(usuarioAct.getRoles());
				usuario.setPlanes(usuarioAct.getPlanes());
				usuarioActualizado = usuarioRepositorio.save(usuario);
				return ResponseEntity.status(HttpStatus.OK).body(usuarioActualizado);
			} else {
				throw new IDFException("1", "No exite el Usuario");
			}
		} catch (IDFException idf) {
			respuesta.setCodigo(idf.getCode());
			respuesta.setMensaje(idf.getMessage());
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		} catch (Exception e) {
			respuesta.setCodigo("-3");
			respuesta.setMensaje(e.getMessage());
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		}

	}

	@Override
	public ResponseEntity<?> guardarUsuario(Usuario usuario) throws JsonProcessingException {
		Respuesta respuesta = new Respuesta();
		try {
			String usr = Constantes.printPrettyJSONString(usuario);
			logger.info(Constantes.MENSAJE2, "[guardarUsuario] ", usr);
			boolean activo = usuarioRepositorio.findById(usuario.getId()).isPresent();
			logger.info(Constantes.MENSAJE2, "[guardarUsuario][activo] ", activo);
			if (!activo) {
				Usuario newUsuario = usuarioRepositorio.save(usuario);
				String usrs = Constantes.printPrettyJSONString(newUsuario);
				logger.info(Constantes.MENSAJE2, "[guardarUsuario][new] ", usrs);
				return ResponseEntity.status(HttpStatus.OK).body(newUsuario);
			} else {
				throw new IDFException("1", "El usuario ya existe.");
			}
		} catch (IDFException idf) {
			respuesta.setCodigo(idf.getCode());
			respuesta.setMensaje(idf.getMessage());
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		} catch (Exception e) {
			respuesta.setCodigo("-3");
			respuesta.setMensaje(e.getMessage());
			String obj = Constantes.printPrettyJSONString(respuesta);
			logger.info(Constantes.MENSAJE2, "[guardarUsuario] ", obj);
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		}
	}

	@Override
	public ResponseEntity<?> obtenerUsuarioPorID(Integer id) throws JsonProcessingException {
		Respuesta respuesta = new Respuesta();
		try {
			Usuario usuario = usuarioRepositorio.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("No existe el usuario"));
			if (usuario != null) {
			String obj = Constantes.printPrettyJSONString(usuario);
			logger.info(Constantes.MENSAJE2, "[obtenerUsuarioPorID] ", obj);
			return ResponseEntity.status(HttpStatus.OK).body(usuario);
			}else {
				throw new IDFException("1", "El usuario no existe.");
			}
		} catch (IDFException idf) {
			respuesta.setCodigo(idf.getCode());
			respuesta.setMensaje(idf.getMessage());
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		} catch (Exception e) {
			respuesta.setCodigo("-3");
			respuesta.setMensaje(e.getMessage());
			String obj = Constantes.printPrettyJSONString(respuesta);
			logger.info(Constantes.MENSAJE2, "[guardarUsuario] ", obj);
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		}
	}

}