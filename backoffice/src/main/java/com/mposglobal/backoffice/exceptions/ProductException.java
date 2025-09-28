package com.mposglobal.backoffice.exceptions;

/**
 * Excepción de tiempo de ejecución específica para errores relacionados con la
 * lógica de negocio de la entidad Producto.
 * <p>
 * Esta excepción se utiliza para señalar condiciones que una aplicación
 * cliente podría desear capturar y manejar, como un producto no encontrado,
 * un intento de crear un producto con una categoría inactiva, o cualquier
 * otra violación de las reglas de negocio específicas de productos.
 * </p>
 *
 * @version 1.0
 * @see java.lang.RuntimeException
 */
public class ProductException extends RuntimeException {
    
	/**
     * El ID de versión de serialización predeterminado.
     * Se usa para verificar que los objetos serializados y deserializados
     * tengan clases compatibles.
     */
	private static final long serialVersionUID = -6386413398723655479L;

	/**
     * Construye una nueva {@code ProductException} con el mensaje de detalle
     * especificado.
     *
     * @param message el mensaje de detalle. Este mensaje se guarda para
     * su posterior recuperación por el método {@link Throwable#getMessage()}.
     */
	public ProductException(String message) {
		super(message);
	}
	
	/**
     * Construye una nueva {@code ProductException} con el mensaje de detalle
     * especificado y la causa subyacente.
     *
     * @param message el mensaje de detalle.
     * @param cause la causa (que es guardada para su posterior recuperación por el
     * método {@link Throwable#getCause()}). (Un valor {@code null} es permitido, e
     * indica que la causa es inexistente o desconocida.)
     */
    public ProductException(String message, Throwable cause) {
        super(message, cause);
    }

}
