package cartas;

public class Derrota extends Resultado {

    public Derrota ( double vida ){
        super( vida );
    }
    
    public void aplicarAJugadores( Monstruo monstruoPerdedor, Monstruo monstruoGanador ) {
        monstruoPerdedor.descontarVidaAlJugador( vidaPerdida );
    }
    
}
