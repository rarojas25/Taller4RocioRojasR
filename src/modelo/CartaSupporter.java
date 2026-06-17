package modelo;

public class CartaSupporter extends Carta {
	private int efectosPorTurno;

	public CartaSupporter(String nombre, int rareza, int efectosPorTurno) {
		super(nombre, rareza);
		this.efectosPorTurno = efectosPorTurno;
	}

	public int getEfectosPorTurno() {
		return efectosPorTurno;
	}

	public void setEfectosPorTurno(int efectosPorTurno) {
		this.efectosPorTurno = efectosPorTurno;
	}
	
	@Override
	public String getTipo() {
		return "Supporter";
	}
	@Override
	public double calcularPoder() {
		return efectosPorTurno * 50.0;
	}
	@Override
	public String toFileLine() {
		return getNombre() + ";" + getRareza() + ";Supporter;" + efectosPorTurno;
	}
	@Override
	public double accept(CartaVisitor visitor) {
		return visitor.visitSupporter(this);
	}
}