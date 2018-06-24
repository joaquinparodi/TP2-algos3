package factories;

import atributos.EfectoAgujeroNegro;
import atributos.EfectoCilindroMagico;
import atributos.EfectoFisura;
import atributos.EfectoOllaDeLaCodicia;
import atributos.EfectoReinforcements;
import atributos.EfectoSogen;
import atributos.EfectoWasteland;
import atributos.Estrellas;
import atributos.Puntos;
import cartas.BrazoDerechoExodia;
import cartas.BrazoIzquierdoExodia;
import cartas.CabezaExodia;
import cartas.Campo;
import cartas.DragonBlancoOjoAzul;
import cartas.DragonDefinitivo;
import cartas.InsectoComeHombres;
import cartas.Jinzo7;
import cartas.Magica;
import cartas.Monstruo;
import cartas.PiernaDerechaExodia;
import cartas.PiernaIzquierdaExodia;
import cartas.Trampa;
import jugabilidad.Jugador;

public class FabricaDeCartas {
	
	Jugador jugador;
	
	public FabricaDeCartas(Jugador unJugador) {
		jugador = unJugador;
	}

	public Magica crearAgujeroNegro() {
		return new Magica("Agujero Negro", jugador, new EfectoAgujeroNegro ());
	}
	
	public Trampa crearCilindroMagico() {
		return new Trampa("Cilindro Magico", jugador, new EfectoCilindroMagico ());
	}
	
	public Magica crearFisura() {
		return new Magica("Fisura", jugador, new EfectoFisura ());
	}
	
	public Magica crearOllaDeLaCodicia() {
		return new Magica("Olla De La Codicia", jugador, new EfectoOllaDeLaCodicia ());
	}
	
	public Trampa crearReinforcements() {
		return new Trampa("Reinforcements", jugador, new EfectoReinforcements ());
	}
	
	public Campo crearSogen() {
		return new Campo("Sogen", jugador, new EfectoSogen ());
	}
	
	public Campo crearWasteland() {
		return new Campo("Wasteland", jugador, new EfectoWasteland());
	}
	
	public Monstruo crearDragonDefinitivo() {
		return new DragonDefinitivo("Dragon Definitivo", jugador, new Estrellas(12), new Puntos(4500, 3800));
	}
	
	public Monstruo crearDragonBlancoDeOjosAzules() {
		return new DragonBlancoOjoAzul("Dragon Blanco Ojos Azules", jugador, new Estrellas(8), new Puntos(3000, 2500));
	}
	
	public Monstruo crearCabezaExodia() {
		return new CabezaExodia("Cabeza Exodia", jugador, new Estrellas(3), new Puntos(450,600));
	}
	
	public Monstruo crearBrazoIzquierdoExodia() {
		return new BrazoIzquierdoExodia("Brazo Izquierdo Exodia", jugador, new Estrellas(3), new Puntos(450,600));
	}
	
	public Monstruo crearBrazoDerechoExodia() {
		return new BrazoDerechoExodia("Brazo Derecho Exodia", jugador, new Estrellas(3), new Puntos(450,600));
	}
	
	public Monstruo crearInsectoComeHombres() {
		return new InsectoComeHombres("Insecto Come-Hombres", jugador, new Estrellas(2), new Puntos(450,600));
	}
	
	public Monstruo crearJinzo7() {
		return new Jinzo7("Jinzo 7",jugador, new Estrellas(2), new Puntos(500,400));
	}
	
	public Monstruo crearPiernaDerechaExodia() {
		return new PiernaDerechaExodia("Pierna Derecha Exodia", jugador, new Estrellas(3), new Puntos(450,600));
	}
	
	public Monstruo crearPiernaIzquierdaExodia() {
		return new PiernaIzquierdaExodia("Pierna Izquierda Exodia", jugador, new Estrellas(3), new Puntos(450,600));
	}
	
	public Monstruo crearMonstruoPersonalizado(String nombre, Estrellas estrellas, Puntos puntos) {
		return new Monstruo(nombre, jugador, estrellas, puntos);
	}
}
