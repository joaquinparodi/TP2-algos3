package factories;

import atributos.EfectoAgujeroNegro;
import cartas.Magica;
import jugabilidad.Jugador;

public class CreadorDeAgujeroNegro extends CreadorDeCartas {

	public CreadorDeAgujeroNegro(Jugador unJugador) {
		super(unJugador);
	}

	public void crearCarta() {
		carta = new Magica("Agujero Negro", jugador, new EfectoAgujeroNegro ());
	}
}
