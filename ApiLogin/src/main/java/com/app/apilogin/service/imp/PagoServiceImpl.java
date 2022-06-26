package com.app.apilogin.service.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.app.apilogin.entity.Respuesta;
import com.app.apilogin.excepciones.IDFException;
import com.app.apilogin.excepciones.WSException;
import com.app.apilogin.integration.UsuarioWs;
import com.app.apilogin.modelo.MedioPago;
import com.app.apilogin.modelo.Pago;
import com.app.apilogin.modelo.Usuario;
import com.app.apilogin.repositorio.PagoRepositorio;
import com.app.apilogin.service.PagoService;
import com.app.apilogin.util.Constantes;
import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class PagoServiceImpl implements PagoService {
	private static final Logger logger = LoggerFactory.getLogger(PagoServiceImpl.class);

	@Autowired
	private PagoRepositorio pagoRepositorio;

	@Autowired
	private UsuarioWs usuarioWs;
	
	@Override
	public ResponseEntity<?> realizarPago(Pago pago,Long id) throws JsonProcessingException {
		Respuesta respuesta = new Respuesta();
		boolean existeMedioPago = false;
		boolean activo = false;
		try {
			Usuario usuario = usuarioWs.obtenerUsuarioPorId(id);
			String usr = Constantes.printPrettyJSONString(usuario);
			logger.info(Constantes.MENSAJE2, "[realizarPago][usuario] ", usr);
			if (usuario != null) {
				existeMedioPago = existeMedioPago(existeMedioPago,usuario,pago,id);
			}else {
				throw new IDFException("1", "El usuario no existe");
			}
			
			activo = existePago(pago,existeMedioPago,activo);
			
			if (!activo) {
				Pago newPago= pagoRepositorio.save(pago);
				String usrs = Constantes.printPrettyJSONString(newPago);
				logger.info(Constantes.MENSAJE2, "[realizarPago][new] ", usrs);
				
				return ResponseEntity.status(HttpStatus.OK).body(newPago);
			} else {
				throw new IDFException("2", "El pago ya se realizo.");
			}
		} catch (IDFException idf) {
			respuesta.setCodigo(idf.getCode());
			respuesta.setMensaje(idf.getMessage());
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		} catch (Exception e) {
			respuesta.setCodigo("-3");
			respuesta.setMensaje(e.getMessage());
			String obj = Constantes.printPrettyJSONString(respuesta);
			logger.info(Constantes.MENSAJE2, "[realizarPago] ", obj);
			return ResponseEntity.status(HttpStatus.OK).body(respuesta);
		}
	}

	private boolean existeMedioPago(boolean existeMedioPago, Usuario usuario, Pago pago, Long id) throws IDFException, WSException {
		if (!usuario.getMedioPago().isEmpty()) {
			for (MedioPago medioPago : usuario.getMedioPago()) {
				if (medioPago.getId().equals(pago.getMedioPago().getId())) {
					existeMedioPago = true;
				}
			}
		}else {

			if (pago.getMedioPago() == null) {
				throw new IDFException("3", "Favor de Agregar un Medio de Pago al usuario");
			}else {
				//agregar medio de pago al usuario consultado
				usuario.getMedioPago().clear();
				usuario.getMedioPago().add(pago.getMedioPago());
				usuarioWs.actualizarUsuarioPorID(id, usuario);
				existeMedioPago = true;
			}
		}
		return existeMedioPago;
	}

	private boolean existePago(Pago pago, boolean existeMedioPago, boolean activo) throws JsonProcessingException {
		if(existeMedioPago) {
			String pag = Constantes.printPrettyJSONString(pago);
			logger.info(Constantes.MENSAJE2, "[realizarPago] ", pag);
			activo = pagoRepositorio.findById(pago.getId()).isPresent();
			logger.info(Constantes.MENSAJE2, "[realizarPago][activo] ", activo);
		}
		return activo;
	}

}