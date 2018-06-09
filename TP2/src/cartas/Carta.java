package cartas;


public abstract class Carta {
	
	protected String nombre;
	protected boolean estaVolteada;
	
	public Carta(String unNombre, boolean estadoVolteada) {
		nombre = unNombre;
		estaVolteada = estadoVolteada;
	}

}
