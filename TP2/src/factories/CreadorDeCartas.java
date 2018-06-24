package factories;

import cartas.Carta;
import jugabilidad.Jugador;

public abstract class CreadorDeCartas {
	
	protected Carta carta;
	protected Jugador jugador;
	
	public CreadorDeCartas(Jugador unJugador) {
		jugador = unJugador;
	}
	
	public Carta obtenerCarta() {
		return carta;
	}
	
	public abstract void crearCarta();
}
