package posiciones;

import atributos.Puntos;
import cartas.Atacable;
import cartas.Magica;
import resultados.Resultado;

public class OrientacionArriba extends Orientacion{
	
	public Orientacion voltear () {
		return new OrientacionAbajo ();
	}
	
	public void aplicarEfecto(Magica cartaDuenia) {
		cartaDuenia.aplicarEfectoBocaArriba();
	}

	public Resultado recibirAtaque(Atacable atacado, Puntos puntosAtacante) {
		return atacado.recibirAtaqueBocaArriba(puntosAtacante);
	}

	public void atacarAplicandoEfecto(Atacable monstruo, Atacable otroMonstruo) {
		monstruo.atacarConEfecto(otroMonstruo);
	}

	public boolean estaVolteada() {
		return false;
	}
	
}
