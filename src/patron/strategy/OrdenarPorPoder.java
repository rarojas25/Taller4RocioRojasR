package patron.strategy;

import java.util.Comparator;
import java.util.List;
import modelo.Carta;

public class OrdenarPorPoder implements OrdenStrategy{
	
	@Override
	public void ordenar(List<Carta> cartas) {
		cartas.sort(new Comparator<Carta>() {
			@Override
			public int compare(Carta carta1, Carta carta2) {
				return Double.compare(carta2.calcularPoder(), carta1.calcularPoder());
			}
		});
	}
	
	@Override
	public String getNombre() {
		return "Por Poder";
	}
}
