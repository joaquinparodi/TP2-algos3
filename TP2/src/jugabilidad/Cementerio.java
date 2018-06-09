package jugabilidad;

import java.util.LinkedList;
import cartas.Carta;

public class Cementerio {
	
	private LinkedList<Carta> cementerio;
	
	public Cementerio() {
		cementerio = new LinkedList<Carta>(); 
	}
	
	public void agregarCarta( Carta carta ) {
		cementerio.add( carta );
	}
	
	public boolean pertenece( Carta cartaBuscada ) {
		return cementerio.contains( cartaBuscada );
	}

}
