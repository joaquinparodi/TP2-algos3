package Vista;

import factories.FabricaDeCartas;


import atributos.Vida;
import cartas.Carta;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jugabilidad.Controlador;
import jugabilidad.Jugador;
import jugabilidad.Randomizador;

public class Main extends Application {
	
	VentanaInicial ventanaInicial;
	VentanaDeJuego ventanaDeJuego;
	
	Jugador jugadorUno;
	Jugador jugadorDos;
	
	Controlador controlador;
	
	public static void main(String[] args) {
		launch(args);
	}

	public void start( Stage stage ) throws Exception {
		this.inicializarJugadores();
		this.repartirCartas(); 
		this.inicializarVentanas();
		
		stage.setTitle("Yu-Gi-Oh! The Game");	
		
		Scene gameScene = ventanaDeJuego.createGameScene();
		Scene initialScene = ventanaInicial.createInitialScene(gameScene, stage, ventanaDeJuego);
		
		stage.setScene(initialScene);
		stage.show();
	}
		
	private void inicializarJugadores() {	
		this.controlador = Controlador.obtener();
		this.jugadorUno = controlador.obtenerJugadorUno();
		this.jugadorDos = controlador.obtenerJugadorDos();
		
	//	Randomizador randomizador = new Randomizador();
	//	randomizador.cargarMazo(jugadorUno);
	//	randomizador.cargarMazo(jugadorDos);
	}
	
	private void inicializarVentanas() {
		ventanaInicial = new VentanaInicial(jugadorUno, jugadorDos);
		ventanaDeJuego = new VentanaDeJuego(jugadorUno, jugadorDos);
	}
	
	//Solo de prueba!
	private void repartirCartas() {
		FabricaDeCartas fabrica1 = new FabricaDeCartas(jugadorUno);
		FabricaDeCartas fabrica2 = new FabricaDeCartas(jugadorDos);
		
		Carta fisura1 = fabrica1.crearFisura();
		Carta devNiveles1 = fabrica1.crearDevoradorDeNiveles();
		Carta sogen1 = fabrica1.crearSogen();
		Carta jirai1 = fabrica1.crearJiraiGumo();
		Carta parasito1 = fabrica1.crearParasitoParacida();
		
		jugadorUno.repartirCarta(fisura1);
		jugadorUno.repartirCarta(devNiveles1);
		jugadorUno.repartirCarta(sogen1);
		jugadorUno.repartirCarta(jirai1);
		jugadorUno.repartirCarta(parasito1);
		
		Carta fisura2 = fabrica2.crearFisura();
		Carta devNiveles2 = fabrica2.crearDevoradorDeNiveles();
		Carta wasteland2 = fabrica2.crearWasteland();
		Carta jirai2 = fabrica2.crearJiraiGumo();
		Carta parasito2 = fabrica2.crearParasitoParacida();
		
		jugadorDos.repartirCarta(fisura2);
		jugadorDos.repartirCarta(devNiveles2);
		jugadorDos.repartirCarta(wasteland2);
		jugadorDos.repartirCarta(jirai2);
		jugadorDos.repartirCarta(parasito2);	
	} 
	
}
