package cartas;

public class PosicionArriba implements Posicion {

	public Posicion cambiarPosicion() {
		return new PosicionAbajo();
	}

}
