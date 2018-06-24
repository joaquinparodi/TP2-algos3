package atributos;

import java.util.Iterator;

import cartas.Carta;
import cartas.Monstruo;
import jugabilidad.Baraja;
import jugabilidad.CampoDeJuego;
import jugabilidad.Jugador;

public class EfectoWasteland extends EfectoDeCampo {

	public void aplicar(Jugador unJugador) {
		
		Jugador jugador = unJugador;
		Jugador rival = unJugador.obtenerRival();
		
		CampoDeJuego campoUno = jugador.obtenerCampo();
		CampoDeJuego campoDos =	rival.obtenerCampo();
		
		Baraja monstruosUno = campoUno.obtenerMonstruos();
		Baraja monstruosDos = campoDos.obtenerMonstruos();
		
		//La carta cambia los puntos de los monstruos de ambos lados
		Iterator<Carta> iterUno = monstruosUno.obtenerIteradorDeBaraja();
		Iterator<Carta> iterDos = monstruosDos.obtenerIteradorDeBaraja();
	
		while(iterUno.hasNext()) {
		    Monstruo monstruo = (Monstruo)iterUno.next();
		    monstruo.incrementarPuntosDeAtaque(200);
		}
		
		while(iterDos.hasNext()) {
		    Monstruo monstruo = (Monstruo)iterDos.next();
		    monstruo.incrementarPuntosDeDefensa(300);
		}
		
	}

	public void aplicarACarta(Monstruo monstruo) {
		monstruo.incrementarPuntosDeAtaque(200);
	}

	public void aplicarACartaRival(Monstruo monstruo) {
		 monstruo.incrementarPuntosDeDefensa(300);
	}
	
	public void desaplicar(Jugador unJugador) {
		
		Jugador jugador = unJugador;
		Jugador rival = unJugador.obtenerRival();
		
		CampoDeJuego campoUno = jugador.obtenerCampo();
		CampoDeJuego campoDos =	rival.obtenerCampo();
		
		Baraja monstruosUno = campoUno.obtenerMonstruos();
		Baraja monstruosDos = campoDos.obtenerMonstruos();
		
		//La carta cambia los puntos de los monstruos de ambos lados
		Iterator<Carta> iterUno = monstruosUno.obtenerIteradorDeBaraja();
		Iterator<Carta> iterDos = monstruosDos.obtenerIteradorDeBaraja();
	
		while(iterUno.hasNext()) {
		    Monstruo monstruo = (Monstruo)iterUno.next();
		    monstruo.restaurarPuntos();
		}
	
		while(iterDos.hasNext()) {
		    Monstruo monstruo = (Monstruo)iterDos.next();
		    monstruo.restaurarPuntos();
		}
	
	}

}
