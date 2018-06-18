package resultados;

import cartas.Atacable;
import jugabilidad.Jugador;

public class Derrota extends Resultado {

	private double diferenciaDePuntos;
	
	public Derrota(double auxDiferenciaDePuntos) {
		diferenciaDePuntos = Math.abs( auxDiferenciaDePuntos );
	}
	
	public void aplicarAJugadores( Jugador jugadorDerrotado ) {
		jugadorDerrotado.hacerDanio( diferenciaDePuntos );
	}

	public void aplicarACartas(Atacable atacante, Atacable atacado) {
		atacante.enviarAlCementerio();		
	}
	
}
