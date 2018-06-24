package factories;

import atributos.EfectoFisura;
import cartas.Magica;
import jugabilidad.Jugador;

public class CreadorDeFisura extends CreadorDeCartas {

	public CreadorDeFisura(Jugador unJugador) {
		super(unJugador);
	}

	public void crearCarta() {
		carta = new Magica("Fisura", jugador, new EfectoFisura ());
	}
}
