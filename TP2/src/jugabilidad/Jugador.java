package jugabilidad;

import cartas.Monstruo;
import cartas.Trampa;
import errores.ErrorCartaNoEncontrada;
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
    
	public void enviarAlCementerio(Trampa trampa) {
		campo.enviarAlCementerio(trampa);
	}
    
    public void agregarCartaEnCampo(Monstruo cartaMonstruo, Sacrificio sacrificios) {
    	//Tambien verificar si los sacrificos pertenecen al campo
    
    	if( !mano.pertenece(cartaMonstruo) ) throw new ErrorCartaNoEncontrada();	
    	mano.eliminarCarta(cartaMonstruo);

    	int sacrificiosNecesarios = cartaMonstruo.obtenerCantidadDeSacrificiosNecesarios();
    	
    	if ( sacrificios.obtenerCantidadDeCartas() != sacrificiosNecesarios ) {
    		throw new ErrorSacrificiosInsuficientes();
    	}    	
    	
    	sacrificios.enviarSacrificiosAlCementerio( campo );
    	campo.agregarCarta(cartaMonstruo);
    }
    
    public void agregarCartaEnCampo(Monstruo cartaMonstruo) {

    	if( !mano.pertenece(cartaMonstruo) ) {
    		throw new ErrorCartaNoEncontrada();
    	}
    	
    	mano.eliminarCarta(cartaMonstruo);
    	
    	if( cartaMonstruo.necesitaSacrificiosParaInvocacion() ) {
    		throw new ErrorSacrificiosInsuficientes();
    	}
    	
    	campo.agregarCarta(cartaMonstruo);
    }
    
    public void agregarCartaEnCampo(Magica cartaMagica) {
    	//Verificar excepciones
    	campo.agregarCarta(cartaMagica);
    }
    
    public void agregarCartaEnCampo(Trampa cartaTrampa) {
    	//verifica excpeciones
    	campo.agregarCarta(cartaTrampa);
    }
    
    public boolean cartaEstaMuerta(Carta carta) {
    	return campo.cartaPerteneceAlCementerio(carta);
    }
    
    public Jugador obtenerRival() {
    	return this.jugadorRival;
    }

    public Vida obtenerVida() {
	    return this.vida;
    }
    
    public CampoDeJuego obtenerCampo() {
    	return this.campo;
    }
    
    public void voltearCartaDeMano (String nombreDeCarta) {
    	mano.voltearCarta(nombreDeCarta);
    }

}
