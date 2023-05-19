package views;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;

import models.Usuario;
import services.Conexion;
import services.ObjectService;

public class InterfazClientesAdmin extends JFrame {

	private static ObjectService os = new ObjectService();
	private static List<Usuario> ListaUsuarios = new ArrayList<>();
	private JTable table;
	private static JtableBloquearCeldasClientes model;
	private static InterfazLogin il;
	private JButton ActivarDesactivar, Atras, Borrar, ReestablecerClave, VerCompras;
	protected static String id;
	private JButton Ver_Grafica;
	private JButton Ver_Grafica_1;

	public InterfazClientesAdmin() {
		super("Menu Clientes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(764, 373);

		JScrollPane scrollPane = new JScrollPane();

		ActivarDesactivar = new JButton("ActivarDesactivar");
		Atras = new JButton("Atras");
		Borrar = new JButton("Borrar");
		ReestablecerClave = new JButton("ReestablecerClave");
		VerCompras = new JButton("VerCompras");
		Ver_Grafica = new JButton("Ver Grafica");
		
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
		
		Ver_Grafica_1 = new JButton("Ver Grafica");
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(10, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 731, GroupLayout.PREFERRED_SIZE)
							.addGap(7))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(ActivarDesactivar)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(Borrar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(ReestablecerClave)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(VerCompras)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(Atras)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(Ver_Grafica_1, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
								.addComponent(Ver_Grafica, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
							.addGap(69)))
					.addGap(7))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(95)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(Borrar)
								.addComponent(ReestablecerClave)
								.addComponent(VerCompras)
								.addComponent(Atras)
								.addComponent(ActivarDesactivar)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(11)
							.addComponent(Ver_Grafica_1, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
							.addGap(5)
							.addComponent(Ver_Grafica, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(34))
		);

		getContentPane().setLayout(groupLayout);

		model.addColumn("Id_Usuario");
		model.addColumn("Nombre");
		model.addColumn("Direccion");
		model.addColumn("Es_Admin");
		model.addColumn("Activo");
		model.addColumn("Clave");

		EscribirTabla();
		Iterator<Usuario> it = ListaUsuarios.iterator();
		
		ActivarDesactivar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				String id = (String) (model.getValueAt(table.getSelectedRow(), 0));
				for (Usuario u : ListaUsuarios) {
					if (u.getId_Usuario().equals(id)) {
						if (u.isActivo()) {
							u.setActivo(false);
						} else {
							u.setActivo(true);
						}
						try {
							os.saveUsuario(Conexion.obtener(), u, 1);
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
					for(int x = 0; x <= table.getRowCount(); x++)
						model.removeRow(0);
					LeerBase();
					EscribirTabla();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}catch(ArrayIndexOutOfBoundsException ex) {
					JOptionPane.showMessageDialog(InterfazClientesAdmin.this, "Seleccionado A un Cliente");
				}
			}
		});
		
		Atras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		Borrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				String id = (String) (model.getValueAt(table.getSelectedRow(), 0));
				Iterator<Usuario> it = ListaUsuarios.iterator();
				while (it.hasNext()) {
					Usuario u = it.next();
					if (u.getId_Usuario().equals(id)) {
						if (JOptionPane.showConfirmDialog(InterfazClientesAdmin.this,
								"Esta Seguro de Eliminar al Cliente") == 0) {
							it.remove();
							try {
									os.removeUsuario(Conexion.obtener(), u);
									model.removeRow(table.getSelectedRow());
									JOptionPane.showMessageDialog(InterfazClientesAdmin.this, "Cliente Eliminado");
								} catch (ClassNotFoundException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(InterfazClientesAdmin.this, "El Cliente Tiene Compras No se Puede Borrar");
								}catch (NoSuchElementException ex) {
									
								}
						}
						
					}
				}
				}catch(ArrayIndexOutOfBoundsException ex) {
					JOptionPane.showMessageDialog(InterfazClientesAdmin.this, "Selecciona A un Cliente");
				}
			}
		});
		
		ReestablecerClave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				String id = (String) (model.getValueAt(table.getSelectedRow(), 0));
				for (Usuario u : ListaUsuarios) {
					if (u.getId_Usuario().equals(id)) {
						u.setContrasena((String) (model.getValueAt(table.getSelectedRow(), 5)));
						if (JOptionPane.showConfirmDialog(InterfazClientesAdmin.this,
								"Esta Seguro de Cambiarle la Clave?") == 0) {
							try {
								os.saveUsuario(Conexion.obtener(), u, 1);
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						
					}

				}
				}catch(ArrayIndexOutOfBoundsException ex) {
					JOptionPane.showMessageDialog(InterfazClientesAdmin.this, "Seleccionado A un Cliente");
				}
			}
		});
		
		VerCompras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				id = (String) (model.getValueAt(table.getSelectedRow(), 0));
				InterfazVerCompras ivc = new InterfazVerCompras();
				ivc.setLocationRelativeTo(null);
				ivc.setVisible(true);
				}catch(ArrayIndexOutOfBoundsException ex) {
					JOptionPane.showMessageDialog(InterfazClientesAdmin.this, "Seleccionado A un Cliente");
				}
			}
		});
		
		Ver_Grafica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				graficocircularcompras gp = new graficocircularcompras ();
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
		
		Ver_Grafica_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				graficocirculargastomedio gm = new graficocirculargastomedio ();
				try {
					gm.mostrargrafico();
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