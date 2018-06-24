package Vista;

import java.util.ArrayList;
import java.util.Iterator;

import cartas.Carta;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import jugabilidad.Baraja;
import jugabilidad.Jugador;		


public class VentanaDeJuego {
	
	Jugador playerOne;
	Jugador playerTwo;
	
	private Text playerOneName;
	private Text playerTwoName;
	private Text playerOneLife;
	private Text playerTwoLife;
	
	ArrayList<Rectangle> handOne;
	ArrayList<Rectangle> handTwo;
	
	BaseDeDatosDeCartas database;
	
	public VentanaDeJuego(Jugador playerOne, Jugador playerTwo) {
		database = new BaseDeDatosDeCartas();
		handOne = new ArrayList<Rectangle>();
		handTwo = new ArrayList<Rectangle>();
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
	}
	
	public void actualizarNombres() {
		playerOneName.setText(playerOne.obtenerNombre());
		playerTwoName.setText(playerTwo.obtenerNombre());
	}
	
	
	public void actualizarManos() {
		Iterator<Rectangle> iterOne = handOne.iterator();
		Iterator<Rectangle> iterTwo = handTwo.iterator();		
		
		Baraja deckOne = playerOne.obtenerMano();
		Baraja deckTwo = playerTwo.obtenerMano();
		
		Iterator<Carta> iterDeckOne = deckOne.obtenerIteradorDeBaraja();
		Iterator<Carta> iterDeckTwo = deckTwo.obtenerIteradorDeBaraja();
		
		final int maxCardHand = 5;
		int actualCards = 0;
		
		String URL; ImagePattern image;
		while(iterOne.hasNext() && actualCards <= maxCardHand && iterDeckOne.hasNext()) {
			URL = database.getURL(iterDeckOne.next().obtenerNombre());
			image = new ImagePattern( new Image(URL) );
			iterOne.next().setFill(image);
			actualCards++;
		}
		
		actualCards = 0;
		while(iterTwo.hasNext() && actualCards <= maxCardHand && iterDeckTwo.hasNext()) {
			URL = database.getURL(iterDeckTwo.next().obtenerNombre());
			image = new ImagePattern( new Image(URL) );
			iterTwo.next().setFill(image);
			actualCards++;
		}
	} 
	
