package cartas;

import TP2.src.cartas.*;

public class PosicionAtaque extends PosicionMonstruo {

    public PosicionAtaque (double puntos){
        super(puntos);
    }

    public Resultado atacar (PosicionMonstruo posicion){

        return posicion.recibirAtaque (this.puntos);

    }


}