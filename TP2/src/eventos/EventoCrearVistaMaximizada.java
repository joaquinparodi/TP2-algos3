package eventos;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
	
	private Stage stage;
	private double offsetX;
	private double offsetY;
	private ImageView image;
	private Stage stageJuego;
	private Paint fill;
	
	public EventoCrearVistaMaximizada(Paint fill,Stage stageJuego,double offsetX,double offsetY) {
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.stageJuego = stageJuego;
		this.fill = fill;
		
	}

	@Override
	public void handle(MouseEvent event) {
		if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
			this.stage = new Stage(StageStyle.UNDECORATED);
			stage.initOwner(stageJuego);
			Group grupo = new Group();
			Rectangle rectangulo = new Rectangle(210,300);
			rectangulo.setFill(fill);
//			GridPane gridPane = new GridPane();
//			gridPane.setBackground(new Background(new BackgroundFill(fill,null,null)));
			grupo.getChildren().add(rectangulo);
			stage.setScene(new Scene(grupo));
	
			stage.show();
		}else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
			stage.close();
		}else if (event.getEventType() == MouseEvent.MOUSE_MOVED) {
			stage.setY(event.getScreenY()+offsetY); stage.setX(event.getScreenX()+offsetX);
		}
	}

}
