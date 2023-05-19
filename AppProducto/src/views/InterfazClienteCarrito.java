package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Date;
import java.util.Iterator;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import models.Compra;
import models.Producto;
import services.Conexion;
import services.ObjectService;

public class InterfazClienteCarrito extends JFrame {

	private JTable table;
	private static DefaultTableModel model;
	private JButton botonVolver;
	private JButton botonPagar;
	private static ObjectService oc=new ObjectService();
	private static JTextField precioTotal;
	protected static double pt=0;
	private static Object[] Fila = new Object[3];

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
					pt=0;
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
		botonPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date currentDate = new Date(System.currentTimeMillis());
		        
		        Time time = new Time(System.currentTimeMillis());
		        
		        
				Iterator<String> it=InterfazClienteBuscar.carrito.keySet().iterator();
				while(it.hasNext()) {
					String id_producto = null;
					String key=it.next();
					double precio_Total=0;
					try {
						for (Producto p : oc.getAllProducts(Conexion.obtener())) {
							System.out.println(InterfazClienteBuscar.carrito.keySet());
							System.out.println("["+p.getNombre()+"]");
							if(key.equals(p.getNombre())) {
								id_producto=p.getId_Producto();
								precio_Total=p.getPrecio()*InterfazClienteBuscar.carrito.get(key);
								p.setCant_Stock(p.getCant_Stock()-Integer.valueOf(String.valueOf(Fila[1])));
								oc.saveProducto(Conexion.obtener(), p, InterfazLogin.User, p.getId_Proveedor(), 1);
							}
						}
					} catch (ClassNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					
					Compra c=new Compra(String.valueOf(ultimocompra()),currentDate,time,InterfazLogin.User.getId_Usuario(),
							id_producto,InterfazClienteBuscar.carrito.get(key),precio_Total);
					
					
					try {
						oc.saveCompra(Conexion.obtener(),c , 0);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				}
				JOptionPane.showMessageDialog(InterfazClienteCarrito.this, "Pedido realizado");
			}
		});
		
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
		model = new DefaultTableModel();
		
		table.setModel(model);
		table.setEnabled(false);
		
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
		precioTotal.setText(String.format("%.2f", pt).replace(",", "."));
	}
	private int ultimocompra() {
		ObjectService os = new ObjectService();
		Compra c = null;
		int idmayor = 0;
		try {
			for (Compra x : os.getAllCompra(Conexion.obtener())) {
				c = x;
				if (Integer.valueOf(c.getId_Compra()) > idmayor) {
					idmayor = Integer.valueOf(c.getId_Compra());
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idmayor+1;
	}
}
