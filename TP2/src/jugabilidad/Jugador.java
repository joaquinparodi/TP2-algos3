package jugabilidad;

import cartas.Monstruo;
import cartas.Trampa;
import errores.ErrorSacrificiosInsuficientes;
import cartas.Carta;
import cartas.Magica;


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
    	campo.enviarMonstruoACementerio(unMonstruo);
    }
    
    public boolean monstruoEstaMuerto(String nombreMonstruo) {
    	return campo.cartaPerteneceAlCementerio(nombreMonstruo);
    }
    
    public void enviarMagicaAlCementerio(Magica unaCartaMagica) {
    	campo.enviarMagicaACementerio(unaCartaMagica);
    }
   
    public void agregarCartaMonstruoEnCampo(String unMonstruo, Baraja monstruosASacrificar) {
    	Monstruo monstruo = (Monstruo) mano.obtenerCarta(unMonstruo);
    	mano.eliminarCarta(monstruo);
    	if (monstruo.obtenerEstrellas() == 5 || monstruo.obtenerEstrellas() == 6) {
    		if ( monstruosASacrificar.obtenerCantidadDeCartas() < 1 )
    			throw new ErrorSacrificiosInsuficientes();
			campo.enviarMonstruoACementerio(monstruosASacrificar.obtenerPrimeraCarta());
		} else if(monstruo.obtenerEstrellas() > 6) {
			if ( monstruosASacrificar.obtenerCantidadDeCartas() < 2 )
				throw new ErrorSacrificiosInsuficientes();
			campo.enviarMonstruoACementerio(monstruosASacrificar.obtenerPrimeraCarta());
			monstruosASacrificar.eliminarCarta(monstruosASacrificar.obtenerPrimeraCarta());
			campo.enviarMonstruoACementerio(monstruosASacrificar.obtenerPrimeraCarta());
		}
    	campo.agregarCartaMonstruo(monstruo);
    }
    
    public void agregarCartaMagicaEnCampo(String nombreDeCarta) {
    	Magica cartaBuscada = (Magica) mano.obtenerCarta(nombreDeCarta);
    	campo.agregarCartaMagica(cartaBuscada);
    }
    
    public void agregarCartaTrampaEnCampo(String nombreDeCarta) {
    	Trampa cartaBuscada = (Trampa) mano.obtenerCarta(nombreDeCarta);
    	campo.agregarCartaTrampa(cartaBuscada);
    }
    
    public Jugador obtenerRival() {
    	return this.jugadorRival;
    }
    
    public CampoDeJuego obtenerCampo() {
    	return this.campo;
    }
    
    public void voltearCartaDeMano (String nombreDeCarta) {
    	mano.voltearCarta(nombreDeCarta);
    }

	public void enviarTrampaAlCementerio(Trampa trampa) {
		campo.enviarTrampaAlCementerio(trampa);
	}

}
