package eventos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BotonInicioHandler implements EventHandler<ActionEvent>  {

	private Scene nextScene;
	private Stage stage;
	
	public BotonInicioHandler(Scene nextScene, Stage stage) {
		this.nextScene = nextScene;
		this.stage = stage;
	}
	
	public void handle(ActionEvent event) {
        stage.setScene(nextScene);
        //stage.setFullScreen(true);
	}
	
}
