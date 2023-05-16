package views;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import models.Producto;
import models.Proveedor;
import services.Conexion;
import services.ObjectService;
import java.awt.Window.Type;
import java.awt.Color;
import java.awt.Font;

public class InterfazProductosAdmin extends JFrame {

	private static ObjectService os = new ObjectService();
	private static List<Producto> ListaProductos = new ArrayList<>();
	private JTable table;
	private JButton Insertar, Eliminar, Cambiar, Actualizar, Atras, Ver_Imagen;
	private JTextField Id_Producto, Nombre, Descripcion, Clave, Cant_Stock, Precio, Id_Proveedor, Categoria;
	private static DefaultTableModel model;
	private static InterfazLogin il;
	private JLabel lblNombre, lblDescripcion, lblCategoria, lblClave, lblPrecio, ID_1, lbCant_Stock, lbId_Proveedor;
	private JComboBox ListaImagenes;
	private String[]RutasImagenes ;
	private static ImageIcon Ver ;
	private static Icon icon;
	private JButton InsertarFoto;

	public InterfazProductosAdmin() {
		super("Menu Productos");
		setFont(new Font("Arial", Font.PLAIN, 12));
		setForeground(Color.GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(940, 507);
		setLocationRelativeTo(null);

		JScrollPane scrollPane = new JScrollPane();
		
		Insertar = new JButton("Create");

		Id_Producto = new JTextField();
		Id_Producto.setColumns(10);

		Nombre = new JTextField();
		Nombre.setColumns(10);

		Descripcion = new JTextField();
		Descripcion.setColumns(10);

		Eliminar = new JButton("Delete");

		Cambiar = new JButton("Update");

		Actualizar = new JButton("Actualizar");

		Clave = new JTextField();
		Clave.setColumns(10);

		Cant_Stock = new JTextField();
		Cant_Stock.setColumns(10);

		Precio = new JTextField();
		Precio.setColumns(10);

		Atras = new JButton("Atras");

		Id_Proveedor = new JTextField();
		Id_Proveedor.setColumns(10);

		Categoria = new JTextField();
		Categoria.setColumns(10);

		JLabel ID = new JLabel("ID");

		lblNombre = new JLabel("NOMBRE");

		lblDescripcion = new JLabel("DESCRIPCION");

		lblCategoria = new JLabel("CATEGORIA");

		lblClave = new JLabel("ID_Usuario");

		lblPrecio = new JLabel("PRECIO");

		ID_1 = new JLabel("");

		lbCant_Stock = new JLabel("Cant_Stock");

		lbId_Proveedor = new JLabel("Id_Proveedor");
		
		JLabel Foto = new JLabel("");
		
	    Ver_Imagen = new JButton("Imagen");
		
	    File f = new File ("IconProducts");
	    File[] Rutas =f.listFiles();
	    RutasImagenes = new String[Rutas.length];
	    int siguiente = 0;
	    for(File r : Rutas)
			try {
				String[] Ruta= r.getCanonicalPath().split("AppProducto");
				RutasImagenes[siguiente++]=Ruta[1].substring(1, Ruta[1].length()).strip();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		ListaImagenes = new JComboBox(RutasImagenes);
		
		InsertarFoto = new JButton("");
		ImageIcon IconInsertarImage = new ImageIcon ("Icon/InsertarImagen.png");
		InsertarFoto.setIcon(IconInsertarImage);

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 565, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(112)
							.addComponent(InsertarFoto, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(30)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(Foto, GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(Ver_Imagen, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED, 207, Short.MAX_VALUE)
											.addComponent(ID_1, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE)
											.addGap(22))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(18)
											.addComponent(ListaImagenes, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
											.addContainerGap())))))))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(Id_Producto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(ID))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(Nombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDescripcion)
						.addComponent(Descripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(Categoria, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCategoria, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(Clave, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPrecio, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(Precio, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblClave))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(Cant_Stock, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbCant_Stock))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lbId_Proveedor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(Id_Proveedor, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(107, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(203)
					.addComponent(Actualizar)
					.addGap(36)
					.addComponent(Insertar)
					.addGap(18)
					.addComponent(Cambiar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(Eliminar)
					.addGap(16)
					.addComponent(Atras, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(292, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 349, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(Foto, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(ID_1)
							.addGap(26)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(Ver_Imagen)
								.addComponent(ListaImagenes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(InsertarFoto, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(ID)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(Id_Producto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDescripcion)
								.addComponent(lblCategoria))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(Descripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(Categoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNombre)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(Nombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPrecio)
								.addComponent(lblClave))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(Clave, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(Precio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lbCant_Stock)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(Cant_Stock, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lbId_Proveedor)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(Id_Proveedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(Eliminar)
						.addComponent(Atras)
						.addComponent(Cambiar)
						.addComponent(Insertar)
						.addComponent(Actualizar))
					.addContainerGap(21, Short.MAX_VALUE))
		);
		try {
			LeerBase();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		table = new JTable();
		model = new DefaultTableModel();
		table.setModel(model);

		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);

		model.addColumn("Id_Producto");
		model.addColumn("Nombre");
		model.addColumn("Imagen");
		model.addColumn("Descripcion");
		model.addColumn("Categoria");
		model.addColumn("Precio");
		model.addColumn("Cant_Stock");
		model.addColumn("Id_Usuario");
		model.addColumn("Id_Proveedor");

		EscribirTabla();

		Insertar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Object[] Fila = new Object[model.getColumnCount()];
				Fila[0] = Id_Producto.getText();
				Fila[1] = Nombre.getText();
				Fila[2] = Descripcion.getText();
				Fila[3] = Categoria.getText();
				Fila[4] = Clave.getText();
				Fila[5] = Cant_Stock.getText();
				Fila[6] = Precio.getText();
				model.addRow(Fila);

				Producto p = new Producto(Id_Producto.getText(), Nombre.getText(), " ", Descripcion.getText(),
						Categoria.getText(), Float.valueOf(Precio.getText()), Integer.valueOf(Cant_Stock.getText()),
						String.valueOf(1), String.valueOf(Id_Proveedor.getText()));

				try {
					for (Proveedor pro : os.getAllProveedor(Conexion.obtener())) {
						if (p.getId_Proveedor().equals(Id_Proveedor.getText()))
							os.saveProducto(Conexion.obtener(), p, il.User, Id_Proveedor.getText(), 0);
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

		Atras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		Iterator<Producto> it = ListaProductos.iterator();
		Eliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				while (it.hasNext()) {
					Producto p = it.next();
					if (p.getId_Producto().equals(model.getValueAt(table.getSelectedRow(), 0).toString()))

						it.remove();
					model.removeRow(table.getSelectedRow());
				}

			}
		});

		Cambiar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
				Producto pupdate = null;
				String id = (String) (model.getValueAt(table.getSelectedRow(), 0));
				for (Producto p : ListaProductos) {
					if (p.getId_Producto().equals(id)) {
						if (!p.getNombre().equals((String) (model.getValueAt(table.getSelectedRow(), 1)))) {
							p.setNombre((String) (model.getValueAt(table.getSelectedRow(), 1)));
							pupdate = p;
						}
						if (!p.getImagen().equals(ListaImagenes.getSelectedItem().toString())) {
							p.setImagen(ListaImagenes.getSelectedItem().toString());
							pupdate = p;
						}
						if (!p.getDescripcion().equals((String) (model.getValueAt(table.getSelectedRow(), 3)))) {
							p.setDescripcion((String) (model.getValueAt(table.getSelectedRow(), 3)));
							pupdate = p;
						}
						if (!p.getCategoria().equals((String) (model.getValueAt(table.getSelectedRow(), 4)))) {
							p.setCategoria((String) (model.getValueAt(table.getSelectedRow(), 4)));
							pupdate = p;
						}
						try {
							if (!(String.valueOf(p.getPrecio()).equals(model.getValueAt(table.getSelectedRow(), 5)))) {
								p.setPrecio(Float.valueOf(String.valueOf((float) model.getValueAt(table.getSelectedRow(), 5))));
								pupdate = p;
							}
							if (!(String.valueOf(p.getCant_Stock()).equals(model.getValueAt(table.getSelectedRow(), 6)))) {
								p.setCant_Stock(Integer.valueOf(String.valueOf((int) model.getValueAt(table.getSelectedRow(), 6))));
								pupdate = p;
							}
						} catch (ClassCastException ex) {
							if (!(String.valueOf(p.getPrecio()).equals(model.getValueAt(table.getSelectedRow(), 5)))) {
								p.setPrecio(Float.valueOf(String.valueOf( model.getValueAt(table.getSelectedRow(), 5))));
								pupdate = p;
							}
							if (!(String.valueOf(p.getCant_Stock()).equals(model.getValueAt(table.getSelectedRow(), 6)))) {
								p.setCant_Stock(Integer.valueOf(String.valueOf( model.getValueAt(table.getSelectedRow(), 6))));
								pupdate = p;
							}
						}
						if (!(p.getId_Proveedor().equals((String) (model.getValueAt(table.getSelectedRow(), 8))))) {
							p.setId_Proveedor((String) (model.getValueAt(table.getSelectedRow(), 8)));
							pupdate = p;
						}
					}
				}
				try {
					os.saveProducto(Conexion.obtener(), pupdate, il.User, pupdate.getId_Proveedor(), 1);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}catch(ArrayIndexOutOfBoundsException ex) {
					JOptionPane.showMessageDialog(InterfazProductosAdmin.this, "No Hay Ningun Producto Seleccionado");
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
		Ver_Imagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				Ver = new ImageIcon (model.getValueAt(table.getSelectedRow(), 2).toString());
				icon = new ImageIcon(Ver.getImage().getScaledInstance(Foto.getWidth(), Foto.getHeight(), Image.SCALE_DEFAULT));
				Foto.setIcon(icon);
				}catch (ArrayIndexOutOfBoundsException ex) {
					JOptionPane.showMessageDialog(InterfazProductosAdmin.this, "Ningun Producto Seleccionado para Imagen");
				}
			}
		});
		
		ListaImagenes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			    Ver = new ImageIcon (ListaImagenes.getSelectedItem().toString());
			    icon = new ImageIcon(Ver.getImage().getScaledInstance(Foto.getWidth(), Foto.getHeight(), Image.SCALE_DEFAULT));
				Foto.setIcon(icon);
			}
		});
		
		setVisible(true);
	}

	private static void EscribirTabla() {
		for (Producto p : ListaProductos) {
			Object[] Fila = new Object[model.getColumnCount()];
			Fila[0] = p.getId_Producto();
			Fila[1] = p.getNombre();
			Fila[2] = p.getImagen();
			Fila[3] = p.getDescripcion();
			Fila[4] = p.getCategoria();
			Fila[5] = p.getPrecio();
			Fila[6] = p.getCant_Stock();
			Fila[7] = p.getId_Usuario();
			Fila[8] = p.getId_Proveedor();
			model.addRow(Fila);
		}
	}

	private static void LeerBase() throws ClassNotFoundException {
		try {
			ListaProductos = os.getAllProducts(Conexion.obtener());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}