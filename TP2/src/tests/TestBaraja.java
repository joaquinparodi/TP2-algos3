package tests;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import atributos.Estrellas;
import atributos.Puntos;
import atributos.Vida;
import cartas.Carta;
import cartas.Monstruo;
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
		Estrellas estrellas = new Estrellas(1);
		Carta monstruo = new Monstruo("CartaDePrueba", jugador, estrellas, puntos);
		baraja.agregarCarta(monstruo);
		
		assertTrue( baraja.pertenece("CartaDePrueba") );
	}
	
	@Test
	public void test02CartaNoEncontrada(){
		Baraja baraja = new Baraja();
		
		Assertions.assertThrows(ErrorCartaNoEncontrada.class, () -> baraja.obtenerCarta("CartaTest"));
	}
	
}
