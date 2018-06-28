package eventos;

import javafx.scene.input.KeyEvent;

import javafx.event.EventHandler;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;

public class EventoMostrarToolbar implements EventHandler<KeyEvent> {

	private ToolBar toolbar;

	public EventoMostrarToolbar(ToolBar toolbar) {
		this.toolbar = toolbar;
	}

	@Override
	public void handle(KeyEvent event) {
		if (event.getCode() == KeyCode.ALT) {
			toolbar.setManaged(!toolbar.isVisible());
			toolbar.setVisible(!toolbar.isVisible());
		}
	}

}
