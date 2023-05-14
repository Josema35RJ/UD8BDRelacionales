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

public class InterfazProveedoresAdmin extends JFrame {

	private static ObjectService os = new ObjectService();
	private static List<Proveedor> ListaProveedores = new ArrayList<>();
	private JTable table;
	private JButton Insertar,Eliminar, Cambiar,Guardar, Actualizar, Atras;
	private JTextField Nombre, Direccion;
	private static DefaultTableModel model;
	private JTextField Clave;
	private static InterfazLogin il;
	private JTextField Id_Proveedor;

	public InterfazProveedoresAdmin() {
		super("Menu Productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(764, 373);
		setLocationRelativeTo(null);

		JScrollPane scrollPane = new JScrollPane();

		Insertar = new JButton("Create");

		Nombre = new JTextField();
		Nombre.setColumns(10);

		Direccion = new JTextField();
		Direccion.setColumns(10);

		Eliminar = new JButton("Delete");
		Eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		Cambiar = new JButton("Update");

		Guardar = new JButton("Save");

		Actualizar = new JButton("Actualizar");
		
		Clave = new JTextField();
		Clave.setColumns(10);
		
		Atras = new JButton("Atras");
		Atras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		Id_Proveedor = new JTextField();
		Id_Proveedor.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 731, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(71)
							.addComponent(Insertar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addComponent(Eliminar)
									.addGap(29)
									.addComponent(Cambiar)
									.addGap(18))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(Id_Proveedor, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(Nombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(47)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(Direccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(Guardar))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(29)
									.addComponent(Atras, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
									.addGap(32)
									.addComponent(Actualizar))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(66)
									.addComponent(Clave, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(7, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
					.addGap(70)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(Nombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(Id_Proveedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(Clave, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(Direccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(Cambiar)
						.addComponent(Eliminar)
						.addComponent(Insertar)
						.addComponent(Guardar)
						.addComponent(Atras)
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

		model.addColumn("Id_Proveedor");
		model.addColumn("Nombre");
		model.addColumn("Direccion");
		model.addColumn("Clave");

		EscribirTabla();

		Insertar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Object[] Fila = new Object[model.getColumnCount()];
				Fila[0] = Id_Proveedor.getText();
				Fila[1] = Nombre.getText();
				Fila[2] = Direccion.getText();
				Fila[3] = Clave.getText();
				model.addRow(Fila);		
				
			Proveedor p=new Proveedor(Id_Proveedor.getText(), Nombre.getText(), Direccion.getText(),
								Clave.getText());
					
				try {
					for (Proveedor pro : os.getAllProveedor(Conexion.obtener())){
							if(p.getId_Proveedor().equals(Id_Proveedor.getText()))
					os.saveProveedor(Conexion.obtener(), p );
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
		Iterator<Proveedor> it = ListaProveedores.iterator();
        Eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                while(it.hasNext()) {
                	Proveedor p = it.next();
                    if (p.getId_Proveedor().equals(model.getValueAt(table.getSelectedRow(), 0).toString()))
                        it.remove();
                   
                }
                model.removeRow(table.getSelectedRow());
            }
        });

		Cambiar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for (Proveedor p : ListaProveedores) {
					if (!p.getNombre().equals(Nombre.getText())) {
						p.setNombre(Nombre.getText());
					}
					if (p.getDireccion().equals(Direccion.getText())) {
						p.setDireccion(Direccion.getText());
					}
				}
				EscribirTabla();
			}
		});

		Guardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
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
		for (Proveedor p : ListaProveedores) {
			Object[] Fila = new Object[model.getColumnCount()];
			Fila[0] = p.getId_Proveedor();
			Fila[1] = p.getNombre();
			Fila[2] = p.getDireccion();
			Fila[3] = p.getContrasena();
			model.addRow(Fila);
		}
	}

	private static void EscribirBase() {

	}

	private static void LeerBase() throws ClassNotFoundException {
		try {
			ListaProveedores =os.getAllProveedor(Conexion.obtener());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}