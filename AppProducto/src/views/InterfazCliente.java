package views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class InterfazCliente extends JFrame {

	private JPanel contentPane;
	private JButton botonEditar,botonHistorial,botonBuscar,botonCerrar;
	private ImageIcon iHistorial,iEditar,iBuscar;
	
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
		
		iEditar=new ImageIcon("Icon/EditarCliente.png");
		
		botonEditar = new JButton("Editar Perfil");
		botonEditar.setBounds(237, 93, 120, 37);
		botonEditar.addActionListener(ma);
		botonEditar.setIcon(iEditar);
		botonEditar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		contentPane.add(botonEditar);
		
		iHistorial=new ImageIcon("Icon/PedidosClientes.png");
		
		botonHistorial = new JButton("Ver Historial");
		botonHistorial.setBounds(46, 92, 120, 39);
		botonHistorial.addActionListener(ma);
		botonHistorial.setIcon(iHistorial);
		botonHistorial.setFont(new Font("Tahoma", Font.PLAIN, 10));
		contentPane.add(botonHistorial);
		
		iBuscar=new ImageIcon("Icon/BuscarCliente.png");
		
		botonBuscar = new JButton("Buscar Producto");
		botonBuscar.setBounds(129, 25, 138, 39);
		botonBuscar.addActionListener(ma);
		botonBuscar.setIcon(iBuscar);
		botonBuscar.setFont(new Font("Tahoma", Font.PLAIN, 10));
		contentPane.add(botonBuscar);
		
		botonCerrar = new JButton("Cerrar Sesion");
		botonCerrar.setFont(new Font("Tahoma", Font.PLAIN, 8));
		botonCerrar.setBounds(153, 152, 85, 21);
		botonCerrar.addActionListener(ma);
		contentPane.add(botonCerrar);
	}
	private class ManejadorA implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object o=e.getSource();
			if(o==botonEditar) {
				//clase editar cliente
				
				dispose();
			}else if(o==botonHistorial) {
				InterfazClienteHistorial ich;
				try {
					ich = new InterfazClienteHistorial();
					ich.setVisible(true);
					dispose();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}else if(o==botonBuscar) {
				//clase buscar productos
				
				dispose();
			}else if(o==botonCerrar) {
				//clase buscar productos
				InterfazLogin il=new InterfazLogin();
				il.setVisible(true);
				dispose();
			}
		}
		
	}
}

