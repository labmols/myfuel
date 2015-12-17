package myfuel.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import myfuel.GUIActions.GUIActions;

public class BackMainMenu implements ActionListener {

	private GUIActions actions;
	
	public BackMainMenu(GUIActions actions)
	{
		this.setActions(actions);
	}
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
