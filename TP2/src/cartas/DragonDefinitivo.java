package cartas;

import java.util.Iterator;

import atributos.Estrellas;
import atributos.Puntos;
import atributos.Sacrificio;
import errores.ErrorAtaqueDesdePosicionInvalida;
import jugabilidad.Jugador;

public class DragonDefinitivo extends Monstruo{

	public DragonDefinitivo(String nombre, Jugador unJugador, Estrellas estrellas, Puntos puntos) {
		super(nombre, unJugador, estrellas, puntos);
	}
	
	public void atacar (Atacable otroMonstruo) {
			
			try {
				this.posicion.atacar(otroMonstruo);
			}catch (ErrorAtaqueDesdePosicionInvalida error) {
				throw new ErrorAtaqueDesdePosicionInvalida();
			}
			
			this.jugadorDuenio.hacerDanioAlRival(puntos.obtenerPuntosDeAtaque());
			
		}
	
	@Override
	public int obtenerCantidadDeSacrificiosNecesarios() {
		return 3;
	}
	
	@Override
	public boolean verificarSacrificios(Sacrificio sacrificios) {
		Iterator<Carta> iter = sacrificios.iterator();
		while(iter.hasNext()) {
		    Monstruo monstruo = (Monstruo) iter.next();
		    if (monstruo.getClass() != DragonBlancoOjoAzul.class) {
		    	return false;
		    }  	
		}
		return true;
	}
}
