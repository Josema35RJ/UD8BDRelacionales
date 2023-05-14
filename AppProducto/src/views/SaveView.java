package views;

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
import models.Producto;
import models.Proveedor;
import models.Usuario;
import services.Conexion;
import services.ObjectService;

public class SaveView extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtPrecio;
	private final ObjectService services = new ObjectService();
	private final Producto product;
	private final Usuario user;
	private final Proveedor pr;
	/**
	 * Create the frame.
	 */
	
	public SaveView(Producto product, Usuario user) {
		this.product = product;
		this.user = user;
		initComponents();
		txtNombre.setText(this.product.getNombre());
		txtPrecio.setText(String.valueOf(this.product.getPrecio()));
		this.pr = new Proveedor();
	}
	public SaveView() {
		this.product=new Producto();
		this.user=new Usuario();
		this.pr = new Proveedor();
		initComponents();
	}
	
	public void initComponents() {
		setTitle("Product");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 372, 205);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(30, 26, 61, 16);
		contentPane.add(lblNombre);
		
		JLabel lblNewLabel = new JLabel("Precio:");
		lblNewLabel.setBounds(30, 62, 61, 16);
		contentPane.add(lblNewLabel);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(103, 21, 130, 26);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(103, 57, 130, 26);
		contentPane.add(txtPrecio);
		txtPrecio.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = txtNombre.getText();
				Float precio = Float.valueOf(txtPrecio.getText());
				product.setNombre(nombre);
				product.setPrecio(precio);
				try {
					services.saveProducto(Conexion.obtener(), product, user,pr );
					SaveView.this.dispose();
					ListView vista = new ListView();
					vista.setVisible(true);
					vista.setLocationRelativeTo(null);
				} catch (SQLException ex) {
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(SaveView.this, "Ha surgido un error y no se ha podido guardar el registro.");
				} catch (ClassNotFoundException ex) {
					System.out.println(ex);
					JOptionPane.showMessageDialog(SaveView.this, "Ha surgido un error y no se ha podido guardar el registro.");
				}
			}
		});
		btnGuardar.setBounds(46, 123, 117, 29);
		contentPane.add(btnGuardar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ListView vista = new ListView();
				vista.setVisible(true);
				vista.setLocationRelativeTo(null);
			}
		});
		btnCancelar.setBounds(221, 123, 117, 29);
		contentPane.add(btnCancelar);
	}
}
