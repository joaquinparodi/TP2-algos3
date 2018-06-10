package jugabilidad;

public class Jugador {

    double vidaActual;
    HashMap<Carta> cartasEnMano;
    
	public Jugador( double vidaInicial ) {
	    vidaActual = vidaInicial;
	    cartasEnMano = new HashMap<Carta>();
    }

    public double obtenerVida() {
	    return vidaActual;
    }
    
}
