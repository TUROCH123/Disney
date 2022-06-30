package com.app.apilogin.service;

import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
import com.app.apilogin.modelo.Pago;
import com.fasterxml.jackson.core.JsonProcessingException;

@org.springframework.stereotype.Service
public interface PagoService {

//	public ResponseEntity<?> obtenerPagoPorID(@PathVariable Long id) throws JsonProcessingException;
	public ResponseEntity<?> realizarPago(Pago pago, Integer id)throws JsonProcessingException;
}
