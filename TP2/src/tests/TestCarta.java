package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import cartas.Monstruo;
import errores.ErrorAtaqueDesdePosicionInvalida;
import factories.AbstractFabricaDeCartas;
import factories.FabricaDeCartas;

import org.junit.jupiter.api.Test;
import atributos.Estrellas;
import atributos.Puntos;
import atributos.Vida;
import jugabilidad.Jugador;

class TestCarta {

	//private static final double DELTA = 1e-2;
	
	@Test
	public void test01CartaAtacaAOtraEnPosicionDeAtaqueConMasPuntosRestaPuntosDeVidaAlAtacante() {
		
		AbstractFabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugaforDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugaforDos);

		//La carta se inicializa en modo ataque
		Puntos puntosUnaCarta = new Puntos(1000, 2000);
		Puntos puntosOtraCarta = new Puntos(1500, 2500);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo unaCarta = fabricaDeCartas.crearCarta("Facundo", jugadorUno, estrellas, puntosUnaCarta);
		Monstruo otraCarta = fabricaDeCartas.crearCarta("Javier", jugadorDos, estrellas, puntosOtraCarta);
		
		unaCarta.atacar( otraCarta );

		assertEquals(7500, jugadorUno.obtenerVida().obtenerPuntosDeVida() );
	}
	
	@Test
	public void test02CartaAtacaAOtraEnPosicionDeAtaqueConMenosPuntosRestaPuntosDeVidaAlDefensor() {
		
		AbstractFabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugaforDos = new Vida (8000);
		Jugador unJugador = new Jugador (vidaJugadorUno);
		Jugador otroJugador = new Jugador (vidaJugaforDos);
		
		otroJugador.asignarRival( unJugador );
		unJugador.asignarRival( otroJugador );
		
		Puntos puntosUnaCarta = new Puntos(1600, 2000);
		Puntos puntosOtraCarta = new Puntos(1500, 2000);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo unaCarta = fabricaDeCartas.crearCarta("Facundo", unJugador, estrellas, puntosUnaCarta);
		Monstruo otraCarta = fabricaDeCartas.crearCarta("Javier", otroJugador, estrellas, puntosOtraCarta);

		unaCarta.atacar(otraCarta);
		
		assertEquals(7900, otroJugador.obtenerVida().obtenerPuntosDeVida());
	}
	
	@Test
	public void test03CartaAtacaAOtraConIgualPuntosDeAtaqueNoLeRestaANadie() {
		
		AbstractFabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugaforDos = new Vida (8000);
		Jugador unJugador = new Jugador (vidaJugadorUno);
		Jugador otroJugador = new Jugador (vidaJugaforDos);

		otroJugador.asignarRival( unJugador );
		unJugador.asignarRival( otroJugador );
		
		Puntos puntosUnaCarta = new Puntos(1500, 2000);
		Puntos puntosOtraCarta = new Puntos(1500, 2000);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo unaCarta = fabricaDeCartas.crearCarta("Facundo", unJugador, estrellas, puntosUnaCarta);
		Monstruo otraCarta = fabricaDeCartas.crearCarta("Javier", otroJugador, estrellas, puntosOtraCarta);

		unaCarta.atacar(otraCarta);

		assertEquals(8000, unJugador.obtenerVida().obtenerPuntosDeVida() );
		assertEquals(8000, otroJugador.obtenerVida().obtenerPuntosDeVida() );
	}
	
	@Test
	public void test04CartaAtacaAOtraEnModoDefensaConMasPuntosYNingunoPierdeVida () {
		
		AbstractFabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugaforDos = new Vida (8000);
		Jugador unJugador = new Jugador (vidaJugadorUno);
		Jugador otroJugador = new Jugador (vidaJugaforDos);

		otroJugador.asignarRival( unJugador );
		unJugador.asignarRival( otroJugador );
		
		//Las cartas se inician en modo ataque
		Puntos puntosUnaCarta = new Puntos(1600, 2000);
		Puntos puntosOtraCarta = new Puntos(1500, 2500);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo unaCarta = fabricaDeCartas.crearCarta("Facundo", unJugador, estrellas, puntosUnaCarta);
		Monstruo otraCarta = fabricaDeCartas.crearCarta("Javier", otroJugador, estrellas, puntosOtraCarta);
		
		otraCarta.cambiarPosicion();
		unaCarta.atacar(otraCarta);

		assertEquals(8000, unJugador.obtenerVida().obtenerPuntosDeVida() );
		assertEquals(8000, otroJugador.obtenerVida().obtenerPuntosDeVida() );
	}
	
	@Test
	public void test05CartaAtacaAOtraEnDefensaConMenosPuntosYNingunoPierdeVida() {
		
		AbstractFabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugaforDos = new Vida (8000);
		Jugador unJugador = new Jugador (vidaJugadorUno);
		Jugador otroJugador = new Jugador (vidaJugaforDos);
		
		otroJugador.asignarRival( unJugador );
		unJugador.asignarRival( otroJugador );

		Puntos puntosUnaCarta = new Puntos(1600, 2000);
		Puntos puntosOtraCarta = new Puntos(1500, 1000);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo unaCarta = fabricaDeCartas.crearCarta("Facundo", unJugador, estrellas, puntosUnaCarta);
		Monstruo otraCarta = fabricaDeCartas.crearCarta("Javier", otroJugador, estrellas, puntosOtraCarta);
		
		otraCarta.cambiarPosicion();
		unaCarta.atacar(otraCarta);

		assertEquals(8000, unJugador.obtenerVida().obtenerPuntosDeVida() );
		assertEquals(8000, otroJugador.obtenerVida().obtenerPuntosDeVida() );
	}
	
	@Test
	public void test06CartaAtacaAOtraEnDefensaConIgualPuntosYNingunoPierdeVida() {
		
		AbstractFabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugaforDos = new Vida (8000);
		Jugador unJugador = new Jugador (vidaJugadorUno);
		Jugador otroJugador = new Jugador (vidaJugaforDos);
		
		otroJugador.asignarRival( unJugador );
		unJugador.asignarRival( otroJugador );

		Puntos puntosUnaCarta = new Puntos(1600, 2000);
		Puntos puntosOtraCarta = new Puntos(1500, 2000);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo unaCarta = fabricaDeCartas.crearCarta("Facundo", unJugador, estrellas, puntosUnaCarta);
		Monstruo otraCarta = fabricaDeCartas.crearCarta("Javier", otroJugador, estrellas, puntosOtraCarta);
		
		otraCarta.cambiarPosicion();	
		unaCarta.atacar(otraCarta);

		assertEquals( 8000,unJugador.obtenerVida().obtenerPuntosDeVida() );
		assertEquals( 8000,otroJugador.obtenerVida().obtenerPuntosDeVida() );
	}
	
	@Test
	public void test07CartaAtacaAOtraEnAtaqueConMenosPuntosYLaDestruye() {
		
		AbstractFabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugaforDos = new Vida (8000);
		Jugador unJugador = new Jugador (vidaJugadorUno);
		Jugador otroJugador = new Jugador (vidaJugaforDos);
		
		otroJugador.asignarRival( unJugador );
		unJugador.asignarRival( otroJugador );
		
		Puntos puntosUnaCarta = new Puntos(1600, 2000);
		Puntos puntosOtraCarta = new Puntos(1500, 2000);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo unaCarta = fabricaDeCartas.crearCarta("Facundo", unJugador, estrellas, puntosUnaCarta);
		Monstruo otraCarta = fabricaDeCartas.crearCarta("Javier", otroJugador, estrellas, puntosOtraCarta);

		unaCarta.atacar(otraCarta);
		
		assertTrue( otroJugador.cartaEstaMuerta(otraCarta) );
	}
	
	@Test
	public void test08CartaAtacaAOtraEnAtaqueConMasPuntosYSeDestruye() {
		
		AbstractFabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugaforDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugaforDos);

		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		//La carta se inicializa en modo ataque
		Puntos puntosUnaCarta = new Puntos(1000, 2000);
		Puntos puntosOtraCarta = new Puntos(1500, 2000);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo unaCarta = fabricaDeCartas.crearCarta("Facundo", jugadorUno, estrellas, puntosUnaCarta);
		Monstruo otraCarta = fabricaDeCartas.crearCarta("Javier", jugadorDos, estrellas, puntosOtraCarta);
		
		unaCarta.atacar( otraCarta );

		assertTrue( jugadorUno.cartaEstaMuerta(unaCarta) );
	}
	
	@Test
	public void test09CartaAtacaAOtraEnAtaqueConIgualPuntosYSeDestruyenAmbas() {
		
		AbstractFabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugaforDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugaforDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		//La carta se inicializa en modo ataque
		Puntos puntosUnaCarta = new Puntos(1500, 2000);
		Puntos puntosOtraCarta = new Puntos(1500, 2000);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo unaCarta = fabricaDeCartas.crearCarta("Facundo", jugadorUno, estrellas, puntosUnaCarta);
		Monstruo otraCarta = fabricaDeCartas.crearCarta("Javier", jugadorDos, estrellas, puntosOtraCarta);

		unaCarta.atacar( otraCarta );

		assertTrue( jugadorUno.cartaEstaMuerta(unaCarta) );
		assertTrue( jugadorDos.cartaEstaMuerta(otraCarta) );
	}
	
	@Test
	public void test10CartaAtacaAOtraEnDefensaConMenosPuntosYLaDestruye() {
		
		AbstractFabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugaforDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugaforDos);

		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		//La carta se inicializa en modo ataque
		Puntos puntosUnaCarta = new Puntos(1600, 2500);
		Puntos puntosOtraCarta = new Puntos(1500, 2000);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo unaCarta = fabricaDeCartas.crearCarta("Facundo", jugadorUno, estrellas, puntosUnaCarta);
		Monstruo otraCarta = fabricaDeCartas.crearCarta("Javier", jugadorDos, estrellas, puntosOtraCarta);
		
		otraCarta.cambiarPosicion();
		unaCarta.atacar( otraCarta );

		assertTrue( jugadorDos.cartaEstaMuerta(otraCarta) );
	}	
	
	@Test
	public void test11CartaAtacaAOtraEnDefensaConMasPuntosYSeDestruye() {
		
		AbstractFabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugaforDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugaforDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		//La carta se inicializa en modo ataque
		Puntos puntosUnaCarta = new Puntos(1600, 2000);
		Puntos puntosOtraCarta = new Puntos(1500, 2500);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo unaCarta = fabricaDeCartas.crearCarta("Facundo", jugadorUno, estrellas, puntosUnaCarta);
		Monstruo otraCarta = fabricaDeCartas.crearCarta("Javier", jugadorDos, estrellas, puntosOtraCarta);
		
		otraCarta.cambiarPosicion();
		unaCarta.atacar( otraCarta );

		assertTrue( jugadorUno.cartaEstaMuerta(unaCarta) );
	}	
	
	@Test
	public void test12CartaAtacaAOtraEnDefensaConIgualPuntosYSeDestruyenAmabas() {

		AbstractFabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugaforDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugaforDos);

		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		//La carta se inicializa en modo ataque
		Puntos puntosUnaCarta = new Puntos(1600, 2000);
		Puntos puntosOtraCarta = new Puntos(1600, 2000);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo unaCarta = fabricaDeCartas.crearCarta("Facundo", jugadorUno, estrellas, puntosUnaCarta);
		Monstruo otraCarta =fabricaDeCartas.crearCarta("Javier", jugadorDos, estrellas, puntosOtraCarta);
		
		otraCarta.cambiarPosicion();
		unaCarta.atacar( otraCarta );

		assertTrue( jugadorUno.cartaEstaMuerta(unaCarta) );
		assertTrue( jugadorDos.cartaEstaMuerta(otraCarta) );
	}
	
	@Test
	public void test13AtaqueDesdePosicionInvalid() {
		
		AbstractFabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugaforDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugaforDos);

		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		//La carta se inicializa en modo ataque
		Puntos puntosUnaCarta = new Puntos(1600, 2000);
		Puntos puntosOtraCarta = new Puntos(1500, 2000);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo unaCarta = fabricaDeCartas.crearCarta("Facundo", jugadorUno, estrellas, puntosUnaCarta);
		Monstruo otraCarta = fabricaDeCartas.crearCarta("Javier", jugadorDos, estrellas, puntosOtraCarta);
		
		unaCarta.cambiarPosicion();
		
		//La carta ataca cuando esta en posicion defensa
		Assertions.assertThrows(ErrorAtaqueDesdePosicionInvalida.class, () -> unaCarta.atacar( otraCarta ));

	}
	
	
	
}
