package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import models.Compra;
import models.Producto;
import models.Proveedor;
import services.Conexion;
import services.ObjectService;

public class InterfazProveedoresAdmin extends JFrame {

	private static ObjectService os = new ObjectService();
	private static List<Proveedor> ListaProveedores = new ArrayList<>();
	private JTable table;
	private JButton Insertar, Eliminar, Guardar, Actualizar, Atras, Ver_Grafica;
	private JTextField Nombre, Direccion, Clave, Id_Proveedor;
	private static JtableBloquearCeldasClientes model;
	private static InterfazLogin il;
	private ImageIcon InsertarProveedor, GuardarProveedores, AtrasProveedores, EliminarProveedores,
			ActualizarProveedores, GraficaVer;

	public InterfazProveedoresAdmin() {
		super("Menu Proveedores");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(790, 394);
		setLocationRelativeTo(null);

		JScrollPane scrollPane = new JScrollPane();

		Insertar = new JButton("");
		InsertarProveedor = new ImageIcon("Icon/InsertarProveedor.png");
		Insertar.setIcon(InsertarProveedor);

		Nombre = new JTextField();
		Nombre.setColumns(10);

		Direccion = new JTextField();
		Direccion.setColumns(10);

		Eliminar = new JButton("");
		EliminarProveedores = new ImageIcon ("Icon/Eliminar2.png");
		Eliminar.setIcon(EliminarProveedores);

		Guardar = new JButton("");
		GuardarProveedores = new ImageIcon ("Icon/Subir.png");
		Guardar.setIcon(GuardarProveedores);

		Actualizar = new JButton("");
		ActualizarProveedores= new ImageIcon ("Icon/Actualizar2.png");
		Actualizar.setIcon(ActualizarProveedores);

		Clave = new JTextField();
		Clave.setColumns(10);

		Atras = new JButton("");
		AtrasProveedores = new ImageIcon("Icon/Volver.png");
		Atras.setIcon(AtrasProveedores);

		Id_Proveedor = new JTextField();
		Id_Proveedor.setColumns(10);

		JLabel lbId_Proveedor = new JLabel("Id_Proveedor");

		JLabel lbNombre = new JLabel("Nombre");

		JLabel lbDireccion = new JLabel("Direccion");

		JLabel lbClave = new JLabel("Clave");

		Ver_Grafica = new JButton("Ver Grafica");
		GraficaVer = new ImageIcon ("Icon/VerGrafico.png");
		Ver_Grafica.setIcon(GraficaVer);

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 731, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(86)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(Id_Proveedor, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(Nombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lbId_Proveedor)
											.addGap(30)
											.addComponent(lbNombre, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lbDireccion, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
										.addComponent(Direccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(Clave, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
											.addComponent(Ver_Grafica, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
											.addGap(58))
										.addComponent(lbClave, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(39)
							.addComponent(Insertar, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(Eliminar, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(Guardar, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(Atras, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(Actualizar, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(33, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbNombre)
								.addComponent(lbId_Proveedor)
								.addComponent(lbClave)
								.addComponent(lbDireccion))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(Nombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(Direccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(Id_Proveedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(Clave, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(45))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(Ver_Grafica, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
							.addGap(18)))
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(Eliminar)
						.addComponent(Insertar)
						.addComponent(Atras)
						.addComponent(Actualizar)
						.addComponent(Guardar))
					.addContainerGap(61, Short.MAX_VALUE))
		);
		try {
			LeerBase();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		table = new JTable();
		model = new JtableBloquearCeldasClientes();
		table.setModel(model);

		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);

		model.addColumn("Id_Proveedor");
		model.addColumn("Nombre");
		model.addColumn("Direccion");
		model.addColumn("Clave");

		EscribirTabla();

		Insertar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Proveedor p = null;
				Object[] Fila = new Object[model.getColumnCount()];
				Fila[0] = Id_Proveedor.getText();
				Fila[1] = Nombre.getText();
				Fila[2] = Direccion.getText();
				Fila[3] = Clave.getText();

				if (Id_Proveedor.getText().length() > 0 && Nombre.getText().length() > 0
						&& Direccion.getText().length() > 0 && Clave.getText().length() > 0) {
					p = new Proveedor(Id_Proveedor.getText(), Nombre.getText(), Direccion.getText(), Clave.getText());
					try {
						for (Proveedor pro : os.getAllProveedor(Conexion.obtener())) {
							if (!pro.getId_Proveedor().equals(p.getId_Proveedor())) {
								os.saveProveedor(Conexion.obtener(), p, 0);
								model.addRow(Fila);
							} else if (pro.getId_Proveedor().equals(p.getId_Proveedor())) {
								JOptionPane.showMessageDialog(InterfazProveedoresAdmin.this,
										"Ya hay un Proveedor con ese Id");
							}
						}
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
					}
				} else {
					JOptionPane.showMessageDialog(InterfazProveedoresAdmin.this, "Rellena Todos Los Campos");
				}
			}
		});
		Iterator<Proveedor> it = ListaProveedores.iterator();
		Eliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				while (it.hasNext()) {
					try {
						Proveedor p = it.next();
						if (p.getId_Proveedor().equals(model.getValueAt(table.getSelectedRow(), 0).toString())) {
							try {
								os.removeProveedor(Conexion.obtener(), p);
								it.remove();
								model.removeRow(table.getSelectedRow());
								JOptionPane.showMessageDialog(InterfazProveedoresAdmin.this, "Proveedor Borrado");
							} catch (SQLIntegrityConstraintViolationException ex) {
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(InterfazProveedoresAdmin.this,
										"Este Proveedor Tiene Productos Registrados");
								if (JOptionPane.showConfirmDialog(InterfazProveedoresAdmin.this,
										"Seguro Que Quieres Eliminarlo") == 0) {
									try {
										for (Producto pro : os.getAllProducts(Conexion.obtener())) {
											if (pro.getId_Proveedor().equals(p.getId_Proveedor())) {
												for (Compra compra : os.getAllCompra(Conexion.obtener())) {
													if (compra.getId_Producto().equals(pro.getId_Producto()))
														os.removeCompra(Conexion.obtener(), compra);
												}
												os.removeProducto(Conexion.obtener(), pro);
											}
											os.removeProveedor(Conexion.obtener(), p);
											it.remove();
											model.removeRow(table.getSelectedRow());
										}
									} catch (ClassNotFoundException e11) {
										// TODO Auto-generated catch block
										e11.printStackTrace();
									} catch (SQLException e2) {
										// TODO Auto-generated catch block

									} catch (IllegalStateException ex) {
									}
								} else {

								}
							}
							p = it.next();

						}
					} catch (NoSuchElementException ex) {
					} catch (ArrayIndexOutOfBoundsException ex) {
						JOptionPane.showMessageDialog(InterfazProveedoresAdmin.this,
								"No Hay Proveedores Seleccionados");
					}
				}
			}
		});

		Guardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Proveedor pudapte = null;
				try {
					String id = model.getValueAt(table.getSelectedRow(), 0).toString();
					for (Proveedor p : ListaProveedores) {
						if (p.getId_Proveedor().equals(id)) {
							if (!p.getNombre().equals(Nombre.getText()))
								p.setNombre(model.getValueAt(table.getSelectedRow(), 1).toString());
							if (!p.getDireccion().equals(Direccion.getText()))
								p.setDireccion(model.getValueAt(table.getSelectedRow(), 2).toString());
							if (!p.getContrasena().equals(Clave.getText()))
								p.setContrasena(model.getValueAt(table.getSelectedRow(), 3).toString());
							pudapte = p;
						}
					}
				} catch (ArrayIndexOutOfBoundsException ex) {
					JOptionPane.showMessageDialog(InterfazProveedoresAdmin.this, "Selecciona Algun Proveedor");
				}
				try {
					os.saveProveedor(Conexion.obtener(), pudapte, 1);
				} catch (NullPointerException ex) {
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		Actualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					LeerBase();
					while (model.getRowCount() > 0) {
						model.removeRow(0);
					}
					EscribirTabla();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		Ver_Grafica.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				graficocircularproveedor gp = new graficocircularproveedor();
				try {
					gp.mostrargrafico();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		Atras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		setVisible(true);
	}

	private static void EscribirTabla() {
		for (Proveedor p : ListaProveedores) {
			Object[] Fila = new Object[model.getColumnCount()];
			Fila[0] = p.getId_Proveedor();
			Fila[1] = p.getNombre();
			Fila[2] = p.getDireccion();
			Fila[3] = p.getContrasena();
			model.addRow(Fila);
		}
	}

	private static void LeerBase() throws ClassNotFoundException {
		try {
			ListaProveedores = os.getAllProveedor(Conexion.obtener());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}