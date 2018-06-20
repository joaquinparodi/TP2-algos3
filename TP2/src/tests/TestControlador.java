package tests;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import atributos.Vida;
import cartas.*;
import factories.FabricaDeMonstruosEspeciales;
import jugabilidad.Controlador;
import jugabilidad.Jugador;

public class TestControlador {

	@Test
	public void test01ObtenerExodiaEnLaManoGanaAutomaticamenteElJuego() {
		
		Vida vidaUno = new Vida(8000);
		Vida vidaDos = new Vida(8000);
		
		Jugador jugadorUno = new Jugador(vidaUno);
		Jugador jugadorDos = new Jugador(vidaDos);
				
		Controlador controlador = new Controlador(jugadorUno, jugadorDos);
		
		FabricaDeMonstruosEspeciales fabrica = new FabricaDeMonstruosEspeciales();
		
		BrazoIzquierdoExodia brazoIzq = fabrica.crearBrazoIzquierdoExodia(jugadorUno);
		BrazoDerechoExodia brazoDer = fabrica.crearBrazoDerechoExodia(jugadorUno);
		PiernaDerechaExodia piernaDer = fabrica.crearPiernaDerechaExodia(jugadorUno);
		PiernaIzquierdaExodia piernaIzq = fabrica.crearPiernaIzquierdaExodia(jugadorUno);
		CabezaExodia cabeza = fabrica.crearCabezaExodia(jugadorUno);
		
		jugadorUno.repartirCarta(brazoIzq);
		jugadorUno.repartirCarta(brazoDer);
		jugadorUno.repartirCarta(piernaIzq);
		jugadorUno.repartirCarta(piernaDer);
		jugadorUno.repartirCarta(cabeza);
		
		assertTrue( controlador.hayGanador() );
		
	}
	
	/*@Test
	public void test02ObtenerExodiaEnLaManoGanaAutomaticamenteElJuego() {
		
		Vida vidaUno = new Vida(8000);
		Vida vidaDos = new Vida(8000);
		
		Jugador jugadorUno = new Jugador(vidaUno);
		Jugador jugadorDos = new Jugador(vidaDos);
				
		Controlador controlador = new Controlador(jugadorUno, jugadorDos);
		
		FabricaDeMonstruosEspeciales fabrica = new FabricaDeMonstruosEspeciales();
		
		BrazoIzquierdoExodia brazoIzq = fabrica.crearBrazoIzquierdoExodia(jugadorUno);
		BrazoDerechoExodia brazoDer = fabrica.crearBrazoDerechoExodia(jugadorUno);
		PiernaDerechaExodia piernaDer = fabrica.crearPiernaDerechaExodia(jugadorUno);
		PiernaIzquierdaExodia piernaIzq = fabrica.crearPiernaIzquierdaExodia(jugadorUno);
		CabezaExodia cabeza = fabrica.crearCabezaExodia(jugadorUno);
		
		jugadorUno.repartirCarta(brazoIzq);
		jugadorUno.repartirCarta(brazoDer);
		jugadorUno.repartirCarta(piernaIzq);
		jugadorUno.repartirCarta(piernaDer);
		jugadorUno.repartirCarta(cabeza);
		
		assertTrue( controlador.hayGanador() );
		
	}*/
	
		
}
