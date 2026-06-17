package patron.strategy;

import java.util.List;
import modelo.Carta;

public interface OrdenStrategy {
	
	void ordenar(List<Carta> cartas);
	String getNombre();
}
