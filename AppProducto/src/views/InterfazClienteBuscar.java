package views;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

import models.Producto;
import services.Conexion;
import services.ObjectService;
import javax.swing.JSpinner;

public class InterfazClienteBuscar extends JFrame {

	private JTable table;
	private static DefaultTableModel model;
	private static JComboBox FiltroCategoria;
	private JButton botonCarrito;
	private JButton botonVolver;
	private JButton botonAnadir;
	private static ObjectService oc=new ObjectService();
	private static List<Producto> listaP=new ArrayList<>();
	protected static List<Producto> carrito=new ArrayList<>();
	private JSpinner cantidad;
	private SpinnerNumberModel spinerModel=new SpinnerNumberModel ();

	public InterfazClienteBuscar() throws ClassNotFoundException, SQLException {
		super("Buscar producto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(420, 220);
		setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		
		FiltroCategoria = new JComboBox();
		FiltroCategoria.setModel(new DefaultComboBoxModel(new String[] {"Hogar", "Deporte", "Electrónica", "Comida", "Libros", "Moda", "Jardinería", "Infantil", "Sanitario", "Perfumería", "Motor"}));
		FiltroCategoria.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					for(int i=0;i<model.getRowCount()+1;i++) {
						model.removeRow(0);
					}
				
					EscribirTabla();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		ImageIcon imagenCarro=new ImageIcon("Icon/CarritoCliente.png");
		botonCarrito = new JButton();
		botonCarrito.setIcon(imagenCarro);
		
		botonVolver = new JButton("Volver Atras");
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfazCliente ic=new InterfazCliente();
				ic.setVisible(true);
				dispose();
			}
		});
		
		botonAnadir = new JButton("Añadir al carrito");
		botonAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					for (Producto p : oc.getAllProducts(Conexion.obtener())) {
						if(p.getId_Producto().equals(model.getValueAt(table.getSelectedRow(), 0).toString())) {
							
							carrito.add(p);
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
		});
		
		cantidad = new JSpinner();
		cantidad.setModel(spinerModel);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(149)
							.addComponent(FiltroCategoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(cantidad, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
							.addComponent(botonCarrito)))
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(43)
					.addComponent(botonVolver, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
					.addComponent(botonAnadir)
					.addGap(43))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(8)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(FiltroCategoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(botonCarrito)
						.addComponent(cantidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(botonAnadir, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(botonVolver, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(14, Short.MAX_VALUE))
		);


		table = new JTable();
		model = new DefaultTableModel();
		table.setModel(model);

		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
		
		model.addColumn("Nombre");
		model.addColumn("Imagen");
		model.addColumn("Descripcion");
		model.addColumn("Precio");

		EscribirTabla();

		for (int c = 0; c < table.getColumnCount(); c++) { 
            Class<?> col_class = table.getColumnClass(c); 
            table.setDefaultEditor(col_class, null);
		}
		
		setVisible(true);
	}
	private void setSpinner() throws ClassNotFoundException, SQLException {
		for (Producto p : oc.getAllProducts(Conexion.obtener())) {
			if(p.getId_Producto().equals(model.getValueAt(table.getSelectedRow(), 0).toString())) {
				spinerModel.setMinimum(0);
				spinerModel.setMaximum(p.getCant_Stock());
			}
		}
	}
	
	private static void EscribirTabla() throws ClassNotFoundException, SQLException {
		listaP.clear();
		for (Producto a : oc.getAllProducts(Conexion.obtener())) {
			if(FiltroCategoria.getSelectedItem().toString().equals(a.getCategoria())) {
				Object[] Fila = new Object[4];
				Fila[0] = a.getNombre();
				Fila[1] = a.getImagen();
				Fila[2] = a.getDescripcion();
				Fila[3] = a.getPrecio();
				model.addRow(Fila);
				listaP=oc.getAllProducts(Conexion.obtener());
			}
		}
	}
}
