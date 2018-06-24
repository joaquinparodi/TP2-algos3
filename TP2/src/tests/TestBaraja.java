package tests;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import atributos.Estrellas;
import atributos.Puntos;
import atributos.Vida;
import cartas.Carta;
import errores.ErrorCartaNoEncontrada;
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
		Carta monstruo = fabricaDeCartas.crearMonstruoPersonalizado("CartaDePrueba", jugador, estrellas, puntos);
		baraja.agregarCarta(monstruo);
		
		assertTrue( baraja.pertenece(monstruo) );
	}
	
	//Este de aca no se si sirve, xq si se manda el objeto no hay necesidad de obtener la carta
	@Test
	public void test02CartaNoEncontrada(){
		Baraja baraja = new Baraja();
		
		Assertions.assertThrows(ErrorCartaNoEncontrada.class, () -> baraja.obtenerCarta("CartaTest"));
	}
	
}
