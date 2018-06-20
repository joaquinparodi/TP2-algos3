package atributos;

import cartas.Monstruo;
import jugabilidad.Jugador;

public abstract class EfectoDeCampo extends Efecto {

	public abstract void aplicarACarta(Monstruo monstruo);
	
	public abstract void aplicarACartaRival(Monstruo monstruo);
	
	public abstract void desaplicar(Jugador unJugador);

}
