package cartas;

import Vista.ResultadoDeAtaque;
import atributos.EfectoDeTrampa;
import jugabilidad.Jugador;

public class Trampa extends Carta {

	EfectoDeTrampa efecto;
	
	public Trampa(String auxNombre, Jugador auxJugador, EfectoDeTrampa unEfecto) {
		super(auxNombre, auxJugador);
		this.efecto = unEfecto;
	}
	
	//redefinir voltear con una excpecion de que no se pueda

	public void enviarAlCementerio() {
		this.jugadorDuenio.enviarAlCementerio(this);	
	}

	public void aplicarEfecto(Atacable atacante, Atacable atacado, Jugador jugadorRival) {
		this.efecto.aplicar( atacante,  atacado,  jugadorRival, this);
		ResultadoDeAtaque.obtener().setTrampaUsada(this);
	}

	public boolean esParteDeExodia() {
		return false;
	}

	public void agregarseEnCampo() {
		jugadorDuenio.agregarCartaEnCampo(this);
	}

	@Override
	public void cambiarPosicion() {
		return;	
	}

	@Override
	public boolean esDeMagia() {
		return false;
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
		return true;
	}
	
	

}