	public Scene createGameScene() {
	    BorderPane rootBorderPane = this.createBorderPane();
		
	    /* Creacion de la escena */
	    Color backgroundColor = Color.GREEN;
	    double with = 1360; double height = 1280;
	    Scene scene = new Scene(rootBorderPane, with, height, backgroundColor);
	    
	    return scene;
	}
	
	
	private BorderPane createBorderPane() {
		BorderPane rootBorderPane = new BorderPane();
		
		GridPane leftGridPane = this.createLeftGridPane();
		GridPane centerGridPane = this.createCenterGridPane();
		GridPane rightGridPane = this.createRightGridPane();
		
		ToolBar toolbar = new ToolBar();
		
		rootBorderPane.setLeft(leftGridPane);
		rootBorderPane.setCenter(centerGridPane);
		rootBorderPane.setRight(rightGridPane);
		rootBorderPane.setTop(toolbar);
		
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
		
		double lifeOne = 100; double lifeTwo = 100;
		Rectangle P1life = new Rectangle(10, lifeOne, Color.LAWNGREEN); //Hay que cambiar el tamanio para la vida
		Rectangle P2life = new Rectangle(10, lifeTwo, Color.LAWNGREEN); //Hay que cambiar el tamanio para la vida
		
		square1.setArcWidth(15); square1.setArcHeight(15);
		square2.setArcWidth(15); square2.setArcHeight(15);
		
		playerOneName = new Text(playerOne.obtenerNombre()); 
		playerTwoName = new Text(playerOne.obtenerNombre()); 
		playerOneLife = new Text("8000");
		playerTwoLife = new Text("8000");
		
		gridPane.setHgap(10);
		gridPane.setVgap(9);
		
		gridPane.setMargin(square1, new Insets(10)); 
		gridPane.setMargin(square2, new Insets(10)); 
		
		gridPane.setMargin(P1Deck, new Insets(10,10,10,30));
		gridPane.setMargin(P2Deck, new Insets(10,10,10,30));
		
		gridPane.setMargin(playerOneName, new Insets(0,0,0,10));
		gridPane.setMargin(playerTwoName, new Insets(0,0,0,10));
		gridPane.setMargin(playerOneLife, new Insets(0,0,0,-30));
		gridPane.setMargin(playerTwoLife, new Insets(0,0,0,-30));
		
		gridPane.setMargin(P1life, new Insets(0,0,0,-10));
		gridPane.setMargin(P2life, new Insets(0,0,0,-10));
		
		gridPane.add(square1, 0, 0);
		gridPane.add(playerOneName, 0, 1);
		gridPane.add(playerOneLife, 1, 1);
		gridPane.add(P1Deck, 0, 5);
		gridPane.add(P1life, 1, 0);
		
		gridPane.add(P2Deck, 0, 15);
		gridPane.add(playerTwoLife, 1, 19);
		gridPane.add(playerTwoName, 0, 19);
		gridPane.add(square2, 0, 20);
		gridPane.add(P2life, 1, 20);
		
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
		
		//gridPane.setGridLinesVisible(true); //->para prueba
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
		gridPane.setVgap(14);
		
		double with = 70; double height = 100;
		
		Rectangle P1card1 = new Rectangle(with, height, Color.DARKCYAN); 
		Rectangle P1card2 = new Rectangle(with, height, Color.DARKCYAN); 
		Rectangle P1card3 = new Rectangle(with, height, Color.DARKCYAN); 
		Rectangle P1card4 = new Rectangle(with, height, Color.DARKCYAN); 
		Rectangle P1card5 = new Rectangle(with, height, Color.DARKCYAN); 
		Rectangle P1mount = new Rectangle(with, height, Color.DARKCYAN); 
		
		Rectangle P2card1 = new Rectangle(with, height, Color.DARKCYAN); 
		Rectangle P2card2 = new Rectangle(with, height, Color.DARKCYAN); 
		Rectangle P2card3 = new Rectangle(with, height, Color.DARKCYAN); 
		Rectangle P2card4 = new Rectangle(with, height, Color.DARKCYAN); 
		Rectangle P2card5 = new Rectangle(with, height, Color.DARKCYAN); 
		Rectangle P2mount = new Rectangle(with, height, Color.DARKCYAN); 
		
		handOne.add(P1card1); handTwo.add(P2card1); 
		handOne.add(P1card2); handTwo.add(P2card2); 
		handOne.add(P1card3); handTwo.add(P2card3); 
		handOne.add(P1card4); handTwo.add(P2card4); 
		handOne.add(P1card5); handTwo.add(P2card5); 

		gridPane.setMargin(P1card1, new Insets(10)); gridPane.setMargin(P2card1, new Insets(10)); 
		gridPane.setMargin(P1card2, new Insets(10)); gridPane.setMargin(P2card2, new Insets(10)); 
		gridPane.setMargin(P1card3, new Insets(10)); gridPane.setMargin(P2card3, new Insets(10)); 
		gridPane.setMargin(P1card4, new Insets(10)); gridPane.setMargin(P2card4, new Insets(10)); 
		gridPane.setMargin(P1card5, new Insets(10)); gridPane.setMargin(P2card5, new Insets(10)); 
		
		gridPane.setMargin(P1mount, new Insets(10,10,10,25)); 
		gridPane.setMargin(P2mount, new Insets(10,10,10,25)); 
		
		gridPane.add(P2card1, 1, 34); gridPane.add(P1card1, 1, 0); 
		gridPane.add(P2card2, 2, 34); gridPane.add(P1card2, 2, 0);
		gridPane.add(P2card3, 3, 34); gridPane.add(P1card3, 3, 0);
		gridPane.add(P2card4, 4, 34); gridPane.add(P1card4, 4, 0);
		gridPane.add(P2card5, 5, 34); gridPane.add(P1card5, 5, 0);
		gridPane.add(P2mount, 6, 34); gridPane.add(P1mount, 6, 0);
		
		//gridPane.setGridLinesVisible(true); //->para prueba
		
		return gridPane;
	}
	
}
