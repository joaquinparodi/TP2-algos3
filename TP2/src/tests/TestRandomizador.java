package tests;

import org.junit.Test;

import atributos.Vida;
import jugabilidad.Jugador;
import jugabilidad.Randomizador;

public class TestRandomizador {
	
	@Test
	public void test01CrearMazosDiferentes() {
		Vida vida = new Vida(8000);
		Jugador jugadorUno = new Jugador(vida);
		Jugador jugadorDos = new Jugador(vida);
		Randomizador randomizador = new Randomizador();
		randomizador.cargarMazo(jugadorUno);
		randomizador.cargarMazo(jugadorDos);
		
		assert(jugadorDos.obtenerMazo() != jugadorUno.obtenerMazo());
	}
	
	
}
