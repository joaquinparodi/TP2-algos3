package posiciones;

import atributos.Efecto;
import atributos.Puntos;
import cartas.Carta;
import jugabilidad.Jugador;
import resultados.Derrota;
import resultados.Resultado;

public class InsectoComeHombresAbajo extends Orientacion {
	
	public Resultado recibirAtaque (Carta cartaInsecto, Posicion posicionInsecto, Puntos puntosAtacante) {
		cartaInsecto.voltear();
		return new Derrota(0);		
	}
	
	public Orientacion voltear() {
		return new InsectoComeHombresArriba();
	}

	public void aplicarEfecto(Efecto unEfecto, Jugador unJugador) {
	}
	
}
