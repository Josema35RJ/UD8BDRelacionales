package models;

public class Usuario {
	private String Id_Usuario,Nombre,Direccion;
	private boolean Es_Admin;
	
	public Usuario() {
		super();
	}

	public Usuario(String id_Usuario, String nombre, String direccion, boolean es_Admin) {
		super();
		Id_Usuario = id_Usuario;
		Nombre = nombre;
		Direccion = direccion;
		Es_Admin = es_Admin;
	}

	public String getId_Usuario() {
		return Id_Usuario;
	}

	public void setId_Usuario(String id_Usuario) {
		Id_Usuario = id_Usuario;
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

	public boolean isEs_Admin() {
		return Es_Admin;
	}

	public void setEs_Admin(boolean es_Admin) {
		Es_Admin = es_Admin;
	}

	@Override
	public String toString() {
		return "Usuario [Id_Usuario=" + Id_Usuario + ", Nombre=" + Nombre + ", Direccion=" + Direccion + ", Es_Admin="
				+ Es_Admin + "]";
	}
	
	
}
