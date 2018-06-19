package cartas;

import atributos.Puntos;
import jugabilidad.Jugador;
import resultados.Resultado;

public interface Atacable {
	
	void enviarAlCementerio();
	
	void atacar(Atacable otroAtacable);
	
	void cambiarPosicion();
	
	Resultado recibirAtaque(Puntos puntosAtacante);

	boolean lePerteneceA(Jugador jugador);
	
}
