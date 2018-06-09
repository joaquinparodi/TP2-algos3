package cartas;

public class PosicionAtaque implements Posicion {
	
	public Posicion cambiarPosicion() {
		return new PosicionDefensa();
	}
	
}
