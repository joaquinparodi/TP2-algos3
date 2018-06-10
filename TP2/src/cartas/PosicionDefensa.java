package TP2.src.cartas;

public class PosicionDefensa extends PosicionMonstruo {

    public PosicionDefensa (double puntos){
        super(puntos);
    }

    @Override
    public Resultado atacar(PosicionMonstruo otraPosicion) {
        return null;
    }
}
