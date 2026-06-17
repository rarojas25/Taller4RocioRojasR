package patron.factory;

import modelo.Carta;
import modelo.CartaEnergy;
import modelo.CartaItem;
import modelo.CartaPokemon;
import modelo.CartaSupporter;

public class CartaFactoryImpl implements CartaFactory{

	@Override
	public Carta crearCarta(String[] partes) {
		if(partes == null || partes.length < 3) {
			throw new IllegalArgumentException("Formato de carta invalido: faltan campos.");
		}
		String nombre = partes[0].trim();
		int rareza;
		try {
			rareza = Integer.parseInt(partes[1].trim());
		}catch (NumberFormatException e) {
			throw new IllegalArgumentException("Rareza invalida: " + partes[1]);
		}
		String tipo = partes[2].trim();
		
		if(tipo.equals("Pokemon")){
			if(partes.length < 5) throw new IllegalArgumentException("Datos insufientes para Pokemon.");
			int danio = Integer.parseInt(partes[3].trim());
			int cantEnergias = Integer.parseInt(partes[4].trim());
			return new CartaPokemon(nombre, rareza, danio, cantEnergias);
		
		}else if(tipo.equals("Item")) {
			if(partes.length < 4) throw new IllegalArgumentException("Datos insuficientes para Item.");
			int bonificacion = Integer.parseInt(partes[3].trim());
			return new CartaItem(nombre, rareza, bonificacion);
		
		}else if(tipo.equals("Supporter")) {
			if(partes.length < 4) throw new IllegalArgumentException("Datos insuficientes para Supporter.");
			int efectos = Integer.parseInt(partes[3].trim());
			return new CartaSupporter(nombre, rareza, efectos);
		
		}else if(tipo.equals("Energy")) {
			if(partes.length < 4) throw new IllegalArgumentException("Datos insuficientes para Energy.");
			String elemento = partes[3].trim();
			return new CartaEnergy(nombre, rareza, elemento);
		
		}else {
			throw new IllegalArgumentException("Tipo de carta desconocido: " + tipo);
		}
	}
}
