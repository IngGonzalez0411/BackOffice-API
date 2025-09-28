package com.mposglobal.backoffice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * Clase principal de la aplicación Spring Boot para el Backoffice.
 * <p>
 * Esta clase contiene el método {@code main} que sirve como punto de entrada
 * para ejecutar la aplicación, inicializando el contenedor de Spring
 * y cargando la configuración necesaria.
 * </p>
 */
@OpenAPIDefinition(
	    info = @Info(
	        title = "API de Backoffice de Usuarios", // <-- ¡Podría tener un valor por defecto que sobrescribe!
	        version = "v1.0.0",
	        description = "Documentacion de la API para gestion de usuarios y productos con sus catergorias."
	    )
	)
@SpringBootApplication
public class BackofficeApplication {

	/**
     * Punto de entrada principal de la aplicación.
     * <p>
     * Llama a {@link SpringApplication#run(Class, String...)} para iniciar
     * la aplicación Spring Boot.
     * </p>
     *
     * @param args Argumentos de la línea de comandos pasados al inicio de la aplicación.
     */
	public static void main(String[] args) {
		SpringApplication.run(BackofficeApplication.class, args);
	}

}
