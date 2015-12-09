package front;

public class Oferta {
	public enum Estado {
		ACEPTADA,GANADADORA,RECHAZADA,ERROR
	}
	private Estado estado;
	private int monto;
	private SubastaClientHandle handle;
	public Oferta(final int monto, final SubastaClientHandle handle) {
		this.monto = monto;
		this.handle=handle;
	}
	public int getMonto() {
		return monto;
	}
	public void setMonto(int monto) {
		this.monto = monto;
	}
	public SubastaClientHandle getHandle() {
		return handle;
	}
	public void setHandle(SubastaClientHandle handle) {
		this.handle = handle;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
}
