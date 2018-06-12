package tests;

import static org.junit.jupiter.api.Assertions.*;
import cartas.Monstruo;
import org.junit.jupiter.api.Test;
import jugabilidad.Jugador;

class TestCarta {

	//private static final double DELTA = 1e-2;
	
	@Test
	public void testCartaAtacaAOtraEnPosicionDeAtaqueConMasPuntosRestaPuntosDeVidaAlAtacante() {
		Jugador jugadorUno = new Jugador (8000);
		Jugador jugadorDos = new Jugador (8000);

		//La carta se inicializa en modo ataque
		Monstruo unaCarta = new Monstruo ("Facundo", jugadorUno, 1, 1000, 2000);
		Monstruo otraCarta = new Monstruo ("Javier", jugadorDos, 1, 1500, 2000);
		
		unaCarta.atacar( otraCarta );

		assertEquals(7500, jugadorUno.obtenerVida());
	}
	
	@Test
	public void testCartaAtacaAOtraEnPosicionDeAtaqueConMenosPuntosRestaPuntosDeVidaAlDefensor() {
		Jugador unJugador = new Jugador (8000);
		Jugador otroJugador = new Jugador (8000);
		
		otroJugador.asignarRival( unJugador );
		unJugador.asignarRival( otroJugador );
		
		Monstruo unaCarta = new Monstruo ("Facundo", unJugador, 1, 1600,2000);
		Monstruo otraCarta = new Monstruo ("Javier", otroJugador, 1, 1500,2000);

		unaCarta.atacar(otraCarta);
		
		assertEquals(7900, otroJugador.obtenerVida());
	}
	
	@Test
	public void testCartaAtacaAOtraConIgualPuntosDeAtaqueNoLeRestaANadie() {
		Jugador unJugador = new Jugador (8000);
		Jugador otroJugador = new Jugador (8000);

		otroJugador.asignarRival( unJugador );
		unJugador.asignarRival( otroJugador );
		
		Monstruo unaCarta = new Monstruo ("Facundo", unJugador, 1, 1500, 2000);
		Monstruo otraCarta = new Monstruo ("Javier", otroJugador, 1, 1500, 2000);

		unaCarta.atacar(otraCarta);

		assertEquals(8000, unJugador.obtenerVida());
		assertEquals(8000, otroJugador.obtenerVida());
	}
	
	@Test
	public void testCartaAtacaAOtraEnModoDefensaConMasPuntosYNingunoPierdeVida () {
		Jugador unJugador = new Jugador (8000);
		Jugador otroJugador = new Jugador (8000);

		otroJugador.asignarRival( unJugador );
		unJugador.asignarRival( otroJugador );
		
		//Las cartas se inician en modo ataque
		Monstruo unaCarta = new Monstruo ("Facundo", unJugador, 1, 1500,2000);
		Monstruo otraCarta = new Monstruo ("Javier", otroJugador, 1, 1500,2000);
		
		otraCarta.cambiarPosicion();
		unaCarta.atacar(otraCarta);

		assertEquals(8000, unJugador.obtenerVida());
		assertEquals(8000, otroJugador.obtenerVida());
	}
	
	@Test
	public void testCartaAtacaAOtraEnDefensaConMenosPuntosYNingunoPierdeVida() {
		Jugador unJugador = new Jugador (8000);
		Jugador otroJugador = new Jugador (8000);
		
		otroJugador.asignarRival( unJugador );
		unJugador.asignarRival( otroJugador );

		Monstruo unaCarta = new Monstruo ("Facundo", unJugador, 1, 1500,2000);
		Monstruo otraCarta = new Monstruo ("Javier", otroJugador, 1, 1500,1000);
		
		otraCarta.cambiarPosicion();
		unaCarta.atacar(otraCarta);

		assertEquals(8000, unJugador.obtenerVida());
		assertEquals(8000, otroJugador.obtenerVida());
	}
	
	@Test
	public void testCartaAtacaAOtraEnDefensaConIgualPuntosYNingunoPierdeVida() {
		Jugador unJugador = new Jugador (8000);
		Jugador otroJugador = new Jugador (8000);
		
		otroJugador.asignarRival( unJugador );
		unJugador.asignarRival( otroJugador );

		Monstruo unaCarta = new Monstruo ("Facundo", unJugador, 1, 3000,3300);
		Monstruo otraCarta = new Monstruo ("Javier", otroJugador, 1, 2200,3000);
		
		otraCarta.cambiarPosicion();	
		unaCarta.atacar(otraCarta);

		assertEquals(8000,unJugador.obtenerVida());
		assertEquals(8000,otroJugador.obtenerVida());
	}
	
