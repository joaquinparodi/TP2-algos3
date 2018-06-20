package tests;

import jugabilidad.Baraja;
import jugabilidad.ControladorDelJuego;
import jugabilidad.Jugador;
import cartas.Campo;
import cartas.Magica;
import org.junit.jupiter.api.Test;
import atributos.Efecto;
import atributos.EfectoAgujeroNegro;
import atributos.EfectoCilindroMagico;
import atributos.EfectoDeCampo;
import atributos.EfectoDeTrampa;
import atributos.EfectoFisura;
import atributos.EfectoOllaDeLaCodicia;
import atributos.EfectoReinforcements;
import atributos.EfectoSogen;
import atributos.EfectoWasteland;
import atributos.Estrellas;
import atributos.Puntos;
import atributos.Sacrificio;
import atributos.Vida;
import cartas.Monstruo;
import cartas.Trampa;
import errores.ErrorCartaAUsarNoPerteneceAlJugadorQueLaIntentaUsar;
import errores.ErrorCartaEnManoNoPuedeAtacar;
import errores.ErrorIntentaAtacarACartaPropia;
import errores.ErrorSacrificiosInsuficientes;
import factories.FabricaDeCartas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;

class TestJugador {

    @Test
    public void test01JugadorNuevoTieneXPuntos (){
        
    	Vida vida = new Vida(8000);
    	Jugador unJugador = new Jugador(vida);

        double vidaEsperada = 8000;
        double vidaObtenida = unJugador.obtenerVida().obtenerPuntosDeVida();

        assertEquals(vidaEsperada,vidaObtenida);
    }

    @Test
    public void test02JugadorRecibeDanio(){

    	Vida vida = new Vida(8000);
    	Jugador unJugador = new Jugador(vida);

        unJugador.hacerDanio(2000);

        double vidaEsperada = 6000;
        double vidaObtenida = unJugador.obtenerVida().obtenerPuntosDeVida();

        assertEquals(vidaEsperada,vidaObtenida);
    }
    
	@Test
	public void test03ColocarMosnstruoConEstrellasInferiorACincoYNoSacrificaANadie() {
    	
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vida = new Vida(8000);
    	Jugador jugador = new Jugador(vida);
		
		Estrellas estrellas = new Estrellas(1);
    	Puntos puntos = new Puntos(2000, 2000);
    	
		Monstruo monstruoUno = fabricaDeCartas.crearCarta("Auriane", jugador, estrellas,puntos);
		Monstruo monstruoDos = fabricaDeCartas.crearCarta("Javir", jugador, estrellas, puntos);
		Monstruo monstruoTres = fabricaDeCartas.crearCarta("Facundo", jugador, estrellas, puntos);
		
		jugador.repartirCarta(monstruoUno);
		jugador.repartirCarta(monstruoDos);
		jugador.repartirCarta(monstruoTres);
		
		jugador.agregarCartaEnCampo(monstruoUno);
		jugador.agregarCartaEnCampo(monstruoDos);
		
		//Al agregar "Facundo", no se tendrian que borrar ninguno de los 2 mosntruos del tablero
		jugador.agregarCartaEnCampo(monstruoTres);
		
		assertFalse( jugador.cartaEstaMuerta(monstruoUno) );
		assertFalse( jugador.cartaEstaMuerta(monstruoDos) );
	}
	
	@Test
	public void test04ColocarMonstruoConSiesteEstrellasRequiereDosSacrificios() {
 
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vida = new Vida(8000);
    	Jugador jugador = new Jugador(vida);
		
		Estrellas estrellas = new Estrellas(1);
		Estrellas estrellasMonstruoUno = new Estrellas(7);
		Puntos puntos = new Puntos(2000, 2000);
		
		Monstruo monstruoUno = fabricaDeCartas.crearCarta("joaco", jugador, estrellasMonstruoUno, puntos);
		Monstruo monstruoDos = fabricaDeCartas.crearCarta("javi", jugador, estrellas, puntos);
		Monstruo monstruoTres = fabricaDeCartas.crearCarta("facu", jugador, estrellas, puntos);
		
		jugador.repartirCarta(monstruoUno);
		jugador.repartirCarta(monstruoDos);
		jugador.repartirCarta(monstruoTres);
		
		Sacrificio monstruosASacrificar = new Sacrificio();
		monstruosASacrificar.agregarCarta(monstruoDos);
		monstruosASacrificar.agregarCarta(monstruoTres);
		
		jugador.agregarCartaEnCampo(monstruoUno, monstruosASacrificar);
		
		assertTrue( jugador.cartaEstaMuerta(monstruoDos) );
		assertTrue( jugador.cartaEstaMuerta(monstruoTres) );
	}
	
	@Test
	public void test05ColocarMonstruoConCincoEstrellasRequiere1Sacrificio() {
    
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vida = new Vida(8000);
    	Jugador jugador = new Jugador(vida);
		
		Estrellas estrellas = new Estrellas(1);
		Estrellas estrellasMonstruoUno = new Estrellas(5);
		Puntos puntos = new Puntos(2000, 2000);
		
		Monstruo monstruoUno = fabricaDeCartas.crearCarta("joaco", jugador, estrellasMonstruoUno, puntos);
		Monstruo monstruoDos = fabricaDeCartas.crearCarta("javi", jugador, estrellas, puntos);
		
		jugador.repartirCarta(monstruoUno);
		jugador.repartirCarta(monstruoDos);
		
		Sacrificio monstruosASacrificar = new Sacrificio();
		monstruosASacrificar.agregarCarta(monstruoDos);
		
		jugador.agregarCartaEnCampo(monstruoUno, monstruosASacrificar);
		
		assert( jugador.cartaEstaMuerta(monstruoDos) );
	}
	
