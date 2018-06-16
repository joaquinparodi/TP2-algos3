package jugabilidad;

import cartas.Carta;
import cartas.Magica;
import cartas.Monstruo;
import cartas.Trampa;



public class CampoDeJuego {

	private Baraja filaMonstruos;
	private Baraja filaMagicas;
	private Baraja cementerio;
	private Baraja filaTrampas;

	public CampoDeJuego() {
		filaMonstruos = new Baraja();
		filaMagicas = new Baraja();
		filaTrampas = new Baraja();
		cementerio = new Baraja();
	}
	
	public void enviarMonstruoACementerio(Carta unaCarta) {
		filaMonstruos.eliminarCarta(unaCarta);
		cementerio.agregarCarta(unaCarta);
	}

	public void enviarMagicaACementerio(Carta unaCarta) {
		filaMagicas.eliminarCarta(unaCarta);
		cementerio.agregarCarta(unaCarta);
	}
	
	public boolean cartaPerteneceAlCementerio(String nombreCarta) {
		return cementerio.pertenece(nombreCarta);
	}
	
	public void agregarCartaMonstruo(Monstruo monstruo) {
		filaMonstruos.agregarCarta(monstruo);
	}
	
	public void agregarCartaMagica(Magica cartaMagica) {
		filaMagicas.agregarCarta(cartaMagica);
		cartaMagica.aplicarEfecto();
	}
	
	public Baraja obtenerMonstruos() {
		return this.filaMonstruos;
	}

	public void enviarTrampaAlCementerio(Trampa trampa) {
		filaTrampas.eliminarCarta(trampa);
		cementerio.agregarCarta(trampa);
	}

	public void agregarCartaTrampa(Trampa cartaTrampa) {
		filaTrampas.agregarCarta(cartaTrampa);
	}
	
	public Monstruo obtenerMonstruo(String nombreMonstruo) {
		return (Monstruo)filaMonstruos.obtenerCarta(nombreMonstruo);
	}
}
