package cartas;

import atributos.Estrellas;
import atributos.Puntos;
import jugabilidad.Jugador;

public class BrazoIzquierdoExodia extends Monstruo {

	public BrazoIzquierdoExodia(String nombre, Jugador unJugador, Estrellas estrellas, Puntos puntos) {
		super(nombre, unJugador, estrellas, puntos);
	}
	
	public boolean esParteDeExodia() {
		return true;
	}
	
}