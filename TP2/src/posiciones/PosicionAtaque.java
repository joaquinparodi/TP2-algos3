package posiciones;

import cartas.Monstruo;
import cartas.Puntos;
import resultados.Derrota;
import resultados.Empate;
import resultados.Resultado;
import resultados.Victoria;

public class PosicionAtaque implements Posicion {

	private Puntos puntosMonstruo;
	
	public PosicionAtaque( Puntos auxPuntosMonstruo ) {
		this.puntosMonstruo = auxPuntosMonstruo;
	}
	
	public Posicion cambiarPosicion() {
		return new PosicionDefensa( this.puntosMonstruo );
	}
	
	public Resultado atacar(Monstruo monstruo) {
		return monstruo.recibirAtaque( this.puntosMonstruo );
	}

	public Resultado recibirAtaque(Puntos puntosAtacante) {		
		double puntosDelDefensor = this.puntosMonstruo.obtenerPuntosDeAtaque();
		double puntosDelAtacante = puntosAtacante.obtenerPuntosDeAtaque();
		return this.determinarResultado(puntosDelDefensor - puntosDelAtacante);
	}
	
	private Resultado determinarResultado(double diferenciaDePuntos) {
		if( diferenciaDePuntos > 0) return new Derrota(diferenciaDePuntos);
		if( diferenciaDePuntos < 0) return new Victoria(diferenciaDePuntos);
		return new Empate(diferenciaDePuntos);
	}

}