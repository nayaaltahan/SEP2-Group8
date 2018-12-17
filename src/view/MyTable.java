package view;

import java.util.Vector;

import javax.swing.JTable;

class MyTable extends JTable {
	public MyTable(Vector data, Vector title) {
		super(data, title);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
