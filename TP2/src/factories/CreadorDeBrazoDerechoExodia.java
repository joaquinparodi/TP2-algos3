package factories;

import atributos.Estrellas;
import atributos.Puntos;
import cartas.BrazoDerechoExodia;
import jugabilidad.Jugador;

public class CreadorDeBrazoDerechoExodia extends CreadorDeCartas {
	
	public CreadorDeBrazoDerechoExodia(Jugador unJugador) {
		super(unJugador);
	}

	public void crearCarta() {
		carta = new BrazoDerechoExodia("Brazo Derecho Exodia", jugador, new Estrellas(3), new Puntos(450,600));
	}
}
