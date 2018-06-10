package TP2.src.cartas;
import jugabilidad.Jugador;

public abstract class Carta {

    protected Jugador jugador;
    protected String nombre;

    public Carta (String unNombre, Jugador unJugador){
        this.nombre = unNombre;
        this.jugador = unJugador;
    }

    public void enviarAlCementerio (){
    }
}
