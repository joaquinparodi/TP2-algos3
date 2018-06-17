package cartas;
import atributos.Efecto;
import jugabilidad.Jugador;
import posiciones.OrientacionArriba;
import posiciones.Orientacion;

public abstract class Carta {

	protected Jugador jugadorDuenio;
    protected String nombreCarta;
    protected Orientacion orientacion;
    private Efecto efecto;
    
    public Carta( String auxNombre, Jugador auxJugador ) {
        nombreCarta = auxNombre;
        jugadorDuenio = auxJugador;
        orientacion = new OrientacionArriba();
    }
    
    public boolean seLlama( String auxNombre ) {
    	return auxNombre == nombreCarta;
    }

    public abstract void enviarAlCementerio();
    
    public abstract void aplicarEfecto ();
    
    public void voltear () {
    	orientacion = orientacion.voltear();
    }
}
