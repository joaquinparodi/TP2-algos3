package tests;
import jugabilidad.Baraja;
import jugabilidad.Jugador;
import cartas.Magica;

import org.junit.jupiter.api.Test;

import atributos.Efecto;
import atributos.EfectoAgujeroNegro;
import atributos.Estrellas;
import atributos.Puntos;
import atributos.Sacrificio;
import atributos.Vida;
import cartas.Monstruo;
import errores.ErrorSacrificiosInsuficientes;

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
		
		jugador.agregarCartaMonstruoEnCampo("Auriane");
		jugador.agregarCartaMonstruoEnCampo("Javir");
		
		//Al agregar "Facundo", no se tendrian que borrar ninguno de los 2 mosntruos del tablero
		jugador.agregarCartaMonstruoEnCampo("Facundo");
		
		assertFalse( jugador.monstruoEstaMuerto("Javir") );
		assertFalse( jugador.monstruoEstaMuerto("Auriane") );
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
		
		jugador.agregarCartaMonstruoEnCampo("joaco", monstruosASacrificar);
		
		assertTrue( jugador.monstruoEstaMuerto("javi") );
		assertTrue( jugador.monstruoEstaMuerto("facu") );
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
		
		jugador.agregarCartaMonstruoEnCampo("joaco", monstruosASacrificar);
		
		assert( jugador.monstruoEstaMuerto("javi") );
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
		
		jugador.agregarCartaMonstruoEnCampo("joaco", monstruosASacrificar);
		
		assert( ((jugador.obtenerCampo()).obtenerMonstruos()).pertenece("joaco") );
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
		
		Assertions.assertThrows(ErrorSacrificiosInsuficientes.class, () -> jugador.agregarCartaMonstruoEnCampo("joaco", monstruosASacrificar));
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
		
		jugador.agregarCartaMonstruoEnCampo("joaco");
		assertFalse( jugador.monstruoEstaMuerto("javi") );
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
		
		Efecto efecto = new EfectoAgujeroNegro ();
		Magica agujeroNegro = new Magica ("Agujero Negro", jugadorUno, efecto);
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorUno.repartirCarta(agujeroNegro);
		jugadorDos.repartirCarta(monstruoDos);
		
		jugadorUno.agregarCartaMonstruoEnCampo("facu");
		jugadorDos.agregarCartaMonstruoEnCampo("javi");
		
		jugadorUno.agregarCartaMagicaEnCampo("Agujero Negro");
		
		assertTrue(jugadorUno.monstruoEstaMuerto("facu"));
		assertTrue(jugadorDos.monstruoEstaMuerto("javi"));
	}
	
	@Test
	public void test10AgregarAgujeroNegroNoQuitaVidaAJugadores () {
			
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Efecto efecto = new EfectoAgujeroNegro ();
		Magica agujeroNegro = new Magica ("Agujero Negro", jugadorUno, efecto);
		
		jugadorUno.repartirCarta(agujeroNegro);
		jugadorUno.agregarCartaMagicaEnCampo("Agujero Negro");
		
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
		
		Efecto efecto = new EfectoAgujeroNegro ();
		Magica agujeroNegro = new Magica ("Agujero Negro", jugadorUno, efecto);
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorUno.repartirCarta(agujeroNegro);
		jugadorDos.repartirCarta(monstruoDos);
		
		jugadorUno.voltearCartaDeMano("Agujero Negro");
		
		jugadorUno.agregarCartaMonstruoEnCampo("facu");
		jugadorDos.agregarCartaMonstruoEnCampo("javi");
		
		jugadorUno.agregarCartaMagicaEnCampo("Agujero Negro");
		
		assertFalse(jugadorUno.monstruoEstaMuerto("facu"));
		assertFalse(jugadorDos.monstruoEstaMuerto("javi"));
		
	}
}