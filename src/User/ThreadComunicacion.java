/**
 * $Id: ThreadComunicacion.java 2090 2010-11-02 16:50:02Z cm.rodriguez155 $
 * Universidad de los Andes (Bogotá - Colombia)
 * Departamento de Ingeniería de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License version 2.1 
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_cupiEmail
 * Autor: Camilo Alvarez Duran - 12-ene-2010
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package User;

import uniandes.cupi2.cupiEmail.comun.mundo.ProtocoloComunicacion;

/**
 * Representa un thread para enviar mensaje de forma asíncrona
 */
public class ThreadComunicacion extends Thread {
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Es el manejador de comunicaciones
	 */
	private ManejadorComunicacionesCliente manejadorComunicaciones;

	/**
	 * Es el mensaje que se va a enviar
	 */
	private Mensaje mensaje;

	/**
	 * Es el cliente de cupiemail
	 */
	private ClienteCupiEmail cliente;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Construye un nuevo thread para enviar el mensaje
	 * @param nManejadorComunicaciones El manejador de comunicaciones. nManejadorComunicaciones != null
	 * @param nMensaje El mensaje que se va a enviar. nMensaje != null
	 * @param nCliente El cliente de cupiemail. nCliente != null
	 */
	public ThreadComunicacion( ManejadorComunicacionesCliente nManejadorComunicaciones, Mensaje nMensaje, ClienteCupiEmail nCliente) {
		this.manejadorComunicaciones = nManejadorComunicaciones;
		this.mensaje = nMensaje;
		cliente = nCliente;
	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Método que se invoca cuando inicia la ejecución del thread
	 */
	public void run() {
	    
	    //TODO envía el mensaje y espera respuesta, utilizando el manejador de comunicaciones
	    try{
	    	
	    
		Mensaje a = manejadorComunicaciones.enviarMensajeEsperar(mensaje);
		cliente.nuevoMensaje(a);

	    }
	    catch(Exception e){
	    	try {
				cliente.nuevoMensaje(new Mensaje(ProtocoloComunicacion.ERROR));
			} catch (ErrorProtocoloException e1) {
					e1.printStackTrace();
			}
	    }
	    //TODO Llama al método nuevoMensaje del cliente, enviando por parámetro el mensaje que recibió como respuesta
	    // por parte del servidor
	    
	}

}
