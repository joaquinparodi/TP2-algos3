package posiciones;

import atributos.Efecto;
import atributos.Puntos;
import cartas.Atacable;
import cartas.Carta;
import jugabilidad.Jugador;
import resultados.Resultado;

public class OrientacionArriba extends Orientacion{
	
	public Orientacion voltear () {
		return new OrientacionAbajo ();
	}

	public void aplicarEfecto(Efecto unEfecto, Jugador unJugador) {
		unEfecto.aplicar(unJugador);
	}

	public Resultado recibirAtaque(Atacable atacado, Puntos puntosAtacante) {
		return atacado.recibirAtaqueBocaArriba(puntosAtacante);
	}

	@Override
	public void atacarAplicandoEfecto(Atacable monstruo, Atacable otroMonstruo) {
		monstruo.atacarConEfecto(otroMonstruo);
	}


}
