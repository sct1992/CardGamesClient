package User;

import java.util.ArrayList;

import common.Card;
import common.User;
import common.Workspace;

public interface IGame 
{

	/**
	 * metodo que realiza el login
	 * @param user usuario que desea logearse
	 * @param password password del usuario
	 * @return true si el usuario y contraseña coinciden, false en caso contrario
	 * @exception en caso de error
	 */
    public boolean login(String user, String password) throws Exception;
	
    
    /**
     * metodo que se encarga de registrar a un usuario
     * @param username username que desea registrarse
     * @param name nombre del usuario
     * @param password password que desea tener
     * @param email email del usuario
     * @return true si se ha registrado, false en caso contrario
     * @throws Exception en caso de que haya algun error en el registro
     */
	public boolean signUp(String username, String name, String password, String email) throws Exception;
	
	/**
	 * dar la informacion del usuario
	 * @return
	 */
	public User getInfoUser()throws Exception ;
	
	/**
	 * buscar un workspace dado un id
	 * @param id del workspace
	 * @return workspace con el id dado
	 */
	public Workspace getWorkspace(int idWorkspace)throws Exception ;
	
	/**
	 * retorna un arraylist con los usuarios que estan activos en el juego
	 * @return arraylist de usuarios activos
	 */
	public ArrayList<User> getActiveUsers()throws Exception ;
	
	/**
	 * retorna las cartas a las que tiene acceso el usuario (predeterminadas mas las propias creadas)
	 * @return arraylist de cartas 
	 */
	public ArrayList<Card> getCards()throws Exception ;
	
	/**
	 * retorna los workspaces activos del usuario
	 * @return arraylist de workspaces
	 */
	public ArrayList<Workspace> getMyWorkspaces()throws Exception ;
	
	/**
	 * metodo que crea una carta dado unos parametros
	 * @param name nombre de la carta
	 * @param description descripcion de la carta
	 * @param imageUrl url de la imagen, debe ser web
	 * @param place lugar de la carta
	 * @param category categoria de la carta
	 * @return true si fue creada con exito, false en caso contrario
	 * @throws Exception en caso de algun error en la creacion de la carta
	 */
	public boolean createCard( String name, String description, String imageUrl, String place, String category) throws Exception;
	
	/**
	 * metodo que añade una carta a la baraja
	 * @param cardId id de la carta a añadir
	 * @return true si se realizo correctamente
	 */
	public boolean addCardToDeck(int cardId)throws Exception ;
	
	/**
	 * metodo que remueve una carta de la baraja
	 * @param cardId id de la carta a remover
	 * @return true si se realizo crrectamente
	 */
	public boolean removeCardFromDeck(int cardId)throws Exception ;
	
	/**
	 * Metodo que es llamado paracomenzar un juego, no importa si esta creado o no
	 *@param usernames arraylist con los usuarios a jugar
	 * @return true si se puede, false en caso de error
	 */
	public boolean startGame(ArrayList<String> usernames) throws Exception;
	
	/**
	 * Metodo que es llamado para comenzar un juego a partir de una carta
	 * @param usernames arraylist con los usuarios a jugar
	 * @param cardId id de la carta a jugar
	 * Pre: el workspace entre los jugadores no ha sido nunca creado
     * @return true si se puede, false en caso de error
	 */
	public boolean startGame(ArrayList<String> usernames, int cardId)throws Exception ;
	
	/**
	 * metodo que se encarga de proponer una carta
	 * @param workspaceId id del workspace en el cual se propone la carta
	 * @param cardId id de la carta proponer
	 * @return true si se propone, false en caso de error
	 */
	public boolean proposeCard(int workspaceId, int cardId)throws Exception ;
	
	/**
	 * metodo que se encarga de votar por una carta
	 * @param workspaceId id del workspace en el cual se va a votar la carta
	 * @param cardId id de la carta a votar
	 * @return true si se propone, false en caso de error
	 */
	public boolean voteCard(int workspaceId, int cardId)throws Exception ;
	
	/**
	 * metodo que envia un mensaje de chat al servidor
	 * @param workspaceId id del workspace del chat
	 * @param message mensaje
	 * @return true si se envio, false en caso contrario
	 */
	public boolean sendMessage(int workspaceId, String message)throws Exception ;
	
	/**
	 * metodo que acepta y confirma la invitacion a un juego 
	 * @param threadId id temporal al juego a aceptar
	 * @return true en caso de confirmar, false en caso de error
	 */
	public boolean acceptGame(String threadId)throws Exception ;	
	
	/**
	 * metodo que rechaza la invitacion del juego
	 * @param threadId id de la partida a rechazar
	 * @return true si se rechaza con exito, false en caso contrario
	 */
	public boolean rejectGame(String threadId)throws Exception ;


	public void quit()throws Exception ;


	public ArrayList<Card> getMyCards() throws Exception;


	public void quitWorkspace(int idActiveWorkspace) throws Exception;
	
		
}
