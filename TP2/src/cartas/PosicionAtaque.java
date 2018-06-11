package cartas;
import jugabilidad.Jugador;

public class PosicionAtaque extends PosicionMonstruo {

    public PosicionAtaque (double puntos){
        super(puntos);
    }

    public Resultado atacar (PosicionMonstruo posicion){

        return posicion.recibirAtaque (this.puntos);

    }

    public void hacerDanioAJugador(Jugador unJugador, double vida) {
    	
    	unJugador.hacerDanio(vida);
    }
}