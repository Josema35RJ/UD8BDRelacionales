package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

public class InterfazLogin extends JFrame {
	
	private JLabel Nombre, Id_Usuario;
	private JTextField Nombretext;
	private JPasswordField Id_Usuariotext;
	private JButton Entrar, Cancelar;
	
	public InterfazLogin () {
		super("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(420,220);
		setLocation(null);
		
		Nombre = new JLabel ("Nombre");
		Id_Usuario = new JLabel ("Contrase\u00F1a");
		Nombretext = new JTextField (20);
		Id_Usuariotext = new JPasswordField(20);
		Entrar = new JButton ("Entrar");
		Cancelar = new JButton ("Cancelar");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(121)
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
								.addComponent(Id_Usuariotext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(122, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(46)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(Nombre)
						.addComponent(Nombretext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(Id_Usuario)
						.addComponent(Id_Usuariotext, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(Cancelar)
						.addComponent(Entrar))
					.addGap(28))
		);
		getContentPane().setLayout(groupLayout);
		
		Entrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		setVisible(true);
	}

}
