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
import factories.AbstractFabricaDeCartas;
import factories.FabricaDeCartas;
import jugabilidad.CampoDeJuego;
import jugabilidad.Jugador;

public class TestSacrificio {
	
	@Test
	public void test01LasCartasSacrificadasVanAlCementerio() {
		
		AbstractFabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
		
		//Jugador se usa para inicializar el mosntruo, para el test se usa otro campo
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Puntos puntosCartaUno = new Puntos(1500, 2000);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo unaCarta = fabricaDeCartas.crearCarta("Facundo", jugadorUno, estrellas, puntosCartaUno);
		CampoDeJuego campo = new CampoDeJuego();
		
		Sacrificio sacrificio = new Sacrificio();
		sacrificio.agregarCarta( unaCarta );
		sacrificio.enviarSacrificiosAlCementerio(campo);
		
		assertTrue( campo.cartaPerteneceAlCementerio(unaCarta) );
	}
	
	@Test
	public void test02LasCartasSacrificadasSeSacanDelCampoDeJuego() {
		
		AbstractFabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		
		Vida vidaJugadorUno = new Vida (8000);
	
		//Jugador se usa para inicializar el mosntruo, para el test se usa otro campo
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Puntos puntosCartaUno = new Puntos(1500, 2000);
		Estrellas estrellas = new Estrellas(1);
		
		Monstruo unaCarta = fabricaDeCartas.crearCarta("Facundo", jugadorUno, estrellas, puntosCartaUno);
		CampoDeJuego campo = new CampoDeJuego();
		campo.agregarCarta(unaCarta);
		
		Sacrificio sacrificio = new Sacrificio();
		sacrificio.agregarCarta( unaCarta );
		sacrificio.enviarSacrificiosAlCementerio(campo);
		
		//Al no encontrar la carta, lanza la excepcion
		assertThrows(ErrorCartaNoEncontrada.class, () -> campo.obtenerMonstruo( "Facundo" ));
	}
}
