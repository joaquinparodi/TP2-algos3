package atributos;

public class Puntos {

	private double puntosAtaque;
	private double puntosDefensa;
	private double plusAtaque;
	private double plusDefensa;
	
	public Puntos(double auxPuntosAtaque, double auxPuntosDefensa) {
		this.puntosAtaque = auxPuntosAtaque;
		this.puntosDefensa = auxPuntosDefensa;
		this.plusAtaque = 0;
		this.plusDefensa = 0;
	}
	
	public double obtenerPuntosDeDefensa() {
		return puntosDefensa + plusDefensa;
	}
	
	public double obtenerPuntosDeAtaque() {
		return puntosAtaque + plusAtaque;
	}

	public void incrementarAtaque(double incremento) {
		this.plusAtaque = incremento;
	}
	
	public void incrementarDefensa(double incremento) {
		this.plusDefensa = incremento;
	}
	
}
