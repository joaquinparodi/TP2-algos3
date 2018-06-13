package tests;
import jugabilidad.Baraja;
import jugabilidad.Jugador;
import cartas.AgujeroNegro;
import cartas.Magica;

import org.junit.jupiter.api.Test;

import cartas.Monstruo;

import static org.junit.jupiter.api.Assertions.*;

class TestJugador {

    @Test
    public void testJugadorNuevoTieneXPuntos (){
        Jugador unJugador = new Jugador(8000);

        double vidaEsperada = 8000;
        double vidaObtenida = unJugador.obtenerVida();

        assertEquals(vidaEsperada,vidaObtenida);
    }

    @Test
    public void testJugadorRecibeDanio(){

        Jugador unJugador = new Jugador(8000);

        unJugador.hacerDanio(2000);

        double vidaEsperada = 6000;
        double vidaObtenida = unJugador.obtenerVida();

        assertEquals(vidaEsperada,vidaObtenida);
    }
    
	@Test
	public void testColocarMosnstruoConNivelInferiorACincoYNoSacrificaANadie() {
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
	public void testColocarMonstruoConNivel7Requiere2Sacrificios() {
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
	public void testColocarMonstruoConNivel5Requiere1Sacrificio() {
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
	public void testColocarMonstruoConNivel3NoRequiereSacrificio() {
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
	public void testAgregarAgujeroNegroEliminaTodasLasCartas () {
		
		Jugador jugadorUno = new Jugador ( 8000 );
		Jugador jugadorDos = new Jugador ( 8000 );
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Monstruo monstruoUno = new Monstruo("facu", jugadorUno, 1, 2000, 2000);
		Monstruo monstruoDos = new Monstruo("javi", jugadorDos, 1, 2000, 2000);
		
		Magica agujeroNegro = new AgujeroNegro ("Agujero Negro", jugadorUno);
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorUno.repartirCarta(agujeroNegro);
		jugadorDos.repartirCarta(monstruoDos);
		
		jugadorUno.agregarCartaMonstruoEnCampo("facu", new Baraja());
		jugadorDos.agregarCartaMonstruoEnCampo("javi", new Baraja());
		
		jugadorUno.agregarCartaMagicaEnCampo("Agujero Negro");
		
		assertTrue(jugadorUno.monstruoEstaMuerto("facu"));
		assertTrue(jugadorDos.monstruoEstaMuerto("javi"));
	}
	
}