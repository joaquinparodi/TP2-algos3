package jugabilidad;

import java.util.ArrayList;
import java.util.Iterator;

import cartas.Carta;
import cartas.Monstruo;

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
	    	Monstruo monstruo = (Monstruo)iter.next();
	    	if( monstruo.seLlama(nombreCarta) ) return monstruo;
	    }
	    //Lanzar excpecion
	    return null;
	}
	
	public boolean pertenece(String nombreCarta) {
		Iterator<Carta> iter = contenedor.iterator();
	    while(iter.hasNext()) {
	    	Monstruo monstruo = (Monstruo)iter.next();
	    	if( monstruo.seLlama(nombreCarta) ) return true;
	    }
	    return false;
	}

}
