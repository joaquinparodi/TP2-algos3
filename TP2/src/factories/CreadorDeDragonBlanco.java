package factories;

import atributos.Estrellas;
import atributos.Puntos;
import cartas.DragonBlancoOjoAzul;
import jugabilidad.Jugador;

public class CreadorDeDragonBlanco extends CreadorDeCartas {
	
	public CreadorDeDragonBlanco(Jugador unJugador) {
		super(unJugador);
	}

	public void crearCarta() {
		carta = new DragonBlancoOjoAzul("Dragon Blanco Ojos Azules", jugador, new Estrellas(8), new Puntos(3000, 2500));
	}
}
