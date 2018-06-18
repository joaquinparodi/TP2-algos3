package factories;

import atributos.Estrellas;
import atributos.Puntos;
import cartas.DragonBlancoOjoAzul;
import cartas.DragonDefinitivo;
import cartas.InsectoComeHombres;
import cartas.Jinzo7;
import jugabilidad.Jugador;

public class FabricaDeMonstruosEspeciales {

	public Jinzo7 crearJinzo7(Jugador unJugador) {
		return new Jinzo7("Jinzo 7",unJugador, new Estrellas(2), new Puntos(500,400));
	}

	public DragonBlancoOjoAzul crearDragonBlanco(Jugador jugador) {
		return new DragonBlancoOjoAzul("Dragon Blanco Ojos Azules", jugador, new Estrellas(8), new Puntos(3000, 2500));
	}

	public DragonDefinitivo crearDragonDefinitivo(Jugador jugador) {
		return new DragonDefinitivo("Dragon Definitivo", jugador, new Estrellas(12), new Puntos(4500, 3800));
	}
	
	public InsectoComeHombres crearInsectoComeHombres(Jugador unJugador) {
		return new InsectoComeHombres("Insecto Come-Hombres", unJugador, new Estrellas(2), new Puntos(450,600));
	}
	
}
