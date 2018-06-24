package factories;

import atributos.EfectoReinforcements;
import cartas.Trampa;
import jugabilidad.Jugador;

public class CreadorDeReinforcements extends CreadorDeCartas {

	public CreadorDeReinforcements(Jugador unJugador) {
		super(unJugador);
	}

	public void crearCarta() {
		carta = new Trampa("Reinforcements", jugador, new EfectoReinforcements ());
	}
}