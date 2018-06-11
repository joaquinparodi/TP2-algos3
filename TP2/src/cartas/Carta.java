package cartas;
import jugabilidad.Jugador;

public abstract class Carta{

    protected Jugador jugadorDuenio;
    protected String nombreCarta;

    public Carta( String auxNombre, Jugador auxJugador ) {
        nombreCarta = auxNombre;
        jugadorDuenio = auxJugador;
    }

    public abstract void enviarAlCementerio();
    
}
