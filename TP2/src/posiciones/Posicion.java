package posiciones;

import atributos.Puntos;
import cartas.Atacable;
import resultados.Resultado;

public abstract class Posicion {

	public abstract Posicion cambiarPosicion();
	
	public abstract Resultado recibirAtaque(Puntos puntos);

	public abstract void atacar(Atacable atacante, Atacable atacado);
	
	public abstract boolean estaRotada();
	
}