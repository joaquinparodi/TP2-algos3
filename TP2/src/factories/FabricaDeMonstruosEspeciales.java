package factories;

import atributos.Estrellas;
import atributos.Puntos;
import cartas.Jinzo7;
import jugabilidad.Jugador;

public class FabricaDeMonstruosEspeciales {

	public Jinzo7 crearJinzo7(Jugador unJugador) {
		return new Jinzo7("Jinzo 7",unJugador, new Estrellas(2), new Puntos(500,400));
	}
	
}