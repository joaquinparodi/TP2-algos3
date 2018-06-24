package Vista;



import eventos.BotonInicioHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class VentanaInicial {
	
	private static final String backgroundURL = "file:images/background_initial_scene.jpg";
	
	private Scene nextScene;
	private Stage stage;
	
	public Scene createInitialScene(Scene nextScene, Stage stage ) {
		this.nextScene = nextScene;
		this.stage = stage;
		
		BorderPane rootBorderPane = this.createBorderPane();
		
		double with = 1360; double height = 1280;
		Scene initScene = new Scene(rootBorderPane, with, height);
		return initScene;
	}

	private BorderPane createBorderPane() {
		BorderPane rootBorderPane = new BorderPane();
	
		Button enterButton = new Button("Comenzar");
		BotonInicioHandler handler = new BotonInicioHandler(nextScene, stage);
		enterButton.setOnAction(handler);
		
		Image image = new Image(backgroundURL);
		BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
		rootBorderPane.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
		            				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bSize)));
		
		rootBorderPane.setCenter(enterButton);
		return rootBorderPane;
	}
}
