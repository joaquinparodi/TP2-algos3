package cartas;

import jugabilidad.Jugador;
import jugabilidad.CampoDeJuego;
import jugabilidad.Baraja;

public class AgujeroNegro extends Magica{
	
	public AgujeroNegro(String auxNombre, Jugador auxJugador) {
		super(auxNombre, auxJugador);
	}

	public void aplicarEfecto() {
		
		Jugador jugador = this.jugadorDuenio;
		Jugador rival = this.jugadorDuenio.obtenerRival();
		
		CampoDeJuego campoUno = jugador.obtenerCampo();
		CampoDeJuego campoDos =	rival.obtenerCampo();
		
		Baraja monstruosUno = campoUno.obtenerMonstruos();
		Baraja monstruosDos = campoDos.obtenerMonstruos();
		
		while (monstruosUno.tieneCartas()) {
			campoUno.enviarMonstruoACementerio(monstruosUno.obtenerPrimeraCarta());			
		}
		
		while (monstruosDos.tieneCartas()) {
			campoDos.enviarMonstruoACementerio(monstruosDos.obtenerPrimeraCarta());			
		}
		
		
	}

	
}
