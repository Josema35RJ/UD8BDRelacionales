package views;

import javax.swing.table.DefaultTableModel;

class JtableBloquearCeldasCarrito extends DefaultTableModel {
	   @Override
	   public boolean isCellEditable(int row, int column) {
	      if(column==1) {
	    	  return true;
	      }
		   return false;
	   }
	}
