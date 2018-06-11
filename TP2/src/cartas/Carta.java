package cartas;
import jugabilidad.Jugador;

public abstract class Carta implements Clase {

    protected Jugador jugadorDuenio;
    protected String nombreCarta;

    public abstract void atacar( Carta otraCarta );
    
    public Carta( String auxNombre, Jugador auxJugador ) {
        nombreCarta = auxNombre;
        jugadorDuenio = auxJugador;
    }

    public void enviarAlCementer() {
    }
    

}
