package factories;

import atributos.Estrellas;
import atributos.Puntos;
import cartas.DragonDefinitivo;
import jugabilidad.Jugador;

public class CreadorDeDragonDefinitivo extends CreadorDeCartas {
	
	public CreadorDeDragonDefinitivo(Jugador unJugador) {
		super(unJugador);
	}

	public void crearCarta() {
		carta = new DragonDefinitivo("Dragon Definitivo", jugador, new Estrellas(12), new Puntos(4500, 3800));
	}
}
