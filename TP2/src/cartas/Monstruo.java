package cartas;

import jugabilidad.Jugador;
import posiciones.PosicionAtaque;
import posiciones.Posicion;
import resultados.Resultado;

public class Monstruo extends Carta {

	private Posicion posicion;
	private Puntos puntos;
	private int nivel;
	
	public Monstruo( String nombre, Jugador unJugador, int auxNivel, Puntos puntos ) {
		super( nombre, unJugador );
		this.nivel = auxNivel;
		this.puntos = puntos;
		this.posicion = new PosicionAtaque( puntos );
	}

	public void atacar( Monstruo otroMonstruo ) {
		Resultado resultado = posicion.atacar( otroMonstruo );
		resultado.aplicarAJugadores( jugadorDuenio );
		resultado.aplicarACartas(this, otroMonstruo);
	}

	public Resultado recibirAtaque( Puntos puntosAtacante ) {
		return posicion.recibirAtaque( puntosAtacante );
	}

	public void enviarAlCementerio() {
		this.jugadorDuenio.enviarMonstruoAlCementerio(this);
	}

	public void cambiarPosicion() {
		posicion = posicion.cambiarPosicion();
	}
	
	public int obtenerEstrellas() {
		return this.nivel;
	}
	
	public void asignarPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

	public void aplicarEfecto () {
	}
}

