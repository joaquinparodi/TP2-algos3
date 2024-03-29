package posiciones;

import atributos.EfectoDeCampo;
import atributos.Puntos;
import cartas.Atacable;
import cartas.Magica;
import jugabilidad.Jugador;
import resultados.Resultado;

public class OrientacionAbajo extends Orientacion {

	public Orientacion voltear() {
		return new OrientacionArriba ();
	}
	
	public void aplicarEfecto(Magica cartaDuenia) {
		cartaDuenia.aplicarEfectoBocaAbajo();
	}
	
	public void aplicarEfecto(EfectoDeCampo unEfecto, Jugador unJugador) {
	}

	public Resultado recibirAtaque(Atacable atacado, Puntos puntosAtacante) {
		return atacado.recibirAtaqueBocaAbajo(puntosAtacante);
	}

	public void atacarAplicandoEfecto(Atacable monstruo, Atacable otroMonstruo) {
		monstruo.atacarSinEfecto(otroMonstruo);
	}

	public boolean estaVolteada() {
		return true;
	}

}
