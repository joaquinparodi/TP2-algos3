package cartas;

import atributos.EfectoDeCampo;
import errores.ErrorCartaNoVolteable;
import jugabilidad.Jugador;

public class Campo extends Carta {
	
	private EfectoDeCampo efecto;
	
	public Campo( String nombre, Jugador jugador, EfectoDeCampo efecto ) {
		super(nombre, jugador);
		this.efecto = efecto;
	}

	public void enviarAlCementerio() {
		this.jugadorDuenio.enviarAlCementerio(this);	
	}

	public void aplicarEfecto() {
		efecto.aplicar(this.jugadorDuenio);;
	}
	
	public void aplicarEfectoACarta(Monstruo monstruo) {
		efecto.aplicarACarta(monstruo);
	}
	
	public void aplicarEfectoACartaRival(Monstruo monstruoEnemigo) {
		efecto.aplicarACartaRival(monstruoEnemigo);
	}
	
	public void voltear() {
		throw new ErrorCartaNoVolteable();
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
		return false;
	}

	@Override
	public boolean esMonstruo() {
		return false;
	}

	@Override
	public boolean esDeCampo() {
		return true;
	}

	@Override
	public boolean esDeTrampa() {
		return false;
	}
	

}
