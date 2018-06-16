package tests;
import jugabilidad.Baraja;
import jugabilidad.Jugador;
import cartas.Efecto;
import cartas.EfectoAgujeroNegro;
import cartas.Magica;

import org.junit.jupiter.api.Test;

import cartas.Monstruo;
import errores.ErrorSacrificiosInsuficientes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;

class TestJugador {

    @Test
    public void test01JugadorNuevoTieneXPuntos (){
        Jugador unJugador = new Jugador(8000);

        double vidaEsperada = 8000;
        double vidaObtenida = unJugador.obtenerVida();

        assertEquals(vidaEsperada,vidaObtenida);
    }

    @Test
    public void test02JugadorRecibeDanio(){

        Jugador unJugador = new Jugador(8000);

        unJugador.hacerDanio(2000);

        double vidaEsperada = 6000;
        double vidaObtenida = unJugador.obtenerVida();

        assertEquals(vidaEsperada,vidaObtenida);
    }
    
	@Test
	public void test03ColocarMosnstruoConNivelInferiorACincoYNoSacrificaANadie() {
		Jugador jugador = new Jugador( 8000 );
		
		int nivel = 2;
		Monstruo monstruoUno = new Monstruo("Auriane", jugador, nivel, 2000, 2000);
		Monstruo monstruoDos = new Monstruo("Javir", jugador, nivel, 2000, 2000);
		Monstruo monstruoTres = new Monstruo("Facundo", jugador, nivel, 2000, 2000);
		
		jugador.repartirCarta(monstruoUno);
		jugador.repartirCarta(monstruoDos);
		jugador.repartirCarta(monstruoTres);
		
		jugador.agregarCartaMonstruoEnCampo("Auriane", new Baraja());
		jugador.agregarCartaMonstruoEnCampo("Javir", new Baraja());
		
		//Al agregar "Facundo", no se tendrian que borrar ninguno de los 2 mosntruos del tablero
		jugador.agregarCartaMonstruoEnCampo("Facundo", new Baraja());
		
		assertFalse( jugador.monstruoEstaMuerto("Javir") );
		assertFalse( jugador.monstruoEstaMuerto("Auriane") );
	}
	
	
	@Test
	public void test04ColocarMonstruoConNivel7Requiere2Sacrificios() {
		Jugador jugador = new Jugador( 8000 );
		
		int nivel = 7;
		Monstruo monstruoUno = new Monstruo("joaco", jugador, nivel, 2000, 2000);
		Monstruo monstruoDos = new Monstruo("javi", jugador, nivel, 2000, 2000);
		Monstruo monstruoTres = new Monstruo("facu", jugador, nivel, 2000, 2000);
		
		jugador.repartirCarta(monstruoUno);
		jugador.repartirCarta(monstruoDos);
		jugador.repartirCarta(monstruoTres);
		
		Baraja monstruosASacrificar = new Baraja();
		monstruosASacrificar.agregarCarta(monstruoDos);
		monstruosASacrificar.agregarCarta(monstruoTres);
		
		jugador.agregarCartaMonstruoEnCampo("joaco", monstruosASacrificar);
		
		assert( jugador.monstruoEstaMuerto("javi") );
		assert( jugador.monstruoEstaMuerto("facu") );
	}
	
	@Test
	public void test05ColocarMonstruoConNivel5Requiere1Sacrificio() {
		Jugador jugador = new Jugador( 8000 );
		
		int nivel = 5;
		Monstruo monstruoUno = new Monstruo("joaco", jugador, nivel, 2000, 2000);
		Monstruo monstruoDos = new Monstruo("javi", jugador, nivel, 2000, 2000);
		
		jugador.repartirCarta(monstruoUno);
		jugador.repartirCarta(monstruoDos);
		
		Baraja monstruosASacrificar = new Baraja();
		monstruosASacrificar.agregarCarta(monstruoDos);
		
		jugador.agregarCartaMonstruoEnCampo("joaco", monstruosASacrificar);
		
		assert( jugador.monstruoEstaMuerto("javi") );
	}
	
	@Test
	public void test06VerificarSiElMonstroColocadoSeEncuentraEnElCampoLuegoDeSacrificio() {
		Jugador jugador = new Jugador( 8000 );
		
		int nivel = 5;
		Monstruo monstruoUno = new Monstruo("joaco", jugador, nivel, 2000, 2000);
		Monstruo monstruoDos = new Monstruo("javi", jugador, nivel, 2000, 2000);
		
		jugador.repartirCarta(monstruoUno);
		jugador.repartirCarta(monstruoDos);
		
		Baraja monstruosASacrificar = new Baraja();
		monstruosASacrificar.agregarCarta(monstruoDos);
		
		jugador.agregarCartaMonstruoEnCampo("joaco", monstruosASacrificar);
		
		assert ( ((jugador.obtenerCampo()).obtenerMonstruos()).pertenece("joaco") );
	}
	
