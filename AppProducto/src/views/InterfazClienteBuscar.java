package views;



import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
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

public class InterfazClienteBuscar extends JFrame {

	private static JTable table;
	private static DefaultTableModel model;
	private static JComboBox FiltroCategoria;
	private JButton botonCarrito;
	private JButton botonVolver;
	private JButton botonAnadir;
	private static ObjectService oc=new ObjectService();
	private static List<Producto> listaP=new ArrayList<>();
	protected static HashMap<String,Integer> carrito=new HashMap<>();
	private JSpinner cantidad;
	private JFormattedTextField textField;
	private SpinnerNumberModel spinerModel=new SpinnerNumberModel ();
	private JLabel lbFoto;
	private ImageIcon FotoProducto;
	private Icon FotoProductoAjustada;

	public InterfazClienteBuscar() throws ClassNotFoundException, SQLException {
		super("Buscar producto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(573, 520);
		setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		
		FiltroCategoria = new JComboBox();
		FiltroCategoria.setModel(new DefaultComboBoxModel(new String[] {"Hogar", "Deporte", "ElectrÃ³nica", "Comida", "Libros", "Moda", "JardinerÃ­a", "Infantil", "Sanitario", "PerfumerÃ­a", "Motor"}));
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
		botonCarrito.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfazClienteCarrito icc=new InterfazClienteCarrito();
				icc.setVisible(true);
				dispose();
			}
		});
		botonCarrito.setIcon(imagenCarro);
		
		botonVolver = new JButton("Volver");
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfazCliente ic=new InterfazCliente();
				ic.setVisible(true);
				dispose();
			}
		});
		
		botonAnadir = new JButton("Insertar al carrito");
		botonAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//if(Integer.valueOf(String.valueOf(cantidad.getValue()))<=0) {
					//	JOptionPane.showMessageDialog(InterfazClienteBuscar.this, "Insertar Una Cantidad del Producto");
					//}
					{
					for (Producto p : oc.getAllProducts(Conexion.obtener())) {
						if(p.getNombre().equals(model.getValueAt(table.getSelectedRow(), 0).toString())) {
							int cant=Integer.valueOf(String.valueOf(cantidad.getValue()));
							if(Integer.valueOf(String.valueOf(cantidad.getValue()))>0) {
								if(!carrito.containsKey(p.getNombre()))
									carrito.put(p.getNombre(), cant);
								else {
									carrito.put(p.getNombre(),carrito.get(p.getNombre())+cant);
									if(carrito.get(p.getNombre())+cant==p.getCant_Stock()) {
										carrito.put(p.getNombre(),p.getCant_Stock());
										JOptionPane.showMessageDialog(InterfazClienteBuscar.this, "Se ha aÃ±adido todo el stock disponible");
									}else if (carrito.get(p.getNombre())+cant>p.getCant_Stock()){
										if(JOptionPane.showConfirmDialog(InterfazClienteBuscar.this, "Cantidad en Stock Excedida, quiere comprar el Stock Restante?")==0)
										carrito.put(p.getNombre(),p.getCant_Stock());
									}
								}
							}
						}
					}
					}
				} catch (ArrayIndexOutOfBoundsException ex) {
					JOptionPane.showMessageDialog(InterfazClienteBuscar.this, "Selecciona Algun Producto");
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
		
		lbFoto = new JLabel("");
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(149)
					.addComponent(FiltroCategoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(cantidad, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
					.addComponent(botonCarrito)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(61)
					.addComponent(botonVolver, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 171, Short.MAX_VALUE)
					.addComponent(botonAnadir)
					.addGap(49))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(167)
					.addComponent(lbFoto, GroupLayout.PREFERRED_SIZE, 221, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(169, Short.MAX_VALUE))
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
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbFoto, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
					.addGap(11)
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
					for(Producto p : oc.getAllProducts(Conexion.obtener())) {
						if(p.getNombre().equals(table.getValueAt(table.getSelectedRow(), 0))) {
							FotoProducto = new ImageIcon (p.getImagen());
							FotoProductoAjustada = new ImageIcon (FotoProducto.getImage().getScaledInstance(lbFoto.getWidth(), 
									lbFoto.getHeight(), Image.SCALE_DEFAULT));
							lbFoto.setIcon(FotoProductoAjustada);
						}
					}
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
				} catch (ArrayIndexOutOfBoundsException ex) {
					JOptionPane.showMessageDialog(InterfazClienteBuscar.this, "Selecciona Algun Producto Para Poder Ponerle Cantidad");
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
				if(carrito.containsKey(p.getNombre())) {
					spinerModel.setMinimum(0);
					spinerModel.setMaximum(Integer.valueOf(String.valueOf(p.getCant_Stock()))-carrito.get(p.getNombre()));
				}else {
					spinerModel.setMinimum(0);
					spinerModel.setMaximum(Integer.valueOf(String.valueOf(p.getCant_Stock())));
				}
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
