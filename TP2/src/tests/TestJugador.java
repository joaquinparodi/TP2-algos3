package tests;

import jugabilidad.Baraja;
import jugabilidad.Jugador;
import cartas.Campo;
import cartas.Carta;
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
import errores.ErrorYaHayCartaDeCampoInvocada;
import errores.ErrorYaHayCincoCartasEnLaFila;
import factories.BuilderDeCartas;
import factories.CreadorDeAgujeroNegro;
import factories.CreadorDeCilindroMagico;
import factories.CreadorDeDragonBlanco;
import factories.CreadorDeFisura;
import factories.CreadorDeOllaDeLaCodicia;
import factories.CreadorDeReinforcements;
import factories.CreadorDeSogen;
import factories.CreadorDeWasteland;
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
	public void test03ColocarMonstruoConEstrellasInferiorACincoYNoSacrificaANadie() {

		Vida vida = new Vida(8000);
    	Jugador jugador = new Jugador(vida);
		
		Estrellas estrellas = new Estrellas(1);
    	Puntos puntos = new Puntos(2000, 2000);
    	
		Monstruo monstruoUno = new Monstruo("Auriane", jugador, estrellas,puntos);
		Monstruo monstruoDos = new Monstruo("Javir", jugador, estrellas, puntos);
		Monstruo monstruoTres = new Monstruo("Facundo", jugador, estrellas, puntos);
		
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

		Vida vida = new Vida(8000);
    	Jugador jugador = new Jugador(vida);
		
		Estrellas estrellas = new Estrellas(1);
		Estrellas estrellasMonstruoUno = new Estrellas(7);
		Puntos puntos = new Puntos(2000, 2000);
		
		Monstruo monstruoUno = new Monstruo("joaco", jugador, estrellasMonstruoUno, puntos);
		Monstruo monstruoDos = new Monstruo("javi", jugador, estrellas, puntos);
		Monstruo monstruoTres = new Monstruo("facu", jugador, estrellas, puntos);
		
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

		Vida vida = new Vida(8000);
    	Jugador jugador = new Jugador(vida);
		
		Estrellas estrellas = new Estrellas(1);
		Estrellas estrellasMonstruoUno = new Estrellas(5);
		Puntos puntos = new Puntos(2000, 2000);
		
		Monstruo monstruoUno = new Monstruo("joaco", jugador, estrellasMonstruoUno, puntos);
		Monstruo monstruoDos = new Monstruo("javi", jugador, estrellas, puntos);
		
		jugador.repartirCarta(monstruoUno);
		jugador.repartirCarta(monstruoDos);
		
		Sacrificio monstruosASacrificar = new Sacrificio();
		monstruosASacrificar.agregarCarta(monstruoDos);
		
		jugador.agregarCartaEnCampo(monstruoUno, monstruosASacrificar);
		
		assert( jugador.cartaEstaMuerta(monstruoDos) );
	}
	
	@Test
	public void test06VerificarSiElMonstroColocadoSeEncuentraEnElCampoLuegoDeSacrificio() {

    	Vida vida = new Vida(8000);
    	Jugador jugador = new Jugador(vida);
		
		Puntos puntos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(1);
		Estrellas estrellasMonstruoUno = new Estrellas(5);
		
		Monstruo monstruoUno = new Monstruo("joaco", jugador, estrellasMonstruoUno, puntos);
		Monstruo monstruoDos = new Monstruo("javi", jugador, estrellas, puntos);
		
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
	
		Monstruo monstruoUno = new Monstruo("joaco", jugador, estrellasMonstruoUno, puntos);
		
		jugador.repartirCarta(monstruoUno);
		
		Sacrificio monstruosASacrificar = new Sacrificio();
		
		Assertions.assertThrows(ErrorSacrificiosInsuficientes.class, () -> jugador.agregarCartaEnCampo(monstruoUno, monstruosASacrificar));
	}
	
	@Test
	public void test08ColocarMonstruoConTresEstrellasNoRequiereSacrificio() {

		Vida vida = new Vida(8000);
    	Jugador jugador = new Jugador(vida);
		
		Estrellas estrellas = new Estrellas(3);
		Puntos puntos = new Puntos(2000, 2000);
		
		Monstruo monstruoUno = new Monstruo("joaco", jugador, estrellas, puntos);
		Monstruo monstruoDos = new Monstruo("javi", jugador, estrellas, puntos);
		
		jugador.repartirCarta(monstruoUno);
		jugador.repartirCarta(monstruoDos);
		
		jugador.agregarCartaEnCampo(monstruoUno);
		assertFalse( jugador.cartaEstaMuerta(monstruoDos) );
	}
	
	@Test
	public void test09AgregarAgujeroNegroEliminaTodasLasCartas () {
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(3);
		
		Monstruo monstruoUno = new Monstruo("facu", jugadorUno, estrellas, puntos);
		Monstruo monstruoDos = new Monstruo("javi", jugadorDos, estrellas, puntos);
		
		CreadorDeAgujeroNegro creador = new CreadorDeAgujeroNegro(jugadorUno);
		BuilderDeCartas builder = new BuilderDeCartas(creador);
		Carta agujeroNegro = builder.obtenerCarta();
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorUno.repartirCarta(agujeroNegro);
		jugadorDos.repartirCarta(monstruoDos);
		
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		
		jugadorUno.agregarCartaEnCampo((Magica) agujeroNegro);
		
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

		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo monstruoUno = new Monstruo("facu", jugadorUno, estrellas, puntos);
		Monstruo monstruoDos = new Monstruo("javi", jugadorDos, estrellas, puntos);
		
		CreadorDeAgujeroNegro creador = new CreadorDeAgujeroNegro(jugadorUno);
		BuilderDeCartas builder = new BuilderDeCartas(creador);
		Carta agujeroNegro = builder.obtenerCarta();
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorUno.repartirCarta(agujeroNegro);
		jugadorDos.repartirCarta(monstruoDos);
		
		jugadorUno.voltearCartaDeMano("Agujero Negro");
		
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		jugadorUno.agregarCartaEnCampo((Magica) agujeroNegro);
		
		assertFalse(jugadorUno.cartaEstaMuerta(monstruoUno) );
		assertFalse(jugadorDos.cartaEstaMuerta(monstruoDos) );
		
	}
	
	@Test
	public void test12AgregarCartaDeCampoWastelandAumenta200PuntoDeAtaquesDeljugadorQuePusoLaCarta() {

		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntosUno = new Puntos(2000, 2000);
		Puntos puntosDos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo monstruoUno = new Monstruo("Facu", jugadorUno, estrellas, puntosUno);
		Monstruo monstruoDos = new Monstruo("Javi", jugadorDos, estrellas, puntosDos);
		
		CreadorDeWasteland creador = new CreadorDeWasteland(jugadorUno);
		BuilderDeCartas builder = new BuilderDeCartas(creador);
		Carta wasteland = builder.obtenerCarta();
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorDos.repartirCarta(monstruoDos);
		jugadorUno.repartirCarta(wasteland);
		
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		jugadorUno.agregarCartaEnCampo((Campo) wasteland);
		
		assertEquals( puntosUno.obtenerPuntosDeAtaque(), 2200 );
	}
	
	@Test
	public void test13AgregarCartaDeCampoWastelandAumenta300PuntoDeDefensaDeljugadorOponente() {

		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntosUno = new Puntos(2000, 2000);
		Puntos puntosDos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo monstruoUno = new Monstruo("Facu", jugadorUno, estrellas, puntosUno);
		Monstruo monstruoDos = new Monstruo("Javi", jugadorDos, estrellas, puntosDos);
		
		CreadorDeWasteland creador = new CreadorDeWasteland(jugadorUno);
		BuilderDeCartas builder = new BuilderDeCartas(creador);
		Carta wasteland = builder.obtenerCarta();
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorDos.repartirCarta(monstruoDos);
		jugadorUno.repartirCarta(wasteland);
		
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		jugadorUno.agregarCartaEnCampo((Campo) wasteland);
		
		assertEquals( 2300, puntosDos.obtenerPuntosDeDefensa());
	}
	

	@Test
	public void test15CartaDeCampoSogenAumenta500PuntosDeDefensaDelJugadorQuePusoLaCarta() {

		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntosUno = new Puntos(2000, 2000);
		Puntos puntosDos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo monstruoUno = new Monstruo("Facu", jugadorUno, estrellas, puntosUno);
		Monstruo monstruoDos = new Monstruo("Javi", jugadorDos, estrellas, puntosDos);
		
		CreadorDeSogen creador = new CreadorDeSogen(jugadorUno);
		BuilderDeCartas builder = new BuilderDeCartas(creador);
		Carta sogen = builder.obtenerCarta();
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorDos.repartirCarta(monstruoDos);
		jugadorUno.repartirCarta(sogen);
		
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		jugadorUno.agregarCartaEnCampo((Campo) sogen);
		
		assertEquals( puntosUno.obtenerPuntosDeDefensa(), 2500 );
	}
	
	@Test
	public void test16CartaDeCampoSogenAumenta200PuntosDeAtaqueDelJugadorOponente() {

		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntosUno = new Puntos(2000, 2000);
		Puntos puntosDos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo monstruoUno = new Monstruo("Facu", jugadorUno, estrellas, puntosUno);
		Monstruo monstruoDos = new Monstruo("Javi", jugadorDos, estrellas, puntosDos);
		
		CreadorDeSogen creador = new CreadorDeSogen(jugadorUno);
		BuilderDeCartas builder = new BuilderDeCartas(creador);
		Carta sogen = builder.obtenerCarta();
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorDos.repartirCarta(monstruoDos);
		jugadorUno.repartirCarta(sogen);
		
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		jugadorUno.agregarCartaEnCampo((Campo) sogen);
		
		assertEquals( puntosDos.obtenerPuntosDeAtaque(), 2200 );
	}
	
	@Test
	public void test17CartaMagicaFisuraEliminaLaCartaDelCampoEnemigoConMenorAtaque() {
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntosDosA = new Puntos(3000, 2000);
		Puntos puntosDosB = new Puntos(1000, 2000);
		
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo monstruoDosA = new Monstruo("Facu", jugadorDos, estrellas, puntosDosA);
		Monstruo monstruoDosB = new Monstruo("Javi", jugadorDos, estrellas, puntosDosB);
		
		CreadorDeFisura creador = new CreadorDeFisura(jugadorUno);
		BuilderDeCartas builder = new BuilderDeCartas(creador);
		Carta fisura = builder.obtenerCarta();
		
		jugadorDos.repartirCarta(monstruoDosA);
		jugadorDos.repartirCarta(monstruoDosB);
		jugadorUno.repartirCarta(fisura);
		
		jugadorDos.agregarCartaEnCampo(monstruoDosA);
		jugadorDos.agregarCartaEnCampo(monstruoDosB);
		jugadorUno.agregarCartaEnCampo((Magica) fisura);
		
		//La carta con menos puntos de ataque (monstruoDosB) debe estar en el cementerio 
		assertTrue( jugadorDos.cartaEstaMuerta(monstruoDosB) );
		assertFalse( jugadorDos.cartaEstaMuerta(monstruoDosA) );
		
	}
	
	@Test
	public void test18CartaMagicaFisuraNoAplicaElEfectoSiSeColocaHaciaAbajo() {

		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntosDosA = new Puntos(3000, 2000);
		Puntos puntosDosB = new Puntos(1000, 2000);
		
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo monstruoDosA = new Monstruo("Facu", jugadorDos, estrellas, puntosDosA);
		Monstruo monstruoDosB = new Monstruo("Javi", jugadorDos, estrellas, puntosDosB);
		
		CreadorDeFisura creador = new CreadorDeFisura(jugadorUno);
		BuilderDeCartas builder = new BuilderDeCartas(creador);
		Carta fisura = builder.obtenerCarta();
		fisura.voltear();
		
		jugadorDos.repartirCarta(monstruoDosA);
		jugadorDos.repartirCarta(monstruoDosB);
		jugadorUno.repartirCarta(fisura);
		
		jugadorDos.agregarCartaEnCampo(monstruoDosA);
		jugadorDos.agregarCartaEnCampo(monstruoDosB);
		jugadorUno.agregarCartaEnCampo((Magica) fisura);
		
		assertFalse( jugadorDos.cartaEstaMuerta(monstruoDosB) );
		assertFalse( jugadorDos.cartaEstaMuerta(monstruoDosA) );
		
	}

	@Test
	public void test19EfectoDeLaCartaDeCampoSogenPerduraACartarAgregadasDespues() {

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
		
		Monstruo monstruoUno = new Monstruo("Facu", jugadorUno, estrellas, puntosUno);
		Monstruo monstruoDos = new Monstruo("Javi", jugadorDos, estrellas, puntosDos);
		
		CreadorDeSogen creador = new CreadorDeSogen(jugadorUno);
		BuilderDeCartas builder = new BuilderDeCartas(creador);
		Carta sogen = builder.obtenerCarta();
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorDos.repartirCarta(monstruoDos);
		jugadorUno.repartirCarta(sogen);
		
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		jugadorUno.agregarCartaEnCampo((Campo) sogen);
		
		Monstruo monstruoTres = new Monstruo("Facu", jugadorUno, estrellas, puntosTres);
		Monstruo monstruoCuatro = new Monstruo("Javi", jugadorDos, estrellas, puntosCuatro);		
		
		jugadorUno.repartirCarta(monstruoTres);
		jugadorDos.repartirCarta(monstruoCuatro);
		jugadorUno.agregarCartaEnCampo(monstruoTres);
		jugadorDos.agregarCartaEnCampo(monstruoCuatro);
		
		assertEquals( 2500, puntosTres.obtenerPuntosDeDefensa() );
		assertEquals( 2200, puntosCuatro.obtenerPuntosDeAtaque() );
	}
	
	@Test
	public void test20EfectoDeLaCartaDeCampoSogenNoModificaLasCartasQueYaSeLesAplicoElEfecto() {

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
		
		Monstruo monstruoUno = new Monstruo("Facu", jugadorUno, estrellas, puntosUno);
		Monstruo monstruoDos = new Monstruo("Javi", jugadorDos, estrellas, puntosDos);
		
		CreadorDeSogen creador = new CreadorDeSogen(jugadorUno);
		BuilderDeCartas builder = new BuilderDeCartas(creador);
		Carta sogen = builder.obtenerCarta();
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorDos.repartirCarta(monstruoDos);
		jugadorUno.repartirCarta(sogen);
		
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		jugadorUno.agregarCartaEnCampo((Campo) sogen);
		
		Monstruo monstruoTres = new Monstruo("Facu", jugadorUno, estrellas, puntosTres);
		Monstruo monstruoCuatro = new Monstruo("Javi", jugadorDos, estrellas, puntosCuatro);		
		
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
		
		Monstruo monstruoUno = new Monstruo("Facu", jugadorUno, estrellas, puntosUno);
		Monstruo monstruoDos = new Monstruo("Javi", jugadorDos, estrellas, puntosDos);
		
		CreadorDeWasteland creador = new CreadorDeWasteland(jugadorUno);
		BuilderDeCartas builder = new BuilderDeCartas(creador);
		Carta wasteland = builder.obtenerCarta();
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorDos.repartirCarta(monstruoDos);
		jugadorUno.repartirCarta(wasteland);
		
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		jugadorUno.agregarCartaEnCampo((Campo) wasteland);
		
		Monstruo monstruoTres = new Monstruo("Joaco", jugadorUno, estrellas, puntosTres);
		Monstruo monstruoCuatro = new Monstruo("Auriane", jugadorDos, estrellas, puntosCuatro);		
		
		jugadorUno.repartirCarta(monstruoTres);
		jugadorDos.repartirCarta(monstruoCuatro);
		jugadorUno.agregarCartaEnCampo(monstruoTres);
		jugadorDos.agregarCartaEnCampo(monstruoCuatro);
		
		assertEquals( 2200, puntosTres.obtenerPuntosDeAtaque() );
		assertEquals( 2300, puntosCuatro.obtenerPuntosDeDefensa() );
	}
	
	@Test
	public void test22EfectoDeLaCartaDeCampoWastelandNoModificaLasCartasQueYaSeLesAplicoElEfecto() {

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
		
		Monstruo monstruoUno = new Monstruo("Facu", jugadorUno, estrellas, puntosUno);
		Monstruo monstruoDos = new Monstruo("Javi", jugadorDos, estrellas, puntosDos);
		
		CreadorDeWasteland creador = new CreadorDeWasteland(jugadorUno);
		BuilderDeCartas builder = new BuilderDeCartas(creador);
		Carta wasteland = builder.obtenerCarta();
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorDos.repartirCarta(monstruoDos);
		jugadorUno.repartirCarta(wasteland);
		
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		jugadorUno.agregarCartaEnCampo((Campo) wasteland);
		
		Monstruo monstruoTres = new Monstruo("Facu", jugadorUno, estrellas, puntosTres);
		Monstruo monstruoCuatro = new Monstruo("Javi", jugadorDos, estrellas, puntosCuatro);		
		
		jugadorUno.repartirCarta(monstruoTres);
		jugadorDos.repartirCarta(monstruoCuatro);
		jugadorUno.agregarCartaEnCampo(monstruoTres);
		jugadorDos.agregarCartaEnCampo(monstruoCuatro);
		
		assertEquals( 2200, puntosUno.obtenerPuntosDeAtaque() );
		assertEquals( 2300, puntosDos.obtenerPuntosDeDefensa() );
	}
	

	@Test
	public void test24CadaJugadorAgregaUnaCartaCampoYSeAplicaElEfectoDeAmbas() {

		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntosUno = new Puntos(2200, 1100);
		Puntos puntosDos = new Puntos(2000, 2500);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo monstruoUno = new Monstruo("Facu", jugadorUno, estrellas, puntosUno);
		Monstruo monstruoDos = new Monstruo("Javi", jugadorDos, estrellas, puntosDos);
		
		CreadorDeSogen creador = new CreadorDeSogen(jugadorUno);
		BuilderDeCartas builder = new BuilderDeCartas(creador);
		Carta sogen = builder.obtenerCarta();
		
		CreadorDeWasteland creador2 = new CreadorDeWasteland(jugadorDos);
		BuilderDeCartas builder2 = new BuilderDeCartas(creador2);
		Carta wasteland = builder2.obtenerCarta();
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorDos.repartirCarta(monstruoDos);
		
		jugadorUno.repartirCarta(sogen);
		jugadorDos.repartirCarta(wasteland);
		
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		
		jugadorUno.agregarCartaEnCampo((Campo) sogen);
		jugadorDos.agregarCartaEnCampo((Campo) wasteland);
		
		
		assertEquals( puntosDos.obtenerPuntosDeAtaque(), 2400 );
		assertEquals( puntosDos.obtenerPuntosDeDefensa(), 2500 );

		assertEquals( puntosUno.obtenerPuntosDeAtaque(), 2200 );
		assertEquals( puntosUno.obtenerPuntosDeDefensa(), 1900 );
		
	}
	
	@Test
	public void test27SeTomaron2CartasDelMazoLuegoDeInvocarOllaDeLaCodicia() {

		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(3);
		
		Monstruo monstruo1 = new Monstruo("monstruo1", jugadorUno, estrellas, puntos);
		Monstruo monstruo2 = new Monstruo("monstruo2", jugadorDos, estrellas, puntos);
		
		CreadorDeOllaDeLaCodicia creador = new CreadorDeOllaDeLaCodicia(jugadorUno);
		BuilderDeCartas builder = new BuilderDeCartas(creador);
		Carta ollaDeLaCodicia = builder.obtenerCarta();
		
		Baraja mazo = new Baraja();
		mazo.agregarCarta(monstruo1);
		mazo.agregarCarta(monstruo2);
		jugadorUno.asignarMazo(mazo);
		
		jugadorUno.repartirCarta(ollaDeLaCodicia);	
		jugadorUno.agregarCartaEnCampo((Magica) ollaDeLaCodicia);
		
		assertTrue(jugadorUno.contieneCartaEnMano(monstruo1));
		assertTrue(jugadorUno.contieneCartaEnMano(monstruo2));
	}
	
	@Test
	public void test28InvocarOllaDeLaCodiciaBocaAbajoNoSeRetiranCartas() {

		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(3);
		
		Monstruo monstruo1 = new Monstruo("monstruo1", jugadorUno, estrellas, puntos);
		Monstruo monstruo2 = new Monstruo("monstruo2", jugadorDos, estrellas, puntos);
		
		CreadorDeOllaDeLaCodicia creador = new CreadorDeOllaDeLaCodicia(jugadorUno);
		BuilderDeCartas builder = new BuilderDeCartas(creador);
		Carta ollaDeLaCodicia = builder.obtenerCarta();
		
		Baraja mazo = new Baraja();
		mazo.agregarCarta(monstruo1);
		mazo.agregarCarta(monstruo2);
		jugadorUno.asignarMazo(mazo);
		
		ollaDeLaCodicia.voltear();
		jugadorUno.repartirCarta(ollaDeLaCodicia);
		jugadorUno.agregarCartaEnCampo((Magica) ollaDeLaCodicia);
		
		assertFalse(jugadorUno.contieneCartaEnMano(monstruo1));
		assertFalse(jugadorUno.contieneCartaEnMano(monstruo2));
	}
	
	@Test
	public void test29jugadorPierdeVidaAlAtacarConCartaDebilAOtraMasFuerte() {

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
		
		Monstruo unaCarta = new Monstruo("Facundo", jugadorUno, estrellas, puntosUnaCarta);
		Monstruo otraCarta = new Monstruo("Javier", jugadorDos, estrellas, puntosOtraCarta);
		
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
		
		Monstruo unaCarta = new Monstruo("Facundo", jugadorUno, estrellas, puntosUnaCarta);
		Monstruo otraCarta = new Monstruo("Javier", jugadorDos, estrellas, puntosOtraCarta);
		
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
		
		Monstruo unaCarta = new Monstruo("Facundo", jugadorUno, estrellas, puntosUnaCarta);
		Monstruo otraCarta = new Monstruo("Javier", jugadorDos, estrellas, puntosOtraCarta);
		
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
		
		Monstruo unaCarta = new Monstruo("Facundo", jugadorUno, estrellas, puntosUnaCarta);
		Monstruo otraCarta = new Monstruo("Javier", jugadorUno, estrellas, puntosOtraCarta);
		
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

		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(3);
		
		Monstruo monstruoUno = new Monstruo("monstruo1", jugadorUno, estrellas, puntos);
		Monstruo monstruoDos = new Monstruo("monstruo2", jugadorDos, estrellas, puntos);
		
		CreadorDeCilindroMagico creador = new CreadorDeCilindroMagico(jugadorDos);
		BuilderDeCartas builder = new BuilderDeCartas(creador);
		Carta cilindroMagico = builder.obtenerCarta();
		
		jugadorDos.repartirCarta(cilindroMagico);
		jugadorDos.repartirCarta(monstruoDos);
		jugadorDos.agregarCartaEnCampo((Trampa) cilindroMagico);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		
		jugadorUno.atacarCartaConCarta(monstruoDos, monstruoUno);
		
		//Pierde 2000 de vida, que era el ataque del monstruoUno
		assertEquals(jugadorUno.obtenerVida().obtenerPuntosDeVida(), 6000);
		//El jugador atacado no pierde puntos de vida
		assertEquals(jugadorDos.obtenerVida().obtenerPuntosDeVida(), 8000);

	} 
	
	@Test
	public void test34CartaTrampaCilindroMagicoDespuesDelAtaqueVaAlCementerio() {
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(3);
		
		Monstruo monstruoUno = new Monstruo("monstruo1", jugadorUno, estrellas, puntos);
		Monstruo monstruoDos = new Monstruo("monstruo2", jugadorDos, estrellas, puntos);
		
		CreadorDeCilindroMagico creador = new CreadorDeCilindroMagico(jugadorDos);
		BuilderDeCartas builder = new BuilderDeCartas(creador);
		Carta cilindroMagico = builder.obtenerCarta();
		
		jugadorDos.repartirCarta(cilindroMagico);
		jugadorDos.repartirCarta(monstruoDos);
		jugadorDos.agregarCartaEnCampo((Trampa) cilindroMagico);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		
		jugadorUno.atacarCartaConCarta(monstruoDos, monstruoUno);
		
		//Pierde 2000 de vida, que era el ataque del monstruoUno
		assertTrue(jugadorDos.cartaEstaMuerta(cilindroMagico));
		
	} 
	
	@Test
	public void test35AtacarJugadorConCilindroMataLaCartaAtacante() {

		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(3);
		
		Monstruo monstruoUno = new Monstruo("monstruo1", jugadorUno, estrellas, puntos);
		Monstruo monstruoDos = new Monstruo("monstruo2", jugadorDos, estrellas, puntos);
		
		CreadorDeCilindroMagico creador = new CreadorDeCilindroMagico(jugadorDos);
		BuilderDeCartas builder = new BuilderDeCartas(creador);
		Carta cilindroMagico = builder.obtenerCarta();
		
		jugadorDos.repartirCarta(cilindroMagico);
		jugadorDos.repartirCarta(monstruoDos);
		jugadorDos.agregarCartaEnCampo((Trampa) cilindroMagico);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		
		jugadorUno.atacarCartaConCarta(monstruoDos, monstruoUno);
		
		assertTrue(jugadorUno.cartaEstaMuerta(monstruoUno));
		
	} 
	

	@Test
	public void test36AtacarJugadorConReinforcementAumentaLosPuntosDeAtaqueDeLaCartaAtacada() {

		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntosUno = new Puntos(400, 2000);
		Puntos puntosDos = new Puntos(0, 2000);
		Estrellas estrellas = new Estrellas(3);
		
		Monstruo monstruoUno = new Monstruo("monstruo1", jugadorUno, estrellas, puntosUno);
		Monstruo monstruoDos = new Monstruo("monstruo2", jugadorDos, estrellas, puntosDos);
		
		CreadorDeReinforcements creador = new CreadorDeReinforcements(jugadorDos);
		BuilderDeCartas builder = new BuilderDeCartas(creador);
		Carta reinforcements = builder.obtenerCarta();
		
		jugadorDos.repartirCarta(reinforcements);
		jugadorDos.repartirCarta(monstruoDos);
		jugadorDos.agregarCartaEnCampo((Trampa) reinforcements);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		
		jugadorUno.atacarCartaConCarta(monstruoDos, monstruoUno);
		
		//Aumenta de 0 a 500 el ataque, y por diferencia de puntos resta 100 al juagdorUno 
		assertEquals(jugadorUno.obtenerVida().obtenerPuntosDeVida(), 7900);
		
	} 
	
	@Test
	public void test37AgregarCartaDeCampoCuandoYaHayUnaCartaCampo() {

		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);

		CreadorDeWasteland creador = new CreadorDeWasteland(jugadorUno);
		BuilderDeCartas builder = new BuilderDeCartas(creador);
		Carta wastelandUno = builder.obtenerCarta();
		
		CreadorDeWasteland creador2 = new CreadorDeWasteland(jugadorUno);
		BuilderDeCartas builder2 = new BuilderDeCartas(creador2);
		Carta wastelandDos = builder2.obtenerCarta();
		
		jugadorUno.repartirCarta(wastelandUno);
		jugadorUno.agregarCartaEnCampo((Campo) wastelandUno);
		
		jugadorUno.repartirCarta(wastelandDos);

		
		assertThrows(ErrorYaHayCartaDeCampoInvocada.class, () -> jugadorUno.agregarCartaEnCampo((Campo) wastelandDos));
	}
	
	
	@Test
	public void test38Agregar6CartasMonstruosLanzaExcepcion() {
		
		Vida vida = new Vida (8000);
		Jugador jugador = new Jugador (vida);

		Puntos puntosUno = new Puntos(400, 2000);
		Estrellas estrellas = new Estrellas(3);
		
		Monstruo monstruoUno = new Monstruo("monstruo1", jugador, estrellas, puntosUno);
		Monstruo monstruoDos = new Monstruo("monstruo2", jugador, estrellas, puntosUno);
		Monstruo monstruoTres = new Monstruo("monstruo3", jugador, estrellas, puntosUno);
		Monstruo monstruoCuatro = new Monstruo("monstruo4", jugador, estrellas, puntosUno);
		Monstruo monstruoCinco = new Monstruo("monstruo5", jugador, estrellas, puntosUno);
		Monstruo monstruoSeis = new Monstruo("monstruo6", jugador, estrellas, puntosUno);
		
		jugador.repartirCarta(monstruoUno);
		jugador.repartirCarta(monstruoDos);
		jugador.repartirCarta(monstruoTres);
		jugador.repartirCarta(monstruoCuatro);
		jugador.repartirCarta(monstruoCinco);
		jugador.repartirCarta(monstruoSeis);
		
		jugador.agregarCartaEnCampo(monstruoUno);
		jugador.agregarCartaEnCampo(monstruoDos);
		jugador.agregarCartaEnCampo(monstruoTres);
		jugador.agregarCartaEnCampo(monstruoCuatro);
		jugador.agregarCartaEnCampo(monstruoCinco);
		
		assertThrows(ErrorYaHayCincoCartasEnLaFila.class, () -> jugador.agregarCartaEnCampo(monstruoSeis));
		
	}
	
	

}