package atributos;

import cartas.Atacable;
import jugabilidad.Jugador;

public class EfectoReinforcements extends EfectoDeTrampa {

	public void aplicar(Atacable atacante, Atacable atacado, Jugador jugadorRival) {
		atacado.incrementarPuntosDeAtaque(500);
	}

	
	public void desaplicar(Atacable atacante, Atacable atacado, Jugador jugadorRival) {
		atacado.restaurarPuntos();
	}
	
}
