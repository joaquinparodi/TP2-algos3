package factories;

import atributos.EfectoCilindroMagico;
import cartas.Trampa;
import jugabilidad.Jugador;

public class CreadorDeCilindroMagico extends CreadorDeCartas {

	public CreadorDeCilindroMagico(Jugador unJugador) {
		super(unJugador);
	}

	public void crearCarta() {
		carta = new Trampa("Cilindro Magico", jugador, new EfectoCilindroMagico ());
	}
}
