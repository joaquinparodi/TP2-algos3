package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import atributos.Vida;
import cartas.Monstruo;
import factories.FabricaDeCartas;
import jugabilidad.ArenaDeCombate;
import jugabilidad.Jugador;

public class TestArenaDeCombate {
	
	
	@Test
	public void test01gregarMonstruoAtacado() {
		ArenaDeCombate arena = ArenaDeCombate.obtener();
		Vida vida = new Vida(8000);
		Jugador jugador = new Jugador(vida);
		
		FabricaDeCartas fabrica = new FabricaDeCartas(jugador);
		Monstruo monstruo = fabrica.crearBrazoDerechoExodia();
		arena.agregarMonstruoAtacado(monstruo);
		assert(arena.tieneMonstruoAtacado());
		assertEquals(arena.obtenerNombreMonstruoAtacado(), "Brazo Derecho Exodia" );
	}
	
	@Test
	public void test02AgregarMonstruoAtacante() {
		ArenaDeCombate arena = ArenaDeCombate.obtener();
		arena.reiniciarCombatientes();
		
		Vida vida = new Vida(8000);
		Jugador jugador = new Jugador(vida);
		Jugador jugador2 = new Jugador(vida);
		jugador.asignarRival(jugador2);
		jugador2.asignarRival(jugador);
		
		FabricaDeCartas fabrica2 = new FabricaDeCartas(jugador2);
		Monstruo monstruo2 = fabrica2.crearGigobyte();
		arena.agregarMonstruoAtacante(monstruo2);
		
		assert(arena.tieneMonstruoAtacante());
		assertEquals(arena.obtenerNombreMonstruoAtacante(), "Gigobyte" );
	}
	
	
	
	@Test
	public void test03ReinicilizarArena() {
		ArenaDeCombate arena = ArenaDeCombate.obtener();
		Vida vida = new Vida(8000);
		Jugador jugador = new Jugador(vida);
		Jugador jugador2 = new Jugador(vida);
		jugador.asignarRival(jugador2);
		jugador2.asignarRival(jugador);
		
		arena.reiniciarCombatientes();
		FabricaDeCartas fabrica2 = new FabricaDeCartas(jugador2);
		Monstruo monstruo2 = fabrica2.crearBrazoDerechoExodia();
		arena.agregarMonstruoAtacante(monstruo2);
		
		FabricaDeCartas fabrica = new FabricaDeCartas(jugador);
		Monstruo monstruo = fabrica.crearBrazoDerechoExodia();
		arena.agregarMonstruoAtacado(monstruo);
		
		arena.reiniciarCombatientes();
		
		assertFalse(arena.tieneMonstruoAtacado());
		assertFalse(arena.tieneMonstruoAtacante());
		
	}
	
	
}
