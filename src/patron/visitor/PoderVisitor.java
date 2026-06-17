package patron.visitor;

import modelo.CartaEnergy;
import modelo.CartaItem;
import modelo.CartaPokemon;
import modelo.CartaSupporter;
import modelo.CartaVisitor;

public class PoderVisitor implements CartaVisitor {
	
	@Override
	public double visitPokemon(CartaPokemon carta) {
		
		if(carta.getCantEnergias() == 0)return 0;
		return ((double) carta.getDanio() / carta.getCantEnergias()) * 100;
	}
	@Override
	public double visitItem(CartaItem carta) {
		return carta.getBonificacion() * 20.0;
	}
	@Override
	public double visitSupporter(CartaSupporter carta) {
		return carta.getEfectosPorTurno() * 50.0;
	}
	@Override
	public double visitEnergy(CartaEnergy carta) {
		return 1.0;
	}
}
