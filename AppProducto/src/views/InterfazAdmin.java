package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

public class InterfazAdmin extends JFrame {
	
	private JButton Productos, Proveedores, Clientes, Pedidos, Compras, CerrarSesion, Salir;
	private ImageIcon Clienteimagen, ProveedorImagen, ProductoImagen, PedidosImagen, ComprasImagen, SalirImagen, VolverImagen;

	public InterfazAdmin () {
		super("Menu Admin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,300);
		
		Productos = new JButton ("Productos");
		Proveedores = new JButton ("Proveedores");
	    Clientes = new JButton ("Clientes");
		Pedidos = new JButton ("Pedidos");
		Compras = new JButton ("Compras");
		CerrarSesion = new JButton ("Cerrar Sesion");
		Salir = new JButton ("Salir");
		Clienteimagen = new ImageIcon ("Icon/Cliente.png");
		Clientes.setIcon(Clienteimagen);
		ProveedorImagen = new ImageIcon ("Icon/Proveedor.png");
		Proveedores.setIcon(ProveedorImagen);
		ProductoImagen = new ImageIcon ("Icon/Productos.png");
		Productos.setIcon(ProductoImagen);
		PedidosImagen = new ImageIcon ("Icon/Pedidos.png");
		Pedidos.setIcon(PedidosImagen);
		ComprasImagen = new ImageIcon ("Icon/Compras.png");
		Compras.setIcon(ComprasImagen);
		SalirImagen = new ImageIcon ("Icon/Salir.png");
		Salir.setIcon(SalirImagen);
		VolverImagen = new ImageIcon ("Icon/Volver.png");
		CerrarSesion.setIcon(VolverImagen);
		getContentPane().add(Pedidos);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(60)
					.addComponent(CerrarSesion, GroupLayout.PREFERRED_SIZE, 172, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 161, Short.MAX_VALUE)
					.addComponent(Salir, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
					.addGap(59))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addComponent(Productos, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(Compras, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(Proveedores, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
					.addGap(21))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(Pedidos, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(Clientes, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
					.addGap(107))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(Proveedores, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
						.addComponent(Compras, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
						.addComponent(Productos, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(Clientes, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
						.addComponent(Pedidos, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(CerrarSesion, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(Salir, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)))
		);
		getContentPane().setLayout(groupLayout);
		
		Productos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				InterfazProductosAdmin ipa = new InterfazProductosAdmin();
				ipa.setLocationRelativeTo(null);
				ipa.setVisible(true);
			}
		});
		
		Proveedores.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				InterfazProveedoresAdmin ipa = new InterfazProveedoresAdmin();
				ipa.setLocationRelativeTo(null);
				ipa.setVisible(true);
			}
		});
		
		Clientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				InterfazClientesAdmin ica = new InterfazClientesAdmin();
				ica.setLocationRelativeTo(null);
				ica.setVisible(true);
			}
		});
		
		Compras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				InterfazVerPedidos ivp = new InterfazVerPedidos();
				ivp.setLocationRelativeTo(null);
				ivp.setVisible(true);
			}
		});
		
		CerrarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(JOptionPane.showConfirmDialog(InterfazAdmin.this, "Quieres Cerrrar Sesion?")==0) {
				InterfazLogin il=new InterfazLogin();
				il.setVisible(true);
				dispose();
				}
			}
		});
		
		Salir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int op =JOptionPane.showConfirmDialog(InterfazAdmin.this, "Seguro Quiere Cerrar Ventana", "Cerrar Ventana Admin", JOptionPane.CANCEL_OPTION);
			if(op==0) {
				System.exit(0);
			}
			}
		});
		
	}
}
