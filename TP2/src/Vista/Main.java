package Vista;

import java.nio.file.Paths;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jugabilidad.Controlador;
import jugabilidad.Jugador;
import jugabilidad.Randomizador;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Main extends Application {
	
	VentanaInicial ventanaInicial;
	VentanaDeJuego ventanaDeJuego;
	
	Jugador jugadorUno;
	Jugador jugadorDos;
	
	Controlador controlador;
	MediaPlayer reproductor;
	
	public static void main(String[] args) {
		launch(args);
	}

	public void start( Stage stage ) throws Exception {
		this.inicializarJugadores(); 
		this.inicializarVentanas();
		
		stage.setTitle("Yu-Gi-Oh! The Game");	
		
		Scene gameScene = ventanaDeJuego.createGameScene(stage);
		Scene initialScene = ventanaInicial.createInitialScene(gameScene, stage, ventanaDeJuego);
		
		stage.setScene(initialScene);
		stage.show();
	}
		
	private void inicializarJugadores() {	
		this.controlador = Controlador.obtener();
		this.jugadorUno = controlador.obtenerJugadorUno();
		this.jugadorDos = controlador.obtenerJugadorDos();
		
		Randomizador randomizador = new Randomizador();
		randomizador.cargarMazo(jugadorUno);
		randomizador.cargarMazo(jugadorDos);
		
		Controlador.obtener().repartirCartasIniciales();

	}
	
	private void inicializarVentanas() {
		ventanaInicial = new VentanaInicial(jugadorUno, jugadorDos);
		ventanaDeJuego = new VentanaDeJuego(jugadorUno, jugadorDos);
	}
	
}
