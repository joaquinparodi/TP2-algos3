package posiciones;

import atributos.Puntos;
import cartas.Monstruo;
import resultados.Resultado;

public interface Posicion {

	public Posicion cambiarPosicion();
		
	public Resultado atacar(Monstruo monstruo);
	
	public Resultado recibirAtaque(Puntos puntos);
	
}