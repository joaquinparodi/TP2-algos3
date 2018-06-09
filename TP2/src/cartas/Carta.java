package cartas;

public abstract class Carta {
	
	protected Clase clase;
	protected String nombre;
	protected Posicion posicionActual;
	
	public Carta( String nombreDeLaCarta, Clase claseDeLaCarta ) {
		nombre = nombreDeLaCarta;
		clase = claseDeLaCarta;
		//Falta inicializar posicionActual
	}
	
}
