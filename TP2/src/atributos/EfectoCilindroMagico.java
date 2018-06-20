package atributos;

import cartas.Atacable;
import cartas.Monstruo;
import jugabilidad.Jugador;

public class EfectoCilindroMagico extends EfectoDeTrampa {

	public void aplicar(Atacable atacante, Atacable atacado, Jugador jugadorRival) {
		jugadorRival.hacerDanio(atacante.obtenerPuntos().obtenerPuntosDeAtaque());	
		atacante.anularPuntos();
	}

	
	public void desaplicar(Atacable atacante, Atacable atacado, Jugador jugadorRival) {
		atacante.restaurarPuntos();
	}

}