package tests;
import jugabilidad.Baraja;
import jugabilidad.Jugador;
import cartas.Campo;
import cartas.Magica;

import org.junit.jupiter.api.Test;

import atributos.Efecto;
import atributos.EfectoAgujeroNegro;
import atributos.EfectoWasteland;
import atributos.Estrellas;
import atributos.Puntos;
import atributos.Sacrificio;
import atributos.Vida;
import cartas.Monstruo;
import errores.ErrorSacrificiosInsuficientes;
import factories.AbstractFabricaDeCartas;
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
    	
		AbstractFabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
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
 
		AbstractFabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
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
    
		AbstractFabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
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

		AbstractFabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
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
    	
		AbstractFabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
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
    	
		AbstractFabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
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
		
		AbstractFabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
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
		Magica agujeroNegro = new Magica ("Agujero Negro", jugadorUno, efecto);
		
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
			
		AbstractFabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
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
				
		AbstractFabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
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
		Magica agujeroNegro = new Magica ("Agujero Negro", jugadorUno, efecto);
		
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
	public void test12AgregarCartaDeCampoWastelandAumentaPuntoDeAtaquesDeljugadorQuePusoLaCarta() {
		
		AbstractFabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
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
		
		Efecto efecto = new EfectoWasteland();
		Campo wasteland = new Campo("Wasteland", jugadorUno, efecto);
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorDos.repartirCarta(monstruoDos);
		jugadorUno.repartirCarta(wasteland);
		
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		jugadorUno.agregarCartaEnCampo(wasteland);
		
		assertEquals( puntosUno.obtenerPuntosDeAtaque(), 2300 );
	}
	
	@Test
	public void test13AgregarCartaDeCampoWastelandAumentaPuntoDeDefensaDeljugadorOponente() {
		
		AbstractFabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
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
		
		Efecto efecto = new EfectoWasteland();
		Campo wasteland = new Campo("Wasteland", jugadorUno, efecto);
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorDos.repartirCarta(monstruoDos);
		jugadorUno.repartirCarta(wasteland);
		
		jugadorUno.agregarCartaEnCampo(monstruoUno);
		jugadorDos.agregarCartaEnCampo(monstruoDos);
		jugadorUno.agregarCartaEnCampo(wasteland);
		
		assertEquals( puntosDos.obtenerPuntosDeDefensa(), 2200 );
	}
}