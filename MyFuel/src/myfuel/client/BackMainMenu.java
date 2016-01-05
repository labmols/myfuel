package myfuel.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import myfuel.GUIActions.GUIActions;
/***
 * This class has a method that will activate the backToMenu from specific GUIActions object
 *
 */
public class BackMainMenu implements ActionListener {

	/***
	 * specific GUI controller 
	 */
	private GUIActions actions;
	/***
	 * BackMainMenu constructor
	 * @param actions - specific GUI controller
	 */
	public BackMainMenu(GUIActions actions)
	{
		this.setActions(actions);
	}
	/***
	 * Action Listener method. will activate the backToMenu method from the specific GUIActions object
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		actions.backToMenu();
		
	}
	public GUIActions getActions() {
		return actions;
	}
	public void setActions(GUIActions actions) {
		this.actions = actions;
	}

}
