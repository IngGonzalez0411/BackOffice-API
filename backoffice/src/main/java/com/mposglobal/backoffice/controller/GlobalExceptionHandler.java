package com.mposglobal.backoffice.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mposglobal.backoffice.util.Constant;

/**
 * Manejador de excepciones global para controladores REST.
 * <p>
 * Utiliza la anotación {@code @RestControllerAdvice} para centralizar el manejo de excepciones
 * lanzadas por cualquier controlador en la aplicación, asegurando una respuesta consistente
 * en caso de fallos.
 * </p>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	/**
     * Maneja todas las excepciones no capturadas de manera específica (tanto checked como unchecked).
     * <p>
     * Este es el manejador de "último recurso". Captura la clase base {@code Exception.class}
     * para asegurar que cualquier error inesperado devuelva un cuerpo JSON con el mensaje de error.
     * </p>
     *
     * @param ex La excepción lanzada durante el procesamiento de la solicitud.
     * @return Una respuesta HTTP y un mapa JSON con el mensaje de error.
     */
	@ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleAllExceptions(Exception ex) {
        
        return ResponseEntity.badRequest().body(Map.of(Constant.ERROR, Constant.ERROR_CUSTOM + ex.getMessage()));
    }
}
