package factories;

import atributos.Estrellas;
import atributos.Puntos;
import cartas.InsectoComeHombres;
import jugabilidad.Jugador;

public class CreadorDeInsectoComeHombres extends CreadorDeCartas {
	
	public CreadorDeInsectoComeHombres(Jugador unJugador) {
		super(unJugador);
	}

	public void crearCarta() {
		carta = new InsectoComeHombres("Insecto Come-Hombres", jugador, new Estrellas(2), new Puntos(450,600));
	}
}
