package models;

public class Producto {

	private String Id_Producto, Nombre, Imagen, Descripcion;
	private float Precio;
	private int Cant_Stock;
	private String Id_Usuario;
	
	public Producto() {
		super();
	}

	public Producto(String id_Producto, String nombre, String imagen, String descripcion, float precio, int cant_Stock,
			String id_Usuario) {
		super();
		Id_Producto = id_Producto;
		Nombre = nombre;
		Imagen = imagen;
		Descripcion = descripcion;
		Precio = precio;
		Cant_Stock = cant_Stock;
		Id_Usuario = id_Usuario;
	}

	public String getId_Producto() {
		return Id_Producto;
	}

	public void setId_Producto(String id_Producto) {
		Id_Producto = id_Producto;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getImagen() {
		return Imagen;
	}

	public void setImagen(String imagen) {
		Imagen = imagen;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public float getPrecio() {
		return Precio;
	}

	public void setPrecio(float precio) {
		Precio = precio;
	}

	public int getCant_Stock() {
		return Cant_Stock;
	}

	public void setCant_Stock(int cant_Stock) {
		Cant_Stock = cant_Stock;
	}

	public String getId_Usuario() {
		return Id_Usuario;
	}

	public void setId_Usuario(String id_Usuario) {
		Id_Usuario = id_Usuario;
	}

	@Override
	public String toString() {
		return "Producto [Id_Producto=" + Id_Producto + ", Nombre=" + Nombre + ", Imagen=" + Imagen + ", Descripcion="
				+ Descripcion + ", Precio=" + Precio + ", Cant_Stock=" + Cant_Stock + ", Id_Usuario=" + Id_Usuario
				+ "]";
	}

	
}
