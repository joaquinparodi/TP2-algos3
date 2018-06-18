package posiciones;

import atributos.Efecto;
import atributos.Puntos;
import cartas.Carta;
import jugabilidad.Jugador;
import resultados.Resultado;

public class InsectoArriba extends Orientacion{

	public Resultado recibirAtaque (Carta cartaInsecto, Posicion posicionInsecto, Puntos puntosAtacante) {
		return posicionInsecto.recibirAtaque(puntosAtacante);
	}
	
	public Orientacion voltear () {
		return new InsectoAbajo();
	}

	@Override
	public void aplicarEfecto(Efecto unEfecto, Jugador unJugador) {
	}
}
