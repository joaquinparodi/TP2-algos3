package posiciones;

import atributos.Puntos;
import cartas.Atacable;
import resultados.Derrota;
import resultados.Empate;
import resultados.Resultado;
import resultados.Victoria;

public class PosicionAtaque extends Posicion {

	private Puntos puntos;
	
	public PosicionAtaque( Puntos puntosMonstruo ) {
		this.puntos = puntosMonstruo;
	}
	
	public Posicion cambiarPosicion() {
		return new PosicionDefensa( this.puntos );
	}

	public Resultado recibirAtaque(Puntos puntosAtacante) {		
		double puntosDelDefensor = this.puntos.obtenerPuntosDeAtaque();
		double puntosDelAtacante = puntosAtacante.obtenerPuntosDeAtaque();
		return this.determinarResultado(puntosDelDefensor - puntosDelAtacante);
	}
	
	private Resultado determinarResultado(double diferenciaDePuntos) {
		if( diferenciaDePuntos > 0) return new Derrota(diferenciaDePuntos);
		if( diferenciaDePuntos < 0) return new Victoria(diferenciaDePuntos);
		return new Empate(diferenciaDePuntos);
	}

	public void atacar(Atacable atacante, Atacable atacado) {
		atacante.aplicarAtaque(atacado);
	}
	
	public boolean estaRotada() {
		return false;
	}

}