package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import models.Proveedor;
import models.Usuario;
import services.Conexion;
import services.ObjectService;

public class InterfazLogin extends JFrame {
	
	private JLabel Nombre, Id_Usuario;
	private JTextField Nombretext;
	private JPasswordField Id_Usuariotext;
	private JButton Entrar, Cancelar, Registrar;
	protected static String idproveedorreg;
	protected static Usuario User;
	private JTextField txtanNoTienes;
	private ImageIcon ImageEntrar, ImageCancelar, ImageInsertar;
	
	public InterfazLogin () {
		super("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(420,250);
		setLocationRelativeTo(null);
		
		Nombre = new JLabel ("Nombre");
		Id_Usuario = new JLabel ("Clave");
		Nombretext = new JTextField (20);
		Id_Usuariotext = new JPasswordField(20);
		
		Entrar = new JButton ("Entrar");
		ImageEntrar = new ImageIcon ("Icon/Entrar.png");
		Entrar.setIcon(ImageEntrar);
		
		Cancelar = new JButton ("Cancelar");
		ImageCancelar = new ImageIcon ("Icon/Cancelar.png");
		Cancelar.setIcon(ImageCancelar);
		
	    Registrar = new JButton("Registrate");
		ImageInsertar = new ImageIcon ("Icon/InsertarUsuario.png");
		Registrar.setIcon(ImageInsertar);
		
		txtanNoTienes = new JTextField();
		txtanNoTienes.setText("¿Eres nuevo?");
		txtanNoTienes.setEditable(false);
		txtanNoTienes.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(95)
							.addComponent(Entrar)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(Cancelar))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(54)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(Nombre)
								.addComponent(Id_Usuario))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(Nombretext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(Id_Usuariotext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(87)
							.addComponent(txtanNoTienes, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(Registrar)))
					.addContainerGap(131, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(Registrar)
						.addComponent(txtanNoTienes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(Nombre)
						.addComponent(Nombretext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(Id_Usuario)
						.addComponent(Id_Usuariotext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(Cancelar)
						.addComponent(Entrar))
					.addGap(28))
		);
		getContentPane().setLayout(groupLayout);
		
		Entrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ObjectService os=new ObjectService();
				try {
					try {
						boolean entra=false;
						for(Usuario u:os.getAllUsuarios(Conexion.obtener())) {
							if(u.getNombre().equals(Nombretext.getText()) && u.getContrasena().equals(String.valueOf(Id_Usuariotext.getPassword()))) {
								entra=true;
								if(u.isEs_Admin() && u.isActivo()) {
									//interfaz admin
									User = u;
									dispose();
									InterfazAdmin ia = new InterfazAdmin ();
									ia.setLocationRelativeTo(null);
									ia.setVisible(true);
								}else if(u.isActivo()==false){
									JOptionPane.showMessageDialog(InterfazLogin.this, "NO PUEDES ACCEDER YA QUES ESTÃ�S INACTIVO","ERROR",JOptionPane.ERROR_MESSAGE);
								}else {
									InterfazCliente ic=new InterfazCliente();
									ic.setVisible(true);
									dispose();
									User = u;
								}
							}
						}
						for (Proveedor p : os.getAllProveedor(Conexion.obtener())) {
							if(p.getNombre().equals(Nombretext.getText()) && p.getContrasena().equals(String.valueOf(Id_Usuariotext.getPassword()))) {
								entra=true;
								dispose();
								idproveedorreg=p.getId_Proveedor();
								InterfazProveedor ip=new InterfazProveedor();
								ip.setLocationRelativeTo(null);
								ip.setVisible(true);
							}
						}
						if(!entra) {
							JOptionPane.showMessageDialog(InterfazLogin.this, "Usuario o contraseÃ±a erroneos","Error",JOptionPane.ERROR_MESSAGE);
							Nombretext.setText("");
							Id_Usuariotext.setText("");
						}
							
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		Cancelar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				JOptionPane.showMessageDialog(InterfazLogin.this, "Cerrando Programa");
			}
		});
		
		Registrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				InterfazRegistrar ir=new InterfazRegistrar();
			}
		});
		
		setVisible(true);
	}
}
