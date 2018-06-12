package jugabilidad;
import cartas.Monstruo;
import cartas.Magica;

import cartas.Monstruo;

public class Jugador {

    private double vida;
    private CampoDeJuego campo;
    private Jugador jugadorRival;

	public Jugador(double vida) {
	    this.vida = vida;
	    this.campo = new CampoDeJuego();
    }
	
	public void asignarRival(Jugador auxJugadorRival) {
		this.jugadorRival = auxJugadorRival;
	}

    public double obtenerVida() {
	    return this.vida;
    }

    public void hacerDanio(double danio) {
	    this.vida = this.vida - danio ;
    }
    
    public void hacerDanioAlRival(double danio) {
    	jugadorRival.hacerDanio(danio);
    }
    
    public void enviarMonstruoAlCementerio(Monstruo unMonstruo) {
    	campo.enviarCartaACementerio(unMonstruo);
    }
    
    public boolean monstruoEstaMuerto(Monstruo unMonstruo) {
    	return campo.cartaPerteneceAlCementerio(unMonstruo);
    }
    
    public void enviarMagicaAlCementerio(Magica unaCartaMagica) {
    }
    
}