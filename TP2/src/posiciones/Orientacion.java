package posiciones;

import atributos.Efecto;
import jugabilidad.Jugador;

public abstract class Orientacion {
	
	public abstract Orientacion voltear ();	
	
	public abstract void aplicarEfecto (Efecto unEfecto, Jugador unJugador);
	
}
