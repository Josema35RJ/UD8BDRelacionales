package views;

import javax.swing.table.DefaultTableModel;

class JtableBloquearCeldasProductos extends DefaultTableModel {
	@Override
	public boolean isCellEditable(int row, int column) {
		if (column == 0 || column == 2) {
			return false;
		}
		return true;
	}
}
