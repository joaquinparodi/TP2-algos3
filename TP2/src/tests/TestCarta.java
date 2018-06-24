package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;

import cartas.Atacable;
import cartas.Carta;
import cartas.Monstruo;
import errores.ErrorAtaqueDesdePosicionInvalida;
import errores.ErrorCartasSacrificadasIncorrectas;
import factories.BuilderDeCartas;
import factories.CreadorDeDragonBlanco;
import factories.CreadorDeDragonDefinitivo;
import factories.CreadorDeInsectoComeHombres;
import factories.CreadorDeJinzo7;
import factories.FabricaDeCartas;

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
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugaforDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugaforDos);

		//La carta se inicializa en modo ataque
		Puntos puntosUnaCarta = new Puntos(1000, 2000);
		Puntos puntosOtraCarta = new Puntos(1500, 2500);
		Estrellas estrellas = new Estrellas(1);
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas(jugadorUno);
		FabricaDeCartas fabricaDeCartas2 = new FabricaDeCartas(jugadorDos);
		
		Monstruo unaCarta = fabricaDeCartas.crearMonstruoPersonalizado("Facundo", estrellas, puntosUnaCarta);
		Monstruo otraCarta = fabricaDeCartas2.crearMonstruoPersonalizado("Javier", estrellas, puntosOtraCarta);
		
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
		Estrellas estrellas = new Estrellas(1);
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas(unJugador);
		FabricaDeCartas fabricaDeCartas2 = new FabricaDeCartas(otroJugador);
		
		Monstruo unaCarta = fabricaDeCartas.crearMonstruoPersonalizado("Facundo", estrellas, puntosUnaCarta);
		Monstruo otraCarta = fabricaDeCartas2.crearMonstruoPersonalizado("Javier", estrellas, puntosOtraCarta);

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
		Estrellas estrellas = new Estrellas(1);
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas(unJugador);
		FabricaDeCartas fabricaDeCartas2 = new FabricaDeCartas(otroJugador);
		
		Monstruo unaCarta = fabricaDeCartas.crearMonstruoPersonalizado("Facundo", estrellas, puntosUnaCarta);
		Monstruo otraCarta = fabricaDeCartas2.crearMonstruoPersonalizado("Javier", estrellas, puntosOtraCarta);

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
		Estrellas estrellas = new Estrellas(1);
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas(unJugador);
		FabricaDeCartas fabricaDeCartas2 = new FabricaDeCartas(otroJugador);
		
		Monstruo unaCarta = fabricaDeCartas.crearMonstruoPersonalizado("Facundo", estrellas, puntosUnaCarta);
		Monstruo otraCarta = fabricaDeCartas2.crearMonstruoPersonalizado("Javier", estrellas, puntosOtraCarta);
		
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
		Estrellas estrellas = new Estrellas(1);
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas(unJugador);
		FabricaDeCartas fabricaDeCartas2 = new FabricaDeCartas(otroJugador);
		
		Monstruo unaCarta = fabricaDeCartas.crearMonstruoPersonalizado("Facundo", estrellas, puntosUnaCarta);
		Monstruo otraCarta = fabricaDeCartas2.crearMonstruoPersonalizado("Javier", estrellas, puntosOtraCarta);
		
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
		Estrellas estrellas = new Estrellas(1);
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas(unJugador);
		FabricaDeCartas fabricaDeCartas2 = new FabricaDeCartas(otroJugador);
		
		Monstruo unaCarta = fabricaDeCartas.crearMonstruoPersonalizado("Facundo", estrellas, puntosUnaCarta);
		Monstruo otraCarta = fabricaDeCartas2.crearMonstruoPersonalizado("Javier", estrellas, puntosOtraCarta);
		
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
		Estrellas estrellas = new Estrellas(1);
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas(unJugador);
		FabricaDeCartas fabricaDeCartas2 = new FabricaDeCartas(otroJugador);
		
		Monstruo unaCarta = fabricaDeCartas.crearMonstruoPersonalizado("Facundo", estrellas, puntosUnaCarta);
		Monstruo otraCarta = fabricaDeCartas2.crearMonstruoPersonalizado("Javier", estrellas, puntosOtraCarta);

		unaCarta.atacar(otraCarta);
		
		assertTrue( otroJugador.cartaEstaMuerta(otraCarta) );
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
		Estrellas estrellas = new Estrellas(1);
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas(jugadorUno);
		FabricaDeCartas fabricaDeCartas2 = new FabricaDeCartas(jugadorDos);
		
		Monstruo unaCarta = fabricaDeCartas.crearMonstruoPersonalizado("Facundo", estrellas, puntosUnaCarta);
		Monstruo otraCarta = fabricaDeCartas2.crearMonstruoPersonalizado("Javier", estrellas, puntosOtraCarta);
		
		unaCarta.atacar( otraCarta );

		assertTrue( jugadorUno.cartaEstaMuerta(unaCarta) );
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
		Estrellas estrellas = new Estrellas(1);
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas(jugadorUno);
		FabricaDeCartas fabricaDeCartas2 = new FabricaDeCartas(jugadorDos);
		
		Monstruo unaCarta = fabricaDeCartas.crearMonstruoPersonalizado("Facundo", estrellas, puntosUnaCarta);
		Monstruo otraCarta = fabricaDeCartas2.crearMonstruoPersonalizado("Javier", estrellas, puntosOtraCarta);

		unaCarta.atacar( otraCarta );

		assertTrue( jugadorUno.cartaEstaMuerta(unaCarta) );
		assertTrue( jugadorDos.cartaEstaMuerta(otraCarta) );
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
		Estrellas estrellas = new Estrellas(1);
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas(jugadorUno);
		FabricaDeCartas fabricaDeCartas2 = new FabricaDeCartas(jugadorDos);
		
		Monstruo unaCarta = fabricaDeCartas.crearMonstruoPersonalizado("Facundo", estrellas, puntosUnaCarta);
		Monstruo otraCarta = fabricaDeCartas2.crearMonstruoPersonalizado("Javier", estrellas, puntosOtraCarta);
		
		otraCarta.cambiarPosicion();
		unaCarta.atacar( otraCarta );

		assertTrue( jugadorDos.cartaEstaMuerta(otraCarta) );
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
		Estrellas estrellas = new Estrellas(1);
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas(jugadorUno);
		FabricaDeCartas fabricaDeCartas2 = new FabricaDeCartas(jugadorDos);
		
		Monstruo unaCarta = fabricaDeCartas.crearMonstruoPersonalizado("Facundo", estrellas, puntosUnaCarta);
		Monstruo otraCarta = fabricaDeCartas2.crearMonstruoPersonalizado("Javier", estrellas, puntosOtraCarta);
		
		otraCarta.cambiarPosicion();
		unaCarta.atacar( otraCarta );

		assertTrue( jugadorUno.cartaEstaMuerta(unaCarta) );
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
		Estrellas estrellas = new Estrellas(1);
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas(jugadorUno);
		FabricaDeCartas fabricaDeCartas2 = new FabricaDeCartas(jugadorDos);
		
		Monstruo unaCarta = fabricaDeCartas.crearMonstruoPersonalizado("Facundo", estrellas, puntosUnaCarta);
		Monstruo otraCarta = fabricaDeCartas2.crearMonstruoPersonalizado("Javier", estrellas, puntosOtraCarta);
		
		otraCarta.cambiarPosicion();
		unaCarta.atacar( otraCarta );

		assertTrue( jugadorUno.cartaEstaMuerta(unaCarta) );
		assertTrue( jugadorDos.cartaEstaMuerta(otraCarta) );
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
		Estrellas estrellas = new Estrellas(1);
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas(jugadorUno);
		FabricaDeCartas fabricaDeCartas2 = new FabricaDeCartas(jugadorDos);
		
		Monstruo unaCarta = fabricaDeCartas.crearMonstruoPersonalizado("Facundo", estrellas, puntosUnaCarta);
		Monstruo otraCarta = fabricaDeCartas2.crearMonstruoPersonalizado("Javier", estrellas, puntosOtraCarta);
		
		unaCarta.cambiarPosicion();
		
		//La carta ataca cuando esta en posicion defensa
		Assertions.assertThrows(ErrorAtaqueDesdePosicionInvalida.class, () -> unaCarta.atacar( otraCarta ));

	}
	
	@Test
	public void test14jinzo7AtacaDirectamenteAPuntosDeVida() {
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		Estrellas estrellas = new Estrellas(2);
		
		Puntos puntos = new Puntos (2000,2000);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas(jugadorUno);
		FabricaDeCartas fabricaDeCartas2 = new FabricaDeCartas(jugadorDos);
		
		Monstruo jinzo7 = fabricaDeCartas.crearJinzo7();
		Monstruo otroMonstruo = fabricaDeCartas2.crearMonstruoPersonalizado("Facu", estrellas, puntos);
		
		jinzo7.atacar(otroMonstruo);
		
		//jinzo 7 inicializa boca arriba (efecto activado)
		//le resto 500 puntos de vida al rival a pesar de que el monstruo que ataco era mas poderoso
		Vida vidaObtenida = jugadorDos.obtenerVida();
		Vida vidaEsperada = new Vida(7500);
		assertEquals(vidaObtenida.obtenerPuntosDeVida(),vidaEsperada.obtenerPuntosDeVida());
		
		//jinzo 7 no murio
		assertFalse(jugadorUno.cartaEstaMuerta(jinzo7));

	}
	
	@Test
	public void test14bisJinzo7BocaAbajoAtacaComoUnMonstruoCualquiera () {	
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		Estrellas estrellas = new Estrellas(2);
		
		Puntos puntos = new Puntos (2000,2000);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas(jugadorUno);
		FabricaDeCartas fabricaDeCartas2 = new FabricaDeCartas(jugadorDos);
		
		Monstruo jinzo7 = fabricaDeCartas.crearJinzo7();
		Monstruo otroMonstruo = fabricaDeCartas2.crearMonstruoPersonalizado("Facu", estrellas, puntos);
		
		jinzo7.voltear();
		
		((Atacable) jinzo7).atacar(otroMonstruo);
		
		//jinzo 7 esta boca abajo (efecto desactivado)
		//el jugador que ataca pierde vida
		Vida vidaObtenida = jugadorUno.obtenerVida();
		Vida vidaEsperada = new Vida(6500);
		assertEquals(vidaObtenida.obtenerPuntosDeVida(),vidaEsperada.obtenerPuntosDeVida());
		
		//jinzo 7 murio
		assertTrue(jugadorUno.cartaEstaMuerta(jinzo7));
	
	}
	
	@Test
	public void test15DragonBlancoOjosAzules() {
		
		Vida vidaJugador = new Vida (8000);
		Jugador jugador = new Jugador (vidaJugador);
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas(jugador);
		Monstruo dragon = fabricaDeCartas.crearDragonBlancoDeOjosAzules();
		
		Puntos puntos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(2);
		Monstruo monstruoUno = fabricaDeCartas.crearMonstruoPersonalizado("CartaUna", estrellas, puntos);
		Monstruo monstruoDos = fabricaDeCartas.crearMonstruoPersonalizado("CartaDos", estrellas, puntos);
		
		jugador.repartirCarta(dragon);
		jugador.repartirCarta(monstruoUno);
		jugador.repartirCarta(monstruoDos);
		
		
		Sacrificio monstruosASacrificar = new Sacrificio();
		monstruosASacrificar.agregarCarta(monstruoUno);
		monstruosASacrificar.agregarCarta(monstruoDos);

		
		jugador.agregarCartaEnCampo((Monstruo) dragon, monstruosASacrificar);
		
		assertTrue(jugador.cartaEstaMuerta(monstruoUno));
		assertTrue(jugador.cartaEstaMuerta(monstruoDos));
		
	}
	@Test
	public void test16DragonDefinitivo() {

		Vida vidaJugador = new Vida (8000);
		Jugador jugador = new Jugador (vidaJugador);
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas(jugador);
		Monstruo dragonUno = fabricaDeCartas.crearDragonBlancoDeOjosAzules();
		Monstruo dragonDos = fabricaDeCartas.crearDragonBlancoDeOjosAzules();
		Monstruo dragonTres = fabricaDeCartas.crearDragonBlancoDeOjosAzules();
		Monstruo dragonDefinitivo = fabricaDeCartas.crearDragonDefinitivo();
		
		jugador.repartirCarta(dragonUno);
		jugador.repartirCarta(dragonDos);
		jugador.repartirCarta(dragonTres);
		
		jugador.repartirCarta(dragonDefinitivo);
		
		Sacrificio monstruosASacrificar = new Sacrificio();
		monstruosASacrificar.agregarCarta(dragonUno);
		monstruosASacrificar.agregarCarta(dragonDos);
		monstruosASacrificar.agregarCarta(dragonTres);

		jugador.agregarCartaEnCampo((Monstruo) dragonDefinitivo, monstruosASacrificar);

		assertTrue(jugador.cartaEstaMuerta(dragonUno));
		assertTrue(jugador.cartaEstaMuerta(dragonDos));
		assertTrue(jugador.cartaEstaMuerta(dragonTres));

	}
	
	@Test
	public void test17DragonDefinitivoNoTieneLosBuenosSacrificios() {
		
		Vida vidaJugador = new Vida (8000);
		Jugador jugador = new Jugador (vidaJugador);
		Puntos puntos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(2);
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas(jugador);
		Monstruo dragonUno = fabricaDeCartas.crearDragonBlancoDeOjosAzules();
		Monstruo dragonDos = fabricaDeCartas.crearDragonBlancoDeOjosAzules();
		
		Monstruo monstruo = fabricaDeCartas.crearMonstruoPersonalizado("Monstruo", estrellas, puntos);
		
		Monstruo dragonDefinitivo = fabricaDeCartas.crearDragonDefinitivo();
		
		jugador.repartirCarta(dragonUno);
		jugador.repartirCarta(dragonDos);
		jugador.repartirCarta(monstruo);
		
		jugador.repartirCarta(dragonDefinitivo);
		
		Sacrificio monstruosASacrificar = new Sacrificio();
		monstruosASacrificar.agregarCarta(dragonUno);
		monstruosASacrificar.agregarCarta(dragonDos);
		monstruosASacrificar.agregarCarta(monstruo);
		
		assertThrows(ErrorCartasSacrificadasIncorrectas.class, () -> jugador.agregarCartaEnCampo((Monstruo) dragonDefinitivo, monstruosASacrificar));
	}
	
	@Test
	public void test18InsesctoComeHombresDestruyeAlMonstruoAtacanteSiEstaBocaAbajo() {

		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntosDos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(2);
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas(jugadorUno);
		FabricaDeCartas fabricaDeCartas2 = new FabricaDeCartas(jugadorDos);
		Monstruo insecto = fabricaDeCartas.crearInsectoComeHombres();
		
		Monstruo monstruoDos = fabricaDeCartas2.crearMonstruoPersonalizado("Javi", estrellas, puntosDos);
		
		insecto.voltear();
		
		monstruoDos.atacar((Atacable) insecto);
		
		//muere el monstruo atacante y no el insecto come hombres
		assertFalse(jugadorUno.cartaEstaMuerta(insecto));
		assertTrue(jugadorDos.cartaEstaMuerta(monstruoDos));
		
		//el jugador del monstruo que murio no pierde vida
		Vida vidaEsperada = new Vida (8000);
		Vida vidaObtenida = jugadorUno.obtenerVida();
		
		assertEquals(vidaObtenida.obtenerPuntosDeVida(),vidaEsperada.obtenerPuntosDeVida());
	}
}
