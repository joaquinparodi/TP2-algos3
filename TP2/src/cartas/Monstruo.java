package TP2.src.cartas;
import cartas.PosicionAtaque;

public class Monstruo extends Carta {

	private Carta cartaBase;
	private double puntosAtaque;
	private double puntosDefensa;
	private PosicionMonstruo posicion;

	public Monstruo (String nombre, jugabilidad.Jugador unJugador, double ataque, double defensa){
		super(nombre,unJugador);
		this.puntosAtaque = ataque;
		this.puntosDefensa = defensa;
		this.posicion = new PosicionAtaque(puntosAtaque);
	}

	public void atacar (Monstruo otroMonstruo){
		Resultado resultado = otroMonstruo.recibirAtaque(posicion);
		resultado.aplicarAJugadores(this, otroMonstruo);
	}

	public Resultado recibirAtaque (PosicionMonstruo posicionAtacante){
		return posicionAtacante.atacar(posicion);
	}


	public void descontarVidaAlJugador(double vida) {
		jugador.hacerDanio(vida);
	}
}

