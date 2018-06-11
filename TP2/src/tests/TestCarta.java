package tests;

import static org.junit.jupiter.api.Assertions.*;

import cartas.Carta;
import cartas.Monstruo;
import org.junit.jupiter.api.Test;
import jugabilidad.Jugador;

class TestCarta {

	//private static final double DELTA = 1e-2;
	
	@Test
	public void testCartaAtacaAOtraEnPosicionDeAtaqueConMasPuntosRestaPuntosDeVidaAlAtacante() {

		Jugador unJugador = new Jugador (8000);
		Jugador otroJugador = new Jugador (8000);

		//La carta se inicializa en modo ataque
		Carta unaCarta = new Monstruo ("Facundo", unJugador, 1000, 2000);
		Carta otraCarta = new Monstruo ("Javier", otroJugador, 1500, 2000);

		unaCarta.atacar( otraCarta );

		assertEquals(7500,unJugador.obtenerVida());
	}
	
	@Test
	public void testCartaAtacaAOtraEnPosicionDeAtaqueConMenosPuntosRestaPuntosDeVidaAlDefensor() {
	
		Jugador unJugador = new Jugador (8000);
		Jugador otroJugador = new Jugador (8000);

		//La carta se incializa en modo ataque
		Monstruo unaCarta = new Monstruo ("Facundo", unJugador,1600,2000);
		Monstruo otraCarta = new Monstruo ("Javier", otroJugador,1500,2000);

		unaCarta.atacar(otraCarta);

		assertEquals(7900,otroJugador.obtenerVida());
	}
	
	@Test
	public void testCartaAtacaAOtraConIgualPuntosDeAtaqueNoLeRestaANadie() {
		Jugador unJugador = new Jugador (8000);
		Jugador otroJugador = new Jugador (8000);

		//La carta se inicializa en modo ataque
		Monstruo unaCarta = new Monstruo ("Facundo", unJugador, 1500, 2000);
		Monstruo otraCarta = new Monstruo ("Javier", otroJugador, 1500, 2000);

		unaCarta.atacar(otraCarta);

		assertEquals(8000,unJugador.obtenerVida());
		assertEquals(8000,otroJugador.obtenerVida());
	}
	
}
