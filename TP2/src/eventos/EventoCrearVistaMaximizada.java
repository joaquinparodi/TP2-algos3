package eventos;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EventoCrearVistaMaximizada implements EventHandler<MouseEvent> {
	
	private Paint fill;
	private Stage stageJuego;
	private Stage stage;
	
	public EventoCrearVistaMaximizada(Paint fill,Stage stageJuego) {
		this.fill = fill;
		this.stageJuego = stageJuego;
		this.stage = new Stage(StageStyle.UNDECORATED);
		BorderPane borderPane =  new BorderPane();
		GridPane gridPane = new GridPane();
		gridPane.setBackground(new Background(new BackgroundFill(fill,null,null)));
		borderPane.setCenter(gridPane);
		stage.setScene(new Scene(borderPane, 210,300,Color.AZURE));
		stage.initOwner(stageJuego);
	}

	@Override
	public void handle(MouseEvent event) {
		if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
			stage.show();
		}else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
			stage.hide();
		}else if (event.getEventType() == MouseEvent.MOUSE_MOVED) {
			stage.setY(event.getScreenY()+5); stage.setX(event.getScreenX()+5);
		}
	}

}
