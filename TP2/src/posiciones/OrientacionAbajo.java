package posiciones;

import atributos.Efecto;
import atributos.Puntos;
import cartas.Carta;
import jugabilidad.Jugador;
import resultados.Resultado;

public class OrientacionAbajo extends Orientacion {

	public Orientacion voltear() {
		return new OrientacionArriba ();
	}

	public void aplicarEfecto(Efecto unEfecto, Jugador unJugador) { 
		return; //No aplica el efecto boca abajo
	}

	public Resultado recibirAtaque(Carta carta, Posicion posicion, Puntos puntosAtacante) {
		return null;
	}

}
