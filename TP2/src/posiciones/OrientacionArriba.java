package posiciones;

import atributos.Efecto;
import jugabilidad.Jugador;

public class OrientacionArriba extends Orientacion{
	
	public Orientacion voltear () {
		return new OrientacionAbajo ();
	}

	public void aplicarEfecto(Efecto unEfecto, Jugador unJugador) {
		unEfecto.aplicar(unJugador);
	}
	
}
