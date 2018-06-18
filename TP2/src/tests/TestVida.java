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

}
