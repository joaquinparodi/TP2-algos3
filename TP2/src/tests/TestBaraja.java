package tests;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import atributos.Vida;
import cartas.Carta;
import cartas.Monstruo;
import cartas.Puntos;
import errores.ErrorCartaNoEncontrada;
import jugabilidad.Baraja;
import jugabilidad.Jugador;

public class TestBaraja {
	
	@Test
	public void test01agregarCartaABarajaYPreguntarSiTienePorElNombreDevuelveCorrectamente() {
		
		Vida vidaJugador = new Vida(8000);
		Jugador jugador = new Jugador(vidaJugador);
		Baraja baraja = new Baraja();
		
		Puntos puntos = new Puntos(1000, 1000);
		Carta monstruo = new Monstruo("CartaDePrueba", jugador, 1, puntos);
		baraja.agregarCarta(monstruo);
		
		assertTrue( baraja.pertenece("CartaDePrueba") );
	}
	
	@Test
	public void test02CartaNoEncontrada(){
		Baraja baraja = new Baraja();
		
		Assertions.assertThrows(ErrorCartaNoEncontrada.class, () -> baraja.obtenerCarta("CartaTest"));
	
	}
	
	
	
}
