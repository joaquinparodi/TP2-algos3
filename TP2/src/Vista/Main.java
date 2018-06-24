package Vista;

import atributos.Vida;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jugabilidad.Jugador;
import jugabilidad.Randomizador;

public class Main extends Application {
	
	VentanaInicial ventanaInicial;
	VentanaDeJuego ventanaDeJuego;
	
	Jugador jugadorUno;
	Jugador jugadorDos;
	
	public static void main(String[] args) {
		launch(args);
	}

	public void start( Stage stage ) throws Exception {
		this.inicializarJugadores();
		this.inicializarVentanas();
		this.cargarMazoAJugadores();
		
		stage.setTitle("Yu-Gi-Oh! The Game");	
		
		Scene gameScene = ventanaDeJuego.createGameScene();
		Scene initialScene = ventanaInicial.createInitialScene(gameScene, stage, ventanaDeJuego);
		
		stage.setScene(initialScene);
		stage.show();
	}
	
	private void cargarMazoAJugadores() {
		Randomizador randomizador = new Randomizador();
		randomizador.cargarMazo(jugadorUno);
		randomizador.cargarMazo(jugadorDos);
	}
	
	private void inicializarJugadores() {
		Randomizador randomizador = new Randomizador();
		randomizador.cargarMazo(jugadorUno);
		randomizador.cargarMazo(jugadorDos);
		
		Vida vidaJugadorUno = new Vida(8000);
		Vida vidaJugadorDos = new Vida(8000);
		
		this.jugadorUno = new Jugador(vidaJugadorUno);
		this.jugadorDos = new Jugador(vidaJugadorDos);
	}
	
	private void inicializarVentanas() {
		ventanaInicial = new VentanaInicial(jugadorUno, jugadorDos);
		ventanaDeJuego = new VentanaDeJuego(jugadorUno, jugadorDos);
	}
}
