package jugabilidad;

import java.util.Iterator;

import atributos.EfectoDeTrampa;
import cartas.Atacable;
import cartas.Campo;
import cartas.Carta;
import cartas.Magica;
import cartas.Monstruo;
import cartas.Trampa;
import errores.ErrorNoHayCartasEnLaBaraja;
import errores.ErrorYaHayCartaDeCampoInvocada;

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
		mazo = new Baraja();
		cartasMagicas = new Baraja();
		cartasTrampas = new Baraja();
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
		filaMonstruos.agregarCarta(monstruo);
		//Cambiar las compartaciones con Null!-> ErrorCampoVacio
		if( cartaDeCampo != null ) cartaDeCampo.aplicarEfectoACarta(monstruo);
		if( cartaDeCampoRival != null ) cartaDeCampoRival.aplicarEfectoACartaRival(monstruo);
	}
	
	public void agregarCarta(Magica cartaMagica) {
		filaMagicasYTrampas.agregarCarta(cartaMagica);
		cartasMagicas.agregarCarta(cartaMagica);
		cartaMagica.aplicarEfecto();
	}
	
	public void agregarCarta(Trampa cartaTrampa) {
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

	public Monstruo obtenerMonstruo(String nombreMonstruo) {
		return (Monstruo)filaMonstruos.obtenerCarta(nombreMonstruo);
	}

	public void agregarCartaDeCampoRival(Campo cartaCampoRival) {
		this.cartaDeCampoRival = cartaCampoRival;
	}
	
	public void cargarMazo(Baraja cartasACargar) {
		Iterator<Carta> iterador = cartasACargar.obtenerIteradorDeBaraja();
		while(iterador.hasNext()) {
		    Carta carta = iterador.next();
		    mazo.agregarCarta(carta);
		}
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
}
