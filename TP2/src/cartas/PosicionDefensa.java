package cartas;

public class PosicionDefensa implements Posicion {

	public Posicion cambiarPosicion() {
		return new PosicionAtaque();
	}
	
}
