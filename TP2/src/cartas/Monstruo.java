package cartas;

public class Monstruo implements Clase {
	
	Posicion posicionActual;
	
	public Monstruo() {
		posicionActual = new PosicionAtaque();
	}
	
	public void cambiarPosicion() {
		posicionActual = posicionActual.cambiarPosicion();
	}
	
}

