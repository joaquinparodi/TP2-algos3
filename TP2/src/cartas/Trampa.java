package cartas;

import atributos.Efecto;
import jugabilidad.Jugador;

public class Trampa extends Carta {

	private Efecto efecto;

	public Trampa(String auxNombre, Jugador auxJugador, Efecto unEfecto) {
		super(auxNombre, auxJugador);
		this.efecto = unEfecto;
	}

	public void enviarAlCementerio() {
		this.jugadorDuenio.enviarTrampaAlCementerio(this);	
	}

	public void aplicarEfecto() {
		this.orientacion.aplicarEfecto(this.efecto, this.jugadorDuenio);
	}

}
