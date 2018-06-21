package cartas;

import atributos.Estrellas;
import atributos.Puntos;
import jugabilidad.Jugador;

public class CabezaExodia extends Monstruo {

	public CabezaExodia(String nombre, Jugador unJugador, Estrellas estrellas, Puntos puntos) {
		super(nombre, unJugador, estrellas, puntos);
		this.componeAExodia = true;
	}

}