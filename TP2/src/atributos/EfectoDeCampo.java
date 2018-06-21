package atributos;

import cartas.Monstruo;

public abstract class EfectoDeCampo extends Efecto {

	public abstract void aplicarACarta(Monstruo monstruo);
	
	public abstract void aplicarACartaRival(Monstruo monstruo);
}
