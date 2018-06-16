
package cartas;

import atributos.Efecto;
import jugabilidad.Jugador;
import posiciones.Orientacion;

public class Magica extends Carta{

	private Efecto efecto;

	public Magica(String auxNombre, Jugador auxJugador,Efecto unEfecto) {
		super(auxNombre, auxJugador);
		this.efecto = unEfecto;
	}
	
	public void enviarAlCementerio() {
		this.jugadorDuenio.enviarMagicaAlCementerio(this);
	}

	public void aplicarEfecto() {
		this.orientacion.aplicarEfecto(this.efecto,this.jugadorDuenio);
	}	
}

