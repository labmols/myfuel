package myfuel.gui;

import java.awt.event.ActionEvent;

import myfuel.GUIActions.LowInventoryActions;

public class LowInventoryGUI extends SuperGUI{

	private LowInventoryActions actions;
	
	public LowInventoryGUI(LowInventoryActions actions)
	{
		this.actions = actions;
		this.setContentPane(contentPane);
	}
	@Override
	public void getInput(ActionEvent e) {
		
		
	}

}
