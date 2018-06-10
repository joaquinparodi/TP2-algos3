package tests;

import static org.junit.jupiter.api.Assertions.*;

import TP2.src.cartas.Monstruo;
import org.junit.jupiter.api.Test;
import jugabilidad.Jugador;

class TestCarta {

	//private static final double DELTA = 1e-2;
	
	@Test
	public void testCartaAtacaAOtraEnPosicionDeAtaqueConMasPuntosQuitaPuntos() {

		Jugador unJugador = new Jugador (8000);
		Jugador otroJugador = new Jugador (8000);

		Monstruo unaCarta = new Monstruo ("Facundo", unJugador,1000,2000);
		Monstruo otraCarta = new Monstruo ("Javier", otroJugador,1500,2000);

		unaCarta.atacar(otraCarta);

		assertEquals(7500,unJugador.obtenerVida());

		
	}
	
}
