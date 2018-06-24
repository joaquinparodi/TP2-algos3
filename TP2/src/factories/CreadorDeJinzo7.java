package factories;

import atributos.Estrellas;
import atributos.Puntos;
import cartas.Jinzo7;
import jugabilidad.Jugador;

public class CreadorDeJinzo7 extends CreadorDeCartas {
		
		public CreadorDeJinzo7(Jugador unJugador) {
			super(unJugador);
		}

		public void crearCarta() {
			carta = new Jinzo7("Jinzo 7",jugador, new Estrellas(2), new Puntos(500,400));
		}
}
