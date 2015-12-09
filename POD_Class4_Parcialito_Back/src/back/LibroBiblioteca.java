package back;


public class LibroBiblioteca {
	private int stock;
	private final int total;
	private final Libro libro;
	public LibroBiblioteca(String[] libroData) {
		stock = Integer.valueOf(libroData[0]);
		total = stock;
		this.libro = new Libro(libroData[1],libroData[2],libroData[3],libroData[4],libroData[5]);
	}
	public Libro getLibro() {
		return libro;
	}
	public synchronized Libro prestar() {
		if(stock == 0){
			throw new IllegalStateException("No hay stock para este libro");
		}
		this.stock--;
		return libro;
	};
	public synchronized int getStock() {
		return stock;
	}
	public synchronized void devolver() {
		if(stock == total){
			throw new IllegalStateException("El libro no pertenece a esta biblioteca");
		}
		this.stock++;
	}
}