	@Test
	public void test07InvocarMonstruoNivel5SinEnviarMonstruosASacrificarLanzaError() {
		Jugador jugador = new Jugador( 8000 );
		
		int nivel = 5;
		Monstruo monstruoUno = new Monstruo("joaco", jugador, nivel, 2000, 2000);
		
		jugador.repartirCarta(monstruoUno);
		
		Baraja monstruosASacrificar = new Baraja();
		
		Assertions.assertThrows(ErrorSacrificiosInsuficientes.class, () -> jugador.agregarCartaMonstruoEnCampo("joaco", monstruosASacrificar));
	}
	
	@Test
	public void test08ColocarMonstruoConNivel3NoRequiereSacrificio() {
		Jugador jugador = new Jugador( 8000 );
		
		int nivel = 3;
		Monstruo monstruoUno = new Monstruo("joaco", jugador, nivel, 2000, 2000);
		Monstruo monstruoDos = new Monstruo("javi", jugador, nivel, 2000, 2000);
		
		jugador.repartirCarta(monstruoUno);
		jugador.repartirCarta(monstruoDos);
		
		Baraja monstruosASacrificar = new Baraja();
		monstruosASacrificar.agregarCarta(monstruoDos);
		
		jugador.agregarCartaMonstruoEnCampo("joaco", monstruosASacrificar);
		
		assertFalse( jugador.monstruoEstaMuerto("javi") );
	}
	
	@Test
	public void test09AgregarAgujeroNegroEliminaTodasLasCartas () {
		
		Jugador jugadorUno = new Jugador ( 8000 );
		Jugador jugadorDos = new Jugador ( 8000 );
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Monstruo monstruoUno = new Monstruo("facu", jugadorUno, 1, 2000, 2000);
		Monstruo monstruoDos = new Monstruo("javi", jugadorDos, 1, 2000, 2000);
		
		Efecto efecto = new EfectoAgujeroNegro ();
		Magica agujeroNegro = new Magica ("Agujero Negro", jugadorUno, efecto);
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorUno.repartirCarta(agujeroNegro);
		jugadorDos.repartirCarta(monstruoDos);
		
		jugadorUno.agregarCartaMonstruoEnCampo("facu", new Baraja());
		jugadorDos.agregarCartaMonstruoEnCampo("javi", new Baraja());
		
		jugadorUno.agregarCartaMagicaEnCampo("Agujero Negro");
		
		assertTrue(jugadorUno.monstruoEstaMuerto("facu"));
		assertTrue(jugadorDos.monstruoEstaMuerto("javi"));
	}
	
	@Test
	public void test10AgregarAgujeroNegroNoQuitaVidaAJugadores () {
		
		Jugador jugadorUno = new Jugador ( 8000 );
		Jugador jugadorDos = new Jugador ( 8000 );
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Efecto efecto = new EfectoAgujeroNegro ();
		Magica agujeroNegro = new Magica ("Agujero Negro", jugadorUno, efecto);
		
		jugadorUno.repartirCarta(agujeroNegro);
		jugadorUno.agregarCartaMagicaEnCampo("Agujero Negro");
		
		assertTrue(jugadorUno.obtenerVida() == 8000);
		assertTrue(jugadorDos.obtenerVida() == 8000);
		
	}
	
	@Test
	public void test11AgujeroNegroBocaAbajoNoEnviaMonstruosAlCementerio () {
		
		Jugador jugadorUno = new Jugador ( 8000 );
		Jugador jugadorDos = new Jugador ( 8000 );
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Monstruo monstruoUno = new Monstruo("facu", jugadorUno, 1, 2000, 2000);
		Monstruo monstruoDos = new Monstruo("javi", jugadorDos, 1, 2000, 2000);
		
		Efecto efecto = new EfectoAgujeroNegro ();
		Magica agujeroNegro = new Magica ("Agujero Negro", jugadorUno, efecto);
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorUno.repartirCarta(agujeroNegro);
		jugadorDos.repartirCarta(monstruoDos);
		
		jugadorUno.voltearCartaDeMano("Agujero Negro");
		
		jugadorUno.agregarCartaMonstruoEnCampo("facu", new Baraja());
		jugadorDos.agregarCartaMonstruoEnCampo("javi", new Baraja());
		
		jugadorUno.agregarCartaMagicaEnCampo("Agujero Negro");
		
		assertFalse(jugadorUno.monstruoEstaMuerto("facu"));
		assertFalse(jugadorDos.monstruoEstaMuerto("javi"));
		
	}
}