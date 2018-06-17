package posiciones;

import atributos.Efecto;
import jugabilidad.Jugador;

public class OrientacionAbajo extends Orientacion {

	public Orientacion voltear() {
		return new OrientacionArriba ();
	}

	public void aplicarEfecto(Efecto unEfecto, Jugador unJugador) { 
		return; //No aplica el efecto boca abajo
	}

}
