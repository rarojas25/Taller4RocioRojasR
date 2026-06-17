package modelo;

public class CartaPokemon extends Carta{
	private int danio;
	private int cantEnergias;
	
	public CartaPokemon(String nombre, int rareza, int danio, int cantEnergias) {
		super(nombre, rareza);
		this.danio = danio;
		this.cantEnergias = cantEnergias;
	}

	public int getDanio() {
		return danio;
	}

	public void setDanio(int danio) {
		this.danio = danio;
	}

	public int getCantEnergias() {
		return cantEnergias;
	}

	public void setCantEnergias(int cantEnergias) {
		this.cantEnergias = cantEnergias;
	}
	
	@Override
	public String getTipo() {
		return "Pokemon";
	}

	@Override
	public double calcularPoder() {
		if(cantEnergias == 0) return 0;
		return ((double) danio / cantEnergias) * 100;
	}
	
	@Override
	public String toFileLine() {
		return getNombre() + ";" + getRareza() + ";Pokemon;" + danio + ";" + cantEnergias;
	}
	
	@Override
	public double accept(CartaVisitor visitor) {
		return visitor.visitPokemon(this);
	}
}
