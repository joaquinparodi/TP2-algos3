package factories;

import atributos.Estrellas;
import atributos.Puntos;
import cartas.CabezaExodia;
import jugabilidad.Jugador;

public class CreadorDeCabezaExodia extends CreadorDeCartas {
	
	public CreadorDeCabezaExodia(Jugador unJugador) {
		super(unJugador);
	}

	public void crearCarta() {
		carta = new CabezaExodia("Cabeza Exodia", jugador, new Estrellas(3), new Puntos(450,600));
	}
}
