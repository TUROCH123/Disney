package com.app.apilogin.integration.imp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import org.springframework.remoting.jaxws.JaxWsSoapFaultException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.app.apilogin.excepciones.WSException;
import com.app.apilogin.integration.UsuarioWs;
import com.app.apilogin.modelo.Usuario;
import com.app.apilogin.util.Constantes;

@Component
public class UsuarioWsImp  implements UsuarioWs {
	  
	@Autowired
	RestTemplate restTemplateUsuario;
	  
	@Override
	public Usuario obtenerUsuarioPorId(Integer id) throws WSException {
		Usuario response = new Usuario();
		String url = "http://localhost:8080/api/v1/login/obtenerUsuarioPorID/"+id;
		String nombreComponente = "login";
		String nombreMetodo = "obtenerUsuarioPorID";
		try {
			response = restTemplateUsuario.getForObject(url, Usuario.class);
	    } catch (JaxWsSoapFaultException e) {
	        throw new WSException("-3", String.format("Error inesperado. Error: %s - %s", nombreComponente, nombreMetodo), e);
	    } catch (Exception e) {
	        capturarErrorWs(e, nombreComponente, nombreMetodo, "-1", "-2", "Error de timeout en %s - %s",
	            "Error de disponibilidad en %s - %s");
	      } finally {

	      }
		return response;
	}
	public static void capturarErrorWs(Exception e, String nombreComponente, String nombreMetodo, String idt1Codigo,
		      String idt2Codigo, String idt1Mensaje, String idt2Mensaje) throws WSException {
		    String error = (e + Constantes.TEXTO_VACIO);
		    if (error.contains(Constantes.TIMEOUT)) {
		      throw new WSException(idt1Codigo, String.format(idt1Mensaje, nombreComponente, nombreMetodo), e);
		    } else {
		      throw new WSException(idt2Codigo, String.format(idt2Mensaje, nombreComponente, nombreMetodo), e);
		    }
	}
	@Override
	public ResponseEntity<?> actualizarUsuarioPorID(Integer id, Usuario usuario) throws WSException {
		ResponseEntity<Usuario> response = null;
		String url = "http://localhost:8080/api/v1/login/actualizarUsuarioPorID/";
		String nombreComponente = "login";
		String nombreMetodo = "actualizarUsuarioPorID";
		try {
			HttpEntity<Usuario> req = new HttpEntity<>(usuario);
			Map<String,String> uriVariables = new HashMap<>();
			uriVariables.put("id", String.valueOf(id));
			response = restTemplateUsuario.exchange(url, HttpMethod.PUT, req, Usuario.class, uriVariables);

	    } catch (JaxWsSoapFaultException e) {
	        throw new WSException("-3", String.format("Error inesperado. Error: %s - %s", nombreComponente, nombreMetodo), e);
	    } catch (Exception e) {
	        capturarErrorWs(e, nombreComponente, nombreMetodo, "-1", "-2", "Error de timeout en %s - %s",
	            "Error de disponibilidad en %s - %s");
	    } finally {

	    }
		return response;
	}
	@Override
	public Usuario validarDatos(String email,String pass) throws WSException {
		Usuario response = null;
		String url = "http://localhost:8080/api/v1/login/validarDatos/?email="+email+"&pass="+pass+"";
		String nombreComponente = "login";
		String nombreMetodo = "validarDatos";
		try {

			response = restTemplateUsuario.getForObject(url, Usuario.class);
			
	    } catch (JaxWsSoapFaultException e) {
	        throw new WSException("-3", String.format("Error inesperado. Error: %s - %s", nombreComponente, nombreMetodo), e);
	    } catch (Exception e) {
	        capturarErrorWs(e, nombreComponente, nombreMetodo, "-1", "-2", "Error de timeout en %s - %s",
	            "Error de disponibilidad en %s - %s");
	    } finally {

	    }
		return response;
	}
}