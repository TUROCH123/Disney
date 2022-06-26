package com.app.apilogin.integration;

import org.springframework.http.ResponseEntity;

import com.app.apilogin.excepciones.WSException;
import com.app.apilogin.modelo.Usuario;

public interface UsuarioWs {
	public Usuario obtenerUsuarioPorId(Long id) throws WSException;
	public ResponseEntity<?> actualizarUsuarioPorID(Long id,Usuario usuario) throws WSException;
}
