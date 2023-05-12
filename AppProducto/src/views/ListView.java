package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import models.Producto;
import services.Conexion;
import services.ProductsService;

public class ListView extends JFrame {

	private JPanel contentPane;
	private JTable jtableP;
	private final ProductsService services = new ProductsService();
	private List<Producto> products;

	public ListView() {
		setTitle("Management Products");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListView.this.dispose();
				SaveView vista = new SaveView();
				vista.setVisible(true);
				vista.setLocationRelativeTo(null);
			}
		});
		btnCreate.setBounds(33, 24, 117, 29);
		contentPane.add(btnCreate);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila_seleccionada = jtableP.getSelectedRow();
				if (fila_seleccionada >= 0) {
					ListView.this.dispose();
					SaveView vista = new SaveView(products.get(fila_seleccionada));
					vista.setVisible(true);
					vista.setLocationRelativeTo(null);
				} else {
					JOptionPane.showMessageDialog(ListView.this, "Por favor seleccione una fila.");
				}
			}
		});
		btnUpdate.setBounds(169, 24, 117, 29);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int fila_seleccionada = jtableP.getSelectedRow();
				if (fila_seleccionada >= 0) {
					int decision = JOptionPane.showConfirmDialog(null, "¿Está seguro/a que desea eliminar este producto?",
							"Advertencia", JOptionPane.YES_NO_OPTION);
					if (decision == 0) {
						try {
							services.remove(Conexion.obtener(), products.get(fila_seleccionada));
							showProducts();
						} catch (SQLException ex) {
							System.out.println(ex.getMessage());
							JOptionPane.showMessageDialog(ListView.this, "Ha surgido un error y no se ha podido eliminar el registro.");
						} catch (ClassNotFoundException ex) {
							System.out.println(ex);
							JOptionPane.showMessageDialog(ListView.this, "Ha surgido un error y no se ha podido eliminar el registro.");
						}
					}
				} else {
					JOptionPane.showMessageDialog(ListView.this, "Por favor seleccione una fila.");
				}
			}
		});
		btnDelete.setBounds(308, 24, 117, 29);
		contentPane.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(88, 97, 287, 146);
		contentPane.add(scrollPane);
		
		jtableP = new JTable();
		showProducts();
		scrollPane.setViewportView(jtableP);
		
	}
	
	private void showProducts() {
		try {
			this.products = this.services.getAllProducts(Conexion.obtener());
			jtableP.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

			}, new String[] { "Nombre", "Precio" }));
			DefaultTableModel dtm = (DefaultTableModel) jtableP.getModel();
			dtm.setRowCount(0);
			for (int i = 0; i < this.products.size(); i++) {
				dtm.addRow(new Object[] { this.products.get(i).getNombre(), this.products.get(i).getPrecio()});
			}
	

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			JOptionPane.showMessageDialog(this, "Ha surgido un error y no se han podido recuperar los registros");
		} catch (ClassNotFoundException ex) {
			System.out.println(ex);
			JOptionPane.showMessageDialog(this, "Ha surgido un error y no se han podido recuperar los registros");
		}
	}
}
