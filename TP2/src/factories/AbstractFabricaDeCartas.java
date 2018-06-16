package factories;

import atributos.Efecto;
import atributos.Estrellas;
import atributos.Puntos;
import cartas.Magica;
import cartas.Monstruo;
import jugabilidad.Jugador;

public abstract class AbstractFabricaDeCartas {

	public abstract Monstruo crearCarta(String nombre, Jugador unJugador, Estrellas estrellas, Puntos puntos);
	
	public abstract Magica crearCarta(String auxNombre, Jugador auxJugador,Efecto unEfecto);
	
}
