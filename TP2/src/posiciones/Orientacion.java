package posiciones;

import atributos.Efecto;
import jugabilidad.Jugador;

public interface Orientacion {
	
	public Orientacion voltear ();	
	
	public void aplicarEfecto (Efecto unEfecto, Jugador unJugador);
	
}
