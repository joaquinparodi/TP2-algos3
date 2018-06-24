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
		
    	FabricaDeCartas fabrica = new FabricaDeCartas(jugador);
    	
		Estrellas estrellas = new Estrellas(1);
    	Puntos puntos = new Puntos(2000, 2000);
    	
		Monstruo monstruoUno = fabrica.crearMonstruoPersonalizado("Auriane", estrellas,puntos);
		Monstruo monstruoDos = fabrica.crearMonstruoPersonalizado("Javir", estrellas, puntos);
		Monstruo monstruoTres = fabrica.crearMonstruoPersonalizado("Facundo", estrellas, puntos);
		
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
		
		FabricaDeCartas fabrica = new FabricaDeCartas(jugador);
		
		Monstruo monstruoUno = fabrica.crearMonstruoPersonalizado("joaco", estrellasMonstruoUno, puntos);
		Monstruo monstruoDos = fabrica.crearMonstruoPersonalizado("javi", estrellas, puntos);
		Monstruo monstruoTres = fabrica.crearMonstruoPersonalizado("facu", estrellas, puntos);
		
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
		
		FabricaDeCartas fabrica = new FabricaDeCartas(jugador);
		
		Monstruo monstruoUno = fabrica.crearMonstruoPersonalizado("joaco", estrellasMonstruoUno, puntos);
		Monstruo monstruoDos = fabrica.crearMonstruoPersonalizado("javi", estrellas, puntos);
		
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
		
		FabricaDeCartas fabrica = new FabricaDeCartas(jugador);
		
		Monstruo monstruoUno = fabrica.crearMonstruoPersonalizado("joaco", estrellasMonstruoUno, puntos);
		Monstruo monstruoDos = fabrica.crearMonstruoPersonalizado("javi", estrellas, puntos);
		
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
	
		FabricaDeCartas fabrica = new FabricaDeCartas(jugador);
		
		Monstruo monstruoUno = fabrica.crearMonstruoPersonalizado("joaco", estrellasMonstruoUno, puntos);
		
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
		
		FabricaDeCartas fabrica = new FabricaDeCartas(jugador);
		
		Monstruo monstruoUno = fabrica.crearMonstruoPersonalizado("joaco", estrellas, puntos);
		Monstruo monstruoDos = fabrica.crearMonstruoPersonalizado("javi", estrellas, puntos);
		
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
		
		FabricaDeCartas fabricaUno = new FabricaDeCartas(jugadorUno);
		FabricaDeCartas fabricaDos = new FabricaDeCartas(jugadorDos);

		
		Monstruo monstruoUno = fabricaUno.crearMonstruoPersonalizado("facu", estrellas, puntos);
		Monstruo monstruoDos = fabricaDos.crearMonstruoPersonalizado("javi", estrellas, puntos);
		
		Magica agujeroNegro = fabricaUno.crearAgujeroNegro();
		
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
					
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas(jugadorUno);
		
		Magica agujeroNegro = fabricaDeCartas.crearAgujeroNegro();
		
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
		
		FabricaDeCartas fabricaUno = new FabricaDeCartas(jugadorUno);
		FabricaDeCartas fabricaDos = new FabricaDeCartas(jugadorDos);

		Puntos puntos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo monstruoUno = fabricaUno.crearMonstruoPersonalizado("facu", estrellas, puntos);
		Monstruo monstruoDos = fabricaDos.crearMonstruoPersonalizado("javi", estrellas, puntos);
		
		Carta agujeroNegro = fabricaUno.crearAgujeroNegro();
		
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
		
		FabricaDeCartas fabricaUno = new FabricaDeCartas(jugadorUno);
		FabricaDeCartas fabricaDos = new FabricaDeCartas(jugadorDos);

		Puntos puntosUno = new Puntos(2000, 2000);
		Puntos puntosDos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo monstruoUno = fabricaUno.crearMonstruoPersonalizado("Facu", estrellas, puntosUno);
		Monstruo monstruoDos = fabricaDos.crearMonstruoPersonalizado("Javi", estrellas, puntosDos);

		Carta wasteland = fabricaUno.crearWasteland();
		
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
		
		FabricaDeCartas fabricaUno = new FabricaDeCartas(jugadorUno);
		FabricaDeCartas fabricaDos = new FabricaDeCartas(jugadorDos);
		
		Puntos puntosUno = new Puntos(2000, 2000);
		Puntos puntosDos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo monstruoUno = fabricaUno.crearMonstruoPersonalizado("Facu", estrellas, puntosUno);
		Monstruo monstruoDos = fabricaDos.crearMonstruoPersonalizado("Javi", estrellas, puntosDos);
		
		Carta wasteland = fabricaUno.crearWasteland();
		
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
		
		FabricaDeCartas fabricaUno = new FabricaDeCartas(jugadorUno);
		FabricaDeCartas fabricaDos = new FabricaDeCartas(jugadorDos);
		
		Puntos puntosUno = new Puntos(2000, 2000);
		Puntos puntosDos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo monstruoUno = fabricaUno.crearMonstruoPersonalizado("Facu", estrellas, puntosUno);
		Monstruo monstruoDos = fabricaDos.crearMonstruoPersonalizado("Javi", estrellas, puntosDos);
		
		Carta sogen = fabricaUno.crearSogen();
		
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
		
		FabricaDeCartas fabricaUno = new FabricaDeCartas(jugadorUno);
		FabricaDeCartas fabricaDos = new FabricaDeCartas(jugadorDos);
		
		Puntos puntosUno = new Puntos(2000, 2000);
		Puntos puntosDos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo monstruoUno = fabricaUno.crearMonstruoPersonalizado("Facu", estrellas, puntosUno);
		Monstruo monstruoDos = fabricaDos.crearMonstruoPersonalizado("Javi", estrellas, puntosDos);
		
		Carta sogen = fabricaUno.crearSogen();
		
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
		
		FabricaDeCartas fabricaUno = new FabricaDeCartas(jugadorUno);
		FabricaDeCartas fabricaDos = new FabricaDeCartas(jugadorDos);
		
		Puntos puntosDosA = new Puntos(3000, 2000);
		Puntos puntosDosB = new Puntos(1000, 2000);
		
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo monstruoDosA = fabricaDos.crearMonstruoPersonalizado("Facu", estrellas, puntosDosA);
		Monstruo monstruoDosB = fabricaDos.crearMonstruoPersonalizado("Javi", estrellas, puntosDosB);
		
		Magica fisura = fabricaUno.crearFisura();
		
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

		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		FabricaDeCartas fabricaUno = new FabricaDeCartas(jugadorUno);
		FabricaDeCartas fabricaDos = new FabricaDeCartas(jugadorDos);
		
		Puntos puntosDosA = new Puntos(3000, 2000);
		Puntos puntosDosB = new Puntos(1000, 2000);
		
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo monstruoDosA = fabricaDos.crearMonstruoPersonalizado("Facu", estrellas, puntosDosA);
		Monstruo monstruoDosB = fabricaDos.crearMonstruoPersonalizado("Javi", estrellas, puntosDosB);
		
		Magica fisura = fabricaUno.crearFisura();
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

		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		FabricaDeCartas fabricaUno = new FabricaDeCartas(jugadorUno);
		FabricaDeCartas fabricaDos = new FabricaDeCartas(jugadorDos);
		
		Puntos puntosUno = new Puntos(2000, 2000);
		Puntos puntosDos = new Puntos(2000, 2000);
		Puntos puntosTres = new Puntos(2000, 2000);
		Puntos puntosCuatro = new Puntos(2000, 2000);
		
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo monstruoUno = fabricaUno.crearMonstruoPersonalizado("Facu", estrellas, puntosUno);
		Monstruo monstruoDos = fabricaDos.crearMonstruoPersonalizado("Javi", estrellas, puntosDos);
		
		Campo sogen = fabricaUno.crearSogen();
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorDos.repartirCarta(monstruoDos);
		jugadorUno.repartirCarta(sogen);
		
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		jugadorUno.agregarCartaEnCampo(sogen);
		
		Monstruo monstruoTres = fabricaUno.crearMonstruoPersonalizado("Facu", estrellas, puntosTres);
		Monstruo monstruoCuatro = fabricaDos.crearMonstruoPersonalizado("Javi", estrellas, puntosCuatro);		
		
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
		
		FabricaDeCartas fabricaUno = new FabricaDeCartas(jugadorUno);
		FabricaDeCartas fabricaDos = new FabricaDeCartas(jugadorDos);
		
		Puntos puntosUno = new Puntos(2000, 2000);
		Puntos puntosDos = new Puntos(2000, 2000);
		Puntos puntosTres = new Puntos(2000, 2000);
		Puntos puntosCuatro = new Puntos(2000, 2000);
		
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo monstruoUno = fabricaUno.crearMonstruoPersonalizado("Facu", estrellas, puntosUno);
		Monstruo monstruoDos = fabricaDos.crearMonstruoPersonalizado("Javi", estrellas, puntosDos);
		
		Campo sogen = fabricaUno.crearSogen();
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorDos.repartirCarta(monstruoDos);
		jugadorUno.repartirCarta(sogen);
		
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		jugadorUno.agregarCartaEnCampo(sogen);
		
		Monstruo monstruoTres = fabricaUno.crearMonstruoPersonalizado("Facu", estrellas, puntosTres);
		Monstruo monstruoCuatro = fabricaDos.crearMonstruoPersonalizado("Javi", estrellas, puntosCuatro);		
		
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
		
		FabricaDeCartas fabricaUno = new FabricaDeCartas(jugadorUno);
		FabricaDeCartas fabricaDos = new FabricaDeCartas(jugadorDos);
		
		Puntos puntosUno = new Puntos(2000, 2000);
		Puntos puntosDos = new Puntos(2000, 2000);
		Puntos puntosTres = new Puntos(2000, 2000);
		Puntos puntosCuatro = new Puntos(2000, 2000);
		
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo monstruoUno = fabricaUno.crearMonstruoPersonalizado("Facu", estrellas, puntosUno);
		Monstruo monstruoDos = fabricaDos.crearMonstruoPersonalizado("Javi", estrellas, puntosDos);
		
		Campo wasteland = fabricaUno.crearWasteland();
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorDos.repartirCarta(monstruoDos);
		jugadorUno.repartirCarta(wasteland);
		
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		jugadorUno.agregarCartaEnCampo((Campo) wasteland);
		
		Monstruo monstruoTres = fabricaUno.crearMonstruoPersonalizado("Joaco", estrellas, puntosTres);
		Monstruo monstruoCuatro = fabricaDos.crearMonstruoPersonalizado("Auriane", estrellas, puntosCuatro);		
		
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
		
		FabricaDeCartas fabricaUno = new FabricaDeCartas(jugadorUno);
		FabricaDeCartas fabricaDos = new FabricaDeCartas(jugadorDos);

		Puntos puntosUno = new Puntos(2000, 2000);
		Puntos puntosDos = new Puntos(2000, 2000);
		Puntos puntosTres = new Puntos(2000, 2000);
		Puntos puntosCuatro = new Puntos(2000, 2000);
		
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo monstruoUno = fabricaUno.crearMonstruoPersonalizado("Facu", estrellas, puntosUno);
		Monstruo monstruoDos = fabricaDos.crearMonstruoPersonalizado("Javi", estrellas, puntosDos);
		
		Campo wasteland = fabricaUno.crearWasteland();
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorDos.repartirCarta(monstruoDos);
		jugadorUno.repartirCarta(wasteland);
		
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		jugadorUno.agregarCartaEnCampo(wasteland);
		
		Monstruo monstruoTres = fabricaUno.crearMonstruoPersonalizado("Facu", estrellas, puntosTres);
		Monstruo monstruoCuatro = fabricaDos.crearMonstruoPersonalizado("Javi", estrellas, puntosCuatro);		
		
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
		
		FabricaDeCartas fabricaUno = new FabricaDeCartas(jugadorUno);
		FabricaDeCartas fabricaDos = new FabricaDeCartas(jugadorDos);

		Puntos puntosUno = new Puntos(2200, 1100);
		Puntos puntosDos = new Puntos(2000, 2500);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo monstruoUno = fabricaUno.crearMonstruoPersonalizado("Facu", estrellas, puntosUno);
		Monstruo monstruoDos = fabricaDos.crearMonstruoPersonalizado("Javi", estrellas, puntosDos);
		
		Campo sogen = fabricaUno.crearSogen();
		
		Campo wasteland = fabricaDos.crearWasteland();
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorDos.repartirCarta(monstruoDos);
		
		jugadorUno.repartirCarta(sogen);
		jugadorDos.repartirCarta(wasteland);
		
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		
		jugadorUno.agregarCartaEnCampo(sogen);
		jugadorDos.agregarCartaEnCampo(wasteland);
		
		
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
		
		FabricaDeCartas fabricaUno = new FabricaDeCartas(jugadorUno);
		FabricaDeCartas fabricaDos = new FabricaDeCartas(jugadorDos);

		Puntos puntos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(3);
		
		Monstruo monstruo1 = fabricaUno.crearMonstruoPersonalizado("monstruo1", estrellas, puntos);
		Monstruo monstruo2 = fabricaUno.crearMonstruoPersonalizado("monstruo2", estrellas, puntos);
		
		Magica ollaDeLaCodicia = fabricaUno.crearOllaDeLaCodicia();
		
		Baraja mazo = new Baraja();
		mazo.agregarCarta(monstruo1);
		mazo.agregarCarta(monstruo2);
		jugadorUno.asignarMazo(mazo);
		
		jugadorUno.repartirCarta(ollaDeLaCodicia);	
		jugadorUno.agregarCartaEnCampo(ollaDeLaCodicia);
		
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
		
		FabricaDeCartas fabricaUno = new FabricaDeCartas(jugadorUno);
		FabricaDeCartas fabricaDos = new FabricaDeCartas(jugadorDos);
		
		Puntos puntos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(3);
		
		Monstruo monstruo1 = fabricaUno.crearMonstruoPersonalizado("monstruo1", estrellas, puntos);
		Monstruo monstruo2 = fabricaUno.crearMonstruoPersonalizado("monstruo2", estrellas, puntos);
		
		Magica ollaDeLaCodicia = fabricaUno.crearOllaDeLaCodicia();
		
		Baraja mazo = new Baraja();
		mazo.agregarCarta(monstruo1);
		mazo.agregarCarta(monstruo2);
		jugadorUno.asignarMazo(mazo);
		
		ollaDeLaCodicia.voltear();
		jugadorUno.repartirCarta(ollaDeLaCodicia);
		jugadorUno.agregarCartaEnCampo(ollaDeLaCodicia);
		
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
		
		FabricaDeCartas fabricaUno = new FabricaDeCartas(jugadorUno);
		FabricaDeCartas fabricaDos = new FabricaDeCartas(jugadorDos);

		//La carta se inicializa en modo ataque
		Puntos puntosUnaCarta = new Puntos(1000, 2000);
		Puntos puntosOtraCarta = new Puntos(1500, 2500);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo unaCarta = fabricaUno.crearMonstruoPersonalizado("Facundo", estrellas, puntosUnaCarta);
		Monstruo otraCarta = fabricaDos.crearMonstruoPersonalizado("Javier", estrellas, puntosOtraCarta);
		
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
		
		FabricaDeCartas fabricaUno = new FabricaDeCartas(jugadorUno);
		FabricaDeCartas fabricaDos = new FabricaDeCartas(jugadorDos);

		//La carta se inicializa en modo ataque
		Puntos puntosUnaCarta = new Puntos(1000, 2000);
		Puntos puntosOtraCarta = new Puntos(1500, 2500);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo unaCarta = fabricaUno.crearMonstruoPersonalizado("Facundo", estrellas, puntosUnaCarta);
		Monstruo otraCarta = fabricaDos.crearMonstruoPersonalizado("Javier", estrellas, puntosOtraCarta);
		
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

		FabricaDeCartas fabricaUno = new FabricaDeCartas(jugadorUno);
		FabricaDeCartas fabricaDos = new FabricaDeCartas(jugadorDos);
		
		//La carta se inicializa en modo ataque
		Puntos puntosUnaCarta = new Puntos(1000, 2000);
		Puntos puntosOtraCarta = new Puntos(1500, 2500);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo unaCarta = fabricaUno.crearMonstruoPersonalizado("Facundo", estrellas, puntosUnaCarta);
		Monstruo otraCarta = fabricaDos.crearMonstruoPersonalizado("Javier", estrellas, puntosOtraCarta);
		
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

		FabricaDeCartas fabricaUno = new FabricaDeCartas(jugadorUno);

		//La carta se inicializa en modo ataque
		Puntos puntosUnaCarta = new Puntos(1000, 2000);
		Puntos puntosOtraCarta = new Puntos(1500, 2500);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo unaCarta = fabricaUno.crearMonstruoPersonalizado("Facundo", estrellas, puntosUnaCarta);
		Monstruo otraCarta = fabricaUno.crearMonstruoPersonalizado("Javier", estrellas, puntosOtraCarta);
		
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
		
		FabricaDeCartas fabricaUno = new FabricaDeCartas(jugadorUno);
		FabricaDeCartas fabricaDos = new FabricaDeCartas(jugadorDos);

		Puntos puntos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(3);
		
		Monstruo monstruoUno = fabricaUno.crearMonstruoPersonalizado("monstruo1", estrellas, puntos);
		Monstruo monstruoDos = fabricaDos.crearMonstruoPersonalizado("monstruo2", estrellas, puntos);
		
		Trampa cilindroMagico = fabricaDos.crearCilindroMagico();
		
		jugadorDos.repartirCarta(cilindroMagico);
		jugadorDos.repartirCarta(monstruoDos);
		jugadorDos.agregarCartaEnCampo(cilindroMagico);
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
		
		FabricaDeCartas fabricaUno = new FabricaDeCartas(jugadorUno);
		FabricaDeCartas fabricaDos = new FabricaDeCartas(jugadorDos);

		Puntos puntos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(3);
		
		Monstruo monstruoUno = fabricaUno.crearMonstruoPersonalizado("monstruo1", estrellas, puntos);
		Monstruo monstruoDos = fabricaDos.crearMonstruoPersonalizado("monstruo2", estrellas, puntos);
		
		Trampa cilindroMagico = fabricaDos.crearCilindroMagico();
		
		jugadorDos.repartirCarta(cilindroMagico);
		jugadorDos.repartirCarta(monstruoDos);
		jugadorDos.agregarCartaEnCampo(cilindroMagico);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		
		jugadorUno.atacarCartaConCarta(monstruoDos, monstruoUno);
		
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
		
		FabricaDeCartas fabricaUno = new FabricaDeCartas(jugadorUno);
		FabricaDeCartas fabricaDos = new FabricaDeCartas(jugadorDos);

		Puntos puntos = new Puntos(2000, 2000);
		Estrellas estrellas = new Estrellas(3);
		
		Monstruo monstruoUno = fabricaUno.crearMonstruoPersonalizado("monstruo1", estrellas, puntos);
		Monstruo monstruoDos = fabricaDos.crearMonstruoPersonalizado("monstruo2", estrellas, puntos);
		
		Trampa cilindroMagico = fabricaDos.crearCilindroMagico();
		
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

		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		FabricaDeCartas fabricaUno = new FabricaDeCartas(jugadorUno);
		FabricaDeCartas fabricaDos = new FabricaDeCartas(jugadorDos);

		Puntos puntosUno = new Puntos(400, 2000);
		Puntos puntosDos = new Puntos(0, 2000);
		Estrellas estrellas = new Estrellas(3);
		
		Monstruo monstruoUno = fabricaUno.crearMonstruoPersonalizado("monstruo1", estrellas, puntosUno);
		Monstruo monstruoDos = fabricaDos.crearMonstruoPersonalizado("monstruo2", estrellas, puntosDos);
		
		Trampa reinforcements = fabricaDos.crearReinforcements();
		
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
	public void test37AgregarCartaDeCampoCuandoYaHayUnaCartaCampo() {

		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		FabricaDeCartas fabricaUno = new FabricaDeCartas(jugadorUno);

		Campo wastelandUno = fabricaUno.crearWasteland();
		Campo wastelandDos = fabricaUno.crearWasteland();
		
		jugadorUno.repartirCarta(wastelandUno);
		jugadorUno.agregarCartaEnCampo((Campo) wastelandUno);
		
		jugadorUno.repartirCarta(wastelandDos);

		
		assertThrows(ErrorYaHayCartaDeCampoInvocada.class, () -> jugadorUno.agregarCartaEnCampo((Campo) wastelandDos));
	}
	
	
	@Test
	public void test38Agregar6CartasMonstruosLanzaExcepcion() {
		
		Vida vida = new Vida (8000);
		Jugador jugador = new Jugador (vida);
		
		FabricaDeCartas fabricaUno = new FabricaDeCartas(jugador);

		Puntos puntosUno = new Puntos(400, 2000);
		Estrellas estrellas = new Estrellas(3);
		
		Monstruo monstruoUno = fabricaUno.crearMonstruoPersonalizado("monstruo1", estrellas, puntosUno);
		Monstruo monstruoDos = fabricaUno.crearMonstruoPersonalizado("monstruo2", estrellas, puntosUno);
		Monstruo monstruoTres = fabricaUno.crearMonstruoPersonalizado("monstruo3", estrellas, puntosUno);
		Monstruo monstruoCuatro = fabricaUno.crearMonstruoPersonalizado("monstruo4", estrellas, puntosUno);
		Monstruo monstruoCinco = fabricaUno.crearMonstruoPersonalizado("monstruo5", estrellas, puntosUno);
		Monstruo monstruoSeis = fabricaUno.crearMonstruoPersonalizado("monstruo6", estrellas, puntosUno);
		
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