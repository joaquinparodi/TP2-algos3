package cartas;

public class Magica implements Clase {

	Posicion posicionActual;
	
	public Magica () {
		posicionActual = new PosicionAtaque();
	}
	
	public void cambiarPosicion() {
		posicionActual = posicionActual.cambiarPosicion();
	}

}
