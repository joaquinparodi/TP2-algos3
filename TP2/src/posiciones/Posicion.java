package posiciones;

import atributos.Puntos;
import cartas.Atacable;
import cartas.Monstruo;
import resultados.Resultado;

public interface Posicion {

	public abstract Posicion cambiarPosicion();
		
	public abstract Resultado atacar(Atacable otroMonstruo);
	
	public abstract Resultado recibirAtaque(Puntos puntos);
	
}