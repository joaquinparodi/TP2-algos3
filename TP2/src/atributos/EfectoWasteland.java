package atributos;

import java.util.Iterator;

import cartas.Monstruo;
import jugabilidad.Baraja;
import jugabilidad.CampoDeJuego;
import jugabilidad.Jugador;

public class EfectoWasteland extends Efecto {

	public void aplicar(Jugador unJugador) {
		
		Jugador jugador = unJugador;
		Jugador rival = unJugador.obtenerRival();
		
		CampoDeJuego campoUno = jugador.obtenerCampo();
		CampoDeJuego campoDos =	rival.obtenerCampo();
		
		Baraja monstruosUno = campoUno.obtenerMonstruos();
		Baraja monstruosDos = campoDos.obtenerMonstruos();
		
		//La carta cambia los puntos de los monstruos de ambos lados
		Iterator iterUno = monstruosUno.obtenerIteradorDeBaraja();
		Iterator iterDos = monstruosDos.obtenerIteradorDeBaraja();
	
		while(iterUno.hasNext()) {
		    Monstruo monstruo = (Monstruo)iterUno.next();
		    monstruo.incrementarPuntosDeAtaque(300);
		}
	
		while(iterDos.hasNext()) {
		    Monstruo monstruo = (Monstruo)iterDos.next();
		    monstruo.incrementarPuntosDeDefensa(200);
		}
	
	}

	public void aplicarACarta(Monstruo monstruo) {
		monstruo.incrementarPuntosDeAtaque(300);
	}

	public void aplicarACartaRival(Monstruo monstruo) {
		 monstruo.incrementarPuntosDeDefensa(200);
	}
	
	public void desaplicar(Jugador unJugador) {
		
		Jugador jugador = unJugador;
		Jugador rival = unJugador.obtenerRival();
		
		CampoDeJuego campoUno = jugador.obtenerCampo();
		CampoDeJuego campoDos =	rival.obtenerCampo();
		
		Baraja monstruosUno = campoUno.obtenerMonstruos();
		Baraja monstruosDos = campoDos.obtenerMonstruos();
		
		//La carta cambia los puntos de los monstruos de ambos lados
		Iterator iterUno = monstruosUno.obtenerIteradorDeBaraja();
		Iterator iterDos = monstruosDos.obtenerIteradorDeBaraja();
	
		while(iterUno.hasNext()) {
		    Monstruo monstruo = (Monstruo)iterUno.next();
		    monstruo.incrementarPuntosDeAtaque(0);
		}
	
		while(iterDos.hasNext()) {
		    Monstruo monstruo = (Monstruo)iterDos.next();
		    monstruo.incrementarPuntosDeDefensa(0);
		}
	
	}

}
