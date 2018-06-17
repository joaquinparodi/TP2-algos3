package cartas;

import atributos.Puntos;
import resultados.Resultado;

public interface Atacable {
	
	void enviarAlCementerio();
	
	void atacar(Atacable otroAtacable);
	
	Resultado recibirAtaque(Puntos puntosAtacante);
	
}
