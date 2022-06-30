package com.app.apilogin.service;

import org.springframework.http.ResponseEntity;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import com.app.apilogin.modelo.Usuario;
import com.fasterxml.jackson.core.JsonProcessingException;

@org.springframework.stereotype.Service
//@Transactional
public interface ApiLoginService {

	public ResponseEntity<?> validarDatos(String email, String pass) throws JsonProcessingException;
	public ResponseEntity<?> actualizarUsuarioPorID(Integer id,Usuario usuarioAct) throws JsonProcessingException;
	public ResponseEntity<?> guardarUsuario(Usuario usuario) throws JsonProcessingException;
	public ResponseEntity<?> obtenerUsuarioPorID(@PathVariable Integer id) throws JsonProcessingException;
}
