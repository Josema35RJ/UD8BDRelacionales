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

import models.Producto;
import services.Conexion;
import services.ObjectService;

public class graficocircularproductos {

	private static ObjectService os=new ObjectService();
	private static List<String> listadecategorias = new ArrayList<>();

	public static void mostrargrafico() throws ClassNotFoundException, SQLException {
		DefaultPieDataset data = new DefaultPieDataset();
		HashMap<String,Integer> mapa=new HashMap<>();
		for(Producto p: os.getAllProducts(Conexion.obtener())) {
			if(mapa.containsKey(p.getCategoria())) 
				mapa.replace(p.getCategoria(), mapa.get(p.getCategoria())+1);
			else
				mapa.put(p.getCategoria(),1);
		}
		
		Iterator<String> it=mapa.keySet().iterator();
		while(it.hasNext()) {
			String key=it.next();
			data.setValue(key, mapa.get(key));
		}
		
		JFreeChart chart = ChartFactory.createPieChart("Productos x Categoría", data, true, true, false);
		ChartFrame frame=new ChartFrame("Gráfico Productos",chart);
		frame.pack();
		frame.setSize(600, 500);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
	}
}
