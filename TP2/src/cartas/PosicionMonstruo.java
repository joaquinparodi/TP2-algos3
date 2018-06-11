package cartas;
import jugabilidad.Jugador;

public abstract class PosicionMonstruo {

    protected double puntos;

    public abstract Resultado atacar( PosicionMonstruo otraPosicion );

    public abstract void hacerDanioAJugador( Jugador jugador, double vida );  
    
    public PosicionMonstruo( double auxPuntos ) {
        puntos = auxPuntos;
    }
   
    public Resultado recibirAtaque( double puntos ) {
    	
        if( puntos > this.puntos ) return new Victoria(puntos-this.puntos);
        if( puntos < this.puntos ) return new Derrota(this.puntos-puntos);
        return new Empate(0);
    }

}
