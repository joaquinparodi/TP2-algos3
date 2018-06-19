package cartas;

import atributos.Estrellas;
import atributos.Puntos;
import errores.ErrorAtaqueDesdePosicionInvalida;
import jugabilidad.Jugador;

public class Exodia extends Monstruo {
	
	public Exodia(String nombre, Jugador unJugador, Estrellas estrellas, Puntos puntos) {		
		super(nombre, unJugador, estrellas, puntos);
	}
	
	public void atacar (Atacable otroMonstruo) {
			
			try {
				this.posicion.atacar(otroMonstruo);
			}catch (ErrorAtaqueDesdePosicionInvalida error) {
				throw new ErrorAtaqueDesdePosicionInvalida();
			}
			
		}

}