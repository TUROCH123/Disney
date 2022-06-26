package com.app.apilogin.service;

import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
import com.app.apilogin.modelo.Pago;
import com.fasterxml.jackson.core.JsonProcessingException;

@org.springframework.stereotype.Service
public interface PagoService {

//	public ResponseEntity<?> validarDatos(String email, String pass) throws JsonProcessingException;
//	public ResponseEntity<?> actualizarUsuarioPorID(Long id,Usuario usuarioAct) throws JsonProcessingException;
//	public ResponseEntity<?> guardarUsuario(Usuario usuario) throws JsonProcessingException;
//	public ResponseEntity<?> obtenerUsuarioPorID(@PathVariable Long id) throws JsonProcessingException;
	public ResponseEntity<?> realizarPago(Pago pago, Long id)throws JsonProcessingException;
}
