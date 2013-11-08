package User;

import java.util.ArrayList;

import common.Card;
import common.User;
import common.Workspace;

public interface IGame 
{

    public boolean login(String user, String password);
	
	public boolean signUp(String username, String name, String password, String email);
	
	public User getInfoUser();
	
	public Workspace getWorkspace(int id);
	
	public ArrayList<User> getActiveUsers();
	
	public ArrayList<Card> getCards();
	
	public ArrayList<Workspace> getMyWorkspaces();
	
	public boolean createCard( String name, String description, String imageUrl, String place, String owner, String category);
	
	public boolean addCardToDeck(int cardId);
	
	public boolean removeCardFromDeck(int cardId);
	
	public Workspace startGame(ArrayList<String> usernames);
	
	public Workspace startGame(ArrayList<String> usernames, int cardId);
	
	public boolean proposeCard(int workspaceId, int cardId);
	
	public boolean voteCard(int workspaceId, int cardId);
	
	public boolean sendMessage(int workspaceId, String message);
	
	public boolean acceptGame(String threadId);	
	
	public boolean rejectGame(String threadId);
	
}
