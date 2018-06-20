package cartas;

import atributos.Efecto;
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

	public void aplicarEfecto(Atacable monstruoAtacante, Jugador jugadorRival) {
		this.efecto.aplicar(monstruoAtacante, jugadorRival);
	}


}
