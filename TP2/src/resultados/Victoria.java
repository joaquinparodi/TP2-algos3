package resultados;

import cartas.Atacable;
import jugabilidad.Jugador;

public class Victoria extends Resultado {

	private double diferenciaDePuntos;
	
	public Victoria(double auxDiferenciaDePuntos) {
		diferenciaDePuntos = Math.abs( auxDiferenciaDePuntos );
	}

	public void aplicarAJugadores(Jugador jugadorVencedor) {
		jugadorVencedor.hacerDanioAlRival(diferenciaDePuntos);
	}

	public void aplicarACartas(Atacable atacante, Atacable atacado) {
		atacado.enviarAlCementerio();
	}

}