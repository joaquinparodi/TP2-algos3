package cartas;

public class PosicionAtaque implements Posicion {
	
	public Posicion cambiarPosicion( Monstruo mosntruo ) {
		return new PosicionDefensa();
	}
	
	public double atacar( Atributos atributosAtacante, Carta otraCarta ) {
		return otraCarta.recibirAtaque( atributosAtacante );
	}
	
	public double recibirAtaque( Atributos atributosAtacante, Atributos atributos ) {
		return atributos.obtenerPuntosDeAtaque() - atributosAtacante.obtenerPuntosDeAtaque();
	}
	
}