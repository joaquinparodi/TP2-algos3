package jugabilidad;

import cartas.Monstruo;
import cartas.Trampa;
import errores.ErrorSacrificiosInsuficientes;
import atributos.Sacrificio;
import atributos.Vida;
import cartas.Carta;
import cartas.Magica;


public class Jugador {

    private Vida vida;
    private CampoDeJuego campo;
    private Jugador jugadorRival;
    private Baraja mano;
    

	public Jugador(Vida vida) {
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
	
    public Vida obtenerVida() {
	    return this.vida;
    }

    public void hacerDanio(double danio) {
	    this.vida.quitarVida( danio );
    }
    
    public void hacerDanioAlRival(double danio) {
    	jugadorRival.hacerDanio(danio);
    }
    
    public void enviarAlCementerio(Monstruo unMonstruo) {
    	campo.enviarAlCementerio(unMonstruo);
    }
    
    public void enviarAlCementerio(Magica unaCartaMagica) {
    	campo.enviarAlCementerio(unaCartaMagica);
    }
   
    
    public boolean monstruoEstaMuerto(String nombreMonstruo) {
    	return campo.cartaPerteneceAlCementerio(nombreMonstruo);
    }
    
    public void agregarCartaMonstruoEnCampo(String unMonstruo, Sacrificio sacrificios) {
    	//Tirar exception si no tiene la carta! Tambien verificar si los sacrificos pertenecen al campo
    	Monstruo monstruo = (Monstruo) mano.obtenerCarta(unMonstruo);
    	mano.eliminarCarta(monstruo);

    	int sacrificiosNecesarios = monstruo.obtenerCantidadDeSacrificiosNecesarios();
    	
    	if ( sacrificios.obtenerCantidadDeCartas() != sacrificiosNecesarios ) {
    		throw new ErrorSacrificiosInsuficientes();
    	}    	
    	
    	sacrificios.enviarSacrificiosAlCementerio( campo );
    	campo.agregarCartaMonstruo(monstruo);
    }
    
    public void agregarCartaMonstruoEnCampo(String unMonstruo) {
    	//Tirar exception si no tiene la carta!
    	Monstruo monstruo = (Monstruo) mano.obtenerCarta(unMonstruo);
    	mano.eliminarCarta(monstruo);
    	
    	if( monstruo.necesitaSacrificiosParaInvocacion() ) {
    		throw new ErrorSacrificiosInsuficientes();
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
