package posiciones;

import atributos.Efecto;
import atributos.Puntos;
import cartas.Carta;
import jugabilidad.Jugador;
import resultados.Derrota;
import resultados.Resultado;

public class InsectoAbajo extends Orientacion {
	
	public Resultado recibirAtaque (Carta cartaInsecto, Posicion posicionInsecto, Puntos puntosAtacante) {
		cartaInsecto.voltear();
		return new Derrota(0);		
	}
	
	public Orientacion voltear() {
		return new InsectoArriba();
	}

	public void aplicarEfecto(Efecto unEfecto, Jugador unJugador) {
	}
	
}
