package User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import common.Protocol;
import common.User;


/**
 * Representa un thread para recibir mensajes push del servidor
 */
public class ThreadListener extends Thread {
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * conexion con la clase principal para alertar de los mensajes del servidor
	 */
	private Game game;
	
	 /**
     * El canal usado para comunicarse con el jugador 1
     */
    private Socket socketJugador;

    /**
     * El flujo de escritura conectado con el jugador 1
     */
    private PrintWriter out;

    /**
     * El flujo de lectura conectado con el jugador 1
     */
    private BufferedReader in;
    

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------


    /**
	 * Construye un nuevo thread para recibir notificaciones
     * @param sSocket canal de comunicacion con el server
     * @param username el username del thread, se usa para notificar al servidor el canal de quien es
     */
	public ThreadListener( Socket sSocket, String username, Game game)
    {
    	socketJugador = sSocket;
    	this.game = game;
		try {
		    
			in = new BufferedReader(new InputStreamReader(sSocket.getInputStream()));
			out = new PrintWriter(sSocket.getOutputStream(), true);
			out.println(username);

		} catch (Exception e) {
			e.printStackTrace();
		}
			
	
	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Método que se invoca cuando inicia la ejecución del thread
	 * se encarga de escuhar al servidor y luego procesa el mensaje
	 */
	   public void run( )
	    {
	        try
	        {
	            while( true )
	            {
	            	String command = in.readLine();
	            	processCommand(command);
	            }
	        }
	        catch( Exception e )
	        {
	            e.printStackTrace( );
	        }
	    }
	   
	   /**
	    * metodo auxiliar para procesar las notificaciones del servidor
	    * @param command comando del servidor
	    */
	   private void processCommand(String command)
	   {
		
		   if (command.startsWith(Protocol.NEW_GAME_CARD))
		   {
			   String[] tmp = command.split(Protocol.SEPARATOR1)[1].split(Protocol.SEPARATOR2);
			   String userCreator= tmp[0];
			   String cardIdString=tmp[1];
			   int cardId = Integer.parseInt(cardIdString);
			   String idThreat = tmp[2];
			   try
			   {
			   game.pushedNewGameCard(userCreator, cardId , idThreat);
			   }
			   catch (Exception e) {
	
			}
		   }
		   else if(command.startsWith(Protocol.NEW_GAME))
		   {
			   String[] tmp = command.split(Protocol.SEPARATOR1)[1].split(Protocol.SEPARATOR2);
			   String userCreator= tmp[0];
			   String idThreat = tmp[1];
			   try
			   {
			   game.pushedNewGame(userCreator , idThreat);
			   }
			   catch (Exception e) {
			
			}
		   }
		   else if (command.startsWith(Protocol.REFRESH))
		   {
			   String[] tmp = command.split(Protocol.SEPARATOR1)[1].split(Protocol.SEPARATOR2);
			   int idWorkspace= Integer.parseInt(tmp[0]);
			   boolean onTop = Boolean.parseBoolean(tmp[1]);
			   
			   game.pushedRefresh(idWorkspace, onTop);
		   }
		   else if (command.startsWith(Protocol.CLOSED_GAME))
		   {
			   String[] tmp = command.split(Protocol.SEPARATOR1)[1].split(Protocol.SEPARATOR2);
			   int idWorkspace= Integer.parseInt(tmp[0]);
			   String userThatQuits = tmp[1];
			   
			   game.pushedClosedGame(idWorkspace, userThatQuits);
		   }
		   else if (command.startsWith(Protocol.WORKSPACE_REJECTED))
		   {
			   
			   String message = command.split(Protocol.SEPARATOR1)[1];
			   game.pushedWorkspaceRejected(message);
		   }
		   
		   
	   }

}
