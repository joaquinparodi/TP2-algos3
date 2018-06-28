package tests;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;

import atributos.Estrellas;
import atributos.Puntos;
import atributos.Vida;
import cartas.Carta;
import cartas.Monstruo;
import errores.ErrorCartaNoEncontrada;
import errores.ErrorNoHayCartasEnLaBaraja;
import factories.FabricaDeCartas;
import jugabilidad.Baraja;
import jugabilidad.Jugador;

public class TestBaraja {
	
	@Test
	public void test01agregarCartaABarajaYPreguntarSiTienePorElNombreDevuelveCorrectamente() {
		
		Vida vidaJugador = new Vida(8000);
		Jugador jugador = new Jugador(vidaJugador);
		Baraja baraja = new Baraja();
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas(jugador);
		
		Puntos puntos = new Puntos(1000, 1000);
		Estrellas estrellas = new Estrellas(1);
		Carta monstruo = fabricaDeCartas.crearMonstruoPersonalizado("CartaDePrueba", estrellas, puntos);
		baraja.agregarCarta(monstruo);
		
		assertTrue( baraja.pertenece(monstruo) );
	}
	
	@Test
	public void test02NoHayCartaEnLaBaraja() {
		Baraja baraja = new Baraja();
		assertThrows(ErrorNoHayCartasEnLaBaraja.class, () ->baraja.obtenerCartaDePosicion(2) );
	}
	
	
}
