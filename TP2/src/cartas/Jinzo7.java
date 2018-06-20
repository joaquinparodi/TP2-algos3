package cartas;

import atributos.Estrellas;
import atributos.Puntos;
import jugabilidad.Jugador;

public class Jinzo7 extends Monstruo {

	public Jinzo7(String nombre, Jugador unJugador, Estrellas estrellas, Puntos puntos) {
		super(nombre, unJugador, estrellas, puntos);
	}
	
	public void atacarConEfecto (Atacable otroAtacable) {
		jugadorDuenio.hacerDanioAlRival(puntos.obtenerPuntosDeAtaque());
	}
	
	public void atacarSinEfecto (Atacable otroAtacable) {
		super.atacarSinEfecto(otroAtacable);
	}

}
