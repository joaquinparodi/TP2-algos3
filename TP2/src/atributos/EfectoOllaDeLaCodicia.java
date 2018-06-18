package atributos;

import cartas.Monstruo;
import jugabilidad.Jugador;

public class EfectoOllaDeLaCodicia extends Efecto {
	
	public void aplicar(Jugador unJugador) {
		
		Jugador jugador = unJugador;
		jugador.repartirCartaDelMazo();
		jugador.repartirCartaDelMazo();
		
	}

	public void aplicarACarta(Monstruo unMonstruo) {}

	public void aplicarACartaRival(Monstruo monstruo) {}

	public void desaplicar(Jugador unJugador) {}
}
