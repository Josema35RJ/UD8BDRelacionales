package views;

import javax.swing.table.DefaultTableModel;

class JtableBloquearCeldas extends DefaultTableModel {
	   @Override
	   public boolean isCellEditable(int row, int column) {
	      if(column==2 || column==3) {
	    	  return true;
	      }
		   return false;
	   }
	}
