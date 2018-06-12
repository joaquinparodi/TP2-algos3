package jugabilidad;

import cartas.Monstruo;
import cartas.Carta;
import cartas.Magica;

import cartas.Monstruo;

public class Jugador {

    private double vida;
    private CampoDeJuego campo;
    private Jugador jugadorRival;
    private Baraja mano;

	public Jugador(double vida) {
	    this.vida = vida;
	    this.campo = new CampoDeJuego();
	    this.mano = new Baraja();
    }
	
	public void asignarRival(Jugador auxJugadorRival) {
		this.jugadorRival = auxJugadorRival;
	}

	public void repartirCarta(Carta carta) {
		mano.agregarCarta(carta);
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
    
    public boolean monstruoEstaMuerto(String nombreMonstruo) {
    	return campo.cartaPerteneceAlCementerio(nombreMonstruo);
    }
    
    public void enviarMagicaAlCementerio(Magica unaCartaMagica) {
    }
    
    public void agregarCartaEnCampo(String nombreCarta) {
    	//Considerar caso que no exista
    	Carta cartaBuscada = mano.obtenerCarta(nombreCarta);
    	campo.agregarCartaEnCampo( cartaBuscada );
    }
    
    public void agregarCartaMonstruoEnCampo(Monstruo monstruo) {
    	campo.agregarCartaMonstruo(monstruo);
    }
    
    public void agregarCartaMagicaEnCampo(Magica cartaMagica) {
    	campo.agregarCartaMagica(cartaMagica);
    }
}
