package interfaz;

public interface IListenerPush {

	
	public void refresh(int id, boolean onTop);

	public void pushedClosedGame(int idWorkspace, String userThatQuits);
}
