package jugabilidad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import cartas.Carta;
import errores.ErrorCartaNoEncontrada;
import errores.ErrorNoHayCartasEnLaBaraja;

public class Baraja {

	protected ArrayList<Carta> contenedor; 
	
	public Baraja() {
		contenedor = new ArrayList<Carta>();
	}
	
	public int obtenerCantidadDeCartas() {
		return contenedor.size();
	}
	
	public void agregarCarta(Carta carta) {
		contenedor.add(carta);
	}
	
	public void agregarCartaPrimero(Carta carta) {
		contenedor.add(0, carta);
	}
	
	public void eliminarCarta(Carta carta) {
		contenedor.remove(carta);
	}
	
	public Carta obtenerPrimeraCarta() {
		try {
			return contenedor.get(0);
		}catch(IndexOutOfBoundsException error) {
			throw new ErrorNoHayCartasEnLaBaraja();
		}
	}
	
	public Carta obtenerCarta(String nombreCarta) {
		Iterator<Carta> iter = contenedor.iterator();
	    while(iter.hasNext()) {
	    	Carta carta = iter.next();
	    	if( carta.seLlama(nombreCarta) ) return carta;
	    }
	    throw new ErrorCartaNoEncontrada();
	}
	
	public boolean pertenece(Carta cartaBuscada) {
		return contenedor.contains(cartaBuscada);
	}
	
	public boolean tieneCartas() {
		return (contenedor.size() > 0);
	}
	
	public void voltearCarta (String nombreDeCarta) {
		Carta carta = this.obtenerCarta(nombreDeCarta);
		carta.voltear();
	}

	public Iterator<Carta> obtenerIteradorDeBaraja() {
		Iterator<Carta> iter = contenedor.iterator();
		return iter;	 
	}

	public void mezclarBaraja() {
		Collections.shuffle(contenedor);
	}
}
