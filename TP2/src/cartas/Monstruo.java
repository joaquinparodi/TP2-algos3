package cartas;

import jugabilidad.Jugador;

public class Monstruo extends Carta {

	private double puntosAtaque;
	private double puntosDefensa;
	private PosicionMonstruo posicion;

	public Monstruo( String nombre, Jugador unJugador, double ataque, double defensa ) {
		super( nombre, unJugador );
		this.puntosAtaque = ataque;
		this.puntosDefensa = defensa;
		this.posicion = new PosicionAtaque( puntosAtaque );
	}

	public void atacar( Monstruo otroMonstruo ) {
		Resultado resultado = otroMonstruo.recibirAtaque( posicion );
		resultado.aplicarAJugadores( this, otroMonstruo );
	}

	public Resultado recibirAtaque( PosicionMonstruo posicionAtacante ){
		return posicionAtacante.atacar( posicion );
	}

	public void aplicarResultadoA(Monstruo otroMonstruo,double vida) {
		posicion.aplicarQuitaDeVidaA(otroMonstruo,vida);
		posicion.destruirCarta(otroMonstruo);
	}
	
	public void quitarVidaAJugador(double vida) {
		posicion.hacerDanioAJugador(jugadorDuenio, vida);;
	}
	
	public void cambiarADefensa() {
		posicion = new PosicionDefensa(puntosDefensa);
	}
	
	public void cambiarAAtaque() {
		posicion = new PosicionAtaque(puntosAtaque);
	}
	
	public void enviarAlCementerio() {
		this.jugadorDuenio.enviarMonstruoAlCementerio(this);
	}
}