	@Test
	public void test06VerificarSiElMonstroColocadoSeEncuentraEnElCampoLuegoDeSacrificio() {

		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
    	Vida vida = new Vida(8000);
    	Jugador jugador = new Jugador(vida);
		
		Puntos puntos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(1);
		Estrellas estrellasMonstruoUno = new Estrellas(5);
		
		Monstruo monstruoUno = fabricaDeCartas.crearCarta("joaco", jugador, estrellasMonstruoUno, puntos);
		Monstruo monstruoDos = fabricaDeCartas.crearCarta("javi", jugador, estrellas, puntos);
		
		jugador.repartirCarta(monstruoUno);
		jugador.repartirCarta(monstruoDos);
		
		Sacrificio monstruosASacrificar = new Sacrificio();
		monstruosASacrificar.agregarCarta(monstruoDos);
		
		jugador.agregarCartaEnCampo(monstruoUno, monstruosASacrificar);
		
		assert( ((jugador.obtenerCampo()).obtenerMonstruos()).pertenece(monstruoUno) );
	}
	
	@Test
	public void test07InvocarMonstruoConCincoEstrellasSinEnviarMonstruosASacrificarLanzaError() {
		
		Vida vida = new Vida(8000);
    	Jugador jugador = new Jugador(vida);

		Estrellas estrellasMonstruoUno = new Estrellas(5);
		Puntos puntos = new Puntos(2000, 2000);
	
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		Monstruo monstruoUno = fabricaDeCartas.crearCarta("joaco", jugador, estrellasMonstruoUno, puntos);
		
		jugador.repartirCarta(monstruoUno);
		
		Sacrificio monstruosASacrificar = new Sacrificio();
		
		Assertions.assertThrows(ErrorSacrificiosInsuficientes.class, () -> jugador.agregarCartaEnCampo(monstruoUno, monstruosASacrificar));
	}
	
	@Test
	public void test08ColocarMonstruoConTresEstrellasNoRequiereSacrificio() {
    	
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vida = new Vida(8000);
    	Jugador jugador = new Jugador(vida);
		
		Estrellas estrellas = new Estrellas(3);
		Puntos puntos = new Puntos(2000, 2000);
		
		Monstruo monstruoUno = fabricaDeCartas.crearCarta("joaco", jugador, estrellas, puntos);
		Monstruo monstruoDos = fabricaDeCartas.crearCarta("javi", jugador, estrellas, puntos);
		
		jugador.repartirCarta(monstruoUno);
		jugador.repartirCarta(monstruoDos);
		
		jugador.agregarCartaEnCampo(monstruoUno);
		assertFalse( jugador.cartaEstaMuerta(monstruoDos) );
	}
	
	@Test
	public void test09AgregarAgujeroNegroEliminaTodasLasCartas () {
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(3);
		
		Monstruo monstruoUno = fabricaDeCartas.crearCarta("facu", jugadorUno, estrellas, puntos);
		Monstruo monstruoDos = fabricaDeCartas.crearCarta("javi", jugadorDos, estrellas, puntos);
		
		Efecto efecto = new EfectoAgujeroNegro ();
		Magica agujeroNegro = fabricaDeCartas.crearCarta("Agujero Negro", jugadorUno, efecto);
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorUno.repartirCarta(agujeroNegro);
		jugadorDos.repartirCarta(monstruoDos);
		
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		
		jugadorUno.agregarCartaEnCampo(agujeroNegro);
		
		assertTrue(jugadorUno.cartaEstaMuerta(monstruoUno));
		assertTrue(jugadorDos.cartaEstaMuerta(monstruoDos));
	}
	
	@Test
	public void test10AgregarAgujeroNegroNoQuitaVidaAJugadores () {
			
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Efecto efecto = new EfectoAgujeroNegro ();
		Magica agujeroNegro = fabricaDeCartas.crearCarta("Agujero Negro", jugadorUno, efecto);
		
		jugadorUno.repartirCarta(agujeroNegro);
		jugadorUno.agregarCartaEnCampo(agujeroNegro);
		
		assertTrue(jugadorUno.obtenerVida().obtenerPuntosDeVida() == 8000);
		assertTrue(jugadorDos.obtenerVida().obtenerPuntosDeVida() == 8000);
		
	}
	
	@Test
	public void test11AgujeroNegroBocaAbajoNoEnviaMonstruosAlCementerio () {
				
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo monstruoUno = fabricaDeCartas.crearCarta("facu", jugadorUno, estrellas, puntos);
		Monstruo monstruoDos = fabricaDeCartas.crearCarta("javi", jugadorDos, estrellas, puntos);
		
		Efecto efecto = new EfectoAgujeroNegro ();
		Magica agujeroNegro = fabricaDeCartas.crearCarta("Agujero Negro", jugadorUno, efecto);
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorUno.repartirCarta(agujeroNegro);
		jugadorDos.repartirCarta(monstruoDos);
		
		jugadorUno.voltearCartaDeMano("Agujero Negro");
		
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		jugadorUno.agregarCartaEnCampo(agujeroNegro);
		
		assertFalse(jugadorUno.cartaEstaMuerta(monstruoUno) );
		assertFalse(jugadorDos.cartaEstaMuerta(monstruoDos) );
		
	}
	
