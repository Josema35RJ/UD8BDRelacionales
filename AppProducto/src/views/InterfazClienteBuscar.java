package views;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import models.Producto;
import services.Conexion;
import services.ObjectService;
import javax.swing.JSpinner;

public class InterfazClienteBuscar extends JFrame {

	private static JTable table;
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
		setSize(527, 467);
		setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		
		FiltroCategoria = new JComboBox();
		FiltroCategoria.setModel(new DefaultComboBoxModel(new String[] {"Hogar", "Deporte", "Electrónica", "Comida", "Libros", "Moda", "Jardinería", "Infantil", "Sanitario", "Perfumería", "Motor"}));
		FiltroCategoria.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					for(int i=0;i<model.getRowCount()+1;i++) {
						if(table.getRowCount()>0)
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
		
		botonVolver = new JButton("Volver");
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
						if(p.getNombre().equals(model.getValueAt(table.getSelectedRow(), 0).toString())) {
							System.out.println(carrito);
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
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(149)
					.addComponent(FiltroCategoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(cantidad, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 129, Short.MAX_VALUE)
					.addComponent(botonCarrito)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(61)
					.addComponent(botonVolver, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
					.addComponent(botonAnadir)
					.addGap(81))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(8)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(botonCarrito)
							.addGap(26))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(FiltroCategoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(cantidad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(botonVolver, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(botonAnadir, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);

		
		table = new JTable();
		model = new DefaultTableModel();
		
		table.setModel(model);
		
		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);
		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		model.addColumn("Nombre");
		model.addColumn("Descripcion");
		model.addColumn("Precio");

		EscribirTabla();
		
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					setSpinner();
					cantidad.setModel(spinerModel);
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
		cantidad.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				try {
					for (Producto p : oc.getAllProducts(Conexion.obtener())) {
						if(p.getNombre().equals(model.getValueAt(table.getSelectedRow(), 0).toString())) {
							if(Integer.valueOf(String.valueOf(cantidad.getValue()))>p.getCant_Stock())
								cantidad.setValue(4);
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
		
		for (int c = 0; c < table.getColumnCount(); c++) { 
            Class<?> col_class = table.getColumnClass(c); 
            table.setDefaultEditor(col_class, null);
		}
		
		setVisible(true);
	}
	private void setSpinner() throws ClassNotFoundException, SQLException {
		for (Producto p : oc.getAllProducts(Conexion.obtener())) {
			if(p.getNombre().equals(model.getValueAt(table.getSelectedRow(), 0).toString())) {
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
				Fila[1] = a.getDescripcion();
				Fila[2] = a.getPrecio();
				model.addRow(Fila);
				listaP=oc.getAllProducts(Conexion.obtener());
			}
		}
		table.getColumnModel().getColumn(0).setMaxWidth(165);
		table.getColumnModel().getColumn(2).setMaxWidth(45);
	}
}
