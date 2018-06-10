package cartas;

public class PosicionDefensa implements Posicion {

	public Posicion cambiarPosicion( Monstruo monstruo ) {
		return new PosicionDefensa();
	}

	public double atacar( Atributos atributosAtacante, Carta otraCarta ) {
		return otraCarta.recibirAtaque( atributosAtacante );
	}

	public double recibirAtaque( Atributos atributosAtacante, Atributos atributos ) {
		return 0;
	}
		
}
