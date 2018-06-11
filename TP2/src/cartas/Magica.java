package cartas;

import jugabilidad.Jugador;

public abstract class Magica extends Carta{
	
	public Magica(String auxNombre, Jugador auxJugador) {
		super(auxNombre, auxJugador);
	}

	public abstract void aplicarEfecto();
	
}
