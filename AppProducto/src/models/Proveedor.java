package models;

public class Proveedor {
	private String Id_Proveedor,Nombre,Direccion;

	public Proveedor() {
		super();
	}

	public Proveedor(String id_Proveedor, String nombre, String direccion) {
		super();
		Id_Proveedor = id_Proveedor;
		Nombre = nombre;
		Direccion = direccion;
	}

	public String getId_Proveedor() {
		return Id_Proveedor;
	}

	public void setId_Proveedor(String id_Proveedor) {
		Id_Proveedor = id_Proveedor;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}

	@Override
	public String toString() {
		return "Proveedor [Id_Proveedor=" + Id_Proveedor + ", Nombre=" + Nombre + ", Direccion=" + Direccion + "]";
	}
	
	
}
