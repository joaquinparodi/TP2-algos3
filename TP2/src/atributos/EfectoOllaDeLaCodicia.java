package atributos;

import cartas.Monstruo;
import jugabilidad.Jugador;

public class EfectoOllaDeLaCodicia extends Efecto {
	
	public void aplicar(Jugador unJugador) {
		
		Jugador jugador = unJugador;
		jugador.repartirCarta();
		jugador.repartirCarta();
		
	}

}