	@Test
	public void test12AgregarCartaDeCampoWastelandAumenta300PuntoDeAtaquesDeljugadorQuePusoLaCarta() {
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntosUno = new Puntos(2000, 2000);
		Puntos puntosDos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo monstruoUno = fabricaDeCartas.crearCarta("Facu", jugadorUno, estrellas, puntosUno);
		Monstruo monstruoDos = fabricaDeCartas.crearCarta("Javi", jugadorDos, estrellas, puntosDos);
		
		EfectoDeCampo efecto = new EfectoWasteland();
		Campo wasteland = fabricaDeCartas.crearCarta("Wasteland", jugadorUno, efecto);
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorDos.repartirCarta(monstruoDos);
		jugadorUno.repartirCarta(wasteland);
		
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		jugadorUno.agregarCartaEnCampo(wasteland);
		
		assertEquals( puntosUno.obtenerPuntosDeAtaque(), 2300 );
	}
	
	@Test
	public void test13AgregarCartaDeCampoWastelandAumenta200PuntoDeDefensaDeljugadorOponente() {
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntosUno = new Puntos(2000, 2000);
		Puntos puntosDos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo monstruoUno = fabricaDeCartas.crearCarta("Facu", jugadorUno, estrellas, puntosUno);
		Monstruo monstruoDos = fabricaDeCartas.crearCarta("Javi", jugadorDos, estrellas, puntosDos);
		
		EfectoDeCampo efecto = new EfectoWasteland();
		Campo wasteland = fabricaDeCartas.crearCarta("Wasteland", jugadorUno, efecto);
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorDos.repartirCarta(monstruoDos);
		jugadorUno.repartirCarta(wasteland);
		
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		jugadorUno.agregarCartaEnCampo(wasteland);
		
		assertEquals( puntosDos.obtenerPuntosDeDefensa(), 2200 );
	}
	
	@Test
	public void test14CartaDeCampoWastelandNoAplicaNingunEfectoSiSeAgregaAlCampoBocaAbajo() {
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntosUno = new Puntos(2000, 2000);
		Puntos puntosDos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo monstruoUno = fabricaDeCartas.crearCarta("Facu", jugadorUno, estrellas, puntosUno);
		Monstruo monstruoDos = fabricaDeCartas.crearCarta("Javi", jugadorDos, estrellas, puntosDos);
		
		EfectoDeCampo efecto = new EfectoWasteland();
		Campo wasteland = fabricaDeCartas.crearCarta("Wasteland", jugadorUno, efecto);
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorDos.repartirCarta(monstruoDos);
		jugadorUno.repartirCarta(wasteland);
		
		wasteland.voltear();
		
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		jugadorUno.agregarCartaEnCampo(wasteland);
		
		//No modifico los puntos de ataque del que la invoca, ni los de defensa del adaversario
		assertEquals( puntosDos.obtenerPuntosDeDefensa(), 2000 );
		assertEquals( puntosUno.obtenerPuntosDeAtaque(), 2000 );
	}

	@Test
	public void test15CartaDeCampoSogenAumenta500PuntosDeDefensaDelJugadorQuePusoLaCarta() {
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntosUno = new Puntos(2000, 2000);
		Puntos puntosDos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo monstruoUno = fabricaDeCartas.crearCarta("Facu", jugadorUno, estrellas, puntosUno);
		Monstruo monstruoDos = fabricaDeCartas.crearCarta("Javi", jugadorDos, estrellas, puntosDos);
		
		EfectoDeCampo efecto = new EfectoSogen();
		Campo sogen = fabricaDeCartas.crearCarta("Sogen", jugadorUno, efecto);
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorDos.repartirCarta(monstruoDos);
		jugadorUno.repartirCarta(sogen);
		
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		jugadorUno.agregarCartaEnCampo(sogen);
		
		assertEquals( puntosUno.obtenerPuntosDeDefensa(), 2500 );
	}
	
	@Test
	public void test16CartaDeCampoSogenAumenta200PuntosDeAtaqueDelJugadorOponente() {
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntosUno = new Puntos(2000, 2000);
		Puntos puntosDos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo monstruoUno = fabricaDeCartas.crearCarta("Facu", jugadorUno, estrellas, puntosUno);
		Monstruo monstruoDos = fabricaDeCartas.crearCarta("Javi", jugadorDos, estrellas, puntosDos);
		
		EfectoDeCampo efecto = new EfectoSogen();
		Campo sogen = fabricaDeCartas.crearCarta("Sogen", jugadorUno, efecto);
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorDos.repartirCarta(monstruoDos);
		jugadorUno.repartirCarta(sogen);
		
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		jugadorUno.agregarCartaEnCampo(sogen);
		
		assertEquals( puntosDos.obtenerPuntosDeAtaque(), 2200 );
	}
	
