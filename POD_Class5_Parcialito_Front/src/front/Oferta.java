package front;

public class Oferta {
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
}
