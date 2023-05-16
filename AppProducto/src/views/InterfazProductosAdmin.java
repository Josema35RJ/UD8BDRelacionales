package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import models.Producto;
import models.Proveedor;
import services.Conexion;
import services.ObjectService;
import javax.swing.JLabel;

public class InterfazProductosAdmin extends JFrame {

	private static ObjectService os = new ObjectService();
	private static List<Producto> ListaProductos = new ArrayList<>();
	private JTable table;
	private JButton Insertar,Eliminar, Cambiar, Actualizar, Atras;
	private JTextField Id_Producto,Nombre, Descripcion;
	private static DefaultTableModel model;
	private JTextField Clave;
	private JTextField Cant_Stock;
	private JTextField Precio;
	private static InterfazLogin il;
	private JTextField Id_Proveedor;
	private JTextField Categoria;
	private JLabel lblNombre;
	private JLabel lblDescripcion;
	private JLabel lblCategoria;
	private JLabel lblClave;
	private JLabel lblPrecio;
	private JLabel ID_1;
	private JLabel lbCant_Stock;
	private JLabel lbId_Proveedor;

	public InterfazProductosAdmin() {
		super("Menu Productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(808, 373);
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
		
		lblClave = new JLabel("CLAVE");
		
		lblPrecio = new JLabel("PRECIO");
		
		ID_1 = new JLabel("");
		
		lbCant_Stock = new JLabel("Cant_Stock");
		
		lbId_Proveedor = new JLabel("Id_Proveedor");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 731, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(10)
											.addComponent(Id_Producto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addContainerGap()
											.addComponent(ID)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
										.addComponent(Nombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(Descripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblDescripcion))
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(groupLayout.createSequentialGroup()
									.addContainerGap()
									.addComponent(Insertar)
									.addGap(18)
									.addComponent(Eliminar)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblCategoria, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(Cambiar)
												.addComponent(Categoria, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(Clave, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblPrecio, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
										.addComponent(Precio, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
									.addGap(10)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(Cant_Stock, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
										.addComponent(lbCant_Stock))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(ID_1, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE)
										.addComponent(Id_Proveedor, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
										.addComponent(lbId_Proveedor, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(108)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(Atras, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblClave)
											.addGap(55)))
									.addGap(18)
									.addComponent(Actualizar)))
							.addGap(92)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
					.addGap(50)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(ID)
						.addComponent(lblDescripcion)
						.addComponent(lblCategoria)
						.addComponent(lblClave)
						.addComponent(ID_1)
						.addComponent(lblPrecio)
						.addComponent(lbCant_Stock)
						.addComponent(lbId_Proveedor))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(Id_Producto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(Nombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(Descripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(Categoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(Clave, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(Precio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(Cant_Stock, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(Id_Proveedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(Atras)
						.addComponent(Actualizar)
						.addComponent(Cambiar)
						.addComponent(Eliminar)
						.addComponent(Insertar))
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
				
			Producto p=	new Producto(Id_Producto.getText(), Nombre.getText(), " ", Descripcion.getText(),Categoria.getText(),
								Float.valueOf(Precio.getText()),Integer.valueOf(Cant_Stock.getText()) ,
								String.valueOf(1),String.valueOf(Id_Proveedor.getText()));
					
				try {
					for (Proveedor pro : os.getAllProveedor(Conexion.obtener())){
					if(p.getId_Proveedor().equals(Id_Proveedor.getText()))
					os.saveProducto(Conexion.obtener(), p , il.User, Id_Proveedor.getText(), 0);
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
                while(it.hasNext()) {
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
				Producto pupdate = null;
				String id = (String) (model.getValueAt(table.getSelectedRow(), 0));
				System.out.println(id);
				for (Producto p : ListaProductos) {
					if (p.getId_Producto().equals(id)) {
					if (!p.getNombre().equals((String) (model.getValueAt(table.getSelectedRow(), 1)))) {
						p.setNombre((String) (model.getValueAt(table.getSelectedRow(), 1)));
						pupdate=p;
					}
					if (!p.getImagen().equals((String) (model.getValueAt(table.getSelectedRow(), 2)))) {
						p.setImagen((String) (model.getValueAt(table.getSelectedRow(), 2)));
						pupdate=p;
					}
					
					if (!p.getDescripcion().equals((String) (model.getValueAt(table.getSelectedRow(), 3)))) {
						p.setDescripcion((String) (model.getValueAt(table.getSelectedRow(), 3)));
						pupdate=p;
					}
					if (!p.getCategoria().equals((String) (model.getValueAt(table.getSelectedRow(), 4)))) {
						p.setCategoria((String) (model.getValueAt(table.getSelectedRow(), 4)));
						pupdate=p;
					}
					
					if (!(String.valueOf(p.getCant_Stock())==(model.getValueAt(table.getSelectedRow(), 6)).toString())) {
						p.setCant_Stock(((Number)model.getValueAt(table.getSelectedRow(), 6)).intValue());
						pupdate=p;
					}
					if (!(p.getId_Proveedor().equals((String) (model.getValueAt(table.getSelectedRow(), 8))))) {
						p.setId_Proveedor((String)(model.getValueAt(table.getSelectedRow(), 8)));
						pupdate=p;
					}
					}
				}
				try {
						os.saveProducto(Conexion.obtener(), pupdate , il.User, pupdate.getId_Proveedor(), 1);
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
			ListaProductos =os.getAllProducts(Conexion.obtener());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}