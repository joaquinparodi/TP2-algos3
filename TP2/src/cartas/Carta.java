package cartas;
import jugabilidad.Jugador;
import posiciones.Arriba;
import posiciones.OrientacionCarta;

public abstract class Carta{

    protected Jugador jugadorDuenio;
    protected String nombreCarta;
    protected OrientacionCarta orientacion;
    protected Efecto efecto;
    
    public Carta( String auxNombre, Jugador auxJugador ) {
        nombreCarta = auxNombre;
        jugadorDuenio = auxJugador;
        orientacion = new Arriba();
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
