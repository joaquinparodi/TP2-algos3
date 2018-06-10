package cartas;

public interface Posicion {

	public Posicion cambiarPosicion();
		
	public double atacar( double puntosDeAtaque, double puntosDeDefensa, Carta otroMonstruo );
	
}

