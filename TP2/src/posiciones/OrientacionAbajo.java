package posiciones;

import atributos.Efecto;
import atributos.EfectoDeCampo;
import atributos.Puntos;
import cartas.Atacable;
import jugabilidad.Jugador;
import resultados.Resultado;

public class OrientacionAbajo extends Orientacion {

	public Orientacion voltear() {
		return new OrientacionArriba ();
	}

	public void aplicarEfecto(Efecto unEfecto, Jugador unJugador) { 
	}
	
	public void aplicarEfecto(EfectoDeCampo unEfecto, Jugador unJugador) {
	}

	public Resultado recibirAtaque(Atacable atacado, Puntos puntosAtacante) {
		return atacado.recibirAtaqueBocaAbajo(puntosAtacante);
	}

	public void atacarAplicandoEfecto(Atacable monstruo, Atacable otroMonstruo) {
		monstruo.atacarSinEfecto(otroMonstruo);
	}

}
