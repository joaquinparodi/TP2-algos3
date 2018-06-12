package jugabilidad;

import java.util.ArrayList;
import java.util.Iterator;

import cartas.Carta;
import cartas.Magica;
import cartas.Monstruo;
import posiciones.PosicionMonstruo;


public class CampoDeJuego {

	private Baraja filaMonstruos;
	private Baraja filaMagicas;
	private Baraja cementerio;

	public CampoDeJuego() {
		filaMonstruos = new Baraja();
		filaMagicas = new Baraja();
		cementerio = new Baraja();
	}

	public void agregarCartaEnCampo(Carta carta) {
		carta.agregarseEnCampo();
	}
	
	public void enviarCartaACementerio(Carta unaCarta) {
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
	}
}