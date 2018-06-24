package tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.Test;
import atributos.Estrellas;
import atributos.Puntos;
import atributos.Sacrificio;
import atributos.Vida;
import cartas.Monstruo;
import errores.ErrorCartaNoEncontrada;
import factories.FabricaDeCartas;
import jugabilidad.CampoDeJuego;
import jugabilidad.Jugador;

public class TestSacrificio {
	
	@Test
	public void test01LasCartasSacrificadasVanAlCementerio() {
		
		Vida vidaJugadorUno = new Vida (8000);
		
		//Jugador se usa para inicializar el mosntruo, para el test se usa otro campo
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Puntos puntosCartaUno = new Puntos(1500, 2000);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo unaCarta = new Monstruo("Facundo", jugadorUno, estrellas, puntosCartaUno);
		CampoDeJuego campo = new CampoDeJuego();
		
		Sacrificio sacrificio = new Sacrificio();
		sacrificio.agregarCarta( unaCarta );
		sacrificio.enviarSacrificiosAlCementerio(campo);
		
		assertTrue( campo.cartaPerteneceAlCementerio(unaCarta) );
	}
	
	@Test
	public void test02LasCartasSacrificadasSeSacanDelCampoDeJuego() {
		
		Vida vidaJugadorUno = new Vida (8000);
	
		//Jugador se usa para inicializar el mosntruo, para el test se usa otro campo
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Puntos puntosCartaUno = new Puntos(1500, 2000);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo unaCarta = new Monstruo("Facundo", jugadorUno, estrellas, puntosCartaUno);
		CampoDeJuego campo = new CampoDeJuego();
		campo.agregarCarta(unaCarta);
		
		Sacrificio sacrificio = new Sacrificio();
		sacrificio.agregarCarta( unaCarta );
		sacrificio.enviarSacrificiosAlCementerio(campo);
		
		//Al no encontrar la carta, lanza la excepcion
		assertThrows(ErrorCartaNoEncontrada.class, () -> campo.obtenerMonstruo( "Facundo" ));
	}
}
