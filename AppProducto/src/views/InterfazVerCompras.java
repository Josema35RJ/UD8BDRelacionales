package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import models.Compra;
import models.Producto;
import models.Proveedor;
import services.Conexion;
import services.ObjectService;

public class InterfazVerCompras extends JFrame {

	private static ObjectService os = new ObjectService();
	private JTable table;
	private JButton Atras;
	private static JtableBloquearCeldasListadoPedidosAdmin model;
	private static InterfazLogin il;
	private ImageIcon AtrasProveedores;

	public InterfazVerCompras() {
		super("Listado Compras");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(784, 386);
		setLocationRelativeTo(null);

		JScrollPane scrollPane = new JScrollPane();
		
		table = new JTable();
		model = new JtableBloquearCeldasListadoPedidosAdmin();
		table.setModel(model);

		scrollPane.setViewportView(table);

		model.addColumn("Id_Compra");
		model.addColumn("Fecha");
		model.addColumn("Hora");
		model.addColumn("Id_UsuarioC");
		model.addColumn("Id_Producto");
		model.addColumn("Precio");
		model.addColumn("Cantidad_pedida");
		
		Atras = new JButton("Atras");
		AtrasProveedores = new ImageIcon("Icon/Volver2.png");
		Atras.setIcon(AtrasProveedores);

         getContentPane().add(Atras);
         GroupLayout groupLayout = new GroupLayout(getContentPane());
         groupLayout.setHorizontalGroup(
         	groupLayout.createParallelGroup(Alignment.LEADING)
         		.addGroup(groupLayout.createSequentialGroup()
         			.addContainerGap(27, Short.MAX_VALUE)
         			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
         				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
         					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 731, GroupLayout.PREFERRED_SIZE)
         					.addContainerGap())
         				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
         					.addComponent(Atras, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
         					.addGap(55))))
         );
         groupLayout.setVerticalGroup(
         	groupLayout.createParallelGroup(Alignment.TRAILING)
         		.addGroup(groupLayout.createSequentialGroup()
         			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
         			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE)
         			.addPreferredGap(ComponentPlacement.UNRELATED)
         			.addComponent(Atras, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
         			.addGap(68))
         );
         
         getContentPane().setLayout(groupLayout);
         
         Atras.addActionListener(new ActionListener () {
 			@Override
 			public void actionPerformed(ActionEvent e) {
 				// TODO Auto-generated method stub
 				dispose();
 			}
          });
         
		try {
			EscribirTabla();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void EscribirTabla() throws ClassNotFoundException, SQLException {
		for (Compra c : os.getAllCompra(Conexion.obtener())) {
			if(InterfazClientesAdmin.id.equals(c.getId_UsuarioC())) {
			Object[] Fila = new Object[model.getColumnCount()];
			Fila[0] = c.getId_Compra();
			Fila[1] =c.getFecha();
			Fila[2] = c.getHora();
			Fila[3] = c.getId_UsuarioC();
			Fila[4] = c.getId_Producto();
			Fila[5] = c.getPrecio_Total();
			Fila[6] = c.getCantidad_pedida();
			model.addRow(Fila);
			}
		}
	}
}