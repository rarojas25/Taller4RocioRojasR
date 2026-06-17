package modelo;

public class CartaItem extends Carta{
	private int bonificacion;

	public CartaItem(String nombre, int rareza, int bonificacion) {
		super(nombre, rareza);
		this.bonificacion = bonificacion;
	}

	public int getBonificacion() {
		return bonificacion;
	}

	public void setBonificacion(int bonificacion) {
		this.bonificacion = bonificacion;
	}
	
	@Override
	public String getTipo() {
		return "Item";
	}
	
	@Override
	public double calcularPoder() {
		return bonificacion * 20.0;
	}
	
	@Override
	public String toFileLine() {
		return getNombre() + ";" + getRareza() + ";Item;" + bonificacion; 
	}
	
	@Override
	public double accept(CartaVisitor visitor) {
		return visitor.visitItem(this);
	}
}
