package atributos;

import cartas.Atacable;
import jugabilidad.Jugador;

public abstract class EfectoDeTrampa {
	
	public abstract void aplicar(Atacable atacante, Atacable atacado, Jugador jugadorRival);

	public abstract void desaplicar(Atacable atacante, Atacable atacado, Jugador jugadorRival);

}
