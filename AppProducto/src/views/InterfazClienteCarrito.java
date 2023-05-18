package views;

import java.util.Iterator;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import models.Producto;
import services.Conexion;
import services.ObjectService;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class InterfazClienteCarrito extends JFrame {

	private JTable table;
	private static JtableBloquearCeldasCarrito model;
	private JButton botonVolver;
	private JButton botonPagar;
	private static ObjectService oc=new ObjectService();
	private static JTextField precioTotal;
	private static double pt=0;

	public InterfazClienteCarrito() {
		super("Carrito");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(420, 242);
		setLocationRelativeTo(null);

		JScrollPane scrollPane = new JScrollPane();
		
		botonVolver = new JButton("Volver");
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					InterfazClienteBuscar icb=new InterfazClienteBuscar();
					icb.setVisible(true);
					dispose();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		botonPagar = new JButton("Realizar Pago");
		
		precioTotal = new JTextField();
		precioTotal.setEditable(false);
		precioTotal.setColumns(10);
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(34)
					.addComponent(botonVolver, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addComponent(precioTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
					.addComponent(botonPagar)
					.addGap(20))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(botonPagar, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(botonVolver, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(precioTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);


		table = new JTable();
		model = new JtableBloquearCeldasCarrito();
		table.setModel(model);
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					for (Producto p : oc.getAllProducts(Conexion.obtener())) {
						if(String.valueOf(model.getValueAt(table.getSelectedRow(), 0)).equals(p.getNombre())){
							System.out.println(Integer.valueOf(String.valueOf(model.getValueAt(table.getSelectedRow(), table.getSelectedColumn()))));
							System.out.println(p.getCant_Stock());
							if(Integer.valueOf(String.valueOf(model.getValueAt(table.getSelectedRow(), table.getSelectedColumn())))>p.getCant_Stock()){
								JOptionPane.showMessageDialog(InterfazClienteCarrito.this, "Cantidad en stock superada");
								//table.setEditingRow(table.getSelectedRow());
							}
						}
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
		});
		
		
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);

		model.addColumn("Producto");
		model.addColumn("Cantidad");
		model.addColumn("Precio");

		EscribirTabla();
		setVisible(true);
	}

	private static void EscribirTabla() {
		Iterator<String> it=InterfazClienteBuscar.carrito.keySet().iterator();
		Object[] Fila = new Object[3];
		while(it.hasNext()) {
			String key=it.next();
			
			try {
				for (Producto p : oc.getAllProducts(Conexion.obtener())) {
					if(p.getNombre().equals(key)) {
						Fila[0] = key;
						Fila[1] = InterfazClienteBuscar.carrito.get(key);
						Fila[2] = p.getPrecio()*InterfazClienteBuscar.carrito.get(key);
						pt+=p.getPrecio()*InterfazClienteBuscar.carrito.get(key);
						
						model.addRow(Fila);
					}
					
				}
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		precioTotal.setText(String.valueOf(pt));
	}
}
