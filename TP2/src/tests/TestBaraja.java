package tests;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import cartas.Carta;
import cartas.Monstruo;
import errores.ErrorCartaNoEncontrada;
import jugabilidad.Baraja;
import jugabilidad.Jugador;

public class TestBaraja {
	
	@Test
	public void agregarCartaABarajaYPreguntarSiTienePorElNombreDevuelveCorrectamente() {
		
		Jugador jugador = new Jugador(8000);
		Baraja baraja = new Baraja();
		
		Carta monstruo = new Monstruo("CartaDePrueba", jugador, 1, 1, 1);
		baraja.agregarCarta(monstruo);
		
		assertTrue( baraja.pertenece("CartaDePrueba") );
	}
	
	@Test
	public void testCartanoEncontrada(){
		Baraja baraja = new Baraja();
		
		Assertions.assertThrows(ErrorCartaNoEncontrada.class, () -> baraja.obtenerCarta("CartaTest"));
		
		
	}
	
	
	
}
