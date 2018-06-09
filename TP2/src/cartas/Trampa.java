package cartas;

public class Trampa implements Clase {

	Posicion posicionActual;
	
	public Campo () {
		posicionActual = new PosicionAbajo();
	}
	
	public void cambiarPosicion() {
		posicionActual = posicionActual.cambiarPosicion();
	}
	
}

