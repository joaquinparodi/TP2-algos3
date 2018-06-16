package cartas;

import atributos.Estrellas;
import atributos.Puntos;
import jugabilidad.Jugador;
import posiciones.PosicionAtaque;
import posiciones.Posicion;
import resultados.Resultado;

public class Monstruo extends Carta {

	private Posicion posicion;
	private Puntos puntos;
	private Estrellas estrellas;
	
	public Monstruo( String nombre, Jugador unJugador, Estrellas estrellas, Puntos puntos ) {
		super( nombre, unJugador );
		this.estrellas = estrellas;
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
		this.jugadorDuenio.enviarAlCementerio(this);
	}

	public void cambiarPosicion() {
		posicion = posicion.cambiarPosicion();
	}
	
	public Estrellas obtenerEstrellas() {
		return this.estrellas;
	}
	
	public void asignarPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

	public void aplicarEfecto () {
	}

	public int obtenerCantidadDeSacrificiosNecesarios() {
		return estrellas.cantidadDeSacrificionNecesarios();
	}

	public boolean necesitaSacrificiosParaInvocacion() {
		return estrellas.necesitaSacrificiosParaInvocacion();
	}
}

