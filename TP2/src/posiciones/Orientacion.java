package posiciones;

import atributos.Efecto;
import atributos.Puntos;
import cartas.Carta;
import jugabilidad.Jugador;
import resultados.Resultado;

public abstract class Orientacion {
	
	public abstract Orientacion voltear ();	
	
	public abstract void aplicarEfecto (Efecto unEfecto, Jugador unJugador);
	
	public abstract Resultado recibirAtaque (Carta carta, Posicion posicion, Puntos puntosAtacante);
}
