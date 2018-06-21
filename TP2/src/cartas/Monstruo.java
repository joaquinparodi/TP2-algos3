package cartas;

import atributos.Estrellas;
import atributos.Puntos;
import atributos.Sacrificio;
import jugabilidad.Jugador;
import posiciones.PosicionAtaque;
import posiciones.Posicion;
import resultados.Resultado;

public class Monstruo extends Carta implements Atacable {

	protected Posicion posicion;
	protected Puntos puntos;
	protected Estrellas estrellas;
	protected boolean componeAExodia;
	
	public Monstruo( String nombre, Jugador unJugador, Estrellas estrellas, Puntos puntos ) {
		super( nombre, unJugador );
		this.estrellas = estrellas;
		this.puntos = puntos;
		this.posicion = new PosicionAtaque( puntos );
		this.componeAExodia = false;
	}

	public void atacar( Atacable otroMonstruo ) {
		
		posicion.atacar(this, otroMonstruo);
		
	}
	
	public void aplicarAtaque (Atacable otroMonstruo) {
		
		orientacion.atacarAplicandoEfecto(this, otroMonstruo);
		
	}
	
	public void atacarConEfecto (Atacable otroMonstruo) {
		this.atacarSinEfecto(otroMonstruo);
	}
	
	public void atacarSinEfecto (Atacable otroMonstruo) {
		Resultado resultado = otroMonstruo.recibirAtaque(puntos);
		resultado.aplicarAJugadores( jugadorDuenio );
		resultado.aplicarACartas(this, otroMonstruo);

	}
	
	public Resultado recibirAtaque( Puntos puntosAtacante ) {
		return orientacion.recibirAtaque(this,puntosAtacante);
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

	public void aplicarEfecto () {}

	public int obtenerCantidadDeSacrificiosNecesarios() {
		return estrellas.cantidadDeSacrificionNecesarios();
	}

	public boolean necesitaSacrificiosParaInvocacion() {
		return estrellas.necesitaSacrificiosParaInvocacion();
	}
	
	public void incrementarPuntosDeAtaque(double incremento) {
		this.puntos.incrementarAtaque(incremento);
	}
	
	public void incrementarPuntosDeDefensa(double incremento) {
		this.puntos.incrementarDefensa(incremento);
	}
	
	public Puntos obtenerPuntos() {
		return this.puntos;
	}

	public boolean verificarSacrificios(Sacrificio sacrificios) {
		return true;
	}

	public boolean lePerteneceA(Jugador jugador) {
		return (jugadorDuenio == jugador);
	}

	public Resultado recibirAtaqueBocaArriba(Puntos puntosAtacante) {
		return posicion.recibirAtaque(puntosAtacante);
	}

	public Resultado recibirAtaqueBocaAbajo(Puntos puntosAtacante) {
		return this.recibirAtaqueBocaArriba(puntosAtacante);
	}

	public void anularPuntos() {
		puntos.anularPuntos();
	}

	public void restaurarPuntos() {
		puntos.restaurarPuntos();
	}
	
	public String obtenerNombre() {
		return nombreCarta;
	}

	@Override
	public boolean esParteDeExodia() {
		return componeAExodia;
	}
}

