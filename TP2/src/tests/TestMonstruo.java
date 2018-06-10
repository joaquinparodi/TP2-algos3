package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import cartas.Carta;
import cartas.Clase;
import cartas.Monstruo;

class TestMonstruo {

	//private static final double DELTA = 1e-2;
	
	@Test
	public void testMosntruoAtacaAOtroEnPosicionDeAtaqueConMayorPuntosYDevuelveCorrectamenteLaDiferenciaDePuntos() {
		
		//Las cartqs de clase monstruo se inicializan en mano 
		Clase monstruoJugadorUno = new Monstruo( 1000, 2000 );
		Clase monstruoJugadorDos = new Monstruo( 1500, 2000 );
		Carta cartaJugadorDos = new Carta( "Caniche", monstruoJugadorUno );
		Carta cartaJugadorUno = new Carta( "Chiuaua", monstruoJugadorDos );
		
		//Al cambiarle la posicion, se ponen en ataque
		cartaJugadorUno.cambiarPosicion();
		cartaJugadorDos.cambiarPosicion();
		
		double diferenciaDePuntosObtenida = cartaJugadorUno.atacar( cartaJugadorDos );
		double diferenciaDePuntosEsperada = 1000 - 1500;
		
		assertEquals( diferenciaDePuntosObtenida, diferenciaDePuntosEsperada );
		
	}
	
	@Test
	public void testMosntruoAtacaAOtroEnPosicionDeAtaqueConMenorPuntosYDevuelveCorrectamenteLaDiferenciaDePuntos() {
	
		//Las cartqs de clase monstruo se inicializan en mano 
		Clase monstruoJugadorUno = new Monstruo( 1000, 2000 );
		Clase monstruoJugadorDos = new Monstruo( 1500, 2000 );
		Carta cartaJugadorDos = new Carta( "Caniche", monstruoJugadorUno );
		Carta cartaJugadorUno = new Carta( "Chiuaua", monstruoJugadorDos );
		
		//Al cambiarle la posicion, se ponen en ataque
		cartaJugadorUno.cambiarPosicion();
		cartaJugadorDos.cambiarPosicion();
		
		double diferenciaDePuntosObtenida = cartaJugadorDos.atacar( cartaJugadorUno );
		double diferenciaDePuntosEsperada = 1500 - 1000;
		
		assertEquals( diferenciaDePuntosObtenida, diferenciaDePuntosEsperada );
		
	}
	
}
