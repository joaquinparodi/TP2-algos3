package Vista;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	VentanaInicial ventanaInicial = new VentanaInicial();
	VentanaDeJuego ventanaDeJuego = new VentanaDeJuego();
	
	public static void main(String[] args) {
		launch(args);
	}

	public void start( Stage stage ) throws Exception {
		ventanaInicial.cargar(stage);
		ventanaDeJuego.cargar(stage);
	}

}
