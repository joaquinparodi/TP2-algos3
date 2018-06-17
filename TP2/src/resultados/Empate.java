package resultados;

import cartas.Atacable;
import jugabilidad.Jugador;

public class Empate extends Resultado {
	
	public Empate(double auxDiferenciaDePuntos) {
	}
	
	public void aplicarAJugadores(Jugador jugadorVencedor) {
		return; //No se le hace danio a nadie
	}

	public void aplicarACartas(Atacable atacante, Atacable atacado) {
		atacante.enviarAlCementerio();
		atacado.enviarAlCementerio();
	}

}
