package resultados;

import cartas.Monstruo;
import jugabilidad.Jugador;

public interface Resultado {
	
	public void aplicarAJugadores( Jugador jugador );
	
	public void aplicarACartas(Monstruo monstruoAtacante, Monstruo monstruoAtacado);
	
}
