package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;

import cartas.DragonBlancoOjoAzul;
import cartas.DragonDefinitivo;
import cartas.InsectoComeHombres;
import cartas.Jinzo7;
import cartas.Monstruo;
import errores.ErrorAtaqueDesdePosicionInvalida;
import errores.ErrorSacrificiosNoSonLosBuenos;
import factories.FabricaDeCartas;
import factories.FabricaDeMonstruosEspeciales;

import org.junit.jupiter.api.Test;

import atributos.Estrellas;
import atributos.Puntos;
import atributos.Sacrificio;
import atributos.Vida;
import jugabilidad.Jugador;

class TestCarta {

	//private static final double DELTA = 1e-2;
	
	@Test
	public void test01CartaAtacaAOtraEnPosicionDeAtaqueConMasPuntosRestaPuntosDeVidaAlAtacante() {
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
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
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
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
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
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
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
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
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
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
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
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
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
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
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
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
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
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
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
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
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
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

		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
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
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
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
	
	@Test
	public void test14jinzo7AtacaDirectamenteAPuntosDeVida() {
		
		FabricaDeMonstruosEspeciales fabricaDeMonstruosEspeciales = new FabricaDeMonstruosEspeciales();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Jinzo7 jinzo7 = fabricaDeMonstruosEspeciales.crearJinzo7(jugadorUno);
		
		jinzo7.atacarRival();
		
		Vida vidaObtenida = jugadorDos.obtenerVida();
		Vida vidaEsperada = new Vida(7500);
		assertEquals(vidaObtenida.obtenerPuntosDeVida(),vidaEsperada.obtenerPuntosDeVida());
				

	}
	
	@Test
	public void test15DragonBlancoOjosAzules() {
		
		FabricaDeMonstruosEspeciales fabricaDeMonstruosEspeciales = new FabricaDeMonstruosEspeciales();
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugador = new Vida (8000);
		Jugador jugador = new Jugador (vidaJugador);
		DragonBlancoOjoAzul dragon = fabricaDeMonstruosEspeciales.crearDragonBlanco(jugador);
		
		Puntos puntos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(2);
		Monstruo monstruoUno = fabricaDeCartas.crearCarta("CartaUna", jugador, estrellas, puntos);
		Monstruo monstruoDos = fabricaDeCartas.crearCarta("CartaDos", jugador, estrellas, puntos);
		
		jugador.repartirCarta(dragon);
		jugador.repartirCarta(monstruoUno);
		jugador.repartirCarta(monstruoDos);
		
		
		Sacrificio monstruosASacrificar = new Sacrificio();
		monstruosASacrificar.agregarCarta(monstruoUno);
		monstruosASacrificar.agregarCarta(monstruoDos);

		
		jugador.agregarCartaEnCampo(dragon, monstruosASacrificar);
		
		assertTrue(jugador.cartaEstaMuerta(monstruoUno));
		assertTrue(jugador.cartaEstaMuerta(monstruoDos));
		
	}
	@Test
	public void test16DragonDefinitivo() {
		FabricaDeMonstruosEspeciales fabricaDeMonstruosEspeciales = new FabricaDeMonstruosEspeciales();
		
		Vida vidaJugador = new Vida (8000);
		Jugador jugador = new Jugador (vidaJugador);
		DragonBlancoOjoAzul dragonUno = fabricaDeMonstruosEspeciales.crearDragonBlanco(jugador);
		DragonBlancoOjoAzul dragonDos = fabricaDeMonstruosEspeciales.crearDragonBlanco(jugador);
		DragonBlancoOjoAzul dragonTres = fabricaDeMonstruosEspeciales.crearDragonBlanco(jugador);
		DragonDefinitivo dragonDefinitivo = fabricaDeMonstruosEspeciales.crearDragonDefinitivo(jugador);
		
		jugador.repartirCarta(dragonUno);
		jugador.repartirCarta(dragonDos);
		jugador.repartirCarta(dragonTres);
		
		jugador.repartirCarta(dragonDefinitivo);
		
		Sacrificio monstruosASacrificar = new Sacrificio();
		monstruosASacrificar.agregarCarta(dragonUno);
		monstruosASacrificar.agregarCarta(dragonDos);
		monstruosASacrificar.agregarCarta(dragonTres);

		jugador.agregarCartaEnCampo(dragonDefinitivo, monstruosASacrificar);

		assertTrue(jugador.cartaEstaMuerta(dragonUno));
		assertTrue(jugador.cartaEstaMuerta(dragonDos));
		assertTrue(jugador.cartaEstaMuerta(dragonTres));

	}
	
	@Test
	public void test17DragonDefinitivoNoTieneLosBuenosSacrificios() {
		FabricaDeMonstruosEspeciales fabricaDeMonstruosEspeciales = new FabricaDeMonstruosEspeciales();
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugador = new Vida (8000);
		Jugador jugador = new Jugador (vidaJugador);
		Puntos puntos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(2);
		
		DragonBlancoOjoAzul dragonUno = fabricaDeMonstruosEspeciales.crearDragonBlanco(jugador);
		DragonBlancoOjoAzul dragonDos = fabricaDeMonstruosEspeciales.crearDragonBlanco(jugador);
		Monstruo monstruo = fabricaDeCartas.crearCarta("Monstruo", jugador, estrellas, puntos);
		DragonDefinitivo dragonDefinitivo = fabricaDeMonstruosEspeciales.crearDragonDefinitivo(jugador);
		
		jugador.repartirCarta(dragonUno);
		jugador.repartirCarta(dragonDos);
		jugador.repartirCarta(monstruo);
		
		jugador.repartirCarta(dragonDefinitivo);
		
		Sacrificio monstruosASacrificar = new Sacrificio();
		monstruosASacrificar.agregarCarta(dragonUno);
		monstruosASacrificar.agregarCarta(dragonDos);
		monstruosASacrificar.agregarCarta(monstruo);
		
		assertThrows(ErrorSacrificiosNoSonLosBuenos.class, () -> jugador.agregarCartaEnCampo(dragonDefinitivo, monstruosASacrificar));
	}
	
	@Test
	public void test18InsesctoComeHombresDestruyeAlMonstruoAtacanteSiEstaBocaAbajo() {
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		FabricaDeMonstruosEspeciales fabricaDeMonstruosEspeciales = new FabricaDeMonstruosEspeciales();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntosDos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(2);
		
		InsectoComeHombres insecto = fabricaDeMonstruosEspeciales.crearInsectoComeHombres(jugadorUno);
		Monstruo monstruoDos = fabricaDeCartas.crearCarta("Javi", jugadorDos, estrellas, puntosDos);
		
		insecto.voltear();
		
		monstruoDos.atacar(insecto);
		
		//muere el monstruo atacante y no el insecto come hombres
		assertFalse(jugadorUno.cartaEstaMuerta(insecto));
		assertTrue(jugadorDos.cartaEstaMuerta(monstruoDos));
		
		//el jugador del monstruo que murio no pierde vida
		Vida vidaEsperada = new Vida (8000);
		Vida vidaObtenida = jugadorUno.obtenerVida();
		
		assertEquals(vidaObtenida.obtenerPuntosDeVida(),vidaEsperada.obtenerPuntosDeVida());
	}
}
