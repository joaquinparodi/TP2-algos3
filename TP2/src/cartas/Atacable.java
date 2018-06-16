package cartas;

import atributos.Puntos;
import resultados.Resultado;

public interface Atacable {
	
	void atacar(Atacable otroAtacable);
	void enviarAlCementerio();
	Resultado recibirAtaque(Puntos puntosAtacante);
}