	@Test
	public void testCartaAtacaAOtraEnAtaqueConMenosPuntosYLaDestruye() {
		Jugador unJugador = new Jugador (8000);
		Jugador otroJugador = new Jugador (8000);
		
		otroJugador.asignarRival( unJugador );
		unJugador.asignarRival( otroJugador );
		
		Monstruo unaCarta = new Monstruo ("Facundo", unJugador, 1, 1600,2000);
		Monstruo otraCarta = new Monstruo ("Javier", otroJugador, 1, 1500,2000);

		unaCarta.atacar(otraCarta);
		
		assertTrue( otroJugador.monstruoEstaMuerto("Javier") );
	}
	
	@Test
	public void testCartaAtacaAOtraEnAtaqueConMasPuntosYSeDestruye() {
		Jugador jugadorUno = new Jugador (8000);
		Jugador jugadorDos = new Jugador (8000);

		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		//La carta se inicializa en modo ataque
		Monstruo unaCarta = new Monstruo ("Facundo", jugadorUno, 1, 1000, 2000);
		Monstruo otraCarta = new Monstruo ("Javier", jugadorDos, 1, 1500, 2000);

		unaCarta.atacar( otraCarta );

		assertTrue( jugadorUno.monstruoEstaMuerto("Facundo") );
	}
	
	@Test
	public void testCartaAtacaAOtraEnAtaqueConIgualPuntosYSeDestruyenAmbas() {
		Jugador jugadorUno = new Jugador (8000);
		Jugador jugadorDos = new Jugador (8000);

		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		//La carta se inicializa en modo ataque
		Monstruo unaCarta = new Monstruo ("Facundo", jugadorUno, 1, 1500, 2000);
		Monstruo otraCarta = new Monstruo ("Javier", jugadorDos, 1, 1500, 2000);

		unaCarta.atacar( otraCarta );

		assertTrue( jugadorUno.monstruoEstaMuerto("Facundo") );
		assertTrue( jugadorDos.monstruoEstaMuerto("Javier") );
	}
	
	@Test
	public void testCartaAtacaAOtraEnDefensaConMenosPuntosYLaDestruye() {
		Jugador jugadorUno = new Jugador (8000);
		Jugador jugadorDos = new Jugador (8000);

		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		//La carta se inicializa en modo ataque
		Monstruo unaCarta = new Monstruo ("Facundo", jugadorUno, 1, 1500, 2500);
		Monstruo otraCarta = new Monstruo ("Javier", jugadorDos, 1, 1500, 2000);
		
		otraCarta.cambiarPosicion();
		unaCarta.atacar( otraCarta );

		assertTrue( jugadorDos.monstruoEstaMuerto("Javier") );
	}	
	
	@Test
	public void testCartaAtacaAOtraEnDefensaConMasPuntosYSeDestruye() {
		Jugador jugadorUno = new Jugador (8000);
		Jugador jugadorDos = new Jugador (8000);

		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		//La carta se inicializa en modo ataque
		Monstruo unaCarta = new Monstruo ("Facundo", jugadorUno, 1, 1500, 2000);
		Monstruo otraCarta = new Monstruo ("Javier", jugadorDos, 1, 1500, 2500);
		
		otraCarta.cambiarPosicion();
		unaCarta.atacar( otraCarta );

		assertTrue( jugadorUno.monstruoEstaMuerto("Facundo") );
	}	
	
	@Test
	public void testCartaAtacaAOtraEnDefensaConIgualPuntosYSeDestruyenAmabas() {
		Jugador jugadorUno = new Jugador (8000);
		Jugador jugadorDos = new Jugador (8000);

		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		//La carta se inicializa en modo ataque
		Monstruo unaCarta = new Monstruo ("Facundo", jugadorUno, 1, 1500, 2500);
		Monstruo otraCarta = new Monstruo ("Javier", jugadorDos, 1, 1500, 2500);
		
		otraCarta.cambiarPosicion();
		unaCarta.atacar( otraCarta );

		assertTrue( jugadorUno.monstruoEstaMuerto("Facundo") );
		assertTrue( jugadorDos.monstruoEstaMuerto("Javier") );
	}	
	
}
