package back;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Libro implements Serializable{

	private final String ISBN;
	private final String titulo;
	private final LocalDate fechaDePublicacion;
	private final Autor autor;

	public Libro(final String ISBN,final String titulo, final String fechaDePublicacion, final String apellidoAutor, final String nombreAutor) {
		this.ISBN=ISBN;
		this.titulo=titulo;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		this.fechaDePublicacion=LocalDate.parse(fechaDePublicacion, formatter);
		this.autor= new Autor(nombreAutor,apellidoAutor);

	}

	public String getISBN() {
		return ISBN;
	}

	public String getTitulo() {
		return titulo;
	}

	public LocalDate getFechaDePublicacion() {
		return fechaDePublicacion;
	}

	public Autor getAutor() {
		return autor;
	}
	@Override
	public String toString() {
		return ISBN + " "+ titulo;
	}
}
