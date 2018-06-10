package jugabilidad;

import java.util.LinkedList;

import cartas.Carta;

public class Mazo {
	
	private LinkedList<Carta> mazo;
	
	public Mazo() {
		mazo = new LinkedList<Carta>(); 
	}
	
	public void agregarCarta( Carta carta ) {
		mazo.add( carta );
	}
	
	public boolean pertenece( Carta cartaBuscada ) {
		return mazo.contains( cartaBuscada );
	}

}
