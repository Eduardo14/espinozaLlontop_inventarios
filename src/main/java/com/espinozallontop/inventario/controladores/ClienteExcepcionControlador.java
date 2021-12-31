package com.espinozallontop.inventario.controladores;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.espinozallontop.inventario.excepciones.ClienteDatosIncorrectosExcepcion;
import com.espinozallontop.inventario.excepciones.ClienteNoEncontradoExcepcion;

@ControllerAdvice
public class ClienteExcepcionControlador {

	@ResponseBody
	@ExceptionHandler(ClienteNoEncontradoExcepcion.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String clienteNoEncontradoManejador(ClienteNoEncontradoExcepcion ex) {
		return ex.getMessage();
	}

	@ResponseBody
	@ExceptionHandler(ClienteDatosIncorrectosExcepcion.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String clienteDatosIncorrectosManejador(ClienteDatosIncorrectosExcepcion ex) {
		return ex.getMessage();
	}

}
