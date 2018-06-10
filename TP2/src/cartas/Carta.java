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
		clase.atacar( posicionActual, otraCarta );
		return 0;
	}
	
	public double recibirAtaque ( double puntosAtaqueAtacante, double puntosDefensaAtacante ) {
		return clase.recibirAtaque( posicionActual, puntosAtaqueAtacante, puntosDefensaAtacante ); 
	}
}
