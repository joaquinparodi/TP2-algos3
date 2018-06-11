package cartas;

public class Victoria extends Resultado {

    public Victoria( double vida ) {
        super( vida );
    }

    public void aplicarAJugadores( Monstruo monstruoGanador, Monstruo monstruoPerdedor ) {
        monstruoPerdedor.descontarVidaAlJugador( vidaPerdida );
    }
}
