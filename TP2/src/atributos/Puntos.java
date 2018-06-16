package atributos;

public class Puntos {

	private double puntosAtaque;
	private double puntosDefensa;
	
	public Puntos(double auxPuntosAtaque, double auxPuntosDefensa) {
		this.puntosAtaque = auxPuntosAtaque;
		this.puntosDefensa = auxPuntosDefensa;
	}
	
	public double obtenerPuntosDeDefensa() {
		return puntosDefensa;
	}
	
	public double obtenerPuntosDeAtaque() {
		return puntosAtaque;
	}
	
}
