package cartas;

import atributos.Puntos;
import resultados.Resultado;

public interface Atacable {
	
	void enviarAlCementerio();
	
	void atacar(Atacable otroAtacable);
	
	void cambiarPosicion();
	
	Resultado recibirAtaque(Puntos puntosAtacante);
	
}
