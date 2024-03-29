package Vista;

import eventos.BotonInicioHandler;
import eventos.BotonSalirEvento;
import eventos.ActivarBotonHandler;
import eventos.TextFieldNombreHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jugabilidad.Jugador;

public class VentanaInicial {
	
	private static final String backgroundURL = "file:images/background_initial_scene_2.png";
	
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
		
		ResultadoDeAtaque.obtener().setStage(stage);		

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
		
		gridPane.setAlignment(Pos.CENTER_RIGHT);
		gridPane.setVgap(20);
		gridPane.setHgap(10);
//		gridPane.setPadding(new Insets(350,10,10,550));
		
		Button enterButton = new Button("Comenzar");
		Button exitButton = new Button("Salir");

		enterButton.setDisable(true);
		Text helpText1 = new Text("Ingrese un nombre");
		helpText1.setFill(Color.WHITE);
		Text helpText2 = new Text("Ingrese un nombre");
		helpText2.setFill(Color.WHITE);
		TextField namePlayerOne = new TextField();
		TextField namePlayerTwo = new TextField();
		
		helpText1.setWrappingWidth(480);
		helpText2.setWrappingWidth(480);
		
		namePlayerOne.setPromptText("Nombre jugador uno");
		namePlayerTwo.setPromptText("Nombre jugador dos");
		
		
		ActivarBotonHandler activarBotonHandler = new ActivarBotonHandler(enterButton,namePlayerOne,namePlayerTwo,helpText1,helpText2);
		
		namePlayerOne.setOnKeyPressed(activarBotonHandler);
		namePlayerTwo.setOnKeyPressed(activarBotonHandler);
		namePlayerOne.setOnKeyTyped(activarBotonHandler);
		namePlayerTwo.setOnKeyTyped(activarBotonHandler);
		
		TextFieldNombreHandler nameOneHandler = new TextFieldNombreHandler(playerOne);
		nameOneHandler.asignarNombreObtenido(namePlayerOne.getText());
		
		TextFieldNombreHandler nameTwoHandler = new TextFieldNombreHandler(playerTwo);
		nameTwoHandler.asignarNombreObtenido(namePlayerTwo.getText());
		
		BotonInicioHandler buttonHandler = new BotonInicioHandler(nextScene, stage, playerOne, playerTwo, namePlayerOne, namePlayerTwo, ventanaDeJuego);
		enterButton.setOnAction(buttonHandler);
		BotonSalirEvento botonSalirHandler = new BotonSalirEvento(stage);
		exitButton.setOnAction(botonSalirHandler);
		
		Button botonDummy = new Button();
		
		gridPane.setMargin(helpText1,new Insets(0,0,0,20));
		gridPane.setMargin(helpText2,new Insets(0,0,0,20));

		
		gridPane.add(botonDummy, 10, 0);
		gridPane.add(namePlayerOne, 0, 0);
		gridPane.add(namePlayerTwo, 0, 1);
		gridPane.add(helpText1, 1, 0);
		gridPane.add(helpText2, 1, 1);
		gridPane.add(enterButton, 0, 2);
		gridPane.add(exitButton, 0, 3);
		botonDummy.requestFocus();
		botonDummy.setOpacity(0);
				
		return gridPane;
	}
	
}
