package posiciones;

import atributos.Efecto;
import atributos.EfectoDeCampo;
import atributos.Puntos;
import cartas.Atacable;
import cartas.Magica;
import jugabilidad.Jugador;
import resultados.Resultado;

public abstract class Orientacion {
	
	public abstract Orientacion voltear ();	
	
	public abstract void aplicarEfecto (Efecto unEfecto, Jugador unJugador);
	
	public void aplicarEfecto(Magica cartaDuenia) {}
	
	public abstract Resultado recibirAtaque (Atacable atacante, Puntos puntosAtacante);

	public abstract void atacarAplicandoEfecto(Atacable monstruo, Atacable otroMonstruo);
	
	public abstract boolean estaVolteada();
	
}
