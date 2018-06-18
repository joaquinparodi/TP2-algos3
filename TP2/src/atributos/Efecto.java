package atributos;

import java.util.Iterator;

import cartas.Monstruo;
import jugabilidad.Baraja;
import jugabilidad.CampoDeJuego;
import jugabilidad.Jugador;

public abstract class Efecto {
	
	public abstract void aplicar (Jugador unJugador);

	public abstract void aplicarACarta(Monstruo monstruo);
	
	public abstract void aplicarACartaRival(Monstruo monstruo);
	
	public abstract void desaplicar(Jugador unJugador);
	
}
