package Vista;

import eventos.BotonInicioHandler;
import eventos.EventoActivarBoton;
import eventos.TextFieldNombreHandler;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jugabilidad.Jugador;

public class VentanaInicial {
	
	private static final String backgroundURL = "file:images/background_initial_scene.jpg";
	
	private Scene nextScene;
	private Stage stage;
	private Jugador playerOne;
	private Jugador playerTwo;
	private VentanaDeJuego ventanaDeJuego;
	
	public VentanaInicial(Jugador playerOne, Jugador playerTwo) {
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
	}
	
	public Scene createInitialScene( Scene nextScene, Stage stage, VentanaDeJuego ventanaDeJuego) {
		this.nextScene = nextScene;
		this.stage = stage;
		this.ventanaDeJuego = ventanaDeJuego;
		
		BorderPane rootBorderPane = this.createBorderPane();
		
		double with = 1360; double height = 1280;
		Scene initScene = new Scene(rootBorderPane, with, height, Color.DARKSLATEBLUE);
		return initScene;
	}

	private BorderPane createBorderPane() {
		BorderPane rootBorderPane = new BorderPane();

		Image image = new Image(backgroundURL);
		BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
		rootBorderPane.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
		            				BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bSize)));
		
		GridPane gridPane = this.createCenterGridPane();
		rootBorderPane.setCenter(gridPane);
		return rootBorderPane;
	}
	
	private GridPane createCenterGridPane() {
		GridPane gridPane = new GridPane();
		
		gridPane.setAlignment(Pos.CENTER);
		gridPane.setVgap(10);
		
		Button enterButton = new Button("Comenzar");
		enterButton.setDisable(true);
		TextField namePlayerOne = new TextField();
		TextField namePlayerTwo = new TextField();
		
		namePlayerOne.setPromptText("Nombre jugador uno");
		namePlayerTwo.setPromptText("Nombre jugador dos");
		
		EventoActivarBoton activarBotonHandler = new EventoActivarBoton(enterButton,namePlayerOne,namePlayerTwo);
		
		namePlayerOne.setOnKeyPressed(activarBotonHandler);
		namePlayerTwo.setOnKeyPressed(activarBotonHandler);
		
		TextFieldNombreHandler nameOneHandler = new TextFieldNombreHandler(playerOne);
		nameOneHandler.asignarNombreObtenido(namePlayerOne.getText());
		
		TextFieldNombreHandler nameTwoHandler = new TextFieldNombreHandler(playerTwo);
		nameTwoHandler.asignarNombreObtenido(namePlayerTwo.getText());
		
		BotonInicioHandler buttonHandler = new BotonInicioHandler(nextScene, stage, playerOne, playerTwo, namePlayerOne, namePlayerTwo, ventanaDeJuego);
		enterButton.setOnAction(buttonHandler);
		
		gridPane.add(namePlayerOne, 0, 0);
		gridPane.add(namePlayerTwo, 0, 1);
		gridPane.add(enterButton, 0, 2);

		return gridPane;
	}
	
}
