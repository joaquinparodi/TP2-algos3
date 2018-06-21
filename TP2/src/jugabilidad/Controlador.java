package jugabilidad;

import atributos.Efecto;
import atributos.EfectoAgujeroNegro;
import atributos.EfectoCilindroMagico;
import atributos.EfectoDeTrampa;
import atributos.EfectoFisura;
import atributos.EfectoOllaDeLaCodicia;
import atributos.EfectoReinforcements;
import atributos.EfectoSogen;
import atributos.EfectoWasteland;
import cartas.Carta;
import factories.FabricaDeCartas;
import factories.FabricaDeMonstruosEspeciales;

public class Controlador {
	
	Jugador jugadorUno;
	Jugador jugadorDos;
	Jugador atacante;
	Jugador defensor;

	public Controlador(Jugador jugadorUno, Jugador jugadorDos) {
		
		this.jugadorUno = jugadorUno;
		this.jugadorDos = jugadorDos;
		
		this.asignarRivales(jugadorUno, jugadorDos);
		
		//Decidir esto se va a encargar otra clase(Inicializador)
		atacante = jugadorUno;
		defensor = jugadorDos;
	}
	
	public void asignarMazos(Baraja mazoUno, Baraja mazoDos) {
		jugadorUno.asignarMazo(mazoUno);
		jugadorDos.asignarMazo(mazoDos);
		this.repartirCartasIniciales();
	}
	
	public Jugador obtenerJugadorAtacante() {
		return atacante;
	}
	
	public Jugador obtenerJugadorDefensor() {
		return defensor;
	}
	
	private void asignarRivales(Jugador jugadorUno, Jugador jugadorDos) {
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
	}
	
	private void repartirCartasIniciales() {
		for (int i = 0; i < 6; i++) {
			jugadorUno.repartirCarta();
		}
		
		for(int i = 0; i < 5; i++) {
			jugadorDos.repartirCarta();
		}
	}
	
	/*Metodos usador para determinar ganador*/
	
	private boolean restanCartasEnMazo() { 
		return atacante.poseeCartasEnMazo(); 
	}
	
	private boolean exodiaEstaCompleto() {
		return atacante.poseeExodiaCompleto(); 
	}
	
	private boolean hayJugadorSinVida() {
		return defensor.fueDerrotado();
	}
	
	public boolean hayGanador() {
		return this.exodiaEstaCompleto() || !this.restanCartasEnMazo() || this.hayJugadorSinVida();
	}
	
}
