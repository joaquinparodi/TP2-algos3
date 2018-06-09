package cartas;

public class Monstruo extends Carta {
	
	private int puntosDeAtaque;
	private int puntosDeDefensa;
	private int nivel; // de 1 a 10 estrellas
	private String efecto; // puede tenerlo o no
	private String posicion; // posicion de ataque o defensa
	private boolean atacoEsteTurno;
	
	public Monstruo(int unAtaque, int unaDefensa, int unNivel, String unEfecto, String unNombre, String unaPosicion) {
		puntosDeAtaque = unAtaque;
		puntosDeDefensa = unaDefensa;
		nivel = unNivel;
		efecto = unEfecto;
		nombre = unNombre;
		posicion = unaPosicion;
		atacoEsteTurno = false;
	}
	
	

}
