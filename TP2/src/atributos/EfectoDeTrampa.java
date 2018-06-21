package atributos;

import cartas.Atacable;
import cartas.Trampa;
import jugabilidad.Jugador;

public abstract class EfectoDeTrampa {
	
	public abstract void aplicar(Atacable atacante, Atacable atacado, Jugador jugadorRival, Trampa cartaDuenia);

}
