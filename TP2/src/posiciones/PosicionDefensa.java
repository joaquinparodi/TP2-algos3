package posiciones;

import cartas.Monstruo;
import cartas.Puntos;
import errores.ErrorAtaqueDesdePosicionInvalidad;
import resultados.Derrota;
import resultados.Empate;
import resultados.Resultado;
import resultados.Victoria;

public class PosicionDefensa implements PosicionMonstruo {

	private Puntos puntosMonstruo;
	
	public PosicionDefensa(Puntos auxPuntosMonstruo) {
		this.puntosMonstruo = auxPuntosMonstruo;
	}
	
	public PosicionMonstruo cambiarPosicion() {
		return new PosicionAtaque(puntosMonstruo);
	}

	public Resultado atacar(Monstruo monstruo) {
		throw new ErrorAtaqueDesdePosicionInvalidad();
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

}