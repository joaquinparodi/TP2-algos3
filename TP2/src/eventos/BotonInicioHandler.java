package eventos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jugabilidad.Jugador;

public class BotonInicioHandler implements EventHandler<ActionEvent>  {

	private Scene nextScene;
	private Stage stage;
	private Jugador jugador1;
	private Jugador jugador2;
	private TextField nombreJugador1;
	private TextField nombreJugador2;
	
	public BotonInicioHandler(Scene nextScene, Stage stage, Jugador jugador1, Jugador jugador2, TextField nombreJugador1, TextField nombreJugador2) {
		this.nextScene = nextScene;
		this.stage = stage;
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
		this.nombreJugador1 = nombreJugador1;
		this.nombreJugador2 = nombreJugador2;
	}
	
	public void handle(ActionEvent event) {
		jugador1.asignarNombre(nombreJugador1.getText());
		jugador2.asignarNombre(nombreJugador2.getText());
        stage.setScene(nextScene);
        stage.setFullScreen(true);
	}
	
}