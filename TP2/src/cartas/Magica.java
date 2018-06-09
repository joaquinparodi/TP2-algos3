package cartas;

public class Magica implements Clase {
	
	public Posicion obtenerPosicionInicial() {
		return new PosicionArriba();
	}
	
	public Posicion cambiarPosicion( Posicion posicion ) {
		return posicion.cambiarPosicion();
	}

}
