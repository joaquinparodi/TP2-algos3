package posiciones;

import cartas.Efecto;
import jugabilidad.Jugador;

public class Arriba implements OrientacionCarta{
	
	public OrientacionCarta voltear () {
		return new Abajo ();
	}

	@Override
	public void aplicarEfecto(Efecto unEfecto, Jugador unJugador) {
		unEfecto.aplicar(unJugador);
	}
	
}
