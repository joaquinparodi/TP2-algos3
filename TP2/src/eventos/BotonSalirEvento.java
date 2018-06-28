package eventos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class BotonSalirEvento implements EventHandler<ActionEvent> {

	private Stage stage;

	public BotonSalirEvento(Stage stage) {
		this.stage = stage;
	}

	@Override
	public void handle(ActionEvent arg0) {
		stage.hide();
	}

}
