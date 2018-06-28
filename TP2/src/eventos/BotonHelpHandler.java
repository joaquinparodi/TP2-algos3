package eventos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.Window;

public class BotonHelpHandler implements EventHandler<ActionEvent> {

	private Stage stage;

	public BotonHelpHandler(Stage stage) {
		this.stage = stage;
	}

	@Override
	public void handle(ActionEvent arg0) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.initOwner(stage);
		alert.setHeaderText(null);
		alert.setTitle("About");
		alert.setContentText("Desarrollado por: \n Torraca, Facundo \n Ferreyra, Javier \n Parodi, Joaquin \n Bordenave, Auriane");
		alert.showAndWait();
	}

}
