package posiciones;

import atributos.Puntos;
import cartas.Atacable;
import errores.ErrorAtaqueDesdePosicionInvalida;
import resultados.Derrota;
import resultados.Empate;
import resultados.Resultado;
import resultados.Victoria;

public class PosicionDefensa extends Posicion {

	private Puntos puntosMonstruo;
	
	public PosicionDefensa(Puntos puntosMonstruo) {
		this.puntosMonstruo = puntosMonstruo;
	}
	
	public Posicion cambiarPosicion() {
		return new PosicionAtaque(puntosMonstruo);
	}

	public Resultado recibirAtaque(Puntos puntosAtacante) {
		double puntosDelDefensor = this.puntosMonstruo.obtenerPuntosDeDefensa();
		double puntosDelAtacante = puntosAtacante.obtenerPuntosDeDefensa();
		return this.determinarResultado(puntosDelDefensor - puntosDelAtacante);	
	}
	
	private Resultado determinarResultado(double diferenciaDePuntos) {
		if( diferenciaDePuntos > 0) return new Derrota(0);
		if( diferenciaDePuntos < 0) return new Victoria(0);
		return new Empate(0);
	}

	public void atacar(Atacable atacante, Atacable atacado) {
		throw new ErrorAtaqueDesdePosicionInvalida();
	}

}