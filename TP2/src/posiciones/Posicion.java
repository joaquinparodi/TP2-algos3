package posiciones;

import atributos.Puntos;
import cartas.Atacable;
import cartas.Monstruo;
import resultados.Resultado;

public abstract class Posicion {

	public abstract Posicion cambiarPosicion();
		
	public abstract Resultado atacar(Atacable otroMonstruo);
	
	public abstract Resultado recibirAtaque(Puntos puntos);
	
}