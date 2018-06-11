package cartas;

public abstract class Resultado {

    protected double vida;

    protected Resultado (double vida){
        this.vida = vida;
    }

    abstract public void aplicarAJugadores(Monstruo unMonstruo, Monstruo otroMonstruo);

}
