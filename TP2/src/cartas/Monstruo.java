package cartas;

public class Monstruo implements Clase {
	
	private Atributos atributos;
	
	public Monstruo( double auxPuntosDeAtaque, double auxPuntosDeDefensa ) {
		atributos = new Atributos( auxPuntosDeAtaque, auxPuntosDeDefensa );
	}
	
	public Posicion obtenerPosicionInicial() {
		 return new PosicionAtaque();
	}
	
	public Posicion cambiarPosicion( Posicion posicion ) {
		return posicion.cambiarPosicion();
	}
	
	public double atacar( Posicion posicionAtacante, Carta otroMonstruo ) {
		return posicionAtacante.atacar( atributos, otroMonstruo );
	}
	
	public double recibirAtaque( Posicion posicionActual, Atributos atributoAtacante ) {
		return posicionActual.recibirAtaque( atributoAtacante, atributos );
	}
}

