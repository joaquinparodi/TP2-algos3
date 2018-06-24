package factories;

import atributos.EfectoSogen;
import cartas.Campo;
import jugabilidad.Jugador;

public class CreadorDeSogen extends CreadorDeCartas {

	public CreadorDeSogen(Jugador unJugador) {
		super(unJugador);
	}

	public void crearCarta() {
		carta = new Campo("Sogen", jugador, new EfectoSogen ());
	}
}
