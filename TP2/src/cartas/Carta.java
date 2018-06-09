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
		posicionActual = clase.cambiarPosicion();
	}

}
