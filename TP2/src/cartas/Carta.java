package cartas;
import atributos.Efecto;
import jugabilidad.Jugador;
import posiciones.OrientacionArriba;
import posiciones.Orientacion;

public abstract class Carta {

	protected Jugador jugadorDuenio;
    protected String nombreCarta;
    protected Orientacion orientacion;
    protected Efecto efecto;
    
    public Carta( String nombre, Jugador jugador ) {
        nombreCarta =nombre;
        jugadorDuenio = jugador;
        orientacion = new OrientacionArriba();
    }
    
    public boolean seLlama( String nombre ) {
    	return nombre == nombreCarta;
    }

    public abstract void enviarAlCementerio();
    
    public boolean esParteDeExodia() {
    	return false;
    }
    
    public void voltear() {
    	orientacion = orientacion.voltear();
    }
    
    public boolean verificarTipoExodia() {
		if (this.seLlama("Cabeza Exodia")) return true;
		if (this.seLlama("Brazo Izquierdo Exodia")) return true;
		if (this.seLlama("Brazo Derecho Exodia")) return true;
		if (this.seLlama("Pierna Izquierda Exodia")) return true;
		if (this.seLlama("Pierna Derecha Exodia")) return true;
		return false;
	}

}
