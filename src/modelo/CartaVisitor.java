package modelo;

public interface CartaVisitor {
		double visitPokemon(CartaPokemon carta);
		double visitItem(CartaItem carta);
		double visitSupporter(CartaSupporter carta);
		double visitEnergy(CartaEnergy carta);
		
}