package resultados;

import cartas.Atacable;
import jugabilidad.Jugador;

public class Victoria extends Resultado {

	private double diferenciaDePuntos;
	
	public Victoria(double diferenciaDePuntos) {
		this.diferenciaDePuntos = Math.abs( diferenciaDePuntos );
	}

	public void aplicarAJugadores(Jugador jugadorVencedor) {
		jugadorVencedor.hacerDanioAlRival(diferenciaDePuntos);
	}

	public void aplicarACartas(Atacable atacante, Atacable atacado) {
		atacado.enviarAlCementerio();
	}

}