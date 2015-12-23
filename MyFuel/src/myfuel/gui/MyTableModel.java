package myfuel.gui;

import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	private int columns;
	private int checkBoxCol ;
	
	public MyTableModel(int columns,int checkBoxCol)
	{
		this.columns = columns;
		this.checkBoxCol = checkBoxCol;
	}
	
	@Override
	public Class getColumnClass(int column)
	{
		if(column == checkBoxCol)return Boolean.class;
		else return super.getColumnClass(column);
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
	       //all cells false
			if(column < columns)
	       return false;
			return true;
	    }

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}
}
