package resultados;

import cartas.Monstruo;
import jugabilidad.Jugador;

public class Empate implements Resultado {
	
	public Empate(double auxDiferenciaDePuntos) {
	}
	
	public void aplicarAJugadores(Jugador jugadorVencedor) {
		return; //No se le hace danio a nadie
	}

	public void aplicarACartas(Monstruo monstruoAtacante, Monstruo monstruoAtacado) {
		monstruoAtacante.enviarAlCementerio();
		monstruoAtacado.enviarAlCementerio();
	}

}