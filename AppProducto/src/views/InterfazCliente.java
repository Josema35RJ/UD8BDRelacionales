package views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class InterfazCliente extends JFrame {

	private JPanel contentPane;
	private JButton botonEditar,botonHistorial,botonBuscar;
	
	public InterfazCliente() {
		setTitle("Opciones Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(420,220);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		ManejadorA ma=new ManejadorA();
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		botonEditar = new JButton("Editar Perfil");
		botonEditar.setBounds(239, 109, 120, 37);
		botonEditar.addActionListener(ma);
		contentPane.add(botonEditar);
		
		botonHistorial = new JButton("Editar Historial");
		botonHistorial.setBounds(44, 108, 120, 39);
		botonHistorial.addActionListener(ma);
		contentPane.add(botonHistorial);
		
		botonBuscar = new JButton("Buscar Producto");
		botonBuscar.setBounds(143, 43, 120, 39);
		botonBuscar.addActionListener(ma);
		contentPane.add(botonBuscar);
	}
	private class ManejadorA implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object o=e.getSource();
			if(o==botonEditar) {
				//clase editar cliente
				
				dispose();
			}else if(o==botonHistorial) {
				//clase ver historial
				
				dispose();
			}else if(o==botonBuscar) {
				//clase buscar productos
				
				dispose();
			}
		}
		
	}
}

