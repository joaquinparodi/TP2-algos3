package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import atributos.Sacrificio;
import atributos.Vida;
import cartas.Monstruo;
import factories.FabricaDeCartas;
import jugabilidad.AreaDeSacrificios;
import jugabilidad.Jugador;

public class TestAreaDeSacrificios {

	@Test
	public void test01AgregarMonstruoEnLaArea() {
		AreaDeSacrificios area = AreaDeSacrificios.obtener();
		Vida vida = new Vida(8000);
		Jugador jugador = new Jugador(vida);
		FabricaDeCartas fabrica = new FabricaDeCartas(jugador);
		Monstruo monstruo = fabrica.crearBrazoDerechoExodia();
		area.agregarMonstruo(monstruo);
		
		assert(area.haySacrificios());
		
	}
	
	@Test
	public void test02AgregarMonstruoEnLaArea() {
		AreaDeSacrificios area = AreaDeSacrificios.obtener();
		Vida vida = new Vida(8000);
		Jugador jugador = new Jugador(vida);
		FabricaDeCartas fabrica = new FabricaDeCartas(jugador);
		Monstruo monstruo = fabrica.crearBrazoDerechoExodia();
		area.agregarMonstruo(monstruo);
		Sacrificio sacrificio = area.obtenerSacrificios();
		
		assert(sacrificio.pertenece(monstruo));
		
	}
	
	@Test
	public void test03ReiniciarAreaDeSacrificio() {
		AreaDeSacrificios area = AreaDeSacrificios.obtener();
		Vida vida = new Vida(8000);
		Jugador jugador = new Jugador(vida);
		FabricaDeCartas fabrica = new FabricaDeCartas(jugador);
		Monstruo monstruo = fabrica.crearBrazoDerechoExodia();
		area.agregarMonstruo(monstruo);
		area.reiniciarSacrificios();
		
		assertFalse(area.haySacrificios());
		
	}
	
	@Test
	public void test04HayMasDeUnSacrificio() {
		AreaDeSacrificios area = AreaDeSacrificios.obtener();
		Vida vida = new Vida(8000);
		Jugador jugador = new Jugador(vida);
		FabricaDeCartas fabrica = new FabricaDeCartas(jugador);
		Monstruo monstruo = fabrica.crearBrazoDerechoExodia();
		Monstruo monstruo2 = fabrica.crearBrazoIzquierdoExodia();
		area.agregarMonstruo(monstruo);
		area.agregarMonstruo(monstruo2);
		
		assert(area.hayMasDeUnSacrificios());
		
	}
	
	@Test
	public void test05HayMasDeDosSacrificios() {
		AreaDeSacrificios area = AreaDeSacrificios.obtener();
		Vida vida = new Vida(8000);
		Jugador jugador = new Jugador(vida);
		FabricaDeCartas fabrica = new FabricaDeCartas(jugador);
		Monstruo monstruo = fabrica.crearBrazoDerechoExodia();
		Monstruo monstruo2 = fabrica.crearBrazoIzquierdoExodia();
		Monstruo monstruo3 = fabrica.crearCabezaExodia();
		area.agregarMonstruo(monstruo);
		area.agregarMonstruo(monstruo2);
		area.agregarMonstruo(monstruo3);
		
		assert(area.hayMasDeDosSacrificios());
		
	}
	
	@Test
	public void test06ObtenerPrimerMonstruo() {
		AreaDeSacrificios area = AreaDeSacrificios.obtener();
		area.reiniciarSacrificios();
		Vida vida = new Vida(8000);
		Jugador jugador = new Jugador(vida);
		FabricaDeCartas fabrica = new FabricaDeCartas(jugador);
		Monstruo monstruo = fabrica.crearBrazoDerechoExodia();
		Monstruo monstruo2 = fabrica.crearBrazoIzquierdoExodia();
		Monstruo monstruo3 = fabrica.crearCabezaExodia();
		area.agregarMonstruo(monstruo);
		area.agregarMonstruo(monstruo2);
		area.agregarMonstruo(monstruo3);
		
		assertEquals(area.obtenerNombrePrimerSacrificio(), "Brazo Derecho Exodia");
		assertEquals(area.obtenerPrimerSacrificio(), monstruo);
		
	}
	
	@Test
	public void test07ObtenerSegundoMonstruo() {
		AreaDeSacrificios area = AreaDeSacrificios.obtener();
		area.reiniciarSacrificios();
		Vida vida = new Vida(8000);
		Jugador jugador = new Jugador(vida);
		FabricaDeCartas fabrica = new FabricaDeCartas(jugador);
		Monstruo monstruo = fabrica.crearBrazoDerechoExodia();
		Monstruo monstruo2 = fabrica.crearBrazoIzquierdoExodia();
		Monstruo monstruo3 = fabrica.crearCabezaExodia();
		area.agregarMonstruo(monstruo);
		area.agregarMonstruo(monstruo2);
		area.agregarMonstruo(monstruo3);
		
		assertEquals(area.obtenerNombreSegundoSacrificio(), "Brazo Izquierdo Exodia");
		assertEquals(area.obtenerSegundoSacrificio(), monstruo2);
		
	}
	
	@Test
	public void test08ObtenerTercerMonstruo() {
		AreaDeSacrificios area = AreaDeSacrificios.obtener();
		area.reiniciarSacrificios();
		Vida vida = new Vida(8000);
		Jugador jugador = new Jugador(vida);
		FabricaDeCartas fabrica = new FabricaDeCartas(jugador);
		Monstruo monstruo = fabrica.crearBrazoDerechoExodia();
		Monstruo monstruo2 = fabrica.crearBrazoIzquierdoExodia();
		Monstruo monstruo3 = fabrica.crearCabezaExodia();
		area.agregarMonstruo(monstruo);
		area.agregarMonstruo(monstruo2);
		area.agregarMonstruo(monstruo3);
		
		assertEquals(area.obtenerNombreTercerSacrificio(), "Cabeza Exodia");
		assertEquals(area.obtenerTercerSacrificio(), monstruo3);
		
	}
	
	@Test
	public void test09CancelarSacrificio() {
		AreaDeSacrificios area = AreaDeSacrificios.obtener();
		area.reiniciarSacrificios();
		Vida vida = new Vida(8000);
		Jugador jugador = new Jugador(vida);
		FabricaDeCartas fabrica = new FabricaDeCartas(jugador);
		Monstruo monstruo = fabrica.crearBrazoDerechoExodia();
		Monstruo monstruo2 = fabrica.crearBrazoIzquierdoExodia();
		Monstruo monstruo3 = fabrica.crearCabezaExodia();
		area.agregarMonstruo(monstruo);
		area.agregarMonstruo(monstruo2);
		area.agregarMonstruo(monstruo3);
		area.cancelarSacrificio(monstruo);
		Sacrificio sacrificio = area.obtenerSacrificios();
		
		assertFalse(sacrificio.pertenece(monstruo));
		
	}
	
	
}
