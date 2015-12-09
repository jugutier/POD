package front;


public final class Subasta {
	//	 Un título: que funciona como identificador de la misma
	//	 ● Una oferta mínima (M​inimoEsperado)​: este es el mínimo valor que se cree que
	//	 vale el objeto de la subasta.
	//	 ● Una oferta esperada (V​alorEsperado)​: este el valor que se cree que vale el objeto
	//	 de la subasta. La subasta del objeto sigue abierta hasta que se logre igualar o superar este valor. Debe ser superior a la oferta mínima
	private final String titulo;
	private final Double minimoEsperado;
	private final Double valorEsperado;
	private boolean open;
	private Oferta mejorOferta;
	public Subasta(final String titulo, final Double minimoEsperado, final Double valorEsperado) {
		if(valorEsperado<minimoEsperado){
			throw new IllegalStateException("el valor esperado debe superar el minimo.");
		}
		this.titulo=titulo;
		this.minimoEsperado=minimoEsperado;
		this.valorEsperado=valorEsperado;
		this.open=true;
	}
	public String getTitulo() {
		return titulo;
	}
	public Double getMinimoEsperado() {
		return minimoEsperado;
	}
	public Double getValorEsperado() {
		return valorEsperado;
	}
	public boolean isOpen() {
		return open;
	}
	public Oferta getMejorOferta() {
		return mejorOferta;
	}
	public void setMejorOferta(Oferta mejorOferta) {
		this.mejorOferta = mejorOferta;
	}
	public void close() {
		open = false;		
	}
}
