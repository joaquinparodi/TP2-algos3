package cartas;

public class PosicionAtaque implements Posicion {
	
	public Posicion cambiarPosicion() {
		return new PosicionDefensa();
	}

	public double atacar( double puntosDeAtaque, double puntosDeDefensa, Carta otraCarta ) {
		double vidaPerdida = otraCarta.recibirAtaque( puntosDeAtaque, puntosDeDefensa );
		return vidaPerdida;
	}
	
	public double recibirAtaque( double puntosAtaqueAtacante, double puntosDefensaAtacante, double puntosDeAtaque, double puntosDeDefensa ) {
		return puntosDeAtaque - puntosAtaqueAtacante;
	}
	
}