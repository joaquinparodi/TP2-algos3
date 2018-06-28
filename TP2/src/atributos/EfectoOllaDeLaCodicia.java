package atributos;

import errores.ErrorNoHayCartasEnLaBaraja;
import jugabilidad.Jugador;

public class EfectoOllaDeLaCodicia extends Efecto {
	
	public void aplicar(Jugador unJugador) {
		
		Jugador jugador = unJugador;
		
		try {
			jugador.repartirCarta();
			jugador.repartirCarta();		
		}catch(ErrorNoHayCartasEnLaBaraja error) {
			
		}
		
	}

}
