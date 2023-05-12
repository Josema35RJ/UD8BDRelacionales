package models;

public class Producto {

	private String idProducto, Nombre, Imagen, Descripcion;
	private float Precio;
	private int CantStock;

	public Producto() {
		super();
	}

	public Producto(String id, String nombre, String imagen, String descripcion, float precio, int cantStock) {
		super();
		idProducto = id;
		Nombre = nombre;
		Imagen = imagen;
		Descripcion = descripcion;
		Precio = precio;
		CantStock = cantStock;
	}

	public String getId() {
		return idProducto;
	}

	public void setId(String id) {
		idProducto = id;
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

	public int getCantStock() {
		return CantStock;
	}

	public void setCantStock(int cantStock) {
		CantStock = cantStock;
	}

	@Override
	public String toString() {
		return "Producto [Id=" + idProducto + ", Nombre=" + Nombre + ", Imagen=" + Imagen + ", Descripcion=" + Descripcion
				+ ", Precio=" + Precio + ", CantStock=" + CantStock + "]";
	}
}
