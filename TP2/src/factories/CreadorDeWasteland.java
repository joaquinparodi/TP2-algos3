package factories;

import atributos.EfectoWasteland;
import cartas.Campo;
import jugabilidad.Jugador;

public class CreadorDeWasteland extends CreadorDeCartas {
	
	public CreadorDeWasteland(Jugador unJugador) {
		super(unJugador);
	}

	public void crearCarta() {
		carta = new Campo("Wasteland", jugador, new EfectoWasteland());
	}
}