package views;



import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import models.Usuario;
import services.Conexion;
import services.ObjectService;

public class InterfazRegistrar extends JFrame {

	private JPanel contentPane;
	private JTextField textReg;
	private JTextField txtIntroduceNombre;
	private JTextField txtIntroduceDireccion;
	private JTextField txtIntroduceContrasea_1;
	private JButton btnRegistrar;
	private JTextField textNombre;
	private JTextField textDireccion;
	private JTextField textContra;
	private ImageIcon imagenvolver;
	private ImageIcon imagenregistrar;

	public InterfazRegistrar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 274);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		textReg = new JTextField();
		textReg.setHorizontalAlignment(SwingConstants.CENTER);
		textReg.setEditable(false);
		textReg.setFont(new Font("Wide Latin", Font.PLAIN, 21));
		textReg.setText("REGISTRATE");
		textReg.setColumns(10);

		txtIntroduceNombre = new JTextField();
		txtIntroduceNombre.setText("Introduce nombre:");
		txtIntroduceNombre.setEditable(false);
		txtIntroduceNombre.setColumns(10);

		txtIntroduceDireccion = new JTextField();
		txtIntroduceDireccion.setHorizontalAlignment(SwingConstants.RIGHT);
		txtIntroduceDireccion.setText("Introduce dirección:");
		txtIntroduceDireccion.setEditable(false);
		txtIntroduceDireccion.setColumns(10);

		txtIntroduceContrasea_1 = new JTextField();
		txtIntroduceContrasea_1.setHorizontalAlignment(SwingConstants.RIGHT);
		txtIntroduceContrasea_1.setText("Introduce contraseña:");
		txtIntroduceContrasea_1.setEditable(false);
		txtIntroduceContrasea_1.setColumns(10);

		imagenvolver = new ImageIcon("Icon/Volver.png");
		imagenregistrar = new ImageIcon("Icon/InsertarProveedor.png");

		textNombre = new JTextField();
		textNombre.setColumns(10);

		textDireccion = new JTextField();
		textDireccion.setColumns(10);

		textContra = new JTextField();
		textContra.setColumns(10);
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setIcon(imagenregistrar);
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ObjectService os = new ObjectService();
				try {
					if (textNombre.getText().equals("") || textDireccion.getText().equals("")|| textContra.getText().equals("")) {
						JOptionPane.showMessageDialog(InterfazRegistrar.this, "NO PUEDE HABER VALORES NULOS", "CUIDADO",
								JOptionPane.ERROR_MESSAGE);
					}else {
						byte contNumero = 0, contLetraMay = 0, contLetraMin = 0;
						char clave;
						for (byte i = 0; i < textContra.getText().length(); i++) {
							clave = textContra.getText().charAt(i);
							String passValue = String.valueOf(clave);
							if (passValue.matches("[A-Z]")) {
								contLetraMay++;
							} else if (passValue.matches("[a-z]")) {
								contLetraMin++;
							} else if (passValue.matches("[0-9]")) {
								contNumero++;
							}
						}
						boolean nombre=true;
						for (Usuario x : os.getAllUsuarios(Conexion.obtener())) {
							if(textNombre.getText().equals(x.getNombre())) {
								nombre=false;
							}
						}
						if(nombre==false) {
							JOptionPane.showMessageDialog(InterfazRegistrar.this, "Este nombre NO está disponible");
						}else if(contNumero > 0 && contLetraMay > 0 && contLetraMin > 0) {
							int id=Integer.valueOf(ultimousuario()) + 1;
							String stringid=String.valueOf(id);
							Usuario user = new Usuario(stringid,textNombre.getText(),
									textDireccion.getText(), false, false, textContra.getText());
							os.saveUsuario(Conexion.obtener(), user, 0);
							JOptionPane.showMessageDialog(InterfazRegistrar.this, "Usuario añadido correctamente");
							dispose();
							InterfazLogin il = new InterfazLogin();
						} else 
							JOptionPane.showMessageDialog(InterfazRegistrar.this,
									"LA CONTRASEÑA DEBE CONTENER NÚMEROS, MAYÚSCULAS Y MINÚSCULAS", "CUIDADO",
									JOptionPane.ERROR_MESSAGE);
					}
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtIntroduceNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtIntroduceDireccion, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
						.addComponent(txtIntroduceContrasea_1, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(textContra, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(textDireccion, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
							.addComponent(textNombre, GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)))
					.addGap(60))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(72)
					.addComponent(textReg, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(80, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(140)
					.addComponent(btnRegistrar, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(148, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(textReg, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtIntroduceNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtIntroduceDireccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textDireccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtIntroduceContrasea_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textContra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnRegistrar, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(15))
		);
		contentPane.setLayout(gl_contentPane);
		setVisible(true);
	}

	private int ultimousuario() {
		ObjectService os = new ObjectService();
		Usuario u = null;
		int idmayor=0;
		try {
			for (Usuario x : os.getAllUsuarios(Conexion.obtener())) {
				u = x;
				if(Integer.valueOf(u.getId_Usuario())>idmayor) {
					idmayor=Integer.valueOf(u.getId_Usuario());
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idmayor;
	}

}
