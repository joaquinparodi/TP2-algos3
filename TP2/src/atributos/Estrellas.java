package atributos;

public class Estrellas {

	private int cantidadDeEstrellas;
	
	public Estrellas ( int cantidadDeEstrellas ) {
		this.cantidadDeEstrellas = cantidadDeEstrellas;
	}
	
	private int determinarCantidadDeSacrificios() {
		if(cantidadDeEstrellas >= 7) return 2;
		if(cantidadDeEstrellas >= 5) return 1;
		return 0;
	}
	
	public int cantidadDeSacrificionNecesarios() {
		return this.determinarCantidadDeSacrificios();
	}
	
	public boolean necesitaSacrificiosParaInvocacion() {
		return this.determinarCantidadDeSacrificios() != 0;
	}
	
}
