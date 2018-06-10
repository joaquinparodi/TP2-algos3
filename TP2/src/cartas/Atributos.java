package cartas;

public class Atributos {
	
	private double puntosDeAtaque;
	private double puntosDeDefensa;
	
	public Atributos ( double auxPuntosDeAtaque, double auxPuntosDeDefensa ) {
		puntosDeAtaque = auxPuntosDeAtaque;
		puntosDeDefensa = auxPuntosDeDefensa;
	}
	
	public double obtenerPuntosDeAtaque() {
		return puntosDeAtaque;
	}
	
	public double obtenerPuntosDeDefensa() {
		return puntosDeDefensa;
	}
	
}
