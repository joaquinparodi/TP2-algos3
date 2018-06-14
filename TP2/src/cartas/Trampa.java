package cartas;

import jugabilidad.Jugador;

public class Trampa extends Carta {

	public Trampa(String auxNombre, Jugador auxJugador, Efecto unEfecto) {
		super(auxNombre, auxJugador);
		this.efecto = unEfecto;
	}

	@Override
	public void enviarAlCementerio() {
		this.jugadorDuenio.enviarTrampaAlCementerio(this);	
	}

	@Override
	public void aplicarEfecto() {
		this.orientacion.aplicarEfecto(this.efecto, this.jugadorDuenio);
	}

}
