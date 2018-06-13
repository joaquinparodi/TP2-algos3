
package cartas;

import jugabilidad.Jugador;

public abstract class Magica extends Carta{
	
	public Magica(String auxNombre, Jugador auxJugador) {
		super(auxNombre, auxJugador);
	}

	public abstract void aplicarEfecto();
	
	public void enviarAlCementerio() {
		this.jugadorDuenio.enviarMagicaAlCementerio(this);
	}
	
	public void agregarseEnCampo() {
		this.jugadorDuenio.agregarCartaMagicaEnCampo(this.nombreCarta);
	}
	
}

