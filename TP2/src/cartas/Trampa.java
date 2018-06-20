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
	
	//redefinir voltear

	public void enviarAlCementerio() {
		this.jugadorDuenio.enviarAlCementerio(this);	
	}

	//Habria que poder redefinir o hacer algo para no tener el metodo vacio
	public void aplicarEfecto(Atacable monstruoAtacante, Jugador jugadorRival) {
		this.efecto.aplicar(monstruoAtacante, jugadorRival);
	}

	public void aplicarEfecto() {}

}
