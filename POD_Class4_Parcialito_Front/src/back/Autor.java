package back;

import java.io.Serializable;

public final class Autor implements Serializable{	
	private final String nombre;
	private final String apellido;
	public Autor(final String nombre,final String apellido) {
		this.nombre=nombre;
		this.apellido=apellido;
	}
	public String getNombre() {
		return nombre;
	}
	public String getApellido() {
		return apellido;
	}
}