	@Test
	public void test17CartaMagicaFisuraEliminaLaCartaDelCampoEnemigoConMenorAtaque() {
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntosDosA = new Puntos(3000, 2000);
		Puntos puntosDosB = new Puntos(1000, 2000);
		
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo monstruoDosA = fabricaDeCartas.crearCarta("Facu", jugadorDos, estrellas, puntosDosA);
		Monstruo monstruoDosB = fabricaDeCartas.crearCarta("Javi", jugadorDos, estrellas, puntosDosB);
		
		Efecto efecto = new EfectoFisura();
		Magica fisura = fabricaDeCartas.crearCarta("Fisura", jugadorUno, efecto);
		
		jugadorDos.repartirCarta(monstruoDosA);
		jugadorDos.repartirCarta(monstruoDosB);
		jugadorUno.repartirCarta(fisura);
		
		jugadorDos.agregarCartaEnCampo(monstruoDosA);
		jugadorDos.agregarCartaEnCampo(monstruoDosB);
		jugadorUno.agregarCartaEnCampo(fisura);
		
		//La carta con menos puntos de ataque (monstruoDosB) debe estar en el cementerio 
		assertTrue( jugadorDos.cartaEstaMuerta(monstruoDosB) );
		assertFalse( jugadorDos.cartaEstaMuerta(monstruoDosA) );
		
	}
	
	@Test
	public void test18CartaMagicaFisuraNoAplicaElEfectoSiSeColocaHaciaAbajo() {
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntosDosA = new Puntos(3000, 2000);
		Puntos puntosDosB = new Puntos(1000, 2000);
		
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo monstruoDosA = fabricaDeCartas.crearCarta("Facu", jugadorDos, estrellas, puntosDosA);
		Monstruo monstruoDosB = fabricaDeCartas.crearCarta("Javi", jugadorDos, estrellas, puntosDosB);
		
		Efecto efecto = new EfectoFisura();
		Magica fisura = fabricaDeCartas.crearCarta("Fisura", jugadorUno, efecto);
		fisura.voltear();
		
		jugadorDos.repartirCarta(monstruoDosA);
		jugadorDos.repartirCarta(monstruoDosB);
		jugadorUno.repartirCarta(fisura);
		
		jugadorDos.agregarCartaEnCampo(monstruoDosA);
		jugadorDos.agregarCartaEnCampo(monstruoDosB);
		jugadorUno.agregarCartaEnCampo(fisura);
		
		assertFalse( jugadorDos.cartaEstaMuerta(monstruoDosB) );
		assertFalse( jugadorDos.cartaEstaMuerta(monstruoDosA) );
		
	}

	@Test
	public void test19EfectoDeLaCartaDeCampoSogenPerduraACartarAgregadasDespues() {
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntosUno = new Puntos(2000, 2000);
		Puntos puntosDos = new Puntos(2000, 2000);
		Puntos puntosTres = new Puntos(2000, 2000);
		Puntos puntosCuatro = new Puntos(2000, 2000);
		
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo monstruoUno = fabricaDeCartas.crearCarta("Facu", jugadorUno, estrellas, puntosUno);
		Monstruo monstruoDos = fabricaDeCartas.crearCarta("Javi", jugadorDos, estrellas, puntosDos);
		
		EfectoDeCampo efecto = new EfectoSogen();
		Campo sogen = fabricaDeCartas.crearCarta("Sogen", jugadorUno, efecto);
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorDos.repartirCarta(monstruoDos);
		jugadorUno.repartirCarta(sogen);
		
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		jugadorUno.agregarCartaEnCampo(sogen);
		
		Monstruo monstruoTres = fabricaDeCartas.crearCarta("Facu", jugadorUno, estrellas, puntosTres);
		Monstruo monstruoCuatro = fabricaDeCartas.crearCarta("Javi", jugadorDos, estrellas, puntosCuatro);		
		
		jugadorUno.repartirCarta(monstruoTres);
		jugadorDos.repartirCarta(monstruoCuatro);
		jugadorUno.agregarCartaEnCampo(monstruoTres);
		jugadorDos.agregarCartaEnCampo(monstruoCuatro);
		
		assertEquals( 2500, puntosTres.obtenerPuntosDeDefensa() );
		assertEquals( 2200, puntosCuatro.obtenerPuntosDeAtaque() );
	}
	
	@Test
	public void test20EfectoDeLaCartaDeCampoSogenNoModificaLasCartasQueYaSeLesAplicoElEfecto() {
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntosUno = new Puntos(2000, 2000);
		Puntos puntosDos = new Puntos(2000, 2000);
		Puntos puntosTres = new Puntos(2000, 2000);
		Puntos puntosCuatro = new Puntos(2000, 2000);
		
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo monstruoUno = fabricaDeCartas.crearCarta("Facu", jugadorUno, estrellas, puntosUno);
		Monstruo monstruoDos = fabricaDeCartas.crearCarta("Javi", jugadorDos, estrellas, puntosDos);
		
		EfectoDeCampo efecto = new EfectoSogen();
		Campo sogen = fabricaDeCartas.crearCarta("Sogen", jugadorUno, efecto);
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorDos.repartirCarta(monstruoDos);
		jugadorUno.repartirCarta(sogen);
		
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		jugadorUno.agregarCartaEnCampo(sogen);
		
		Monstruo monstruoTres = fabricaDeCartas.crearCarta("Facu", jugadorUno, estrellas, puntosTres);
		Monstruo monstruoCuatro = fabricaDeCartas.crearCarta("Javi", jugadorDos, estrellas, puntosCuatro);		
		
		jugadorUno.repartirCarta(monstruoTres);
		jugadorDos.repartirCarta(monstruoCuatro);
		jugadorUno.agregarCartaEnCampo(monstruoTres);
		jugadorDos.agregarCartaEnCampo(monstruoCuatro);
		
		//Se agregaron nuevas cartas, que al aplicarles el efecto del campo no modifica a las que ya se les aplico
		assertEquals( 2500, puntosUno.obtenerPuntosDeDefensa() );
		assertEquals( 2200, puntosDos.obtenerPuntosDeAtaque() );
	}
	
