package Vista;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	VentanaInicial ventanaInicial = new VentanaInicial();
	VentanaDeJuego ventanaDeJuego = new VentanaDeJuego();
	
	public static void main(String[] args) {
		launch(args);
	}

	public void start( Stage stage ) throws Exception {
		stage.setTitle("Yu-Gi-Oh! The Game");
		
		Scene gameScene = ventanaDeJuego.createGameScene();
		Scene initiScene = ventanaInicial.createInitialScene(gameScene, stage);
		stage.setScene(initiScene);
		stage.show();
	}

}
