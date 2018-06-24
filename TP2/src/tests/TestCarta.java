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
		
		Monstruo unaCarta = fabricaDeCartas.crearMonstruoPersonalizado("Facundo", jugadorUno, estrellas, puntosUnaCarta);
		Monstruo otraCarta = fabricaDeCartas.crearMonstruoPersonalizado("Javier", jugadorDos, estrellas, puntosOtraCarta);
		
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
		
		Monstruo unaCarta = fabricaDeCartas.crearMonstruoPersonalizado("Facundo", unJugador, estrellas, puntosUnaCarta);
		Monstruo otraCarta = fabricaDeCartas.crearMonstruoPersonalizado("Javier", otroJugador, estrellas, puntosOtraCarta);

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
		
		Monstruo unaCarta = fabricaDeCartas.crearMonstruoPersonalizado("Facundo", unJugador, estrellas, puntosUnaCarta);
		Monstruo otraCarta = fabricaDeCartas.crearMonstruoPersonalizado("Javier", otroJugador, estrellas, puntosOtraCarta);

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
		
		Monstruo unaCarta = fabricaDeCartas.crearMonstruoPersonalizado("Facundo", unJugador, estrellas, puntosUnaCarta);
		Monstruo otraCarta = fabricaDeCartas.crearMonstruoPersonalizado("Javier", otroJugador, estrellas, puntosOtraCarta);
		
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
		
		Monstruo unaCarta = fabricaDeCartas.crearMonstruoPersonalizado("Facundo", unJugador, estrellas, puntosUnaCarta);
		Monstruo otraCarta = fabricaDeCartas.crearMonstruoPersonalizado("Javier", otroJugador, estrellas, puntosOtraCarta);
		
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
		
		Monstruo unaCarta = fabricaDeCartas.crearMonstruoPersonalizado("Facundo", unJugador, estrellas, puntosUnaCarta);
		Monstruo otraCarta = fabricaDeCartas.crearMonstruoPersonalizado("Javier", otroJugador, estrellas, puntosOtraCarta);
		
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
		
		Monstruo unaCarta = new Monstruo("Facundo", unJugador, estrellas, puntosUnaCarta);
		Monstruo otraCarta = new Monstruo("Javier", otroJugador, estrellas, puntosOtraCarta);

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
		
		Monstruo unaCarta = new Monstruo("Facundo", jugadorUno, estrellas, puntosUnaCarta);
		Monstruo otraCarta = new Monstruo("Javier", jugadorDos, estrellas, puntosOtraCarta);
		
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
		
		Monstruo unaCarta = new Monstruo("Facundo", jugadorUno, estrellas, puntosUnaCarta);
		Monstruo otraCarta = new Monstruo("Javier", jugadorDos, estrellas, puntosOtraCarta);

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
		
		Monstruo unaCarta = new Monstruo("Facundo", jugadorUno, estrellas, puntosUnaCarta);
		Monstruo otraCarta = new Monstruo("Javier", jugadorDos, estrellas, puntosOtraCarta);
		
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
		
		Monstruo unaCarta = new Monstruo("Facundo", jugadorUno, estrellas, puntosUnaCarta);
		Monstruo otraCarta = new Monstruo("Javier", jugadorDos, estrellas, puntosOtraCarta);
		
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
		
		Monstruo unaCarta = new Monstruo("Facundo", jugadorUno, estrellas, puntosUnaCarta);
		Monstruo otraCarta = new Monstruo("Javier", jugadorDos, estrellas, puntosOtraCarta);
		
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
		
		Monstruo unaCarta = new Monstruo("Facundo", jugadorUno, estrellas, puntosUnaCarta);
		Monstruo otraCarta = new Monstruo("Javier", jugadorDos, estrellas, puntosOtraCarta);
		
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
		
		CreadorDeJinzo7 creador = new CreadorDeJinzo7(jugadorUno);
		BuilderDeCartas builder = new BuilderDeCartas(creador);
		Carta jinzo7 = builder.obtenerCarta();
		
		Atacable otroMonstruo = new Monstruo("Facu", jugadorDos, estrellas, puntos);
		
		((Atacable) jinzo7).atacar(otroMonstruo);
		
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
		
		CreadorDeJinzo7 creador = new CreadorDeJinzo7(jugadorUno);
		BuilderDeCartas builder = new BuilderDeCartas(creador);
		Carta jinzo7 = builder.obtenerCarta();
		
		Atacable otroMonstruo = new Monstruo("Facu", jugadorDos, estrellas, puntos);
		
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
		
		CreadorDeDragonBlanco creador = new CreadorDeDragonBlanco(jugador);
		BuilderDeCartas builder = new BuilderDeCartas(creador);
		Carta dragon = builder.obtenerCarta();
		
		Puntos puntos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(2);
		Monstruo monstruoUno = new Monstruo("CartaUna", jugador, estrellas, puntos);
		Monstruo monstruoDos = new Monstruo("CartaDos", jugador, estrellas, puntos);
		
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
		
		CreadorDeDragonBlanco creador1 = new CreadorDeDragonBlanco(jugador);
		BuilderDeCartas builder1 = new BuilderDeCartas(creador1);
		Carta dragonUno = builder1.obtenerCarta();
		
		CreadorDeDragonBlanco creador2 = new CreadorDeDragonBlanco(jugador);
		BuilderDeCartas builder2 = new BuilderDeCartas(creador2);
		Carta dragonDos = builder2.obtenerCarta();
		
		CreadorDeDragonBlanco creador3 = new CreadorDeDragonBlanco(jugador);
		BuilderDeCartas builder3 = new BuilderDeCartas(creador3);
		Carta dragonTres = builder3.obtenerCarta();
		
		CreadorDeDragonDefinitivo creador4 = new CreadorDeDragonDefinitivo(jugador);
		BuilderDeCartas builder4 = new BuilderDeCartas(creador4);
		Carta dragonDefinitivo = builder4.obtenerCarta();
		
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

		Monstruo monstruo = new Monstruo("Monstruo", jugador, estrellas, puntos);
		
		CreadorDeDragonBlanco creador1 = new CreadorDeDragonBlanco(jugador);
		BuilderDeCartas builder1 = new BuilderDeCartas(creador1);
		Carta dragonUno = builder1.obtenerCarta();
		
		CreadorDeDragonBlanco creador2 = new CreadorDeDragonBlanco(jugador);
		BuilderDeCartas builder2 = new BuilderDeCartas(creador2);
		Carta dragonDos = builder2.obtenerCarta();
		
		CreadorDeDragonDefinitivo creador3 = new CreadorDeDragonDefinitivo(jugador);
		BuilderDeCartas builder3 = new BuilderDeCartas(creador3);
		Carta dragonDefinitivo = builder3.obtenerCarta();
		
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
		
		CreadorDeInsectoComeHombres creador = new CreadorDeInsectoComeHombres(jugadorUno);
		BuilderDeCartas builder = new BuilderDeCartas(creador);
		Carta insecto = builder.obtenerCarta();
		
		Monstruo monstruoDos = new Monstruo("Javi", jugadorDos, estrellas, puntosDos);
		
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
