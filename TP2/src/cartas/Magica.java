
package cartas;

import atributos.Efecto;
import jugabilidad.Jugador;

public class Magica extends Carta{

	public Magica(String nombre, Jugador jugador,Efecto unEfecto) {
		super(nombre, jugador);
		this.efecto = unEfecto;
	}
	
	public void enviarAlCementerio() {
		this.jugadorDuenio.enviarAlCementerio(this);
	}

	public void aplicarEfecto() {
		this.orientacion.aplicarEfecto(this);
	}
	
	public void aplicarEfectoBocaArriba() {
		efecto.aplicar(jugadorDuenio);
		this.enviarAlCementerio();
	}
	
	public void aplicarEfectoBocaAbajo() {
		
	}
	
	public void voltear() {
		orientacion = orientacion.voltear();
		this.orientacion.aplicarEfecto(this);
	}

	@Override
	public boolean esParteDeExodia() {
		return false;
	}

	@Override
	public void agregarseEnCampo() {
		jugadorDuenio.agregarCartaEnCampo(this);
	}

	@Override
	public void cambiarPosicion() {
		return;
	}

	@Override
	public boolean esDeMagia() {
		return true;
	}

	@Override
	public boolean esMonstruo() {
		return false;
	}

	@Override
	public boolean esDeCampo() {
		return false;
	}

	@Override
	public boolean esDeTrampa() {
		return false;
	}

}

