package cartas;

import jugabilidad.Jugador;
import jugabilidad.CampoDeJuego;
import jugabilidad.Baraja;

public class EfectoAgujeroNegro implements Efecto{

	public void aplicar(Jugador unJugador) {
		
		Jugador jugador = unJugador;
		Jugador rival = unJugador.obtenerRival();
		
		CampoDeJuego campoUno = jugador.obtenerCampo();
		CampoDeJuego campoDos =	rival.obtenerCampo();
		
		Baraja monstruosUno = campoUno.obtenerMonstruos();
		Baraja monstruosDos = campoDos.obtenerMonstruos();
		
		//La carta envia todos los monstruos de los 2 jugadores en el cementario 
		while (monstruosUno.tieneCartas()) {
			campoUno.enviarMonstruoACementerio(monstruosUno.obtenerPrimeraCarta());			
		}
		
		while (monstruosDos.tieneCartas()) {
			campoDos.enviarMonstruoACementerio(monstruosDos.obtenerPrimeraCarta());			
		}
		
		
	}

	
}
