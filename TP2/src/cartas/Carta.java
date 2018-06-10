package cartas;

public class Carta {
	
	private Clase clase;
	private String nombre;
	private Posicion posicionActual;

	public Carta( String nombreDeLaCarta, Clase claseDeLaCarta ) {
		nombre = nombreDeLaCarta;
		clase = claseDeLaCarta;
		posicionActual = new PosicionEnMano();
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
