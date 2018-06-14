package posiciones;

import cartas.Monstruo;
import cartas.Puntos;
import resultados.Resultado;

public interface PosicionMonstruo {

	public PosicionMonstruo cambiarPosicion();
		
	public Resultado atacar(Monstruo monstruo);
	
	public Resultado recibirAtaque(Puntos puntos);
	
}