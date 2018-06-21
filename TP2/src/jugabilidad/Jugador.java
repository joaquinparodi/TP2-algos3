package jugabilidad;

import cartas.Monstruo;
import cartas.Trampa;
import errores.ErrorCartaAUsarNoPerteneceAlJugadorQueLaIntentaUsar;
import errores.ErrorCartaEnManoNoPuedeAtacar;
import errores.ErrorCartaNoEncontrada;
import errores.ErrorIntentaAtacarACartaPropia;
import errores.ErrorSacrificiosInsuficientes;
import errores.ErrorCartasSacrificadasIncorrectas;

import java.util.Iterator;

import atributos.Sacrificio;
import atributos.Vida;
import cartas.Atacable;
import cartas.Campo;
import cartas.Carta;
import cartas.Magica;


public class Jugador {

    private Vida vida;
    private CampoDeJuego campoDeJuego;
    private Jugador jugadorRival;
    private Baraja mano;
  
	public Jugador(Vida vida) {
	    this.vida = vida;
	    this.campoDeJuego = new CampoDeJuego();
	    this.mano = new Baraja();
    }

	public void asignarRival(Jugador jugadorRival) {
		this.jugadorRival = jugadorRival;
	}

	public void repartirCarta(Carta carta) {
		mano.agregarCarta(carta);
	}

    public void repartirCarta() {
    	this.repartirCarta(this.campoDeJuego.obtenerCartaDelMazo());
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
    	
    	if (!cartaMonstruo.verificarSacrificios(sacrificios)) {
    		//Algunas cartas necesitan sacrificios especificos
    		throw new ErrorCartasSacrificadasIncorrectas();
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
    	if( !mano.pertenece(cartaMagica) ) {
    		throw new ErrorCartaNoEncontrada();
    	}
    	
    	mano.eliminarCarta(cartaMagica);
    	campoDeJuego.agregarCarta(cartaMagica);
    }
    
    public void agregarCartaEnCampo(Trampa cartaTrampa) {
    	if( !mano.pertenece(cartaTrampa) ) {
    		throw new ErrorCartaNoEncontrada();
    	}
    	
    	mano.eliminarCarta(cartaTrampa);
    	campoDeJuego.agregarCarta(cartaTrampa);
    }
    
    public void agregarCartaEnCampo(Campo cartaCampo) {
    	if( !mano.pertenece(cartaCampo) ) {
    		throw new ErrorCartaNoEncontrada();
    	}
    	
    	mano.eliminarCarta(cartaCampo);
    	campoDeJuego.agregarCarta(cartaCampo);
    	jugadorRival.agregarCartaDeCampoRival(cartaCampo);
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
    	
    public void agregarCartaDeCampoRival(Campo cartaCampo) {
    	this.campoDeJuego.agregarCartaDeCampoRival(cartaCampo);
    }
    
    public boolean contieneCartaEnMano (Carta unaCarta) {
    	return this.mano.pertenece(unaCarta);
    }
    
    public void atacarCartaConCarta(Atacable cartaAAtacar, Atacable cartaAUsar) {
    	
    	if (!cartaAUsar.lePerteneceA(this)) {
    		throw new ErrorCartaAUsarNoPerteneceAlJugadorQueLaIntentaUsar();
    	}
    	
    	if (cartaAAtacar.lePerteneceA(this)) {
    		throw new ErrorIntentaAtacarACartaPropia();
    	}
    	
    	if ( mano.pertenece((Carta) cartaAUsar) ){
    		throw new ErrorCartaEnManoNoPuedeAtacar();
    	}
    	
    	jugadorRival.recibirAtaqueDeCarta(cartaAUsar,cartaAAtacar);
    	
    }
    
    //Estaria bueno sacar estos ifs de aca!
	private void recibirAtaqueDeCarta(Atacable cartaAtacante, Atacable cartaAtacada) {
		campoDeJuego.aplicarTrampa(cartaAtacante, cartaAtacada, jugadorRival);
	}
	
	public int obtenerCantidadDeCartasEnMano() {
		return mano.obtenerCantidadDeCartas();
	}
	
	public boolean poseeCartasEnMazo() {
		return campoDeJuego.hayCartasEnMazo();
	}

	public boolean poseeExodiaCompleto() {
		return false;
	}
	
	public boolean fueDerrotado() {
		return vida.estaVacia();
	}
	
	public void asignarMazo(Baraja mazo) {
		campoDeJuego.agregarMazo(mazo);
	}

}
