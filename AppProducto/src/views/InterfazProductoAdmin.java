package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

public class InterfazProductoAdmin extends JFrame{
	
	private JButton Create, Update, Read, Delete, Atras;
	private JTable TablaProductos;
	
	public InterfazProductoAdmin () {
		super("Menu Productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(815,392);
		
		TablaProductos = new JTable ();
		TablaProductos.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Id_Producto", "Nombre", "Imagen", "Descripcion", "Precio", "Cant_Stock", "Id_Usuario", "Id_Proveedor"
			}
		));
		Create = new JButton ("Create");
		Update = new JButton ("Update");
		Read = new JButton ("Read");
		Delete = new JButton ("Delete");
		Atras = new JButton ("Atras");
		getContentPane().add(Atras);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(189)
					.addComponent(Create)
					.addGap(29)
					.addComponent(Delete)
					.addGap(26)
					.addComponent(Read)
					.addGap(29)
					.addComponent(Update)
					.addGap(18)
					.addComponent(Atras, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(185, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(29, Short.MAX_VALUE)
					.addComponent(TablaProductos, GroupLayout.PREFERRED_SIZE, 751, GroupLayout.PREFERRED_SIZE)
					.addGap(19))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(TablaProductos, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(Create)
						.addComponent(Delete)
						.addComponent(Update)
						.addComponent(Atras)
						.addComponent(Read))
					.addGap(19))
		);
		getContentPane().setLayout(groupLayout);
		
		Atras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				InterfazAdmin ia = new InterfazAdmin();
			}
		});
	}

}
