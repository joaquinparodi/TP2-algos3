package atributos;

public class Vida {

	double vida;
	
	public Vida ( double vidaInicial ) {
		this.vida = vidaInicial;
	}
	
	public void quitarVida ( double danio ) {
		this.vida -= danio;
	}
	
	//No se si esta bien esto!
	public double obtenerPuntosDeVida () {
		return this.vida;
	}

}
