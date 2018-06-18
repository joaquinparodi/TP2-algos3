package factories;

import atributos.Efecto;
import atributos.Estrellas;
import atributos.Puntos;
import cartas.Magica;
import cartas.Monstruo;
import jugabilidad.Jugador;

public class FabricaDeCartas {

	public Monstruo crearCarta(String nombre, Jugador unJugador, Estrellas estrellas, Puntos puntos) {
		return new Monstruo(nombre, unJugador, estrellas, puntos);
	}

	public Magica crearCarta(String nombre, Jugador jugador, Efecto efecto) {
		return new Magica(nombre, jugador, efecto);
	}
	
}
