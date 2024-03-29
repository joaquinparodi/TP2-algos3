package jugabilidad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import cartas.Carta;
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
	
	public boolean pertenece(Carta cartaBuscada) {
		return contenedor.contains(cartaBuscada);
	}
	
	public boolean tieneCartas() {
		return !contenedor.isEmpty();
	}

	public Iterator<Carta> obtenerIteradorDeBaraja() {
		Iterator<Carta> iter = contenedor.iterator();
		return iter;	 
	}

	public void mezclarBaraja() {
		Collections.shuffle(contenedor);
	}
	
	public boolean tieneExodiaCompleto() {
		Iterator<Carta> iterador = this.obtenerIteradorDeBaraja();
		int contador = 0;
		while ( iterador.hasNext() ){
			if (iterador.next().esParteDeExodia()) contador++;
		}
		return (contador == 5);
	}

	/*Nuevos metodos falta probar*/
	
	public Carta obtenerCartaDePosicion(int index) {
		try {
			return contenedor.get(index);
		}catch(IndexOutOfBoundsException error) {
			throw new ErrorNoHayCartasEnLaBaraja(); ///////CAmbiar!!!!!!!!!!!!!!!!!!!1
		}
	}

		
}
