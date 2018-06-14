package posiciones;

import cartas.Efecto;
import jugabilidad.Jugador;

public class Abajo implements OrientacionCarta {

	public OrientacionCarta voltear() {
		return new Arriba ();
	}

	@Override
	public void aplicarEfecto(Efecto unEfecto, Jugador unJugador) {

	}


}
