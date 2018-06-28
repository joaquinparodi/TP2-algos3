package eventos;

import javafx.event.EventHandler;
import javafx.scene.control.DialogEvent;
import javafx.stage.Window;

public class EventoCerrarJuego implements EventHandler<DialogEvent> {

	private Window stage;

	public EventoCerrarJuego(Window stage) {
		this.stage = stage;
	}

	@Override
	public void handle(DialogEvent arg0) {
		stage.hide();
	}

}
