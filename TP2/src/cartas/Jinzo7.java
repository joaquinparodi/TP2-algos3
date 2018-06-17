package cartas;

import atributos.Estrellas;
import atributos.Puntos;
import errores.ErrorAtaqueDesdePosicionInvalida;
import jugabilidad.Jugador;

public class Jinzo7 extends Monstruo {

	public Jinzo7 (String nombre, Jugador unJugador, Estrellas estrellas, Puntos puntos) {
		super(nombre, unJugador, estrellas, puntos);
	}
	
	public void atacar (Atacable unMonstruo) {
		try {
			posicion.atacar(unMonstruo);
		}catch (ErrorAtaqueDesdePosicionInvalida error) {
			throw new ErrorAtaqueDesdePosicionInvalida();
		}
		
		this.jugadorDuenio.hacerDanioAlRival(this.puntos.obtenerPuntosDeAtaque());
		
	}
}
