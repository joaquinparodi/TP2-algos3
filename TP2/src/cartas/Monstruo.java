package cartas;

public class Monstruo implements Clase {
	
	private Atributos atributos;
	
	public Monstruo( double auxPuntosDeAtaque, double auxPuntosDeDefensa ) {
		atributos = new Atributos( auxPuntosDeAtaque, auxPuntosDeDefensa );
	}
	
	public Posicion cambiarPosicion( Posicion posicion ) {
		return posicion.cambiarPosicion( this );
	}
	
	public double atacar( Posicion posicionAtacante, Carta otroMonstruo ) {
		return posicionAtacante.atacar( atributos, otroMonstruo );
	}
	
	public double recibirAtaque( Posicion posicionActual, Atributos atributoAtacante ) {
		return posicionActual.recibirAtaque( atributoAtacante, atributos );
	}
	
}

