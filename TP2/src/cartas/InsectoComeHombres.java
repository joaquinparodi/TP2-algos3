package cartas;

import atributos.Estrellas;
import atributos.Puntos;
import jugabilidad.Jugador;
import posiciones.InsectoComeHombresArriba;
import resultados.Resultado;

public class InsectoComeHombres extends Monstruo {

	public InsectoComeHombres(String nombre, Jugador unJugador, Estrellas estrellas, Puntos puntos) {
		super(nombre, unJugador, estrellas, puntos);
		this.orientacion = new InsectoComeHombresArriba ();
	}
	
	public Resultado recibirAtaque (Puntos puntosAtacante) {
		return orientacion.recibirAtaque(this,this.posicion,puntosAtacante);	
	}
	
	public void voltear () {
		orientacion = orientacion.voltear();
	}
}