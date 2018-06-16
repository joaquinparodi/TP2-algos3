package posiciones;

import cartas.Efecto;
import jugabilidad.Jugador;

public class OrientacionArriba implements Orientacion{
	
	public Orientacion voltear () {
		return new OrientacionAbajo ();
	}

	@Override
	public void aplicarEfecto(Efecto unEfecto, Jugador unJugador) {
		unEfecto.aplicar(unJugador);
	}
	
}
