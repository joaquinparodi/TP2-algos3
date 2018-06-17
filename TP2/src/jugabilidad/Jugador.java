package jugabilidad;

import cartas.Monstruo;
import cartas.Trampa;
import errores.ErrorCartaNoEncontrada;
import errores.ErrorSacrificiosInsuficientes;
import atributos.Sacrificio;
import atributos.Vida;
import cartas.Campo;
import cartas.Carta;
import cartas.Magica;


public class Jugador {

    private Vida vida;
    private CampoDeJuego campoDeJuego;
    private Jugador jugadorRival;
    private Baraja mano;
    private Baraja mazo;
    

	public Jugador(Vida vida) {
	    this.vida = vida;
	    this.campoDeJuego = new CampoDeJuego();
	    this.mano = new Baraja();
	    this.mazo = new Baraja();
    }

	public void asignarRival(Jugador auxJugadorRival) {
		this.jugadorRival = auxJugadorRival;
	}
	
	public Carta obtenerCartaDelMazo() {
		return mazo.obtenerPrimeraCarta();
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
    	campoDeJuego.enviarAlCementerio(unMonstruo);
    }
    
    public void enviarAlCementerio(Magica cartaMagica) {
    	campoDeJuego.enviarAlCementerio(cartaMagica);
    }
    
	public void enviarAlCementerio(Trampa cartaTrampa) {
		campoDeJuego.enviarAlCementerio(cartaTrampa);
	}
	
	public void enviarAlCementerio(Campo cartaCampo) {
		campoDeJuego.enviarAlCementerio(cartaCampo);
	}
    
    public void agregarCartaEnCampo(Monstruo cartaMonstruo, Sacrificio sacrificios) {
    	//Tambien verificar si los sacrificos pertenecen al campo
    
    	if( !mano.pertenece(cartaMonstruo) ) throw new ErrorCartaNoEncontrada();	
    	mano.eliminarCarta(cartaMonstruo);

    	int sacrificiosNecesarios = cartaMonstruo.obtenerCantidadDeSacrificiosNecesarios();
    	
    	if ( sacrificios.obtenerCantidadDeCartas() != sacrificiosNecesarios ) {
    		throw new ErrorSacrificiosInsuficientes();
    	}    	
    	
    	sacrificios.enviarSacrificiosAlCementerio( campoDeJuego );
    	campoDeJuego.agregarCarta(cartaMonstruo);
    }
    
    public void agregarCartaEnCampo(Monstruo cartaMonstruo) {

    	if( !mano.pertenece(cartaMonstruo) ) {
    		throw new ErrorCartaNoEncontrada();
    	}
    	
    	mano.eliminarCarta(cartaMonstruo);
    	
    	if( cartaMonstruo.necesitaSacrificiosParaInvocacion() ) {
    		throw new ErrorSacrificiosInsuficientes();
    	}
    	
    	campoDeJuego.agregarCarta(cartaMonstruo);
    }
    
    public void agregarCartaEnCampo(Magica cartaMagica) {
    	//Verificar excepciones
    	campoDeJuego.agregarCarta(cartaMagica);
    }
    
    public void agregarCartaEnCampo(Trampa cartaTrampa) {
    	//verifica excpeciones
    	campoDeJuego.agregarCarta(cartaTrampa);
    }
    
    public void agregarCartaEnCampo(Campo cartaCampo) {
    	campoDeJuego.agregarCarta(cartaCampo);
    }
    
    public boolean cartaEstaMuerta(Carta carta) {
    	return campoDeJuego.cartaPerteneceAlCementerio(carta);
    }
    
    public Jugador obtenerRival() {
    	return this.jugadorRival;
    }

    public Vida obtenerVida() {
	    return this.vida;
    }
    
    public CampoDeJuego obtenerCampo() {
    	return this.campoDeJuego;
    }
    
    public void voltearCartaDeMano (String nombreDeCarta) {
    	mano.voltearCarta(nombreDeCarta);
    }

}
