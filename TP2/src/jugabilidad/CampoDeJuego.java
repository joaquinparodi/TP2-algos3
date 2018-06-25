package jugabilidad;

import cartas.Atacable;
import cartas.Campo;
import cartas.Carta;
import cartas.Magica;
import cartas.Monstruo;
import cartas.Trampa;
import errores.ErrorNoHayCartasEnLaBaraja;
import errores.ErrorYaHayCartaDeCampoInvocada;
import errores.ErrorYaHayCincoCartasEnLaFila;

public class CampoDeJuego {

	private Baraja filaMonstruos;
	private Baraja filaMagicasYTrampas;
	
	private Campo cartaDeCampo;
	private Campo cartaDeCampoRival;
	
	private Baraja mazo;
	private Baraja cementerio;

	//Para uso interno del campo de juego
	private Baraja cartasMagicas;
	private Baraja cartasTrampas;

	public CampoDeJuego() {
		filaMonstruos = new Baraja();
		filaMagicasYTrampas = new Baraja();
		cementerio = new Baraja();
		cartasMagicas = new Baraja();
		cartasTrampas = new Baraja();
		mazo = new Baraja();
	}
	
	public void agregarMazo(Baraja nuevoMazo) {
		this.mazo = nuevoMazo;
	}
	
	public void enviarAlCementerio(Monstruo unMonstruo) {
		filaMonstruos.eliminarCarta(unMonstruo);
		cementerio.agregarCarta(unMonstruo);
	}

	public void enviarAlCementerio(Magica cartaMagica) {
		filaMagicasYTrampas.eliminarCarta(cartaMagica);
		cartasMagicas.eliminarCarta(cartaMagica);
		cementerio.agregarCarta(cartaMagica);
	}
	
	public void enviarAlCementerio(Trampa trampa) {
		filaMagicasYTrampas.eliminarCarta(trampa);
		cartasTrampas.eliminarCarta(trampa);
		cementerio.agregarCarta(trampa);
	}
	
	//Arreglar esto xq es horrible
	public void enviarAlCementerio(Campo cartaCampo) {
		if( cartaDeCampo != null ) cementerio.agregarCarta(cartaCampo);	
	}
	
	public void agregarCarta(Monstruo monstruo) {
		if (filaMonstruos.obtenerCantidadDeCartas()==5) {
			throw new ErrorYaHayCincoCartasEnLaFila();
		}
		filaMonstruos.agregarCarta(monstruo);
		//Cambiar las compartaciones con Null!-> ErrorCampoVacio
		if( cartaDeCampo != null ) cartaDeCampo.aplicarEfectoACarta(monstruo);
		if( cartaDeCampoRival != null ) cartaDeCampoRival.aplicarEfectoACartaRival(monstruo);
	}
	
	public void agregarCarta(Magica cartaMagica) {
		if (filaMagicasYTrampas.obtenerCantidadDeCartas()==5) {
			throw new ErrorYaHayCincoCartasEnLaFila();
		}
		filaMagicasYTrampas.agregarCarta(cartaMagica);
		cartasMagicas.agregarCarta(cartaMagica);
		cartaMagica.aplicarEfecto();
	}
	
	public void agregarCarta(Trampa cartaTrampa) {
		if (filaMagicasYTrampas.obtenerCantidadDeCartas()==5) {
			throw new ErrorYaHayCincoCartasEnLaFila();
		}
		filaMagicasYTrampas.agregarCarta(cartaTrampa);
		cartasTrampas.agregarCarta(cartaTrampa);
	}
	
	public void agregarCarta(Campo cartaCampo) {
		//Mato la carta atenrior
		if (this.cartaDeCampo != null) {
			throw new ErrorYaHayCartaDeCampoInvocada();
		}
		
		this.cartaDeCampo = cartaCampo;
		this.cartaDeCampo.aplicarEfecto();
	}
	
	public boolean cartaPerteneceAlCementerio(Carta cartaBuscada) {
		return cementerio.pertenece(cartaBuscada);
	}
	
	public Baraja obtenerMonstruos() {
		return this.filaMonstruos;
	}

	public void agregarCartaDeCampoRival(Campo cartaCampoRival) {
		this.cartaDeCampoRival = cartaCampoRival;
	}
	
	public Carta obtenerCartaDelMazo() {
		Carta carta = mazo.obtenerPrimeraCarta();
		mazo.eliminarCarta(carta);
		return carta;
	}
	
	public int obtenerTamanioDelMazo() {
		return mazo.obtenerCantidadDeCartas();
	}
	
	public Baraja obtenerMazo() {
		return mazo;
	}

	public void aplicarTrampa(Atacable atacante, Atacable atacado, Jugador jugadorRival) {
			
		try {
			
			Trampa cartaTrampa = (Trampa)cartasTrampas.obtenerPrimeraCarta();
			cartaTrampa.aplicarEfecto( atacante, atacado, jugadorRival);
			cartasTrampas.agregarCartaPrimero(cartaTrampa);
			
		}catch (ErrorNoHayCartasEnLaBaraja error) {
			
			atacante.atacar(atacado);
			
		}
		
	}

	public void eliminarTrampa() {
		Trampa cartaTrampa = (Trampa)cartasTrampas.obtenerPrimeraCarta();
		cartaTrampa.enviarAlCementerio();
	}
	
	public boolean hayTrampasEnCampo() {
		return cartasTrampas.tieneCartas();
	}

	public boolean hayCartasEnMazo() {
		return mazo.tieneCartas();
	}
	
	/*Nuevos metodos faltann probar*/
	public void rotarCartaMonstruo(int index) {
		Monstruo monstruoEnIndice = (Monstruo)filaMonstruos.obtenerCartaDePosicion(index);
		monstruoEnIndice.cambiarPosicion();
	}
	
	public boolean cartaMonstruoEnEstaRotada(int index) {
		Monstruo monstruo = (Monstruo)filaMonstruos.obtenerCartaDePosicion(index);
		return monstruo.estaRotado();
	}
		
	public boolean cementerioContieneCartas() {
		return cementerio.tieneCartas();
	}
	
	/*--------------------Metodos utilizados en la interfaz------------------------------------*/
	
	public Baraja obtenerFilaDeMonstruos() {
		return filaMonstruos;
	}
	
	public Baraja obtenerFilaDeMagicasYTrampas() {
		return filaMagicasYTrampas;
	}
	
	public boolean seAgregoCartaDeCampo() {
		return cartaDeCampo != null;
	}

	public String obtenerNombreCartaDeCampo() {
		return cartaDeCampo.obtenerNombre();
	}

	

}
