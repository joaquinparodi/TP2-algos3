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
		
		agregarMusicaAlJuego();

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
		
		for(int i = 0; i < 5; i++) {
			jugadorUno.repartirCarta();
			jugadorDos.repartirCarta();
		}

	}
	
	private void inicializarVentanas() {
		ventanaInicial = new VentanaInicial(jugadorUno, jugadorDos);
		ventanaDeJuego = new VentanaDeJuego(jugadorUno, jugadorDos);
	}
	
	private void agregarMusicaAlJuego() {
		    String cancion = "cancion.mp3";
		    Media media = new Media(Paths.get(cancion).toUri().toString());
		    reproductor = new MediaPlayer(media);
		    reproductor.setCycleCount(MediaPlayer.INDEFINITE);
		    reproductor.play();
		}	
}
