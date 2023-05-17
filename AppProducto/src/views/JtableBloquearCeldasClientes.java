package views;

import javax.swing.table.DefaultTableModel;

class JtableBloquearCeldasClientes extends DefaultTableModel {
	@Override
	public boolean isCellEditable(int row, int column) {
		if (column == 0) {
			return false;
		}
		return true;
	}
}
