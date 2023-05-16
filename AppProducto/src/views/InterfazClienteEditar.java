package views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import models.Usuario;
import services.Conexion;
import services.ObjectService;

public class InterfazClienteEditar extends JFrame {

	private JPanel contentPane;
	private JTextField Nombre;
	private JTextField Direccion;
	private JTextField Contrasena;
	private ObjectService oc=new ObjectService();

	public InterfazClienteEditar() {
		setTitle("Editar Perfil");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 264);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lNombre = new JLabel("Nombre");
		lNombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lNombre.setBounds(65, 37, 75, 33);
		contentPane.add(lNombre);
		
		JLabel lDireccion = new JLabel("Direccion");
		lDireccion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lDireccion.setBounds(65, 80, 75, 33);
		contentPane.add(lDireccion);
		
		JLabel lContrasena = new JLabel("Contrasena");
		lContrasena.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lContrasena.setBounds(65, 123, 75, 33);
		contentPane.add(lContrasena);
		
		JButton botonVolver = new JButton("Volver Atras");
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfazCliente ic=new InterfazCliente();
				ic.setVisible(true);
				dispose();
			}
		});
		botonVolver.setBounds(279, 184, 98, 33);
		contentPane.add(botonVolver);
		
		JButton botonGuardar = new JButton("Guardar");
		botonGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					boolean cambio=false;
					for (Usuario u : oc.getAllUsuarios(Conexion.obtener())) {
						if(u.getId_Usuario().equals(InterfazLogin.User.getId_Usuario())) {
							if(!u.getNombre().equals(Nombre.getText())) {
								u.setNombre(Nombre.getText());
								cambio=true;
							}
							if(!u.getDireccion().equals(Direccion.getText())) {
								u.setDireccion(Direccion.getText());
								cambio=true;
							}
							if(!u.getContrasena().equals(Contrasena.getText())) {
								u.setContrasena(Contrasena.getText());
								cambio=true;
							}
							if(cambio) {
								JOptionPane.showMessageDialog(InterfazClienteEditar.this, "¡Datos actualizados con exito!");
								oc.saveUsuario(Conexion.obtener(), u, 1);
						}
						}
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
		botonGuardar.setBounds(91, 184, 98, 33);
		contentPane.add(botonGuardar);
		
		Nombre = new JTextField(InterfazLogin.User.getNombre());
		Nombre.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Nombre.setBounds(150, 45, 96, 19);
		contentPane.add(Nombre);
		Nombre.setColumns(10);
		
		Direccion = new JTextField(InterfazLogin.User.getDireccion());
		Direccion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Direccion.setColumns(10);
		Direccion.setBounds(150, 88, 96, 19);
		contentPane.add(Direccion);
		
		Contrasena = new JTextField(InterfazLogin.User.getContrasena());
		Contrasena.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Contrasena.setColumns(10);
		Contrasena.setBounds(150, 131, 96, 19);
		contentPane.add(Contrasena);
	}
}
