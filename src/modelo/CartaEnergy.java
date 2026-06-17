package modelo;

public class CartaEnergy extends Carta {
	private String elemento;

	public CartaEnergy(String nombre, int rareza, String elemento) {
		super(nombre, rareza);
		this.elemento = elemento;
	}

	public String getElemento() {
		return elemento;
	}

	public void setElemento(String elemento) {
		this.elemento = elemento;
	}
	
	@Override
	public String getTipo() {
		return "Energy";
	}
	@Override
	public double calcularPoder() {
		return 1.0;
	}
	@Override
	public String toFileLine() {
		return getNombre() + ";" + getRareza() + ";Energy;" + elemento;
	}
	@Override
	public double accept(CartaVisitor visitor) {
		return visitor.visitEnergy(this);
	}

}
