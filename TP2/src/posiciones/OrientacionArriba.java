package posiciones;

import atributos.Efecto;
import atributos.EfectoDeCampo;
import atributos.Puntos;
import cartas.Atacable;
import cartas.Magica;
import jugabilidad.Jugador;
import resultados.Resultado;

public class OrientacionArriba extends Orientacion{
	
	public Orientacion voltear () {
		return new OrientacionAbajo ();
	}

	public void aplicarEfecto(Efecto unEfecto, Jugador unJugador) {
		unEfecto.aplicar(unJugador);
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
