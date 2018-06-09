package cartas;

public class PosicionAbajo implements Posicion {

	public Posicion cambiarPosicion() {
		return new PosicionArriba();
	}

}
