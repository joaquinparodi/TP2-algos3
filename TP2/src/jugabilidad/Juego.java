package jugabilidad;

import java.util.Random;

import cartas.Carta;

public class Juego {
	
	private Jugador jugadorActual;
	private Jugador jugadorContrario;
	
	
	public void decidirJugadorInicial(Jugador unJugador, Jugador otroJugador) {
		Random alAzar = new Random();
		int numeroAlAzar = alAzar.nextInt(2);
		if(numeroAlAzar != 0) {
			jugadorActual = unJugador;
			jugadorContrario = otroJugador;
		} else {
			jugadorActual = otroJugador;
			jugadorContrario = unJugador;
		}
	}
	
	public void proximoJugador() {
		Jugador jugadorAuxiliar = jugadorContrario;
		jugadorContrario = jugadorActual;
		jugadorActual = jugadorAuxiliar;
	}
	
	public void inicioDeLaPartida() {
		decidirJugadorInicial(jugadorActual, jugadorContrario);
		
	}
	
	
	public void agregarCartasALaMano(int cantidadDeCartas) {
		
	}
	
	public void mandarCartaAlCementerio(Carta unaCarta) {
		
	}
}
