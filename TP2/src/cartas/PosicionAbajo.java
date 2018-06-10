package cartas;

public class PosicionAbajo implements Posicion {

	public Posicion cambiarPosicion() {
		return new PosicionArriba();
	}

	@Override
	public double atacar(Atributos atributos, Carta otroMonstruo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double recibirAtaque(Atributos atributoAtacante, Atributos atributoAtacado) {
		// TODO Auto-generated method stub
		return 0;
	}

}
