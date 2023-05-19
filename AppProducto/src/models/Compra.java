package models;

import java.sql.Time;
import java.sql.Date;

public class Compra {
	private String Id_Compra;
	private Date fecha;
	private Time hora;
	private String Id_UsuarioC,Id_Producto;
	private int Cantidad_pedida;
	private double Precio_Total;
	
	public Compra() {
		super();
	}
	
	public Compra(String id_Compra, Date fecha, Time hora, String id_UsuarioC, String id_Producto,
			int cantidad_pedida, double Precio_Total) {
		super();
		Id_Compra = id_Compra;
		this.fecha = fecha;
		this.hora = hora;
		Id_UsuarioC = id_UsuarioC;
		Id_Producto = id_Producto;
		Cantidad_pedida = cantidad_pedida;
		this.Precio_Total = Precio_Total;
	}

	public String getId_Compra() {
		return Id_Compra;
	}

	public void setId_Compra(String id_Compra) {
		Id_Compra = id_Compra;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public String getId_UsuarioC() {
		return Id_UsuarioC;
	}

	public void setId_UsuarioC(String id_UsuarioC) {
		Id_UsuarioC = id_UsuarioC;
	}

	public String getId_Producto() {
		return Id_Producto;
	}

	public void setId_Producto(String id_Producto) {
		Id_Producto = id_Producto;
	}
	

	public int getCantidad_pedida() {
		return Cantidad_pedida;
	}

	public void setCantidad_pedida(int cantidad_pedida) {
		Cantidad_pedida = cantidad_pedida;
	}
	
	public double getPrecio_Total() {
		return Precio_Total;
	}

	public void setPrecio_Total(double precio_Total) {
		Precio_Total = precio_Total;
	}


	@Override
	public String toString() {
		return "Compra [Id_Compra=" + Id_Compra + ", fecha=" + fecha + ", hora=" + hora + ", Id_UsuarioC=" + Id_UsuarioC
				+ ", Id_Producto=" + Id_Producto + ", Cantidad_pedida=" + Cantidad_pedida + ", Precio_Total="
				+ Precio_Total + "]";
	}
	
}
