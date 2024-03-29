package atributos;

import java.util.Iterator;

import cartas.Carta;
import cartas.Monstruo;
import jugabilidad.Baraja;
import jugabilidad.CampoDeJuego;
import jugabilidad.Jugador;

public class EfectoFisura extends Efecto {

	public void aplicar(Jugador unJugador) {
		
		Jugador rival = unJugador.obtenerRival();
		
		CampoDeJuego campoRival = rival.obtenerCampo();
		Baraja monstruosRival = campoRival.obtenerMonstruos();
		
		if(!monstruosRival.tieneCartas()) return;
		
		Iterator<Carta> iter = monstruosRival.obtenerIteradorDeBaraja();
		//Lanzar exception si esta vacio el campo de monstruo rival!
		
		//Busco la carta con menos puntos
		Monstruo monstruo = (Monstruo)iter.next();
		Puntos puntos = monstruo.obtenerPuntos();;
		
		while(iter.hasNext()) {
			Monstruo monstruoActual = (Monstruo)iter.next();
			if( monstruoActual.obtenerPuntos().obtenerPuntosDeAtaque() < puntos.obtenerPuntosDeAtaque() ) {
				monstruo = monstruoActual;
				puntos = monstruoActual.obtenerPuntos();
			} 
		}
		
		rival.enviarAlCementerio( monstruo );
	}
	
}
