package Vista;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class VentanaDeJuego {
	
	public void cargar(Stage stage) {
		stage.setTitle("Yu-Gi-Oh! The Game");
	    BorderPane rootBorderPane = this.createBorderPane();
		
	    /* Creacion de la escena */
	    Color backgroundColor = Color.GREEN;
	    double with = 800; double height = 600;
	    Scene scene = new Scene(rootBorderPane, with, height, backgroundColor);
	    
	    /*Agrego la escena a la pantalla*/
	    stage.setScene(scene);
	    
		stage.show();
	}
	
	private BorderPane createBorderPane() {
		BorderPane rootBorderPane = new BorderPane();
		
		GridPane leftGridPane = this.createLeftGridPane();
		GridPane centerGridPane = this.createCenterGridPane();
		GridPane rightGridPane = this.createRightGridPane();
		
		rootBorderPane.setLeft(leftGridPane);
		rootBorderPane.setCenter(centerGridPane);
		rootBorderPane.setRight(rightGridPane);
		
		return rootBorderPane;
	}
	
	private GridPane createLeftGridPane() {		
		GridPane gridPane = new GridPane();
		//gridPane.setPrefWidth(200);
		
		CornerRadii cornerProperties = new CornerRadii(0, 15, 15, 0, false);
		BackgroundFill fill = new BackgroundFill(Color.DARKRED, cornerProperties, null);
		Background background =  new Background(fill);
		gridPane.setBackground(background);
		
		Rectangle square1 = new Rectangle(100, 100, Color.DARKORANGE); //Van a ser las fotos
		Rectangle square2 = new Rectangle(100, 100, Color.DARKORANGE); //Van a ser las fotos
		
		Rectangle P1Deck = new Rectangle(70, 100, Color.PERU);
		Rectangle P2Deck = new Rectangle(70, 100, Color.PERU);
		
		square1.setArcWidth(15); square1.setArcHeight(15);
		square2.setArcWidth(15); square2.setArcHeight(15);
		
		Text playerOne = new Text("PlayerOne"); //Hay que modificarlos
		Text playerTwo = new Text("PlayerTwo"); //Hay que modificarlos
		
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		
		gridPane.setMargin(square1, new Insets(10)); gridPane.setMargin(P1Deck, new Insets(10));
		gridPane.setMargin(square2, new Insets(10)); gridPane.setMargin(P2Deck, new Insets(10));
		gridPane.add(square1, 0, 0);
		gridPane.add(playerOne, 0, 1);
		gridPane.add(P1Deck, 0, 5);
		
		
		gridPane.add(P2Deck, 0, 15);
		gridPane.add(playerTwo, 0, 19);
		gridPane.add(square2, 0, 20);
		
		//gridPane.setGridLinesVisible(true); //->Solo para guiarse,despues lo sacamos
		
		return gridPane;
	}
	
	private GridPane createCenterGridPane() {		
		GridPane gridPane = new GridPane();
		gridPane.setPrefWidth(600);
		
		CornerRadii cornerProperties = new CornerRadii(0, 15, 15, 0, false);
		BackgroundFill fill = new BackgroundFill(Color.DARKGREEN, cornerProperties, null);
		Background background =  new Background(fill);
		gridPane.setBackground(background);
		
		gridPane.setHgap(0);
		gridPane.setVgap(0);
		double with = 70; double height = 100;
		
		/*Campo de juego jugadorUno */
		Rectangle P1STzone1 = new Rectangle(with, height, Color.DARKCYAN); 
		Rectangle P1STzone2 = new Rectangle(with, height, Color.DARKCYAN); 
		Rectangle P1STzone3 = new Rectangle(with, height, Color.DARKCYAN); 
		Rectangle P1STzone4 = new Rectangle(with, height, Color.DARKCYAN); 
		Rectangle P1STzone5 = new Rectangle(with, height, Color.DARKCYAN); 
		
		Rectangle P1Mzone1 = new Rectangle(with, height, Color.GOLDENROD); 
		Rectangle P1Mzone2 = new Rectangle(with, height, Color.GOLDENROD); 
		Rectangle P1Mzone3 = new Rectangle(with, height, Color.GOLDENROD); 
		Rectangle P1Mzone4 = new Rectangle(with, height, Color.GOLDENROD); 
		Rectangle P1Mzone5 = new Rectangle(with, height, Color.GOLDENROD); 
		
		Rectangle P1cemetery = new Rectangle(with, height, Color.DIMGRAY); 
		Rectangle P1Lzone = new Rectangle(with, height, Color.DARKVIOLET);
		
		gridPane.setMargin(P1STzone1, new Insets(10)); gridPane.setMargin(P1Mzone1, new Insets(10)); 
		gridPane.setMargin(P1STzone2, new Insets(10)); gridPane.setMargin(P1Mzone2, new Insets(10)); 
		gridPane.setMargin(P1STzone3, new Insets(10)); gridPane.setMargin(P1Mzone3, new Insets(10)); 
		gridPane.setMargin(P1STzone4, new Insets(10)); gridPane.setMargin(P1Mzone4, new Insets(10)); 
		gridPane.setMargin(P1STzone5, new Insets(10)); gridPane.setMargin(P1Mzone5, new Insets(10)); 
		
		gridPane.setMargin(P1cemetery, new Insets(10)); gridPane.setMargin(P1Lzone, new Insets(10));
		
		gridPane.add(P1STzone1, 1, 1); gridPane.add(P1Mzone1, 1, 2);
		gridPane.add(P1STzone2, 2, 1); gridPane.add(P1Mzone2, 2, 2);
		gridPane.add(P1STzone3, 3, 1); gridPane.add(P1Mzone3, 3, 2);
		gridPane.add(P1STzone4, 4, 1); gridPane.add(P1Mzone4, 4, 2);
		gridPane.add(P1STzone5, 5, 1); gridPane.add(P1Mzone5, 5, 2);
		
		gridPane.add(P1cemetery, 0, 3); gridPane.add(P1Lzone, 6, 3);
			
		/*Campo de juego jugadorDos*/
		Rectangle P2STzone1 = new Rectangle(with, height, Color.DARKCYAN); 
		Rectangle P2STzone2 = new Rectangle(with, height, Color.DARKCYAN); 
		Rectangle P2STzone3 = new Rectangle(with, height, Color.DARKCYAN); 
		Rectangle P2STzone4 = new Rectangle(with, height, Color.DARKCYAN); 
		Rectangle P2STzone5 = new Rectangle(with, height, Color.DARKCYAN); 
		
		Rectangle P2Mzone1 = new Rectangle(with, height, Color.GOLDENROD); 
		Rectangle P2Mzone2 = new Rectangle(with, height, Color.GOLDENROD); 
		Rectangle P2Mzone3 = new Rectangle(with, height, Color.GOLDENROD); 
		Rectangle P2Mzone4 = new Rectangle(with, height, Color.GOLDENROD); 
		Rectangle P2Mzone5 = new Rectangle(with, height, Color.GOLDENROD); 
		
		Rectangle P2cemetery = new Rectangle(with, height, Color.DIMGRAY);
		Rectangle P2Lzone = new Rectangle(with, height, Color.DARKVIOLET);
		
		gridPane.setMargin(P2STzone1, new Insets(10)); gridPane.setMargin(P2Mzone1, new Insets(10)); 
		gridPane.setMargin(P2STzone2, new Insets(10)); gridPane.setMargin(P2Mzone2, new Insets(10)); 
		gridPane.setMargin(P2STzone3, new Insets(10)); gridPane.setMargin(P2Mzone3, new Insets(10)); 
		gridPane.setMargin(P2STzone4, new Insets(10)); gridPane.setMargin(P2Mzone4, new Insets(10)); 
		gridPane.setMargin(P2STzone5, new Insets(10)); gridPane.setMargin(P2Mzone5, new Insets(10)); 
		
		gridPane.setMargin(P2cemetery, new Insets(10)); gridPane.setMargin(P2Lzone, new Insets(10));
		
		gridPane.add(P2STzone1, 1, 6); gridPane.add(P2Mzone1, 1, 5);
		gridPane.add(P2STzone2, 2, 6); gridPane.add(P2Mzone2, 2, 5);
		gridPane.add(P2STzone3, 3, 6); gridPane.add(P2Mzone3, 3, 5);
		gridPane.add(P2STzone4, 4, 6); gridPane.add(P2Mzone4, 4, 5);
		gridPane.add(P2STzone5, 5, 6); gridPane.add(P2Mzone5, 5, 5);
		
		gridPane.add(P2Lzone, 0, 4); gridPane.add(P2cemetery, 6, 4);
		
		gridPane.setGridLinesVisible(true);
		return gridPane;
	}
	
	private GridPane createRightGridPane() {
		GridPane gridPane = new GridPane();
		gridPane.setPrefWidth(600);
		
		CornerRadii cornerProperties = new CornerRadii(15, 0, 0, 15, false);
		BackgroundFill fill = new BackgroundFill(Color.DARKRED, cornerProperties, null);
		Background background =  new Background(fill);
		gridPane.setBackground(background);
		
		gridPane.setHgap(0);
		gridPane.setVgap(0);
		
		
		
		return gridPane;
	}
	
}
