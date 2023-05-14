package models;

public class Usuario {
	private String Id_Usuario,Nombre,Direccion;
	private boolean Es_Admin, Activo;
	private String contrasena;
	
	public Usuario() {
		super();
	}

	public Usuario(String id_Usuario, String nombre, String direccion, boolean es_Admin, boolean Activo, String contrasena) {
		super();
		Id_Usuario = id_Usuario;
		Nombre = nombre;
		Direccion = direccion;
		Es_Admin = es_Admin;
		this.Activo = Activo;
		this.contrasena = contrasena;
	}

	public boolean isActivo() {
		return Activo;
	}

	public void setActivo(boolean activo) {
		Activo = activo;
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

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	@Override
	public String toString() {
		return "Usuario [Id_Usuario=" + Id_Usuario + ", Nombre=" + Nombre + ", Direccion=" + Direccion + ", Es_Admin="
				+ Es_Admin + ", Activo=" + Activo + ", contrasena=" + contrasena + "]";
	}
}
