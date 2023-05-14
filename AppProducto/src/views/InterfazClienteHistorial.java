package views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import models.Compra;
import services.Conexion;
import services.ObjectService;

public class InterfazClienteHistorial extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	private static DefaultTableModel model;
	private static ObjectService oc=new ObjectService();

	public InterfazClienteHistorial() throws ClassNotFoundException, SQLException {
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
		
		scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(56, Short.MAX_VALUE))
		);

		table = new JTable();
		model = new DefaultTableModel();
		table.setModel(model);

		scrollPane.setViewportView(table);
		getContentPane().setLayout(groupLayout);

		model.addColumn("Id_Compra");
		model.addColumn("Fecha");
		model.addColumn("Hora");
		model.addColumn("Id_Producto");
		model.addColumn("Cantidad_pedida");
		
		table.setEnabled(false);
		
		EscribirTabla();
		setVisible(true);

	}
	private static void EscribirTabla() throws ClassNotFoundException, SQLException {
        for (Compra c :oc.getAllCompra(Conexion.obtener())) {
        	if(InterfazLogin.User.getId_Usuario().equals(c.getId_UsuarioC())) {
	            Object[] Fila = new Object[model.getColumnCount()];
	            Fila[0] = c.getId_Compra();
	            Fila[1] = c.getFecha();
	            Fila[2] = c.getHora();
	            Fila[3] = c.getId_Producto();
	            Fila[4] = c.getCantidad_pedida();
	            model.addRow(Fila);
        	}
        }
    }

}
