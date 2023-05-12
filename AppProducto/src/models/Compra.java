package models;

import java.sql.Time;
import java.sql.Date;

public class Compra {
	private String Id_Compra;
	private Date fecha;
	private Time hora;
	private String Id_UsuarioC,Id_Producto;
	
	public Compra() {
		super();
	}

	public Compra(String id_Compra, Date fecha, Time hora, String id_UsuarioC, String id_Producto) {
		super();
		Id_Compra = id_Compra;
		this.fecha = fecha;
		this.hora = hora;
		Id_UsuarioC = id_UsuarioC;
		Id_Producto = id_Producto;
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

	@Override
	public String toString() {
		return "Compra [Id_Compra=" + Id_Compra + ", fecha=" + fecha + ", hora=" + hora + ", Id_UsuarioC=" + Id_UsuarioC
				+ ", Id_Producto=" + Id_Producto + "]";
	}
	
}
