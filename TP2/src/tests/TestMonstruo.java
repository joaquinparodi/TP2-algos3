package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import cartas.Carta;
import cartas.Clase;
import cartas.Monstruo;

class TestMonstruo {

	//private static final double DELTA = 1e-2;
	
	@Test
	public void testMosntruoAtacaAOtroEnPosicionDeAtaqueDevuelveCorrectamenteLaDiferenciaDePuntos() {
		
		//Las cartes de clase monstruo se inicializan en posicion de ataque
		Clase monstruoJugadorUno = new Monstruo( 1000, 2000 );
		Clase monstruoJugadorDos = new Monstruo( 1500, 2000 );
		Carta cartaJugadorDos = new Carta( "Caniche", monstruoJugadorUno );
		Carta cartaJugadorUno = new Carta( "Chiuaua", monstruoJugadorDos );
		
		double diferenciaDePuntosObtenida = cartaJugadorUno.atacar( cartaJugadorDos );
		double diferenciaDePuntosEsperada = 1000 - 1500;
		assertEquals( diferenciaDePuntosObtenida, diferenciaDePuntosEsperada );
		
	}
	
	@Test
	public void testMonstruoAtacaAOtroEnPosicionDeDefensaDevuelveCorrectamenteLaDiferenciaDePuntos() {
		
		//Las cartes de clase monstruo se inicializan en posicion de ataque
		Clase monstruoJugadorUno = new Monstruo( 1000, 2500 );
		Clase monstruoJugadorDos = new Monstruo( 1500, 2000 );
		Carta cartaJugadorDos = new Carta( "Caniche", monstruoJugadorUno );
		Carta cartaJugadorUno = new Carta( "Chiuaua", monstruoJugadorDos );
		//Pongo las cartas en modo defensa
		cartaJugadorUno.cambiarPosicion(); 
		cartaJugadorDos.cambiarPosicion();
		
		double diferenciaDePuntosObtenida = cartaJugadorUno.atacar( cartaJugadorDos );
		double diferenciaDePuntosEsperada = 2500 - 2000;
		assertEquals( diferenciaDePuntosObtenida, diferenciaDePuntosEsperada );
		
	}

	
}
