package views;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import models.Compra;
import models.Producto;
import services.Conexion;
import services.ObjectService;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;

public class InterfazProveedor extends JFrame {

	private JPanel contentPane;
	private JTextArea arealistado;
	private List<Compra> compras=new ArrayList<>();
	private List<Producto> productos=new ArrayList<>();
	private JScrollPane jscrollpane;
	private JTextField txtPedidosTotales;
	private String idadmin;
	private ImageIcon imagenproveedores;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazProveedor frame = new InterfazProveedor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterfazProveedor() {
		super("Interfaz Proveedor");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 611, 302);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		ObjectService os = new ObjectService();
		imagenproveedores = new ImageIcon ("Icon/ListaAdmin.png");
		JButton btnNewButton = new JButton("Visualizar pedidos admin");
		btnNewButton.setIcon(imagenproveedores);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				arealistado.setText("");
				idadmin = JOptionPane.showInputDialog(null, "Introduzca id del admin", "Pregunta",
						JOptionPane.QUESTION_MESSAGE);
				try {
					for (Compra c : os.getAllCompra(Conexion.obtener())) {
						if (c.getId_UsuarioC().equalsIgnoreCase(idadmin)) {
							compras.add(c);
						}
					}
					for(Producto p:os.getAllProducts(Conexion.obtener())) {
						if(p.getId_Proveedor().equals(InterfazLogin.idproveedorreg)) {
							productos.add(p);
						}
					}
					for(Compra o: compras) {
						for(Producto pr: productos) {
							if(o.getId_Producto().equals(pr.getId_Producto())) {
								arealistado.setText(" Fecha pedido --> "+o.getFecha()+"\n"
							+" Hora Pedido --> "+o.getHora()+"\n"+" Nombre Producto --> "+pr.getNombre()+"\n"+" Precio Producto --> "
							+pr.getPrecio()+"\n"+" Cantidad solicitada -->"+o.getCantidad_pedida()+"\n"+" Precio Total --> "+(o.getCantidad_pedida()*pr.getPrecio())+"€"+"\n"
							+"--------------------------------------"+"\n"+arealistado.getText());
							}
						}
					}
					productos.clear();
					compras.clear();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		jscrollpane= new JScrollPane();
		jscrollpane.setBounds(5, 5, 100, 100);
		getContentPane().add(jscrollpane);
		
		txtPedidosTotales = new JTextField();
		txtPedidosTotales.setText("Pedidos Totales:");
		txtPedidosTotales.setEditable(false);
		txtPedidosTotales.setColumns(10);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(34, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(txtPedidosTotales, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(jscrollpane, GroupLayout.PREFERRED_SIZE, 521, GroupLayout.PREFERRED_SIZE))
					.addGap(32))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(163)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(164, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
					.addComponent(txtPedidosTotales, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jscrollpane, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE))
		);
		
				arealistado=new JTextArea();
				jscrollpane.setViewportView(arealistado);
		contentPane.setLayout(gl_contentPane);
	}
}
