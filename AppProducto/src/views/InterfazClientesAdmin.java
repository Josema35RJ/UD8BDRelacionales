package views;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Producto;
import models.Usuario;
import services.Conexion;
import services.ObjectService;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfazClientesAdmin extends JFrame {

	private static ObjectService os = new ObjectService();
	private static List<Usuario> ListaUsuarios = new ArrayList<>();
	private JTable table;
	private static DefaultTableModel model;
	private static InterfazLogin il;
	private JButton ActivarDesactivar, Atras, Borrar, ReestablecerClave, VerCompras;
	protected static String id;

	public InterfazClientesAdmin() {
		super("Menu Clientes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(764, 373);

		JScrollPane scrollPane = new JScrollPane();

		ActivarDesactivar = new JButton("ActivarDesactivar");
		ActivarDesactivar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = (String) (model.getValueAt(table.getSelectedRow(),0 ));
				for (Usuario u : ListaUsuarios) {
					if (u.getId_Usuario().equals(id)) {
						if (u.isActivo()) {
							u.setActivo(false);
						} else {
							u.setActivo(true);
						}
						System.out.println(u);
						try {
							os.saveUsuario(Conexion.obtener(), u,1);
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					try {
						Conexion.cerrar();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});

		Atras = new JButton("Atras");
		Atras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
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

		Borrar = new JButton("Borrar");
		Borrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = (String) (model.getValueAt(table.getSelectedRow(), 0));
				Iterator<Usuario> it = ListaUsuarios.iterator();
				Usuario u = it.next();
				while (it.hasNext()) {
					if (u.getId_Usuario().equals(id)) {
						if (JOptionPane.showConfirmDialog(InterfazClientesAdmin.this,
								"Esta Seguro de Eliminar al Cliente") == 0) {
							it.remove();
							model.removeRow(table.getSelectedRow());
						}
						try {
							os.removeUsuario(Conexion.obtener(), u);
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						u = it.next();
					}
					u = it.next();
				}
				try {
					Conexion.cerrar();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		ReestablecerClave = new JButton("ReestablecerClave");
		ReestablecerClave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = (String) (model.getValueAt(table.getSelectedRow(), 0));
				for (Usuario u : ListaUsuarios) {
					if (u.getId_Usuario().equals(id)) {
						u.setContrasena((String) (model.getValueAt(table.getSelectedRow(), 5)));
						try {
							os.saveUsuario(Conexion.obtener(), u,1);
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
				}
				try {
					Conexion.cerrar();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		VerCompras = new JButton("VerCompras");
		VerCompras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				id = (String) (model.getValueAt(table.getSelectedRow(), 0));
				InterfazVerCompras ivc = new InterfazVerCompras();
				ivc.setLocationRelativeTo(null);
				ivc.setVisible(true);
			}
		});

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(10)
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 731,
												GroupLayout.PREFERRED_SIZE)
										.addGap(7))
								.addGroup(groupLayout.createSequentialGroup().addGap(45).addComponent(ActivarDesactivar)
										.addGap(40).addComponent(Borrar).addGap(32).addComponent(ReestablecerClave)
										.addGap(52).addComponent(VerCompras)
										.addPreferredGap(ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
										.addComponent(Atras).addGap(66)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(11)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
						.addGap(100)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(ActivarDesactivar)
								.addComponent(Borrar).addComponent(ReestablecerClave).addComponent(Atras)
								.addComponent(VerCompras))));

		getContentPane().setLayout(groupLayout);

		model.addColumn("Id_Usuario");
		model.addColumn("Nombre");
		model.addColumn("Direccion");
		model.addColumn("Es_Admin");
		model.addColumn("Activo");
		model.addColumn("Clave");

		EscribirTabla();
		Iterator<Usuario> it = ListaUsuarios.iterator();
	}

	private static void EscribirTabla() {
		for (Usuario u : ListaUsuarios) {
			if (!u.isEs_Admin()) {
				Object[] Fila = new Object[model.getColumnCount()];
				Fila[0] = u.getId_Usuario();
				Fila[1] = u.getNombre();
				Fila[2] = u.getDireccion();
				Fila[3] = u.isEs_Admin();
				Fila[4] = u.isActivo();
				Fila[5] = u.getContrasena();
				model.addRow(Fila);
			}
		}
	}

	private static void EscribirBase() {

	}

	private static void LeerBase() throws ClassNotFoundException {
		try {
			ListaUsuarios = os.getAllUsuarios(Conexion.obtener());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}