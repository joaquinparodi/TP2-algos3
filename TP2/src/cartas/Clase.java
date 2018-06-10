package cartas;

public interface Clase {
	
	public Posicion obtenerPosicionInicial();
	
	public Posicion cambiarPosicion( Posicion posicion );
	
	public double atacar( Posicion posicionActual, Carta otraCarta );
	
	public double recibirAtaque( Posicion posicionActual, Atributos atributosAtacante );
	
}
