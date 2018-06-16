package posiciones;

import cartas.Monstruo;
import cartas.Puntos;
import resultados.Resultado;

public interface Posicion {

	public Posicion cambiarPosicion();
		
	public Resultado atacar(Monstruo monstruo);
	
	public Resultado recibirAtaque(Puntos puntos);
	
}