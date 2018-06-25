package tests;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import atributos.Estrellas;
import atributos.Puntos;
import atributos.Sacrificio;
import atributos.Vida;
import cartas.Monstruo;
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
		
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas(jugadorUno);
		
		Monstruo unaCarta = fabricaDeCartas.crearMonstruoPersonalizado("Facundo", estrellas, puntosCartaUno);
		CampoDeJuego campo = new CampoDeJuego();
		
		Sacrificio sacrificio = new Sacrificio();
		sacrificio.agregarCarta( unaCarta );
		sacrificio.enviarSacrificiosAlCementerio(campo);
		
		assertTrue( campo.cartaPerteneceAlCementerio(unaCarta) );
	}
	
}
