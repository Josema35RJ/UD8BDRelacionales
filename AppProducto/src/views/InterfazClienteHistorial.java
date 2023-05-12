package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InterfazClienteHistorial extends JFrame {

	private JPanel contentPane;

	public InterfazClienteHistorial() {
		setTitle("Historial");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton botonVolver = new JButton("Volver a opciones");
		botonVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==botonVolver) {
					InterfazCliente ic=new InterfazCliente();
					ic.setVisible(true);
					dispose();
				}
			}
		});
		botonVolver.setFont(new Font("Tahoma", Font.PLAIN, 8));
		botonVolver.setBounds(157, 232, 110, 21);
		contentPane.add(botonVolver);
	}

}
