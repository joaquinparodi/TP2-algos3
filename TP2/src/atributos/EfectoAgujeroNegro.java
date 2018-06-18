package atributos;

import jugabilidad.Jugador;
import jugabilidad.CampoDeJuego;
import cartas.Monstruo;
import jugabilidad.Baraja;

public class EfectoAgujeroNegro extends Efecto{

	public void aplicar(Jugador unJugador) {
		
		Jugador jugador = unJugador;
		Jugador rival = unJugador.obtenerRival();
		
		CampoDeJuego campoUno = jugador.obtenerCampo();
		CampoDeJuego campoDos =	rival.obtenerCampo();
		
		Baraja monstruosUno = campoUno.obtenerMonstruos();
		Baraja monstruosDos = campoDos.obtenerMonstruos();
		
		//La carta envia todos los monstruos de los 2 jugadores en el cementario 
		while (monstruosUno.tieneCartas()) {
			campoUno.enviarAlCementerio((Monstruo)monstruosUno.obtenerPrimeraCarta());			
		}
		
		while (monstruosDos.tieneCartas()) {
			campoDos.enviarAlCementerio((Monstruo)monstruosDos.obtenerPrimeraCarta());			
		}
		
	}

	public void aplicarACarta(Monstruo unMonstruo) {}

	public void aplicarACartaRival(Monstruo monstruo) {}
	
}