	@Test
	public void test21EfectoDeLaCartaDeCampoWastelandPerduraACartarAgregadasDespues() {
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntosUno = new Puntos(2000, 2000);
		Puntos puntosDos = new Puntos(2000, 2000);
		Puntos puntosTres = new Puntos(2000, 2000);
		Puntos puntosCuatro = new Puntos(2000, 2000);
		
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo monstruoUno = fabricaDeCartas.crearCarta("Facu", jugadorUno, estrellas, puntosUno);
		Monstruo monstruoDos = fabricaDeCartas.crearCarta("Javi", jugadorDos, estrellas, puntosDos);
		
		EfectoDeCampo efecto = new EfectoWasteland();
		Campo wasteland = fabricaDeCartas.crearCarta("Wasteland", jugadorUno, efecto);
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorDos.repartirCarta(monstruoDos);
		jugadorUno.repartirCarta(wasteland);
		
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		jugadorUno.agregarCartaEnCampo(wasteland);
		
		Monstruo monstruoTres = fabricaDeCartas.crearCarta("Facu", jugadorUno, estrellas, puntosTres);
		Monstruo monstruoCuatro = fabricaDeCartas.crearCarta("Javi", jugadorDos, estrellas, puntosCuatro);		
		
		jugadorUno.repartirCarta(monstruoTres);
		jugadorDos.repartirCarta(monstruoCuatro);
		jugadorUno.agregarCartaEnCampo(monstruoTres);
		jugadorDos.agregarCartaEnCampo(monstruoCuatro);
		
		assertEquals( 2300, puntosTres.obtenerPuntosDeAtaque() );
		assertEquals( 2200, puntosCuatro.obtenerPuntosDeDefensa() );
	}
	
	@Test
	public void test22EfectoDeLaCartaDeCampoWastelandNoModificaLasCartasQueYaSeLesAplicoElEfecto() {
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntosUno = new Puntos(2000, 2000);
		Puntos puntosDos = new Puntos(2000, 2000);
		Puntos puntosTres = new Puntos(2000, 2000);
		Puntos puntosCuatro = new Puntos(2000, 2000);
		
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo monstruoUno = fabricaDeCartas.crearCarta("Facu", jugadorUno, estrellas, puntosUno);
		Monstruo monstruoDos = fabricaDeCartas.crearCarta("Javi", jugadorDos, estrellas, puntosDos);
		
		EfectoDeCampo efecto = new EfectoWasteland();
		Campo wasteland = fabricaDeCartas.crearCarta("wasteland", jugadorUno, efecto);
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorDos.repartirCarta(monstruoDos);
		jugadorUno.repartirCarta(wasteland);
		
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		jugadorUno.agregarCartaEnCampo(wasteland);
		
		Monstruo monstruoTres = fabricaDeCartas.crearCarta("Facu", jugadorUno, estrellas, puntosTres);
		Monstruo monstruoCuatro = fabricaDeCartas.crearCarta("Javi", jugadorDos, estrellas, puntosCuatro);		
		
		jugadorUno.repartirCarta(monstruoTres);
		jugadorDos.repartirCarta(monstruoCuatro);
		jugadorUno.agregarCartaEnCampo(monstruoTres);
		jugadorDos.agregarCartaEnCampo(monstruoCuatro);
		
		assertEquals( 2300, puntosUno.obtenerPuntosDeAtaque() );
		assertEquals( 2200, puntosDos.obtenerPuntosDeDefensa() );
	}
	
	@Test
	public void test23AgregarCartaDeCampoWastelandHaciaAbajoYVoltearlaAplicaElEfecto() {
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntosUno = new Puntos(2000, 2000);
		Puntos puntosDos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo monstruoUno = fabricaDeCartas.crearCarta("Facu", jugadorUno, estrellas, puntosUno);
		Monstruo monstruoDos = fabricaDeCartas.crearCarta("Javi", jugadorDos, estrellas, puntosDos);
		
		EfectoDeCampo efecto = new EfectoWasteland();
		Campo wasteland = fabricaDeCartas.crearCarta("Wasteland", jugadorUno, efecto);
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorDos.repartirCarta(monstruoDos);
		jugadorUno.repartirCarta(wasteland);
		
		wasteland.voltear();
		
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		jugadorUno.agregarCartaEnCampo(wasteland);
		
		//No modifico los puntos de ataque del que la invoca, ni los de defensa del adaversario
		assertEquals( puntosDos.obtenerPuntosDeDefensa(), 2000 );
		assertEquals( puntosUno.obtenerPuntosDeAtaque(), 2000 );
		
		wasteland.voltear();
		
		//Aplico el efecto sobre las cartas
		assertEquals( puntosDos.obtenerPuntosDeDefensa(), 2200 );
		assertEquals( puntosUno.obtenerPuntosDeAtaque(), 2300 );
		
	}

