package cartas;

public interface Posicion {

	public Posicion cambiarPosicion();
		
	public double atacar( Atributos atributos, Carta otroMonstruo );
	
	public double recibirAtaque( Atributos atributoAtacante, Atributos atributoAtacado );
	
}

