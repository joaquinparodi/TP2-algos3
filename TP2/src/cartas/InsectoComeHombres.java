package cartas;

import atributos.Estrellas;
import atributos.Puntos;
import jugabilidad.Jugador;
import jugabilidad.ReglasDeMonstruos;
import posiciones.OrientacionArriba;
import resultados.Derrota;
import resultados.Resultado;

public class InsectoComeHombres extends Monstruo {

	public InsectoComeHombres(String nombre, Jugador unJugador, Estrellas estrellas, Puntos puntos) {
		super(nombre, unJugador, estrellas, puntos);
		this.orientacion = new OrientacionArriba ();
	}
	
	public Resultado recibirAtaque (Puntos puntosAtacante) {
		return orientacion.recibirAtaque(this,puntosAtacante);	
	}
	
	public Resultado recibirAtaqueBocaAbajo (Puntos puntosAtacante) {
		this.voltear();
		return new Derrota(0);
	}
	
	public Resultado recibirAtaqueBocaArriba (Puntos puntosAtacante) {
		return posicion.recibirAtaque(puntosAtacante);
	}
	
	public void atacarConEfecto(Atacable otroMonstruo) {
		if (ReglasDeMonstruos.obtener().cartaFueVolteadaEsteTurno(this)) {
			otroMonstruo.enviarAlCementerio();
		}else {
			this.atacarSinEfecto(otroMonstruo);
		}
	}
	
}