	@Test
	public void test24AgregarCartaDeCampoSogenHaciaAbajoYVoltearlaAplicaElEfecto() {
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntosUno = new Puntos(2000, 2000);
		Puntos puntosDos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo monstruoUno = fabricaDeCartas.crearCarta("Facu", jugadorUno, estrellas, puntosUno);
		Monstruo monstruoDos = fabricaDeCartas.crearCarta("Javi", jugadorDos, estrellas, puntosDos);
		
		EfectoDeCampo efecto = new EfectoSogen();
		Campo sogen = fabricaDeCartas.crearCarta("sogen", jugadorUno, efecto);
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorDos.repartirCarta(monstruoDos);
		jugadorUno.repartirCarta(sogen);
		
		sogen.voltear();
		
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		jugadorUno.agregarCartaEnCampo(sogen);
		
		//No modifico los puntos de ataque del que la invoca, ni los de defensa del adaversario
		assertEquals( puntosDos.obtenerPuntosDeAtaque(), 2000 );
		assertEquals( puntosUno.obtenerPuntosDeDefensa(), 2000 );
		
		sogen.voltear();
		
		//Aplico el efecto sobre las cartas
		assertEquals( puntosDos.obtenerPuntosDeAtaque(), 2200 );
		assertEquals( puntosUno.obtenerPuntosDeDefensa(), 2500 );
		
	}

	@Test
	public void test25AgregarCartaDeCampoWastelandHaciaArribaYVoltearlaDesaplicaElEfecto() {
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntosUno = new Puntos(2000, 2000);
		Puntos puntosDos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo monstruoUno = fabricaDeCartas.crearCarta("Facu", jugadorUno, estrellas, puntosUno);
		Monstruo monstruoDos = fabricaDeCartas.crearCarta("Javi", jugadorDos, estrellas, puntosDos);
		
		EfectoDeCampo efecto = new EfectoWasteland();
		Campo wasteland = fabricaDeCartas.crearCarta("Wasteland", jugadorUno, efecto);
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorDos.repartirCarta(monstruoDos);
		jugadorUno.repartirCarta(wasteland);
		
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		jugadorUno.agregarCartaEnCampo(wasteland);
		
		assertEquals( puntosDos.obtenerPuntosDeDefensa(), 2200 );
		assertEquals( puntosUno.obtenerPuntosDeAtaque(), 2300 );
		
		wasteland.voltear();
		
		//Desaplico el efecto sobre las cartas
		assertEquals( puntosDos.obtenerPuntosDeDefensa(), 2000 );
		assertEquals( puntosUno.obtenerPuntosDeAtaque(), 2000 );
		
	}
	
	@Test
	public void test26AgregarCartaDeCampoSogenHaciaArribaYVoltearlaDesaplicaElEfecto() {
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntosUno = new Puntos(2000, 2000);
		Puntos puntosDos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo monstruoUno = fabricaDeCartas.crearCarta("Facu", jugadorUno, estrellas, puntosUno);
		Monstruo monstruoDos = fabricaDeCartas.crearCarta("Javi", jugadorDos, estrellas, puntosDos);
		
		EfectoDeCampo efecto = new EfectoSogen();
		Campo sogen = fabricaDeCartas.crearCarta("sogen", jugadorUno, efecto);
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorDos.repartirCarta(monstruoDos);
		jugadorUno.repartirCarta(sogen);
		
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		jugadorUno.agregarCartaEnCampo(sogen);
		
		assertEquals( puntosDos.obtenerPuntosDeAtaque(), 2200 );
		assertEquals( puntosUno.obtenerPuntosDeDefensa(), 2500 );
		
		sogen.voltear();
		
		//Desaplico el efecto sobre las cartas
		assertEquals( puntosDos.obtenerPuntosDeAtaque(), 2000 );
		assertEquals( puntosUno.obtenerPuntosDeDefensa(), 2000 );
		
	}
	
	@Test
	public void test27SeTomaron2CartasDelMazoLuegoDeInvocarOllaDeLaCodicia() {
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(3);
		
		Monstruo monstruo1 = fabricaDeCartas.crearCarta("monstruo1", jugadorUno, estrellas, puntos);
		Monstruo monstruo2 = fabricaDeCartas.crearCarta("monstruo2", jugadorDos, estrellas, puntos);
		
		Efecto efecto = new EfectoOllaDeLaCodicia();
		Magica ollaDeLaCodicia = fabricaDeCartas.crearCarta("olla de la codicia", jugadorUno, efecto);
		
		Baraja mazo = new Baraja();
		mazo.agregarCarta(monstruo1);
		mazo.agregarCarta(monstruo2);
		jugadorUno.obtenerCampo().cargarMazo(mazo);
		
		jugadorUno.repartirCarta(ollaDeLaCodicia);	
		jugadorUno.agregarCartaEnCampo(ollaDeLaCodicia);
		
		assertTrue(jugadorUno.contieneCartaEnMano(monstruo1));
		assertTrue(jugadorUno.contieneCartaEnMano(monstruo2));
	}
	
