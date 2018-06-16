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
	
	public void enviarAlCementerio(Monstruo unMonstruo) {
		filaMonstruos.eliminarCarta(unMonstruo);
		cementerio.agregarCarta(unMonstruo);
	}

	public void enviarAlCementerio(Magica cartaMagica) {
		filaMagicas.eliminarCarta(cartaMagica);
		cementerio.agregarCarta(cartaMagica);
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
