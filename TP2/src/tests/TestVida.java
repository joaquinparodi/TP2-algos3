package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import atributos.Vida;

class TestVida {

	@Test
	void test01QuitarVidaFuncionarCorrectamente() {
		
		Vida vida = new Vida(8000);
		vida.quitarVida(2000);
		
		assertEquals(vida.obtenerPuntosDeVida(), 6000);	
	}
	
	@Test
	public void test02VidaVacia() {
		Vida vida = new Vida(8000);
		vida.quitarVida(8100);
		
		assert(vida.estaVacia());
	}
	
	@Test
	public void test03VidaNoVacio() {
		Vida vida = new Vida(8000);
		assertFalse(vida.estaVacia());
	}

}
