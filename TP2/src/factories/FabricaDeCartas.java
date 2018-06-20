package factories;

import atributos.Efecto;
import atributos.EfectoDeCampo;
import atributos.EfectoDeTrampa;
import atributos.Estrellas;
import atributos.Puntos;
import cartas.Campo;
import cartas.Magica;
import cartas.Monstruo;
import cartas.Trampa;
import jugabilidad.Jugador;

public class FabricaDeCartas {

	public Monstruo crearCarta(String nombre, Jugador unJugador, Estrellas estrellas, Puntos puntos) {
		return new Monstruo(nombre, unJugador, estrellas, puntos);
	}

	public Magica crearCarta(String nombre, Jugador jugador, Efecto efecto) {
		return new Magica(nombre, jugador, efecto);
	}
	
	public Trampa crearCarta(String nombre, Jugador jugador, EfectoDeTrampa efecto ) {
		return new Trampa(nombre, jugador, efecto);
	} 
	
	public Campo crearCarta(String nombre, Jugador jugador, EfectoDeCampo efecto ) {
		return new Campo(nombre, jugador, efecto);
	}	
}
