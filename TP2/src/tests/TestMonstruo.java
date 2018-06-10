package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestMonstruo {

	private static final double DELTA = 1e-2;
	
	@Test
	public void testMosntruoAtacaAOtroEnPosicionDeAtaqueConMayorAtaqueYSeDestruyeElQueAtaco() {
		
		//Las cartes de clase monstruo se inicializan en posicion de ataque
		Clase monstruoJugadorUno = new Monstruo( 1000, 2000 );
		Clase monstruoJugadorDos = new Monstruo( 1500, 2000 );
		Carta cartaJugadorDos = new Carta( "Caniche", monstruoJugadorUno );
		Carta cartaJugadorUno = new Carta( "Chiuaua", mosntruoJugadorDos );
		
		double vidaPerdidaObtenida = cartaJugadorUno.atacar( cartaJugadorDos );
		double vidaPerdiaEsperada = 1000 - 1500;
		assertEquals( vidaPerdidaObtenida, vidaPerdidaEsperada );
		
	}
}
