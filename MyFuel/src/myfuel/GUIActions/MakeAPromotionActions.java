package myfuel.GUIActions;

import java.util.Observable;

import myfuel.client.MyFuelClient;
import myfuel.gui.MakeaPromotionGUI;

public class MakeAPromotionActions extends GUIActions {
	MakeaPromotionGUI gui;
	public MakeAPromotionActions(MyFuelClient client) {
		super(client);
		gui = new MakeaPromotionGUI(this);
		gui.setVisible(true);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

}
