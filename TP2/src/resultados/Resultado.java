package resultados;
import cartas.Atacable;
import jugabilidad.Jugador;

public abstract class Resultado {
	
	public abstract void aplicarAJugadores(Jugador jugador );
	
	public abstract void aplicarACartas(Atacable atacante, Atacable atacado);
	
}
