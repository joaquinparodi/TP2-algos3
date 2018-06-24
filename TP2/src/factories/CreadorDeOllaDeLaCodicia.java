package factories;

import atributos.EfectoOllaDeLaCodicia;
import cartas.Magica;
import jugabilidad.Jugador;

public class CreadorDeOllaDeLaCodicia extends CreadorDeCartas {

	public CreadorDeOllaDeLaCodicia(Jugador unJugador) {
		super(unJugador);
	}

	public void crearCarta() {
		carta = new Magica("Olla De La Codicia", jugador, new EfectoOllaDeLaCodicia ());
	}
}