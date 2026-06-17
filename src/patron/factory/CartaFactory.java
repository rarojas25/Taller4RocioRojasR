package patron.factory;

import modelo.Carta;

public interface CartaFactory {
	Carta crearCarta(String[] partes);
}
