package modelo;

public abstract class Carta {
	private String nombre;
	private int rareza;
	
	public Carta(String nombre, int rareza) {
		super();
		this.nombre = nombre;
		this.rareza = rareza;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getRareza() {
		return rareza;
	}

	public void setRareza(int rareza) {
		this.rareza = rareza;
	}
	public abstract String getTipo();
	
	public abstract double calcularPoder();
	
	public String getRutaImagen() {
		return "resources/images/" + nombre + ".png";
	}
	
	public abstract String toFileLine();
	
	public abstract double accept(CartaVisitor visitor);
	
	@Override
	public String toString() {
		return nombre + "[R:" + rareza + " | " + getTipo() + " | Poder: " + String.format("%.2f", calcularPoder()) + "]";
	}
}
