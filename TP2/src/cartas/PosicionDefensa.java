package cartas;
import jugabilidad.Jugador;

public class PosicionDefensa extends PosicionMonstruo {

    public PosicionDefensa (double puntos){
        super(puntos);
    }

    @Override
    public Resultado atacar(PosicionMonstruo otraPosicion) {
        return null;
    }
    
    @Override
    public void hacerDanioAJugador(Jugador unJugador, double vida) {
    	
    }
}
