package tests;
import jugabilidad.Jugador;
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
		
		jugador.agregarCartaEnCampo("Auriane");
		jugador.agregarCartaEnCampo("Javir");
		
		//Al agregar "Facundo", no se tendrian que borrar ninguno de los 2 mosntruos del tablero
		jugador.agregarCartaEnCampo("Facundo");
		
		assertFalse( jugador.monstruoEstaMuerto("Javir") );
		assertFalse( jugador.monstruoEstaMuerto("Auriane") );
	}
	
	@Test
	public void testColocarMosnstruoConNivelCincoYSacrificaUnaCarta() {
		Jugador jugador = new Jugador( 8000 );
		
		int nivel = 2;
		int nivelAlto = 5;
		Monstruo monstruoUno = new Monstruo("Auriane", jugador, nivel, 2000, 2000);
		Monstruo monstruoDos = new Monstruo("Javir", jugador, nivel, 2000, 2000);
		Monstruo monstruoTres = new Monstruo("Facundo", jugador, nivelAlto, 2000, 2000);
		
		jugador.repartirCarta(monstruoUno);
		jugador.repartirCarta(monstruoDos);
		jugador.repartirCarta(monstruoTres);
		
		jugador.agregarCartaEnCampo("Auriane");
		jugador.agregarCartaEnCampo("Javir");
		
		//Al agregar "Facundo", no se tendria que borrar 1 carta
		jugador.agregarCartaEnCampo("Facundo");
		
		assertFalse( jugador.monstruoEstaMuerto("Javir") );
		assertFalse( jugador.monstruoEstaMuerto("Auriane") );
	}
	
}