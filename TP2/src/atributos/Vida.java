package atributos;

public class Vida {

	double vida;
	
	public Vida ( double vidaInicial ) {
		this.vida = vidaInicial;
	}
	
	public void quitarVida ( double danio ) {
		this.vida -= danio;
	}
	
	public double obtenerPuntosDeVida () {
		return this.vida;
	}

	 public boolean estaVacia() {
		return vida <= 0;
	}

}
