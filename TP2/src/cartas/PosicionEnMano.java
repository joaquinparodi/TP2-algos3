package cartas;

public class PosicionEnMano implements Posicion {

	public Posicion cambiarPosicion( Monstruo mosntruo ) {
		return new PosicionAtaque();
	}

	public double atacar(Atributos atributos, Carta otroMonstruo) {
		//Hay que lanzar excepcion
		return 0;
	}

	public double recibirAtaque(Atributos atributoAtacante, Atributos atributoAtacado) {
		//Hay que lanzar excepcion
		return 0;
	}

}
