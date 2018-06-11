package cartas;

public class Derrota extends Resultado {

    public Derrota (double vida){
        super(vida);
    }

    @Override
    public void aplicarAJugadores(Monstruo monstruoPerdedor, Monstruo monstruoGanador) {
        monstruoPerdedor.descontarVidaAlJugador(vida);
    }
}
