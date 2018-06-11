package jugabilidad;
import cartas.Monstruo;
import cartas.Magica;

public class Jugador {

    private double vida;
    private CampoDeJuego campo;

	public Jugador (double vida){
	    this.vida = vida;
    }

    public double obtenerVida(){
	    return this.vida;
    }

    public void hacerDanio(double danio){
	    this.vida = this.vida - danio ;
    }
    
    public void enviarMonstruoAlCementerio(Monstruo unMonstruo) {

    }
    
    public void enviarMagicaAlCementerio(Magica unaCartaMagica) {
    	
    }
    
}