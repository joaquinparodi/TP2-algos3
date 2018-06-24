package factories;

import atributos.Estrellas;
import atributos.Puntos;
import cartas.PiernaDerechaExodia;
import jugabilidad.Jugador;

public class CreadorDePiernaDerechaExodia extends CreadorDeCartas {
	
	public CreadorDePiernaDerechaExodia(Jugador unJugador) {
		super(unJugador);
	}

	public void crearCarta() {
		carta = new PiernaDerechaExodia("Pierna Derecha Exodia", jugador, new Estrellas(3), new Puntos(450,600));
	}
}
