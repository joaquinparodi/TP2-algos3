package Vista;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class VentanaInicial {
	
	private static final String backgroundURL = "/home/facundotorraca/git/TP2-algos3/images/pantalla_de_inicio.jpg";
	
	public void cargar(Stage stage) {
		
		BorderPane rootBorderPane = this.createBorderPane();
		
		double with = 1360; double height = 1280;
		Scene initScene = new Scene(rootBorderPane, with, height);
		
		stage.setScene(initScene);
		stage.show();
	}

	private BorderPane createBorderPane() {
		BorderPane rootBorderPane = new BorderPane();
	
		Button enterButton = new Button("Comenzar");
		
		EventHandler<? super MouseEvent> value;
		enterButton.setOnMouseClicked(value);
		
		CornerRadii cornerProperties = new CornerRadii(0, 15, 15, 0, false);
		BackgroundFill fill = new BackgroundFill(Color.DARKRED, cornerProperties, null);
		Background background =  new Background(fill);
		rootBorderPane.setBackground(background);
		
		return rootBorderPane;
	}
}
