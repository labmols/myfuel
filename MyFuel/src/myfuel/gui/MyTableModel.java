package myfuel.gui;

import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel {

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
			return String.class;
		case 3:
			return String.class;
		default:
			return Boolean.class;
		}
	}
}
