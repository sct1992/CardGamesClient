package User;

import interfaz.IListenerPush;

import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import common.Card;
import common.InterfaceServer;
import common.Protocol;
import common.User;
import common.Workspace;

public class Game implements IGame
{

	/**
	 * comunicacion para que el servidor genere mensajes push
	 */
	private ThreadListener listener;
	
	/**
	 * usuario del juego
	 */
	private User user;
	
	/**
	 * conexion con el servidor
	 */
	private InterfaceServer server;
	
	/**
	 * interfaz q escucha los push
	 */
	private IListenerPush interfazPush;
	
	/**
	 * contructor del juego
	 * realiza la conexion con el servidor
	 */
	public Game (IListenerPush v)
	{
		interfazPush = v;
		listener =null ;
		user =null;
		connectServer();
	}
	
	/**
	 * metodo auxiliar para la conexion RMI con el servidor
	 */
	private void connectServer()
	{
		try {
			Registry reg = LocateRegistry.getRegistry("localhost",Protocol.PUERTO_SERVER_RMI);
			server = (InterfaceServer) reg.lookup(Protocol.ID_SERVER_RMI); 		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * metodo que realiza el login
	 * @param user usuario que desea logearse
	 * @param password password del usuario
	 * @return true si el usuario y contraseña coinciden, false en caso contrario
	 * @exception en caso de error
	 */
	public boolean login(String user, String password) throws Exception
	{
		
		boolean ans = server.login(user, password);
		if(ans==true)
		{	
			this.user = server.getUser(user);
			createListener();
			return true;
		}
		else
			return false;

	}

	 /**
     * metodo que se encarga de registrar a un usuario
     * @param username username que desea registrarse
     * @param name nombre del usuario
     * @param password password que desea tener
     * @param email email del usuario
     * @return true si se ha registrado, false en caso contrario
     * @throws Exception en caso de que haya algun error en el registro
     */
	public boolean signUp(String username, String name, String password,String email) throws Exception
	{
		
		boolean ans = server.signUp(username, name, password, email);
		if(ans==true)
		{	
			this.user = server.getUser(username);
			createListener();
			return true;
		}
		else
			return false;
	}

	
	
	/**
	 * dar la informacion del usuario
	 * @return
	 */
	public User getInfoUser() 
	{
		return user;
	}

	/**
	 * buscar un workspace dado un id
	 * @param id del workspace
	 * @return workspace con el id dado
	 */
	public Workspace getWorkspace(int id) 
	{
		
		return server.getWorkspace(id);
	}

	/**
	 * retorna un arraylist con los usuarios que estan activos en el juego
	 * @return arraylist de usuarios activos
	 */
	public ArrayList<User> getActiveUsers() 
	{
		
		return server.getActiveUsers(user.getUsername());
	}

	/**
	 * retorna las cartas a las que tiene acceso el usuario (predeterminadas mas las propias creadas)
	 * @return arraylist de cartas 
	 */
	public ArrayList<Card> getCards() 
	{		
		return server.getCards(user.getUsername());
	}

	/**
	 * retorna los workspaces activos del usuario
	 * @return arraylist de workspaces
	 */
	public ArrayList<Workspace> getMyWorkspaces() 
	{
	
		return server.getMyWorkspaces(user.getUsername());
	}

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
	public boolean createCard(String name, String description, String imageUrl, String place, String category) {
		
		return server.createCard(name, description, imageUrl, place, user.getUsername(), category);
	}

	/**
	 * metodo que añade una carta a la baraja
	 * @param cardId id de la carta a añadir
	 * @return true si se realizo correctamente
	 */
	public boolean addCardToDeck(int cardId) {

		ArrayList<Card> myDeck= getCards();
		
		for (int i = 0; i < myDeck.size(); i++) {
			
			int idTmp = myDeck.get(i).getId();
			
			if(idTmp==cardId)
			{
				return false;
			}
		}
		
		return server.addCardToDeck( user.getUsername(), cardId);
	}

	/**
	 * metodo que remueve una carta de la baraja
	 * @param cardId id de la carta a remover
	 * @return true si se realizo crrectamente
	 */
	public boolean removeCardFromDeck(int cardId) {
		
		return server.removeCardFromDeck( user.getUsername(), cardId);
	}

	/**
	 * Metodo que es llamado paracomenzar un juego, no importa si esta creado o no
	 *@param usernames arraylist con los usuarios a jugar
	 * @return true si se puede, false en caso de error
	 */
	public boolean startGame(ArrayList<String> usernames) throws Exception {
		
		if(activeWorkspaceWithUsernames(usernames))
		{
		throw new Exception("Ya existe un active Workspace con los usuarios elegidos");
		}
		
		if(!server.startGame(user.getUsername(),usernames))
		{
			throw new Exception("Error creando la partida, compañeros no encontrados");
		}
		return true;
	}


	private boolean activeWorkspaceWithUsernames(ArrayList<String> usernames)
	{
		
		
		ArrayList<Workspace> all = getMyWorkspaces();
		
		for (int i = 0; i < all.size(); i++) 
		{
			 Workspace tmp = all.get(i);
			 ArrayList<User> usuarios = tmp.getUsers();
		
			 ArrayList<String> usuariosS = new ArrayList<String>();
			 for (int j = 0; j < usuarios.size(); j++) 
			 {
				 usuariosS.add(usuarios.get(i).getUsername());
			 }
			 
			 //Me añado
			 usuariosS.add(user.getUsername());
			 
			if(usernames.containsAll(usuariosS) && usuariosS.containsAll(usernames))
			{
				return true;
			}
		
		}
		return false;
		
	}
	/**
	 * Metodo que es llamado para comenzar un juego a partir de una carta
	 * @param usernames arraylist con los usuarios a jugar
	 * @param cardId id de la carta a jugar
	 * Pre: el workspace entre los jugadores no ha sido nunca creado
     * @return true si se puede, false en caso de error
	 */
	public boolean startGame(ArrayList<String> usernames, int cardId) {
		
		return server.startGame( cardId, user.getUsername(),usernames);
	}

	/**
	 * metodo que se encarga de proponer una carta
	 * @param workspaceId id del workspace en el cual se propone la carta
	 * @param cardId id de la carta proponer
	 * @return true si se propone, false en caso de que la carta ya este 
	 */
	public boolean proposeCard(int workspaceId, int cardId) {
		
		Workspace actual = getWorkspace(workspaceId);
		ArrayList<Card> cards= actual.getProposedCards();
		
		for (int i = 0; i < cards.size(); i++) {
			if(cards.get(i).getId()==cardId)
			{
				return false;
			}
		}
		return server.proposeCard(workspaceId, cardId);
	}

	/**
	 * metodo que se encarga de votar por una carta
	 * @param workspaceId id del workspace en el cual se va a votar la carta
	 * @param cardId id de la carta a votar
	 * @return true si se propone, false en caso de error
	 */
	public boolean voteCard(int workspaceId, int cardId) {
		return server.voteCard(workspaceId, cardId);
	}

	/**
	 * metodo que envia un mensaje de chat al servidor
	 * @param workspaceId id del workspace del chat
	 * @param message mensaje
	 * @return true si se envio, false en caso contrario
	 */
	public boolean sendMessage(int workspaceId, String message) {
		
		return server.sendMessage(workspaceId, user.getUsername(), message);
		
	}

	/**
	 * metodo que acepta y confirma la invitacion a un juego 
	 * @param threadId id temporal al juego a aceptar
	 * @return true en caso de confirmar, false en caso de error
	 */
	public boolean acceptGame(String threadId) {
		
		return server.acceptGame(threadId, user.getUsername());
	}

	/**
	 * metodo que rechaza la invitacion del juego
	 * @param threadId id de la partida a rechazar
	 * @return true si se rechaza con exito, false en caso contrario
	 */
	public boolean rejectGame(String threadId) {
	
		return server.rejectGame(threadId, user.getUsername());
	}
	
	
	/**
	 * metodo auxiliar que crea el listener cuando alguien se registra o loguea	
	 * @return true si se crea con exito, false en caso contrario
	 */
	private boolean createListener()
	{
		try 
		{	
			listener = new ThreadListener( new Socket( "localhost", Protocol.PUERTO_SERVER_SOCKET ), user.getUsername(),this);
			listener.start();
			return true;
		} 
		catch (Exception e) 
		{
			return false;
		}
		
	}

	
	//---------------------------------------------------------------
	//-------FALTA ESTO --union de interfaz-----------------------
	//---------------------------------------------------------------
	
	public void pushedNewGameCard(String userCreator, int cardId,String idThreat) {
		// TODO Auto-generated method stub
		
	}

	public void pushedNewGame(String userCreator, String idThreat) {
	
		String message = "El usuario: " + userCreator +" te esta invitando a jugar una partida con id temporal: \n" + idThreat;
		int rta = JOptionPane.showConfirmDialog(null,message );
		if(rta == JOptionPane.YES_OPTION || rta == JOptionPane.OK_OPTION)
		{
			server.acceptGame(idThreat, user.getUsername());
		}
		else
		{
			server.rejectGame(idThreat, user.getUsername());
		}
		
	}

	public void pushedRefresh(int idWorkspace, boolean onTop) {
		interfazPush.refresh(idWorkspace, onTop);
	}

	public void pushedWorkspaceRejected(String message) {
		// TODO Auto-generated method stub
		
	}
	

}
