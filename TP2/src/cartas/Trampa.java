package cartas;

public class Trampa implements Clase {
	
	public Posicion obtenerPosicionInicial() {
		return new PosicionAbajo();
	}
	
	public Posicion cambiarPosicion( Posicion posicion ) {
		return posicion.cambiarPosicion();
	}
	
}

