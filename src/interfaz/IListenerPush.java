package interfaz;

public interface IListenerPush {

	/**
	 * implementa metodo de refrescar con la informacion del id del workspaces y onTop 
	 * @param id id del workspace
	 * @param onTop variable que define si es necesario cargar el workspace en la interfaz o no (que sea visible a pesar de que estemos en otro workspace)
	 */
	public void refresh(int id, boolean onTop);

	/**
	 * metodo q notifica cuando un workspace activo se cerro por culpa de alguien
	 * @param idWorkspace id del workspace
	 * @param userThatQuits usuario que se salio
	 */
	public void pushedClosedGame(int idWorkspace, String userThatQuits);
}