	@Test
	public void test28InvocarOllaDeLaCodiciaBocaAbajoNoSeRetiranCartas() {
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(3);
		
		Monstruo monstruo1 = fabricaDeCartas.crearCarta("monstruo1", jugadorUno, estrellas, puntos);
		Monstruo monstruo2 = fabricaDeCartas.crearCarta("monstruo2", jugadorDos, estrellas, puntos);
		
		Efecto efecto = new EfectoOllaDeLaCodicia();
		Magica ollaDeLaCodicia = fabricaDeCartas.crearCarta("olla de la codicia", jugadorUno, efecto);
		
		Baraja cartasACargarEnMazo = new Baraja();
		cartasACargarEnMazo.agregarCarta(monstruo1);
		cartasACargarEnMazo.agregarCarta(monstruo2);
		jugadorUno.obtenerCampo().cargarMazo(cartasACargarEnMazo);
		
		ollaDeLaCodicia.voltear();
		jugadorUno.repartirCarta(ollaDeLaCodicia);
		jugadorUno.agregarCartaEnCampo(ollaDeLaCodicia);
		
		assertFalse(jugadorUno.contieneCartaEnMano(monstruo1));
		assertFalse(jugadorUno.contieneCartaEnMano(monstruo2));
	}
	
	@Test
	public void test29jugadorPierdeVidaAlAtacarConCartaDebilAOtraMasFuerte() {
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugaforDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugaforDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);

		//La carta se inicializa en modo ataque
		Puntos puntosUnaCarta = new Puntos(1000, 2000);
		Puntos puntosOtraCarta = new Puntos(1500, 2500);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo unaCarta = fabricaDeCartas.crearCarta("Facundo", jugadorUno, estrellas, puntosUnaCarta);
		Monstruo otraCarta = fabricaDeCartas.crearCarta("Javier", jugadorDos, estrellas, puntosOtraCarta);
		
		jugadorUno.repartirCarta(unaCarta);
		jugadorDos.repartirCarta(otraCarta);
		
		jugadorUno.agregarCartaEnCampo(unaCarta);
		jugadorDos.agregarCartaEnCampo(otraCarta);
		
		jugadorUno.atacarCartaConCarta(otraCarta, unaCarta);
		
		double vidaObtenida = jugadorUno.obtenerVida().obtenerPuntosDeVida();
		double vidaEsperada = 7500;
		
