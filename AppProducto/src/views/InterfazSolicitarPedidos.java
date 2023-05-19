package views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import models.Compra;
import models.Producto;
import services.Conexion;
import services.ObjectService;

public class InterfazSolicitarPedidos extends JFrame {

	private JPanel contentPane;
	private static ObjectService os = new ObjectService();
	private JTable table;
	private JButton Atras;
	private static JtableBloquearCeldas model;
	private static InterfazLogin il;
	private ImageIcon imagenRegistrar, AtrasProveedores;
	private Date utilDate = new Date();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazSolicitarPedidos frame = new InterfazSolicitarPedidos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterfazSolicitarPedidos() {
		setTitle("Solicitar pedidos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 791, 206);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JScrollPane scrollPane = new JScrollPane();
		table = new JTable();
		model = new JtableBloquearCeldas();
		table.setModel(model);

		scrollPane.setViewportView(table);

		model.addColumn("Fecha");
		model.addColumn("Hora");
		model.addColumn("Id_Producto");
		model.addColumn("Cantidad_pedida");
		model.addColumn("Precio_Total");
		
		Atras = new JButton("Atras");
		AtrasProveedores = new ImageIcon("Icon/Volver2.png");
		Atras.setIcon(AtrasProveedores);

		try {
			EscribirTabla();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Atras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		getContentPane().add(Atras);

		JButton btnRegistrar = new JButton("Solicitar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				if (model.getValueAt(0, 2).equals("") || model.getValueAt(0, 3).equals("")) {
					JOptionPane.showMessageDialog(InterfazSolicitarPedidos.this, "NO SE PUEDE INTRODUCIR VALORES NULOS",
							"ERROR", JOptionPane.ERROR_MESSAGE);
				} else {
					int id = Integer.valueOf(ultimocompra()) + 1;
					String stringid = String.valueOf(id);
					long milliseconds = utilDate.getTime();
					java.sql.Date sqlDate = new java.sql.Date(milliseconds);
					long millisecondsT = utilDate.getTime();
					Time sqlDateT = new java.sql.Time(millisecondsT);
					int cantidad_stock = Integer.valueOf(String.valueOf(model.getValueAt(0, 3)));
					double p_total=Double.parseDouble(String.valueOf(model.getValueAt(0, 4)).replace(",", "."));
					Compra c = new Compra(stringid, sqlDate, sqlDateT, il.User.getId_Usuario(),
							model.getValueAt(0, 2).toString(), cantidad_stock,
							p_total);
					boolean encontrado = false;
					try {
						for (Producto x : os.getAllProducts(Conexion.obtener())) {
							if (x.getId_Producto().equals(c.getId_Producto())) {
								x.setCant_Stock(x.getCant_Stock() + c.getCantidad_pedida());
								os.saveProducto(Conexion.obtener(), x, il.User, x.getId_Proveedor(), 1);
								os.saveCompra(Conexion.obtener(), c, 0);
								JOptionPane.showMessageDialog(InterfazSolicitarPedidos.this,
										"PRODUCTO ACTUALIZADO Y COMPRA ACTUALIZADA");
								encontrado = true;
							}
						}
						if (encontrado == false) {
							JOptionPane.showMessageDialog(InterfazSolicitarPedidos.this,
									"PRODUCTO NO ENCONTRADO EN LA BASE DE DATOS");
						}
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(InterfazSolicitarPedidos.this, "Introduce Valores de Id y Cantidad");
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(InterfazSolicitarPedidos.this, "Seleccione Pedido e Introduzca Valores de Id y Cantidad");
				}
			}
		});
		imagenRegistrar = new ImageIcon("Icon/Registrar.png");
		btnRegistrar.setIcon(imagenRegistrar);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 731, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(19, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(300, Short.MAX_VALUE)
					.addComponent(btnRegistrar, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
					.addGap(176)
					.addComponent(Atras, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addGap(39))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRegistrar, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
						.addComponent(Atras, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);

		table.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				try {
					EscribirTabla();
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(InterfazSolicitarPedidos.this, "Introduce Valores de Id y Cantidad");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
	}

	private static void EscribirTabla() throws ClassNotFoundException, SQLException {
		DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
		Calendar fecha2 = new GregorianCalendar();
		String date = fecha.format(fecha.getCalendar().getTime());
		SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
		String hour = hora.format(fecha.getCalendar().getTime());
		Object[] Fila = new Object[model.getColumnCount()];

		if (fecha2.get(Calendar.MONTH) < 10) {
			Fila[0] = fecha2.get(Calendar.YEAR) + "-0" + fecha2.get(Calendar.MONTH) + "-"
					+ fecha2.get(Calendar.DAY_OF_MONTH);
			Fila[1] = hour;
			if(model.getRowCount()==0) {
				Fila[2] = 0;
				Fila[3] = 0;
				}else {
					Fila[2] = model.getValueAt(0, 2).toString();
					Fila[3] = model.getValueAt(0, 3).toString();	
				}
			double precio_total = 0;
			if (model.getRowCount() > 0) {
				for (Producto p : os.getAllProducts(Conexion.obtener())) {
					if (Double.valueOf(model.getValueAt(0, 2).toString()) != 0
							&& Double.valueOf(model.getValueAt(0, 3).toString()) != 0) {
						if (p.getId_Producto().equals(model.getValueAt(0, 2).toString())) {
							precio_total = p.getPrecio() * Double.valueOf(model.getValueAt(0, 3).toString());
						}
					}
				}
				Fila[4] =  String.format("%.2f", precio_total);
			}
			if(model.getRowCount()>1) 
			model.removeRow(1);
			if(model.getRowCount()==1)
				model.removeRow(0);
			model.addRow(Fila);
		} else {
			Fila[0] = fecha2.get(Calendar.YEAR) + "-0" + fecha2.get(Calendar.MONTH) + "-"
					+ fecha2.get(Calendar.DAY_OF_MONTH);
			Fila[1] = hour;
			if(model.getRowCount()==0) {
			Fila[2] = 0;
			Fila[3] = 0;
			}else {
				Fila[2] = model.getValueAt(0, 2).toString();
				Fila[3] = model.getValueAt(0, 3).toString();	
			}
			double precio_total = 0;
			if (model.getRowCount() > 0) {
				for (Producto p : os.getAllProducts(Conexion.obtener())) {
					if (Double.valueOf(model.getValueAt(0, 2).toString()) != 0
							&& Double.valueOf(model.getValueAt(0, 3).toString()) != 0) {
						if (p.getId_Producto().equals(model.getValueAt(0, 2).toString())) {
							precio_total = p.getPrecio() * Double.valueOf(model.getValueAt(0, 3).toString());
						}
						Fila[4] = String.format("%.2f", precio_total);
					}
				}
			}

		}
		if(model.getRowCount()>1) 
			model.removeRow(1);
			if(model.getRowCount()==1)
				model.removeRow(0);
		model.addRow(Fila);
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
		return idmayor;
	}
}
