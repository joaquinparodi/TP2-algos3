package posiciones;
import jugabilidad.Jugador;
import cartas.Efecto;

public interface OrientacionCarta {
	
	public OrientacionCarta voltear ();	
	
	public void aplicarEfecto (Efecto unEfecto, Jugador unJugador);
}
