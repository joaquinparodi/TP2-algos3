package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import cartas.Carta;
import cartas.Clase;
import cartas.Monstruo;
import jugabilidad.Cementerio;

class TestCemenerio {

	@Test
	void testAgregarCartaACementerioYPreguntarSiPerteneceEsCorrecto() {
		Cementerio cementerio = new Cementerio();

		Clase monstruo = new Monstruo();
		Carta carta = new Carta( "PikachuInflitrado", monstruo );

		cementerio.agregarCarta( carta );
		assertTrue( cementerio.pertenece( carta ) ); 
	}

}
