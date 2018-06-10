package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import cartas.Carta;
import cartas.Clase;
import cartas.Monstruo;
import jugabilidad.Jugador;

class TestCarta {

	//private static final double DELTA = 1e-2;
	
	@Test
	public void testCartaAtacaAOtraEnPosicionDeAtaqueConMasPuntosQuitaPuntos() {
		
		//Las cartqs de clase monstruo se inicializan en mano 
		Clase monstruoJugadorUno = new Monstruo( 1000, 2000 );
		Clase monstruoJugadorDos = new Monstruo( 1500, 2000 );

		Jugador unJugador = new Jugador(8000);
		Jugador otroJugador = new Jugador(8000);

		Carta cartaJugadorDos = new Carta( "Caniche", monstruoJugadorUno , otroJugador );
		Carta cartaJugadorUno = new Carta( "Chiuaua", monstruoJugadorDos , unJugador );


		cartaJugadorUno.atacar( cartaJugadorDos );

		double vidaEsperada = 7500;
		double vidaObtenida = unJugador.obtenerVida();

		assertEquals(vidaEsperada,vidaObtenida);
		
	}
	
	@Test
	public void testCartaEnAtaqueAtacaAOtraEnDefensaRestaUnaCantidadDePuntosDistinta() {

		//Las cartes de clase monstruo se inicializan en posicion de ataque
		Clase monstruoJugadorUno = new Monstruo( 1000, 2000 );
		Clase monstruoJugadorDos = new Monstruo( 1500, 2000 );

		Jugador unJugador = new Jugador(8000);
		Jugador otroJugador = new Jugador(8000);

		Carta cartaJugadorDos = new Carta( "Caniche", monstruoJugadorUno , otroJugador );
		Carta cartaJugadorUno = new Carta( "Chiuaua", monstruoJugadorDos , unJugador );

		cartaJugadorDos.cambiarPosicion();

		cartaJugadorUno.atacar( cartaJugadorDos );

		double vidaEsperada = 7000;
		double vidaObtenida = unJugador.obtenerVida();

		assertEquals(vidaEsperada,vidaObtenida);

	}
	
}
