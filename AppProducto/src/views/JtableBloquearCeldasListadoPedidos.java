package views;

import javax.swing.table.DefaultTableModel;

class JtableBloquearCeldasListadoPedidosAdmin extends DefaultTableModel {
	@Override
	public boolean isCellEditable(int row, int column) {
		if (column < 9) {
			return false;
		}
		return true;
	}
}
