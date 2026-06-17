package patron.strategy;

import java.util.Comparator;
import java.util.List;
import modelo.Carta;

public class OrdenPorNombre implements OrdenStrategy {
	
	@Override
	public void ordenar(List<Carta> cartas) {
		cartas.sort(new Comparator<Carta>() {
			@Override
			public int compare(Carta carta1, Carta carta2) {
				return carta1.getNombre().compareToIgnoreCase(carta2.getNombre());
			}
		});
	}
	
	@Override
	public String getNombre() {
		return "Por Nombre";
	}
}
