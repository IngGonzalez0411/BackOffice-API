package com.mposglobal.backoffice.exceptions;

/**
 * Excepción personalizada utilizada para indicar fallos o errores durante
 * el proceso de autenticación o inicio de sesión (Login).
 * Esta clase extiende {@code RuntimeException}, lo que significa que
 * es una excepción no comprobada (unchecked) y no necesita ser declarada
 * en la cláusula {@code throws} de los métodos.
 *
 * @version 1.0
 * @see java.lang.RuntimeException
 */
public class LoginException extends RuntimeException {
    
	/**
     * El ID de versión de serialización predeterminado.
     */
	private static final long serialVersionUID = -6386413398723655479L;

	/**
	 * Construye una nueva {@code LoginException} con el mensaje de detalle
	 * especificado.
	 *
	 * @param message el mensaje de detalle. Este mensaje se guarda para
	 * su posterior recuperación por el método {@link Throwable#getMessage()}.
	 */
	public LoginException(String message) {
		super(message);
	}

}
