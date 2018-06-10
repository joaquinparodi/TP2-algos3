package cartas;

public class Carta {
	
	protected Clase clase;
	protected String nombre;
	protected Posicion posicionActual;
	
	public Carta( String nombreDeLaCarta, Clase claseDeLaCarta ) {
		nombre = nombreDeLaCarta;
		clase = claseDeLaCarta;
		posicionActual = clase.obtenerPosicionInicial();
	}
	
	public void cambiarPosicion() {
		posicionActual = clase.cambiarPosicion( posicionActual );
	}

	public double atacar( Carta otraCarta ) {
		return clase.atacar( posicionActual, otraCarta );	
	}
	
	public double recibirAtaque( Atributos atributosAtacante ) {
		return clase.recibirAtaque( posicionActual, atributosAtacante ); 
	}
	
}
