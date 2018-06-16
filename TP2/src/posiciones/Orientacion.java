package posiciones;
import jugabilidad.Jugador;
import cartas.Efecto;

public interface Orientacion {
	
	public Orientacion voltear ();	
	
	public void aplicarEfecto (Efecto unEfecto, Jugador unJugador);
}
