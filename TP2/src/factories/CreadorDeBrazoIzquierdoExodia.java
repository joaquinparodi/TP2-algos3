package factories;

import atributos.Estrellas;
import atributos.Puntos;
import cartas.BrazoIzquierdoExodia;
import jugabilidad.Jugador;

public class CreadorDeBrazoIzquierdoExodia extends CreadorDeCartas {
	
	public CreadorDeBrazoIzquierdoExodia(Jugador unJugador) {
		super(unJugador);
	}

	public void crearCarta() {
		carta = new BrazoIzquierdoExodia("Brazo Izquierdo Exodia", jugador, new Estrellas(3), new Puntos(450,600));
	}
}
