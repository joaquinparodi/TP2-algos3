package cartas;
import jugabilidad.Jugador;

public abstract class PosicionMonstruo {

    protected double puntos;

    public PosicionMonstruo (double puntos){
        this.puntos = puntos;
    }

    public abstract Resultado atacar (PosicionMonstruo otraPosicion);

    public abstract void hacerDanioAJugador (Jugador jugador, double vida);
    
   
    public Resultado recibirAtaque (double puntos){

        if (puntos>this.puntos){
            return new Victoria(puntos-this.puntos);
        }
        else if (puntos<this.puntos){
            return new Derrota(this.puntos-puntos);
        }
        else{
            return new Empate(0);
        }

    }

}
