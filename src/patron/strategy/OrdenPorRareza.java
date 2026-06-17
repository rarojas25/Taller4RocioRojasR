package patron.strategy;

import java.util.Comparator;
import java.util.List;
import modelo.Carta;

public class OrdenPorRareza implements OrdenStrategy {
	
	@Override
	public void ordenar(List<Carta> cartas) {
		cartas.sort(new Comparator<Carta>() {
			@Override
			public int compare(Carta carta1, Carta carta2) {
				return Integer.compare(carta2.getRareza(), carta1.getRareza());
			}
		});
	}
	
	@Override
	public String getNombre() {
		return "Por Rareza";
	}
}
