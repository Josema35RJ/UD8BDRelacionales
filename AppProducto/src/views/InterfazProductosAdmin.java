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

public class InterfazProductosAdmin extends JFrame {

	private static ObjectService os = new ObjectService();
	private static List<Producto> ListaProductos = new ArrayList<>();
	private JTable table;
	private JButton Insertar,Eliminar, Cambiar,Guardar, Actualizar, Atras;
	private JTextField Id_Producto,Nombre, Direccion;
	private static DefaultTableModel model;
	private JTextField Clave;
	private JTextField Cant_Stock;
	private JTextField Precio;
	private static InterfazLogin il;
	private JTextField Id_Proveedor;
	private JTextField Categoria;

	public InterfazProductosAdmin() {
		super("Menu Productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(764, 373);
		setLocationRelativeTo(null);

		JScrollPane scrollPane = new JScrollPane();

		Insertar = new JButton("Create");

		Id_Producto = new JTextField();
		Id_Producto.setColumns(10);

		Nombre = new JTextField();
		Nombre.setColumns(10);

		Direccion = new JTextField();
		Direccion.setColumns(10);

		Eliminar = new JButton("Delete");

		Cambiar = new JButton("Update");

		Guardar = new JButton("Save");

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
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 731, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(Id_Producto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(21)
									.addComponent(Insertar)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(27)
									.addComponent(Eliminar))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(Nombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(41)
									.addComponent(Cambiar))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(Direccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(Categoria, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
									.addGap(47)
									.addComponent(Guardar)
									.addGap(41)
									.addComponent(Atras, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
									.addGap(64)
									.addComponent(Actualizar)
									.addPreferredGap(ComponentPlacement.RELATED, 62, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(Clave, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(Precio, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(Cant_Stock, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(Id_Proveedor, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
					.addGap(70)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(Id_Producto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(Id_Proveedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(Nombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(Direccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(Cant_Stock, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(Precio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(Clave, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(Categoria, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(Insertar)
						.addComponent(Eliminar)
						.addComponent(Cambiar)
						.addComponent(Guardar)
						.addComponent(Actualizar)
						.addComponent(Atras))
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
				Fila[2] = Direccion.getText();
				Fila[3] = Categoria.getText();
				Fila[4] = Clave.getText();
				Fila[5] = Cant_Stock.getText();
				Fila[6] = Precio.getText();
				model.addRow(Fila);		
				
			Producto p=	new Producto(Id_Producto.getText(), Nombre.getText(), Direccion.getText(),Categoria.getText(),
								Clave.getText(),Float.valueOf(Precio.getText()),Integer.valueOf(Cant_Stock.getText()) ,
								String.valueOf(1),String.valueOf(Id_Proveedor.getText()));
					
				try {
					for (Proveedor pro : os.getAllProveedor(Conexion.obtener())){
					if(p.getId_Proveedor().equals(Id_Proveedor.getText()))
					os.saveProducto(Conexion.obtener(), p , il.User, pro);
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
				for (Producto p : ListaProductos) {
					if (!p.getNombre().equals(Nombre.getText())) {
						p.setNombre(Nombre.getText());
					}
					if (p.getImagen() ==Direccion.getText()) {
						p.setImagen(Direccion.getText());
					}
				}
				EscribirTabla();
			}
		});

		Actualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					LeerBase();
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