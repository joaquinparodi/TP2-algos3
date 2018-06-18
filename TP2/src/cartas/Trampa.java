package cartas;

import atributos.Efecto;
import jugabilidad.Jugador;

public class Trampa extends Carta {

	public Trampa(String auxNombre, Jugador auxJugador, Efecto unEfecto) {
		super(auxNombre, auxJugador);
		this.efecto = unEfecto;
	}

	public void enviarAlCementerio() {
		this.jugadorDuenio.enviarAlCementerio(this);	
	}

	public void aplicarEfecto() {
		this.orientacion.aplicarEfecto(this.efecto, this.jugadorDuenio);
	}

}
