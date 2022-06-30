package com.app.apilogin.controlador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.apilogin.modelo.Pago;
import com.app.apilogin.service.PagoService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/api/v1/pago")
@CrossOrigin(origins = "http://localhost:8080")
public class PagoControlador {
	private static final Logger logger = LoggerFactory.getLogger(PagoControlador.class);
	
	@Autowired
	private PagoService service;
	
	@PutMapping("/realizarPago")
	public ResponseEntity<?> realizarPago(@RequestParam(value = "id",required = true) Integer id,@RequestBody Pago pago) throws JsonProcessingException {
        return service.realizarPago(pago,id);
	}
	
}
