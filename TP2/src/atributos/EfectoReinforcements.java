package atributos;

import cartas.Atacable;
import cartas.Trampa;
import jugabilidad.Jugador;

public class EfectoReinforcements extends EfectoDeTrampa {

	public void aplicar(Atacable atacante, Atacable atacado, Jugador jugadorRival, Trampa trampaDuenia) {
		atacado.incrementarPuntosDeAtaque(500);
		atacante.atacar(atacado);
		atacado.incrementarPuntosDeAtaque(-500);
		trampaDuenia.enviarAlCementerio();
	}
}
