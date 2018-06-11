package tests;

import static org.junit.jupiter.api.Assertions.*;

import cartas.Monstruo;
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
	
	@Test
	public void testCartaAtacaAOtraEnPosicionDeAtaqueConMenosPuntosQuitaPuntosAlOtro() {
		Jugador unJugador = new Jugador (8000);
		Jugador otroJugador = new Jugador (8000);

		Monstruo unaCarta = new Monstruo ("Facundo", unJugador,1600,2000);
		Monstruo otraCarta = new Monstruo ("Javier", otroJugador,1500,2000);

		unaCarta.atacar(otraCarta);

		assertEquals(7900,otroJugador.obtenerVida());
	}
	
	@Test
	public void testCartaAtacaAOtraConIgualPuntosDeAtaqueNoLeRestaANadie () {
		Jugador unJugador = new Jugador (8000);
		Jugador otroJugador = new Jugador (8000);

		Monstruo unaCarta = new Monstruo ("Facundo", unJugador,1500,2000);
		Monstruo otraCarta = new Monstruo ("Javier", otroJugador,1500,2000);

		unaCarta.atacar(otraCarta);

		assertEquals(8000,unJugador.obtenerVida());
		assertEquals(8000,otroJugador.obtenerVida());
	}
	
	@Test
	public void testCartaAtacaAOtraEnModoDefensaConMasPuntosYNingunoPierdeVida () {
		Jugador unJugador = new Jugador (8000);
		Jugador otroJugador = new Jugador (8000);

		Monstruo unaCarta = new Monstruo ("Facundo", unJugador,1500,2000);
		Monstruo otraCarta = new Monstruo ("Javier", otroJugador,1500,2000);
		
		otraCarta.cambiarPosicion();
		
		unaCarta.atacar(otraCarta);

		assertEquals(8000,unJugador.obtenerVida());
		assertEquals(8000,otroJugador.obtenerVida());
	}
	
	@Test
	public void testCartaAtacaAOtraEnDefensaConMenosPuntosYNingunoPierdeVida() {
		Jugador unJugador = new Jugador (8000);
		Jugador otroJugador = new Jugador (8000);

		Monstruo unaCarta = new Monstruo ("Facundo", unJugador,1500,2000);
		Monstruo otraCarta = new Monstruo ("Javier", otroJugador,1500,1000);
		
		otraCarta.cambiarPosicion();
		
		unaCarta.atacar(otraCarta);

		assertEquals(8000,unJugador.obtenerVida());
		assertEquals(8000,otroJugador.obtenerVida());

	}
	
	@Test
	public void testCartaAtacaAOtraEnDefensaConIgualPuntosYNingunoPierdeVida () {
		Jugador unJugador = new Jugador (8000);
		Jugador otroJugador = new Jugador (8000);

		Monstruo unaCarta = new Monstruo ("Facundo", unJugador,3000,3300);
		Monstruo otraCarta = new Monstruo ("Javier", otroJugador,2200,3000);
		
		otraCarta.cambiarPosicion();
		
		unaCarta.atacar(otraCarta);

		assertEquals(8000,unJugador.obtenerVida());
		assertEquals(8000,otroJugador.obtenerVida());

	}
	
}
