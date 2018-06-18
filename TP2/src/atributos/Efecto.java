package atributos;

import cartas.Monstruo;
import jugabilidad.Jugador;

public abstract class Efecto {
	
	public abstract void aplicar (Jugador unJugador);

	public abstract void aplicarACarta(Monstruo monstruo);
	
	public abstract void aplicarACartaRival(Monstruo monstruo);
	
}
