package jugabilidad;

import java.util.ArrayList;
import java.util.Iterator;

import cartas.Carta;

public class Baraja {

	private ArrayList<Carta> contenedor; 
	
	public Baraja() {
		contenedor = new ArrayList<Carta>();
	}
	
	public void agregarCarta(Carta carta) {
		contenedor.add(carta);
	}
	
	public void eliminarCarta(Carta carta) {
		contenedor.remove(carta);
	}
	
	public Carta obtenerPrimeraCarta() {
		return contenedor.get(0);
	}
	
	public Carta obtenerCarta(String nombreCarta) {
		Iterator<Carta> iter = contenedor.iterator();
	    while(iter.hasNext()) {
	    	Carta carta = iter.next();
	    	if( carta.seLlama(nombreCarta) ) return carta;
	    }
	    //Lanzar excpecion
	    return null;
	}
	
	public boolean pertenece(String nombreCarta) {
		Iterator<Carta> iter = contenedor.iterator();
	    while(iter.hasNext()) {
	    	Carta carta = iter.next();
	    	if( carta.seLlama(nombreCarta) ) return true;
	    }
	    return false;
	}
	
	public boolean tieneCartas () {
		return (contenedor.size() > 0);
	}

}
