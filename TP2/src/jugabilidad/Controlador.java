package jugabilidad;

import atributos.Vida;

public class Controlador {
	
	private Jugador jugadorUno;
	private Jugador jugadorDos;
	private Jugador atacante;
	private Jugador defensor;
	private Fase fase;
	
	private static Controlador instancia;
	
	private Controlador() {	
		Vida vidaJugadorUno = new Vida(8000);
		Vida vidaJugadorDos = new Vida(8000);
		
		this.jugadorUno = new Jugador( vidaJugadorUno );
		this.jugadorDos = new Jugador( vidaJugadorDos );
		
		this.jugadorUno.asignarNumeroDeJugador(1);
		this.jugadorDos.asignarNumeroDeJugador(2);
		
		this.asignarRivales(jugadorUno, jugadorDos);
		
		this.fase = new FasePreparacion();
		
		atacante = jugadorUno;
		defensor = jugadorDos;
		
	}
	
	public static Controlador obtener() {
		 if(instancia == null) {
			 instancia = new Controlador();
		 }
		 return instancia;
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
	
	public Jugador obtenerJugadorUno() {
		return jugadorUno;
	}
	
	public Jugador obtenerJugadorDos() {
		return jugadorDos;
	}
	
	/*Metodos usador para determinar ganador*/
	
	private boolean restanCartasEnMazo() { 
		return !defensor.poseeCartasEnMazo(); 
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
	
	public void avanzarFase() {
		fase = fase.avanzarFase();
	}

	public void cambiarTurno() {
		Jugador jugadorAuxiliar = atacante; 
		atacante = defensor;
		defensor = jugadorAuxiliar;
	}
	
	public String obtenerFase() {
		return fase.obtenerNombre();
	}

	public void repartirCartaAJugador() {
		this.atacante.repartirCarta();
	}
	
	public int obtenerNumeroJugadorAtacante() {
		return atacante.obtenerNumeroDeJugador();
	}
	
	public boolean esElTurnoDe(Jugador jugador) {
		return (jugador == atacante);
	}
	
	public boolean partidaEstaEnFase(String nombreFase) {
		return nombreFase == fase.obtenerNombre();
	}

}
