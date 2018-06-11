package jugabilidad;

public class Jugador {

    private double vida;

	public Jugador (double vida){
	    this.vida = vida;
    }

    public double obtenerVida(){
	    return this.vida;
    }

    public void hacerDanio(double danio){
	    this.vida = this.vida - danio ;
    }
    
}