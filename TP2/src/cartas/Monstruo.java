package cartas;

public class Monstruo implements Clase {
	
	public Posicion obtenerPosicionInicial() {
		 return new PosicionAtaque();
	}
	
	public Posicion cambiarPosicion( Posicion posicion ) {
		return posicion.cambiarPosicion();
	}
	
}

