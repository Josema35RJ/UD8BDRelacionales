package views;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import models.Compra;
import models.Producto;
import services.Conexion;
import services.ObjectService;

public class graficocircularcompras {

	private static ObjectService os=new ObjectService();
	private static List<String> listadecategorias = new ArrayList<>();

	public static void mostrargrafico() throws ClassNotFoundException, SQLException {
		DefaultPieDataset data = new DefaultPieDataset();
		HashMap<String,Integer> mapa=new HashMap<>();
		for(Compra p: os.getAllCompra(Conexion.obtener())) {
			for(Producto x: os.getAllProducts(Conexion.obtener())) {
				if(p.getId_Producto().equals(x.getId_Producto())) {
					if(mapa.containsKey(x.getCategoria())) 
						mapa.replace(x.getCategoria(), mapa.get(x.getCategoria())+1);
					else
						mapa.put(x.getCategoria(),1);
				}
			}
			
		}
		
		Iterator<String> it=mapa.keySet().iterator();
		while(it.hasNext()) {
			String key=it.next();
			data.setValue(key, mapa.get(key));
		}
		
		JFreeChart chart = ChartFactory.createPieChart("Categorías que más venden", data, true, true, false);
		ChartFrame frame=new ChartFrame("Gráfico Categorías",chart);
		frame.pack();
		frame.setSize(600, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
}
