package factories;

import atributos.Estrellas;
import atributos.Puntos;
import cartas.PiernaIzquierdaExodia;
import jugabilidad.Jugador;

public class CreadorDePiernaIzquierdaExodia extends CreadorDeCartas {
	
	public CreadorDePiernaIzquierdaExodia(Jugador unJugador) {
		super(unJugador);
	}

	public void crearCarta() {
		carta = new PiernaIzquierdaExodia("Pierna Izquierda Exodia", jugador, new Estrellas(3), new Puntos(450,600));
	}
}
