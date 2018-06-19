package factories;

import atributos.Estrellas;
import atributos.Puntos;
import cartas.BrazoDerechoExodia;
import cartas.BrazoIzquierdoExodia;
import cartas.CabezaExodia;
import cartas.DragonBlancoOjoAzul;
import cartas.DragonDefinitivo;
import cartas.InsectoComeHombres;
import cartas.Jinzo7;
import cartas.PiernaDerechaExodia;
import cartas.PiernaIzquierdaExodia;
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
	
	public CabezaExodia crearCabezaExodia(Jugador unJugador) {
		return new CabezaExodia("Cabeza Exodia", unJugador, new Estrellas(3), new Puntos(450,600));
	}
	
	public BrazoIzquierdoExodia crearBrazoIzquierdoExodia(Jugador unJugador) {
		return new BrazoIzquierdoExodia("Brazo Izquierdo Exodia", unJugador, new Estrellas(3), new Puntos(450,600));
	}
	
	public BrazoDerechoExodia crearBrazoDerechoExodia(Jugador unJugador) {
		return new BrazoDerechoExodia("Brazo Derecho Exodia", unJugador, new Estrellas(3), new Puntos(450,600));
	}
	
	public PiernaIzquierdaExodia crearPiernaIzquierdaExodia(Jugador unJugador) {
		return new PiernaIzquierdaExodia("Pierna Izquierda Exodia", unJugador, new Estrellas(3), new Puntos(450,600));
	}
	
	public PiernaDerechaExodia crearPiernaDerechaExodia(Jugador unJugador) {
		return new PiernaDerechaExodia("Pierna Derecha Exodia", unJugador, new Estrellas(3), new Puntos(450,600));
	}
	
}
