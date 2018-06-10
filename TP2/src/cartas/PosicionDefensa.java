package cartas;

public class PosicionDefensa implements Posicion {

	public Posicion cambiarPosicion() {
		return new PosicionAtaque();
	}

	public double atacar( Atributos atributosAtacante, Carta otraCarta ) {
		return otraCarta.recibirAtaque( atributosAtacante );
	}

	public double recibirAtaque( Atributos atributosAtacante, Atributos atributos ) {
		return atributos.obtenerPuntosDeDefensa() - atributosAtacante.obtenerPuntosDeDefensa();
	}
		
}
