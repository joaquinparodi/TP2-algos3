package jugabilidad;

import cartas.Campo;
import cartas.Carta;
import cartas.Magica;
import cartas.Monstruo;
import cartas.Trampa;
import errores.ErrorCampoVacio;

public class CampoDeJuego {

	private Baraja filaMonstruos;
	private Baraja filaMagicas;
	
	private Campo cartaDeCampo;
	private Campo cartaDeCampoRival;
	
	private Baraja cementerio;
	private Baraja filaTrampas;

	public CampoDeJuego() {
		filaMonstruos = new Baraja();
		filaMagicas = new Baraja();
		filaTrampas = new Baraja();
		cementerio = new Baraja();
	}
	
	public void enviarAlCementerio(Monstruo unMonstruo) {
		filaMonstruos.eliminarCarta(unMonstruo);
		cementerio.agregarCarta(unMonstruo);
	}

	public void enviarAlCementerio(Magica cartaMagica) {
		filaMagicas.eliminarCarta(cartaMagica);
		cementerio.agregarCarta(cartaMagica);
	}
	
	public void enviarAlCementerio(Trampa trampa) {
		filaTrampas.eliminarCarta(trampa);
		cementerio.agregarCarta(trampa);
	}
	
	//Arreglar esto xq es horrible
	public void enviarAlCementerio(Campo cartaCampo) {
		if( cartaDeCampo != null ) cementerio.agregarCarta(cartaCampo);	
	}
	
	public void agregarCarta(Monstruo monstruo) {
		filaMonstruos.agregarCarta(monstruo);
		//Cambiar las compartaciones con Null!
		if( cartaDeCampo != null ) cartaDeCampo.aplicarEfectoACarta(monstruo);
		if( cartaDeCampoRival != null ) cartaDeCampoRival.aplicarEfectoACartaRival(monstruo);
	}
	
	public void agregarCarta(Magica cartaMagica) {
		filaMagicas.agregarCarta(cartaMagica);
		cartaMagica.aplicarEfecto();
	}
	
	public void agregarCarta(Trampa cartaTrampa) {
		filaTrampas.agregarCarta(cartaTrampa);
	}
	
	public void agregarCarta(Campo cartaCampo) {
		//Mato la carta atenrior
		this.enviarAlCementerio(this.cartaDeCampo); 			
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

}
