package cartas;
import atributos.Efecto;
import jugabilidad.Jugador;
import posiciones.OrientacionArriba;
import posiciones.Orientacion;

public abstract class Carta {

	protected Jugador jugadorDuenio;
    protected String nombreCarta;
    protected Orientacion orientacion;
    protected Efecto efecto;
    
    public Carta( String nombre, Jugador jugador ) {
        nombreCarta =nombre;
        jugadorDuenio = jugador;
        orientacion = new OrientacionArriba();
    }
    
    public boolean seLlama( String nombre ) {
    	return nombre == nombreCarta;
    }

    public abstract void enviarAlCementerio();
    
    public abstract boolean esParteDeExodia();
    
    public void voltear() {
    	orientacion = orientacion.voltear();
    }
    
}
