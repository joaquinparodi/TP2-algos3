package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;

import cartas.Monstruo;
import cartas.Puntos;
import errores.ErrorAtaqueDesdePosicionInvalidad;
import org.junit.jupiter.api.Test;

import atributos.Vida;
import jugabilidad.Jugador;

class TestCarta {

	//private static final double DELTA = 1e-2;
	
	@Test
	public void test01CartaAtacaAOtraEnPosicionDeAtaqueConMasPuntosRestaPuntosDeVidaAlAtacante() {
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugaforDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugaforDos);

		//La carta se inicializa en modo ataque
		Puntos puntosUnaCarta = new Puntos(1000, 2000);
		Puntos puntosOtraCarta = new Puntos(1500, 2500);
		Monstruo unaCarta = new Monstruo ("Facundo", jugadorUno, 1, puntosUnaCarta);
		Monstruo otraCarta = new Monstruo ("Javier", jugadorDos, 1, puntosOtraCarta);
		
		unaCarta.atacar( otraCarta );

		assertEquals(7500, jugadorUno.obtenerVida().obtenerPuntosDeVida() );
	}
	
	@Test
	public void test02CartaAtacaAOtraEnPosicionDeAtaqueConMenosPuntosRestaPuntosDeVidaAlDefensor() {
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugaforDos = new Vida (8000);
		Jugador unJugador = new Jugador (vidaJugadorUno);
		Jugador otroJugador = new Jugador (vidaJugaforDos);
		
		otroJugador.asignarRival( unJugador );
		unJugador.asignarRival( otroJugador );
		
		Puntos puntosUnaCarta = new Puntos(1600, 2000);
		Puntos puntosOtraCarta = new Puntos(1500, 2000);
		Monstruo unaCarta = new Monstruo ("Facundo", unJugador, 1, puntosUnaCarta);
		Monstruo otraCarta = new Monstruo ("Javier", otroJugador, 1, puntosOtraCarta);

		unaCarta.atacar(otraCarta);
		
		assertEquals(7900, otroJugador.obtenerVida().obtenerPuntosDeVida());
	}
	
	@Test
	public void test03CartaAtacaAOtraConIgualPuntosDeAtaqueNoLeRestaANadie() {
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugaforDos = new Vida (8000);
		Jugador unJugador = new Jugador (vidaJugadorUno);
		Jugador otroJugador = new Jugador (vidaJugaforDos);

		otroJugador.asignarRival( unJugador );
		unJugador.asignarRival( otroJugador );
		
		Puntos puntosUnaCarta = new Puntos(1500, 2000);
		Puntos puntosOtraCarta = new Puntos(1500, 2000);
		Monstruo unaCarta = new Monstruo ("Facundo", unJugador, 1, puntosUnaCarta);
		Monstruo otraCarta = new Monstruo ("Javier", otroJugador, 1, puntosOtraCarta);

		unaCarta.atacar(otraCarta);

		assertEquals(8000, unJugador.obtenerVida().obtenerPuntosDeVida() );
		assertEquals(8000, otroJugador.obtenerVida().obtenerPuntosDeVida() );
	}
	
	@Test
	public void test04CartaAtacaAOtraEnModoDefensaConMasPuntosYNingunoPierdeVida () {
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugaforDos = new Vida (8000);
		Jugador unJugador = new Jugador (vidaJugadorUno);
		Jugador otroJugador = new Jugador (vidaJugaforDos);

		otroJugador.asignarRival( unJugador );
		unJugador.asignarRival( otroJugador );
		
		//Las cartas se inician en modo ataque
		Puntos puntosUnaCarta = new Puntos(1600, 2000);
		Puntos puntosOtraCarta = new Puntos(1500, 2500);
		Monstruo unaCarta = new Monstruo ("Facundo", unJugador, 1, puntosUnaCarta);
		Monstruo otraCarta = new Monstruo ("Javier", otroJugador, 1, puntosOtraCarta);
		
		otraCarta.cambiarPosicion();
		unaCarta.atacar(otraCarta);

		assertEquals(8000, unJugador.obtenerVida().obtenerPuntosDeVida() );
		assertEquals(8000, otroJugador.obtenerVida().obtenerPuntosDeVida() );
	}
	
	@Test
	public void test05CartaAtacaAOtraEnDefensaConMenosPuntosYNingunoPierdeVida() {
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugaforDos = new Vida (8000);
		Jugador unJugador = new Jugador (vidaJugadorUno);
		Jugador otroJugador = new Jugador (vidaJugaforDos);
		
		otroJugador.asignarRival( unJugador );
		unJugador.asignarRival( otroJugador );

		
		Puntos puntosUnaCarta = new Puntos(1600, 2000);
		Puntos puntosOtraCarta = new Puntos(1500, 1000);
		Monstruo unaCarta = new Monstruo ("Facundo", unJugador, 1, puntosUnaCarta);
		Monstruo otraCarta = new Monstruo ("Javier", otroJugador, 1, puntosOtraCarta);
		
		otraCarta.cambiarPosicion();
		unaCarta.atacar(otraCarta);

		assertEquals(8000, unJugador.obtenerVida().obtenerPuntosDeVida() );
		assertEquals(8000, otroJugador.obtenerVida().obtenerPuntosDeVida() );
	}
	
	@Test
	public void test06CartaAtacaAOtraEnDefensaConIgualPuntosYNingunoPierdeVida() {
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugaforDos = new Vida (8000);
		Jugador unJugador = new Jugador (vidaJugadorUno);
		Jugador otroJugador = new Jugador (vidaJugaforDos);
		
		otroJugador.asignarRival( unJugador );
		unJugador.asignarRival( otroJugador );

		Puntos puntosUnaCarta = new Puntos(1600, 2000);
		Puntos puntosOtraCarta = new Puntos(1500, 2000);
		Monstruo unaCarta = new Monstruo ("Facundo", unJugador, 1, puntosUnaCarta);
		Monstruo otraCarta = new Monstruo ("Javier", otroJugador, 1, puntosOtraCarta);
		
		otraCarta.cambiarPosicion();	
		unaCarta.atacar(otraCarta);

		assertEquals( 8000,unJugador.obtenerVida().obtenerPuntosDeVida() );
		assertEquals( 8000,otroJugador.obtenerVida().obtenerPuntosDeVida() );
	}
	
	@Test
	public void test07CartaAtacaAOtraEnAtaqueConMenosPuntosYLaDestruye() {
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugaforDos = new Vida (8000);
		Jugador unJugador = new Jugador (vidaJugadorUno);
		Jugador otroJugador = new Jugador (vidaJugaforDos);
		
		otroJugador.asignarRival( unJugador );
		unJugador.asignarRival( otroJugador );
		
		Puntos puntosUnaCarta = new Puntos(1600, 2000);
		Puntos puntosOtraCarta = new Puntos(1500, 2000);
		Monstruo unaCarta = new Monstruo ("Facundo", unJugador, 1, puntosUnaCarta);
		Monstruo otraCarta = new Monstruo ("Javier", otroJugador, 1, puntosOtraCarta);

		unaCarta.atacar(otraCarta);
		
		assertTrue( otroJugador.monstruoEstaMuerto("Javier") );
	}
	
	@Test
	public void test08CartaAtacaAOtraEnAtaqueConMasPuntosYSeDestruye() {
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugaforDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugaforDos);

		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		//La carta se inicializa en modo ataque
		Puntos puntosUnaCarta = new Puntos(1000, 2000);
		Puntos puntosOtraCarta = new Puntos(1500, 2000);
		Monstruo unaCarta = new Monstruo ("Facundo", jugadorUno, 1, puntosUnaCarta);
		Monstruo otraCarta = new Monstruo ("Javier", jugadorDos, 1, puntosOtraCarta);
		
		unaCarta.atacar( otraCarta );

		assertTrue( jugadorUno.monstruoEstaMuerto("Facundo") );
	}
	
	@Test
	public void test09CartaAtacaAOtraEnAtaqueConIgualPuntosYSeDestruyenAmbas() {
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugaforDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugaforDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		//La carta se inicializa en modo ataque
		Puntos puntosUnaCarta = new Puntos(1500, 2000);
		Puntos puntosOtraCarta = new Puntos(1500, 2000);
		Monstruo unaCarta = new Monstruo ("Facundo", jugadorUno, 1, puntosUnaCarta);
		Monstruo otraCarta = new Monstruo ("Javier", jugadorDos, 1, puntosOtraCarta);

		unaCarta.atacar( otraCarta );

		assertTrue( jugadorUno.monstruoEstaMuerto("Facundo") );
		assertTrue( jugadorDos.monstruoEstaMuerto("Javier") );
	}
	
	@Test
	public void test10CartaAtacaAOtraEnDefensaConMenosPuntosYLaDestruye() {
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugaforDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugaforDos);

		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		//La carta se inicializa en modo ataque
		Puntos puntosUnaCarta = new Puntos(1600, 2500);
		Puntos puntosOtraCarta = new Puntos(1500, 2000);
		Monstruo unaCarta = new Monstruo ("Facundo", jugadorUno, 1, puntosUnaCarta);
		Monstruo otraCarta = new Monstruo ("Javier", jugadorDos, 1, puntosOtraCarta);
		
		otraCarta.cambiarPosicion();
		unaCarta.atacar( otraCarta );

		assertTrue( jugadorDos.monstruoEstaMuerto("Javier") );
	}	
	
	@Test
	public void test11CartaAtacaAOtraEnDefensaConMasPuntosYSeDestruye() {
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugaforDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugaforDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		//La carta se inicializa en modo ataque
		Puntos puntosUnaCarta = new Puntos(1600, 2000);
		Puntos puntosOtraCarta = new Puntos(1500, 2500);
		Monstruo unaCarta = new Monstruo ("Facundo", jugadorUno, 1, puntosUnaCarta);
		Monstruo otraCarta = new Monstruo ("Javier", jugadorDos, 1, puntosOtraCarta);
		
		otraCarta.cambiarPosicion();
		unaCarta.atacar( otraCarta );

		assertTrue( jugadorUno.monstruoEstaMuerto("Facundo") );
	}	
	
	@Test
	public void test12CartaAtacaAOtraEnDefensaConIgualPuntosYSeDestruyenAmabas() {

		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugaforDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugaforDos);

		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		//La carta se inicializa en modo ataque
		Puntos puntosUnaCarta = new Puntos(1600, 2000);
		Puntos puntosOtraCarta = new Puntos(1600, 2000);
		Monstruo unaCarta = new Monstruo ("Facundo", jugadorUno, 1, puntosUnaCarta);
		Monstruo otraCarta = new Monstruo ("Javier", jugadorDos, 1, puntosOtraCarta);
		
		otraCarta.cambiarPosicion();
		unaCarta.atacar( otraCarta );

		assertTrue( jugadorUno.monstruoEstaMuerto("Facundo") );
		assertTrue( jugadorDos.monstruoEstaMuerto("Javier") );
	}
	
	@Test
	public void test13AtaqueDesdePosicionInvalid() {
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugaforDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugaforDos);

		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		//La carta se inicializa en modo ataque
		Puntos puntosUnaCarta = new Puntos(1600, 2000);
		Puntos puntosOtraCarta = new Puntos(1500, 2000);
		Monstruo unaCarta = new Monstruo ("Facundo", jugadorUno, 1, puntosUnaCarta);
		Monstruo otraCarta = new Monstruo ("Javier", jugadorDos, 1, puntosOtraCarta);
		
		unaCarta.cambiarPosicion();
		
		//La carta ataca cuando esta en posicion defensa
		Assertions.assertThrows(ErrorAtaqueDesdePosicionInvalidad.class, () -> unaCarta.atacar( otraCarta ));

	}
	
}
