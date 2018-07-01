package eventos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToolBar;

public class EventoMostrarToolbar implements EventHandler<ActionEvent> {

	private ToolBar toolbar;

	public EventoMostrarToolbar(ToolBar toolbar) {
		this.toolbar = toolbar;
	}

	@Override
	public void handle(ActionEvent event) {
		toolbar.setManaged(!toolbar.isVisible());
		toolbar.setVisible(!toolbar.isVisible());
	}

}
