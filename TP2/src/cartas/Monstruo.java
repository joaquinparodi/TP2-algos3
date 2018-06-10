package cartas;

public class Monstruo implements Clase {
	
	private double puntosDeAtaque;
	private double puntosDeDefensa;
	
	public Monstruo( double auxPuntosDeAtaque, double auxPuntosDeDefensa ) {
		puntosDeAtaque = auxPuntosDeAtaque;
		puntosDeDefensa = auxPuntosDeDefensa;
	}
	
	public Posicion obtenerPosicionInicial() {
		 return new PosicionAtaque();
	}
	
	public Posicion cambiarPosicion( Posicion posicion ) {
		return posicion.cambiarPosicion();
	}
	
	public double atacar( Posicion posicionAtacante, Carta otroMonstruo ) {
		posicionAtacante.atacar( puntosDeAtaque, puntosDeDefensa, otroMonstruo );
	}
	
	public double recibirAtaque( Posicion posicionActual, double puntosAtaqueAtacante, double puntosDefensaAtacante ) {
		return posicionActual.recibirAtaque ( puntosAtaqueAtacante, puntosDefensaAtacante. puntosDeAtaque, puntosDeDefensa );
	}
}

