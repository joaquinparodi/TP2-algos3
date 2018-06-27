package cartas;

import java.util.Iterator;

import atributos.Estrellas;
import atributos.Puntos;
import atributos.Sacrificio;
import jugabilidad.Jugador;

public class DragonDefinitivo extends Monstruo{

	public DragonDefinitivo(String nombre, Jugador unJugador, Estrellas estrellas, Puntos puntos) {
		super(nombre, unJugador, estrellas, puntos);
	}
	
	@Override
	public int obtenerCantidadDeSacrificiosNecesarios() {
		return 3;
	}
	
	public boolean verificarSacrificios(Sacrificio sacrificios) {
		Iterator<Carta> iter = sacrificios.iterator();
		while(iter.hasNext()) {
		    Monstruo monstruo = (Monstruo) iter.next();
		    if (!monstruo.seLlama("Dragon Blanco Ojos Azules")) return false;  	
		}
		return true;
	}
}
