package cartas;

import jugabilidad.Jugador;
import posiciones.PosicionAtaque;
import posiciones.PosicionMonstruo;
import resultados.Resultado;

public class Monstruo extends Carta {

	private PosicionMonstruo posicion;
	private Puntos puntos;
	private int estrellas;

	public Monstruo( String nombre, Jugador unJugador, int auxEstrellas, double ataque, double defensa ) {
		super( nombre, unJugador );
		this.estrellas = auxEstrellas;
		this.puntos = new Puntos (ataque, defensa);
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
		return this.estrellas;
	}
	
	public void asignarPosicion(PosicionMonstruo posicion) {
		this.posicion = posicion;
	}

}

