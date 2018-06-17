package cartas;

import atributos.Efecto;
import jugabilidad.Jugador;

public class Campo extends Carta {
	
	private Efecto efecto;
	
	public Campo( String nombre, Jugador jugador, Efecto efecto ) {
		super(nombre, jugador);
		this.efecto = efecto;
	}

	public void enviarAlCementerio() {
		this.jugadorDuenio.enviarAlCementerio(this);	
	}

	public void aplicarEfecto() {
		this.orientacion.aplicarEfecto(this.efecto, this.jugadorDuenio);
	}
	
}