		assertEquals(vidaEsperada,vidaObtenida);
		
	}
	
	@Test
	public void test30jugadoNoPuedeAtacarConCartaEnMano () {
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugaforDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugaforDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);

		//La carta se inicializa en modo ataque
		Puntos puntosUnaCarta = new Puntos(1000, 2000);
		Puntos puntosOtraCarta = new Puntos(1500, 2500);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo unaCarta = fabricaDeCartas.crearCarta("Facundo", jugadorUno, estrellas, puntosUnaCarta);
		Monstruo otraCarta = fabricaDeCartas.crearCarta("Javier", jugadorDos, estrellas, puntosOtraCarta);
		
		jugadorUno.repartirCarta(unaCarta);
		jugadorDos.repartirCarta(otraCarta);
		
		try {
			jugadorUno.atacarCartaConCarta(otraCarta, unaCarta);	
			assertTrue(false);
		} catch (ErrorCartaEnManoNoPuedeAtacar error) {
			assertTrue(true);
		}
	}
	
	@Test
	public void test31jugadorNoPuedeAtacarConCartaAjena() {
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugaforDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugaforDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);

		//La carta se inicializa en modo ataque
		Puntos puntosUnaCarta = new Puntos(1000, 2000);
		Puntos puntosOtraCarta = new Puntos(1500, 2500);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo unaCarta = fabricaDeCartas.crearCarta("Facundo", jugadorUno, estrellas, puntosUnaCarta);
		Monstruo otraCarta = fabricaDeCartas.crearCarta("Javier", jugadorDos, estrellas, puntosOtraCarta);
		
		jugadorUno.repartirCarta(unaCarta);
		jugadorDos.repartirCarta(otraCarta);
		
		jugadorUno.agregarCartaEnCampo(unaCarta);
		jugadorDos.agregarCartaEnCampo(otraCarta);
		
		try {
			jugadorUno.atacarCartaConCarta(unaCarta, otraCarta);
			assertTrue(false);
		}catch (ErrorCartaAUsarNoPerteneceAlJugadorQueLaIntentaUsar error) {
			assertTrue(true);
		}
	}
	@Test
	public void test32jugadorNoPuedeAtacarCartaPropia () {
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugaforDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugaforDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);

		//La carta se inicializa en modo ataque
		Puntos puntosUnaCarta = new Puntos(1000, 2000);
		Puntos puntosOtraCarta = new Puntos(1500, 2500);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo unaCarta = fabricaDeCartas.crearCarta("Facundo", jugadorUno, estrellas, puntosUnaCarta);
		Monstruo otraCarta = fabricaDeCartas.crearCarta("Javier", jugadorUno, estrellas, puntosOtraCarta);
		
		jugadorUno.repartirCarta(unaCarta);
		jugadorUno.repartirCarta(otraCarta);
		
		jugadorUno.agregarCartaEnCampo(unaCarta);
		jugadorUno.agregarCartaEnCampo(otraCarta);
		
		try {
			jugadorUno.atacarCartaConCarta(unaCarta, otraCarta);
			assertTrue(false);
		}catch (ErrorIntentaAtacarACartaPropia error) {
			assertTrue(true);
		}

	}
	
	@Test
	public void test33AtacarJugadorConCilindroInflinjeDanioDelAtaqueAlAtacante() {
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(3);
		
		Monstruo monstruoUno = fabricaDeCartas.crearCarta("monstruo1", jugadorUno, estrellas, puntos);
		Monstruo monstruoDos = fabricaDeCartas.crearCarta("monstruo2", jugadorDos, estrellas, puntos);
		
		EfectoDeTrampa efecto = new EfectoCilindroMagico();
		Trampa cilindroMagico = fabricaDeCartas.crearCarta("Cilindro Magico", jugadorDos, efecto);
		
		jugadorDos.repartirCarta(cilindroMagico);
		jugadorDos.repartirCarta(monstruoDos);
		jugadorDos.agregarCartaEnCampo(cilindroMagico);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		
		jugadorUno.atacarCartaConCarta(monstruoDos, monstruoUno);
		
		//Pierde 2000 de vida, que era el ataque del monstruoUno
		assertEquals(jugadorUno.obtenerVida().obtenerPuntosDeVida(), 6000);
		
	} 
	
	@Test
	public void test34CartaTrampaCilindroMagicoDespuesDelAtaqueVaAlCementerio() {
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(3);
		
		Monstruo monstruoUno = fabricaDeCartas.crearCarta("monstruo1", jugadorUno, estrellas, puntos);
		Monstruo monstruoDos = fabricaDeCartas.crearCarta("monstruo2", jugadorDos, estrellas, puntos);
		
		EfectoDeTrampa efecto = new EfectoCilindroMagico();
		Trampa cilindroMagico = fabricaDeCartas.crearCarta("Cilindro Magico", jugadorDos, efecto);
		
		jugadorDos.repartirCarta(cilindroMagico);
		jugadorDos.repartirCarta(monstruoDos);
		jugadorDos.agregarCartaEnCampo(cilindroMagico);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		
		jugadorUno.atacarCartaConCarta(monstruoDos, monstruoUno);
		
		//Pierde 2000 de vida, que era el ataque del monstruoUno
		assertTrue(jugadorDos.cartaEstaMuerta(cilindroMagico));
		
	} 
	
	@Test
	public void test35AtacarJugadorConCilindroMataLaCartaAtacante() {
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(3);
		
		Monstruo monstruoUno = fabricaDeCartas.crearCarta("monstruo1", jugadorUno, estrellas, puntos);
		Monstruo monstruoDos = fabricaDeCartas.crearCarta("monstruo2", jugadorDos, estrellas, puntos);
		
		EfectoDeTrampa efecto = new EfectoCilindroMagico();
		Trampa cilindroMagico = fabricaDeCartas.crearCarta("Cilindro Magico", jugadorDos, efecto);
		
		jugadorDos.repartirCarta(cilindroMagico);
		jugadorDos.repartirCarta(monstruoDos);
		jugadorDos.agregarCartaEnCampo(cilindroMagico);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		
		jugadorUno.atacarCartaConCarta(monstruoDos, monstruoUno);
		
		assertTrue(jugadorUno.cartaEstaMuerta(monstruoUno));
		
	} 
	

	@Test
	public void test36AtacarJugadorConReinforcementAumentaLosPuntosDeAtaqueDeLaCartaAtacada() {
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntosUno = new Puntos(400, 2000);
		Puntos puntosDos = new Puntos(0, 2000);
		Estrellas estrellas = new Estrellas(3);
		
		Monstruo monstruoUno = fabricaDeCartas.crearCarta("monstruo1", jugadorUno, estrellas, puntosUno);
		Monstruo monstruoDos = fabricaDeCartas.crearCarta("monstruo2", jugadorDos, estrellas, puntosDos);
		
		EfectoDeTrampa efecto = new EfectoReinforcements();
		Trampa reinforcements = fabricaDeCartas.crearCarta("Reinforcements", jugadorDos, efecto);
		
		jugadorDos.repartirCarta(reinforcements);
		jugadorDos.repartirCarta(monstruoDos);
		jugadorDos.agregarCartaEnCampo(reinforcements);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		
		jugadorUno.atacarCartaConCarta(monstruoDos, monstruoUno);
		
		//Aumenta de 0 a 500 el ataque, y por diferencia de puntos resta 100 al juagdorUno 
		assertEquals(jugadorUno.obtenerVida().obtenerPuntosDeVida(), 7900);
		
	} 
	
	@Test
	public void test37AlIniciarElJuegoCadaJugadorDebeTener5CartasEnLaMano() {
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		ControladorDelJuego controladorDelJuego = new ControladorDelJuego(jugadorUno, jugadorDos);
		
		Jugador jugadorActivo = controladorDelJuego.obtenerJugadorActivo();
		Jugador jugadorPasivo = controladorDelJuego.obtenerJugadorPasivo();
		
		//verifica la contidad de cartas en mano
		assertEquals(jugadorActivo.obtenerCantidadDeCartasEnMano(), 6);
		assertEquals(jugadorPasivo.obtenerCantidadDeCartasEnMano(), 5);
		
	} 
	
	
	

}