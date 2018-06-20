package cartas;

import atributos.Efecto;
import atributos.EfectoDeCampo;
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
		this.orientacion.aplicarEfecto(this.efecto, this.jugadorDuenio);
	}
	
	public void aplicarEfectoACarta(Monstruo monstruo) {
		efecto.aplicarACarta(monstruo);
	}
	
	public void aplicarEfectoACartaRival(Monstruo monstruoEnemigo) {
		efecto.aplicarACartaRival(monstruoEnemigo);
	}
	
	public void voltear() {
		this.orientacion = orientacion.voltear();
		orientacion.aplicarEfecto(this.efecto, this.jugadorDuenio);
	}

}
