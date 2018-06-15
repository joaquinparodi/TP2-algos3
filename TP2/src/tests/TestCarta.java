package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;

import cartas.Monstruo;
import errores.ErrorAtaqueDesdePosicionInvalidad;
import org.junit.jupiter.api.Test;
import jugabilidad.Jugador;

class TestCarta {

	//private static final double DELTA = 1e-2;
	
	@Test
	public void test01CartaAtacaAOtraEnPosicionDeAtaqueConMasPuntosRestaPuntosDeVidaAlAtacante() {
		Jugador jugadorUno = new Jugador (8000);
		Jugador jugadorDos = new Jugador (8000);

		//La carta se inicializa en modo ataque
		Monstruo unaCarta = new Monstruo ("Facundo", jugadorUno, 1, 1000, 2000);
		Monstruo otraCarta = new Monstruo ("Javier", jugadorDos, 1, 1500, 2000);
		
		unaCarta.atacar( otraCarta );

		assertEquals(7500, jugadorUno.obtenerVida());
	}
	
	@Test
	public void test02CartaAtacaAOtraEnPosicionDeAtaqueConMenosPuntosRestaPuntosDeVidaAlDefensor() {
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
	public void test03CartaAtacaAOtraConIgualPuntosDeAtaqueNoLeRestaANadie() {
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
	public void test04CartaAtacaAOtraEnModoDefensaConMasPuntosYNingunoPierdeVida () {
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
	public void test05CartaAtacaAOtraEnDefensaConMenosPuntosYNingunoPierdeVida() {
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
	public void test06CartaAtacaAOtraEnDefensaConIgualPuntosYNingunoPierdeVida() {
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
	public void test07CartaAtacaAOtraEnAtaqueConMenosPuntosYLaDestruye() {
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
	public void test08CartaAtacaAOtraEnAtaqueConMasPuntosYSeDestruye() {
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
	public void test09CartaAtacaAOtraEnAtaqueConIgualPuntosYSeDestruyenAmbas() {
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
	public void test10CartaAtacaAOtraEnDefensaConMenosPuntosYLaDestruye() {
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
	public void test11CartaAtacaAOtraEnDefensaConMasPuntosYSeDestruye() {
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
	public void test12CartaAtacaAOtraEnDefensaConIgualPuntosYSeDestruyenAmabas() {
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
	
	@Test
	public void test13AtaqueDesdePosicionInvalid() {
		Jugador jugadorUno = new Jugador (8000);
		Jugador jugadorDos = new Jugador (8000);

		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		//La carta se inicializa en modo ataque
		Monstruo unaCarta = new Monstruo ("Facundo", jugadorUno, 2, 2000, 2500);
		Monstruo otraCarta = new Monstruo ("Javier", jugadorDos, 1, 2000, 2500);
		
		unaCarta.cambiarPosicion();
		
		//La carta ataqua cuando esta en posicion defensa
		Assertions.assertThrows(ErrorAtaqueDesdePosicionInvalidad.class, () -> unaCarta.atacar( otraCarta ));

	}
	
}
