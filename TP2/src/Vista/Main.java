package Vista;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	VentanaDeJuego ventanaInicial = new VentanaDeJuego();
	
	public static void main(String[] args) {
		launch(args);
	}

	public void start( Stage stage ) throws Exception {
		ventanaInicial.cargar(stage);
	}

}
