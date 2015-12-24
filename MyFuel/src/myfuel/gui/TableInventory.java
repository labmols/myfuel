package myfuel.gui;

import javax.swing.table.DefaultTableModel;

public class TableInventory extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	
	@Override
	public Class getColumnClass(int column)
	{
		switch(column)
		{
		case 0:
			return Integer.class;
		case 1:
			return String.class;
		case 2:
			return Float.class;
		default:
			return Boolean.class;
		}
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
	       //all cells false
			if(column <3)
	       return false;
			return true;
	    }

}
