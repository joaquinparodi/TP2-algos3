package cartas;
import jugabilidad.Jugador;

public class PosicionDefensa extends PosicionMonstruo {

    public PosicionDefensa ( double puntos ){
        super( puntos );
    }

    public Resultado atacar( PosicionMonstruo otraPosicion ) {
        return null;
    }
    
    public void hacerDanioAJugador( Jugador unJugador, double vida ) {
    }
    
    public void aplicarQuitaDeVidaA(Monstruo unMonstruo, double vida) {
    	
    }
    
    public void destruirCarta (Monstruo unMonstruo) {
    	
    }
    
}
