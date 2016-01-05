package myfuel.gui;

import javax.swing.table.DefaultTableModel;

/**
 * Custom Table Model , used for every table created.
 * @author Maor
 *
 */
public class MyTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 1L;
	/**
	 * Number of Columns.
	 */
	private int columns;
	/**
	 * Index of CheckBox column (if exist, if not -1).
	 */
	private int checkBoxCol ;
	
/**
 * Create new Table Model with specific values.
 * @param columns - Number of columns in table.
 * @param checkBoxCol- Index of CheckBox column if exist(if not exist -1).
 */
	public MyTableModel(int columns,int checkBoxCol)
	{
		this.columns = columns;
		this.checkBoxCol = checkBoxCol;
	}
	
/**
 * Get the column Class from super func , if the column it's checkBox column return Boolean.class.
 */
	@Override
	public Class getColumnClass(int column)
	{
		if(column == checkBoxCol)return Boolean.class;
		else return super.getColumnClass(column);
	}

/**
 * Make all cells not editable.
 */
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
