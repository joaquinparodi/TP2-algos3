package cartas;

public abstract class Resultado {

    protected double vidaPerdida;

    protected Resultado ( double auxVidaPerdida ){
    	vidaPerdida = auxVidaPerdida;
    }

    abstract public void aplicarAJugadores( Monstruo unMonstruo, Monstruo otroMonstruo );

}
