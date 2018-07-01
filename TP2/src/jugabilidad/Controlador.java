package jugabilidad;

import atributos.Vida;

public class Controlador {
	
	private Jugador jugadorUno;
	private Jugador jugadorDos;
	private Jugador atacante;
	private Jugador defensor;
	private Fase fase;
	private boolean cartaRepartida;
	
	private static Controlador instancia;
	
	private Controlador() {	
		Vida vidaJugadorUno = new Vida(500);
		Vida vidaJugadorDos = new Vida(500);
		
		this.jugadorUno = new Jugador( vidaJugadorUno );
		this.jugadorDos = new Jugador( vidaJugadorDos );
		
		this.jugadorUno.asignarNumeroDeJugador(1);
		this.jugadorDos.asignarNumeroDeJugador(2);
		
		this.asignarRivales(jugadorUno, jugadorDos);
		
		this.fase = new FasePreparacion();
		
		this.cartaRepartida = false;
		
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
	
	public void repartirCartasIniciales() {
		for (int i = 0; i < 6; i++) {
			jugadorUno.repartirCarta();
		}
		
		this.cartaRepartida = true;
		
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
	
	private boolean restanCartasEnMazo(Jugador jugador) { 
		return jugador.poseeCartasEnMazo(); 
	}
	
	private boolean exodiaEstaCompleto(Jugador jugador) {
		return jugador.poseeExodiaCompleto(); 
	}
	
	private boolean hayJugadorSinVida(Jugador jugador) {
		return jugador.fueDerrotado();
	}
	
	public boolean ganoElJugadorDelTurnoActual() {
		return (this.exodiaEstaCompleto(atacante) || !this.restanCartasEnMazo(defensor) || this.hayJugadorSinVida(defensor));
	}
	
	public boolean ganoElOponente() {
		return (this.exodiaEstaCompleto(defensor) || !this.restanCartasEnMazo(atacante) || this.hayJugadorSinVida(atacante));
	}
	
	/*Metodos usados para manejar los turnos*/
	public void avanzarFase() {
		fase = fase.avanzarFase();
	}

	public void cambiarTurno() {
		this.cartaRepartida = false;
		Jugador jugadorAuxiliar = atacante; 
		atacante = defensor;
		defensor = jugadorAuxiliar;
		ReglasDeMonstruos.obtener().reiniciar();
	}
	
	public String obtenerFase() {
		return fase.obtenerNombre();
	}

	public void repartirCartaAJugador() {
		this.atacante.repartirCarta();
		this.cartaRepartida = true;
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

	public boolean jugadorYaRepartiCarta() {
		return this.cartaRepartida;
	}

	public boolean hayGanador() {
		return (this.ganoElJugadorDelTurnoActual() || this.ganoElOponente());
	}

	public void reiniciarControlador() {
		instancia = new Controlador();
	}

}
