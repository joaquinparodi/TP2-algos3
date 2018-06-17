package resultados;
import cartas.Atacable;
import jugabilidad.Jugador;

public interface Resultado {
	
	public void aplicarAJugadores(Jugador jugador );
	
	public void aplicarACartas(Atacable atacante, Atacable atacado);
	
}
