package Vista;

import java.util.ArrayList;
import java.util.Iterator;

import cartas.Carta;
import eventos.CartasManoHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
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
import jugabilidad.CampoDeJuego;
import jugabilidad.Jugador;		


public class VentanaDeJuego {
	
	Jugador playerOne;
	Jugador playerTwo;
	CampoDeJuego campoDeJuegoUno;
	CampoDeJuego campoDeJuegoDos;
	Baraja manoJugadorUno;
	Baraja manoJugadorDos;
	
	private Text playerOneName;
	private Text playerTwoName;
	private Text playerOneLife;
	private Text playerTwoLife;
	
	ArrayList<Rectangle> handOne;
	ArrayList<Rectangle> handTwo;
	
	ArrayList<Rectangle> P1STZone;
	ArrayList<Rectangle> P2STZone;
	
	ArrayList<Rectangle> P1MZone;
	ArrayList<Rectangle> P2MZone;
	
	BaseDeDatosDeCartas database;
	private Rectangle P1mount;
	private Rectangle P2mount;
	private GridPane gridScrollPlayer1;
	private GridPane gridScrollPlayer2;
	private ScrollPane scrollPane1;
	private ScrollPane scrollPane2;
	private ScrollPane scrollPlayer1;
	private ScrollPane scrollPlayer2;
	
	public VentanaDeJuego(Jugador playerOne, Jugador playerTwo) {
		database = new BaseDeDatosDeCartas();
		handOne = new ArrayList<Rectangle>();
		handTwo = new ArrayList<Rectangle>();
		P1STZone = new ArrayList<Rectangle>();
		P2STZone = new ArrayList<Rectangle>();
		P1MZone = new ArrayList<Rectangle>();
		P2MZone = new ArrayList<Rectangle>();
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		this.campoDeJuegoUno = playerOne.obtenerCampo();
		this.campoDeJuegoDos = playerTwo.obtenerCampo();
		this.manoJugadorUno = playerOne.obtenerMano();
		this.manoJugadorDos = playerTwo.obtenerMano();
	}
	
	public void actualizarNombres() {
		playerOneName.setText(playerOne.obtenerNombre());
		playerTwoName.setText(playerTwo.obtenerNombre());
	}
	
	public void actualizarCampoDeJuego() {
		this.restaurarPosiciones();
		this.actualizarFilaMonstruosJugadorUno();
		this.actualizarFilaMonstruosJugadorDos();
		this.actualizarFilaMagicasYTrampasJugadorUno();
		this.actualizarFilaMagicasYTrampasJugadorDos();
		this.actualizarManos();
	}
	
	public void actualizarManos() {
		
		handOne.clear();
		handTwo.clear();
		
		this.gridScrollPlayer1 = new GridPane();
		this.gridScrollPlayer2 = new GridPane();
		
		BackgroundFill fill = new BackgroundFill(Color.DARKRED, null, null);
		Background background =  new Background(fill);
		gridScrollPlayer1.setBackground(background);
		gridScrollPlayer2.setBackground(background);
		gridScrollPlayer1.setPrefWidth(480);gridScrollPlayer1.setPrefHeight(180);
		gridScrollPlayer2.setPrefWidth(480);gridScrollPlayer2.setPrefHeight(180);
		scrollPlayer1.setContent(gridScrollPlayer1);
		scrollPlayer2.setContent(gridScrollPlayer2);
		
		scrollPlayer1.setMaxWidth(480);scrollPlayer1.setMinWidth(480);
		scrollPlayer2.setMaxWidth(480);scrollPlayer2.setMinWidth(480);
		scrollPlayer1.setMaxHeight(200);scrollPlayer1.setMinHeight(150);
		scrollPlayer2.setMaxHeight(200);scrollPlayer2.setMinHeight(150);

		Iterator<Carta> iterDeckOne = manoJugadorUno.obtenerIteradorDeBaraja();
		Iterator<Carta> iterDeckTwo = manoJugadorDos.obtenerIteradorDeBaraja();
		
		final int maxCardHand = 5;
		int actualCards = 0;
		
		String URL; ImagePattern image;
//		while(iterDeckOne.hasNext()) {
//			URL = database.getURL(iterDeckOne.next().obtenerNombre());
//			image = new ImagePattern( new Image(URL) );
//			iterOne.next().setFill(image);
//		}

		actualCards = 0;
		while(iterDeckOne.hasNext()) {
			URL = database.getURL(iterDeckOne.next().obtenerNombre());
			Rectangle rectangulo = new Rectangle(70, 100, Color.DARKCYAN); 
			this.gridScrollPlayer1.add(rectangulo, actualCards, 0);
			this.gridScrollPlayer1.setMargin(rectangulo, new Insets(30,30,30,30));
			handOne.add(rectangulo);
			image = new ImagePattern( new Image(URL) );
			rectangulo.setFill(image);
			actualCards++;
		} 
		
		
		
		actualCards = 0;
		while(iterDeckTwo.hasNext()) {
			URL = database.getURL(iterDeckTwo.next().obtenerNombre());
			Rectangle rectangulo = new Rectangle(70, 100, Color.DARKCYAN); 
			this.gridScrollPlayer2.add(rectangulo, actualCards, 0);
			this.gridScrollPlayer2.setMargin(rectangulo, new Insets(30,30,30,30));
			handTwo.add(rectangulo);
			image = new ImagePattern( new Image(URL) );
			rectangulo.setFill(image);
			actualCards++;
		} 

		scrollPlayer1.setBackground(background);
		scrollPlayer2.setBackground(background);

		setActionToHand();
		//Repartir mas cartas no solo 5
	} 
	
	private void actualizarFilaMonstruosJugadorUno() {
		Baraja filaMonstruos = campoDeJuegoUno.obtenerFilaDeMonstruos();
		Iterator<Rectangle> iterZone = P1MZone.iterator();		
		Iterator<Carta> iterFila = filaMonstruos.obtenerIteradorDeBaraja();

		final int maxCardHand = 5;
		int actualCards = 0;
		
		String URL; ImagePattern image;
		while(iterZone.hasNext() && iterFila.hasNext()) {
			URL = database.getURL(iterFila.next().obtenerNombre());
			image = new ImagePattern( new Image(URL) );
			iterZone.next().setFill(image);
			actualCards++;
		}
	}
	
	private void actualizarFilaMonstruosJugadorDos() {
		Baraja filaMonstruos = campoDeJuegoDos.obtenerFilaDeMonstruos();
		Iterator<Rectangle> iterZone = P2MZone.iterator();		
		Iterator<Carta> iterFila = filaMonstruos.obtenerIteradorDeBaraja();

		final int maxCardHand = 5;
		int actualCards = 0;
		
		String URL; ImagePattern image;
		while(iterZone.hasNext() && iterFila.hasNext()) {
			URL = database.getURL(iterFila.next().obtenerNombre());
			image = new ImagePattern( new Image(URL) );
			iterZone.next().setFill(image);
			actualCards++;
		}
	}

	private void actualizarFilaMagicasYTrampasJugadorUno() {
		Baraja filaMagicasYTrampas = campoDeJuegoUno.obtenerFilaDeMagicasYTrampas();
		Iterator<Rectangle> iterZone = P1STZone.iterator();		
		Iterator<Carta> iterFila = filaMagicasYTrampas.obtenerIteradorDeBaraja();

		final int maxCardHand = 5;		
		int actualCards = 0;
		
		String URL; ImagePattern image;
		while(iterZone.hasNext() && iterFila.hasNext()) {
			URL = database.getURL(iterFila.next().obtenerNombre());
			image = new ImagePattern( new Image(URL) );
			iterZone.next().setFill(image);
			actualCards++;
		}
	}
	
	private void actualizarFilaMagicasYTrampasJugadorDos() {
		Baraja filaMagicasYTrampas = campoDeJuegoDos.obtenerFilaDeMagicasYTrampas();
		Iterator<Rectangle> iterZone = P2STZone.iterator();		
		Iterator<Carta> iterFila = filaMagicasYTrampas.obtenerIteradorDeBaraja();

		final int maxCardHand = 5;
		int actualCards = 0;
		
		String URL; ImagePattern image;
		while(iterZone.hasNext() && iterFila.hasNext()) {
			URL = database.getURL(iterFila.next().obtenerNombre());
			image = new ImagePattern( new Image(URL) );
			iterZone.next().setFill(image);
			actualCards++;
		}
	}
	
	private void restaurarPosiciones() {
		Iterator<Rectangle> iterMZoneOne = P1MZone.iterator();	
		Iterator<Rectangle> iterMZoneTwo = P2MZone.iterator();
		Iterator<Rectangle> iterSTZoneOne = P1STZone.iterator();	
		Iterator<Rectangle> iterSTZoneTwo = P2STZone.iterator();
		Iterator<Rectangle> iterHandOne = handOne.iterator();
		Iterator<Rectangle> iterHandTwo = handTwo.iterator();

		while(iterMZoneOne.hasNext()) {
			iterMZoneOne.next().setFill(Color.GOLDENROD);
		}
		
		while(iterMZoneTwo.hasNext()) {
			iterMZoneTwo.next().setFill(Color.GOLDENROD);
		}
		
		while(iterSTZoneOne.hasNext()) {
			iterSTZoneOne.next().setFill(Color.DARKCYAN);
		}
		
		while(iterSTZoneTwo.hasNext()) {
			iterSTZoneTwo.next().setFill(Color.DARKCYAN);
		}
		
		while(iterHandOne.hasNext()) {
			iterHandOne.next().setFill(Color.DARKCYAN);
		}
		
		while(iterHandTwo.hasNext()) {
			iterHandTwo.next().setFill(Color.DARKCYAN);
		}
		
	}
	
	/*----------------------------------Creacion de la vista inicial--------------------------------------*/
	
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
		
		//CornerRadii cornerProperties = new CornerRadii(0, 15, 15, 0, false);
		BackgroundFill fill = new BackgroundFill(Color.DARKRED, null, null);
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
		
		gridPane.setMargin(square1, new Insets(10,10,10,10)); 
		gridPane.setMargin(square2, new Insets(10,10,10,10)); 
		
		gridPane.setMargin(P1Deck, new Insets(10,10,10,30));
		gridPane.setMargin(P2Deck, new Insets(40,10,10,30));
		
		gridPane.setMargin(playerOneName, new Insets(0,0,0,10));
		gridPane.setMargin(playerTwoName, new Insets(40,0,0,10));
		gridPane.setMargin(playerOneLife, new Insets(0,0,0,-30));
		gridPane.setMargin(playerTwoLife, new Insets(40,0,0,-20));
		
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
		
		//CornerRadii cornerProperties = new CornerRadii(0, 15, 15, 0, false);
		BackgroundFill fill = new BackgroundFill(Color.DARKGREEN, null, null);
		Background background =  new Background(fill);
		gridPane.setBackground(background);
		
		gridPane.setHgap(5);
		gridPane.setVgap(0);
		double with = 70; double height = 100;
		
		/*---------------------------Campo de juego jugadorUno-------------------------------------*/
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
		
		gridPane.setMargin(P1STzone1, new Insets(15,20,10,20)); gridPane.setMargin(P1Mzone1, new Insets(20)); 
		gridPane.setMargin(P1STzone2, new Insets(15,20,10,20)); gridPane.setMargin(P1Mzone2, new Insets(20)); 
		gridPane.setMargin(P1STzone3, new Insets(15,20,10,20)); gridPane.setMargin(P1Mzone3, new Insets(20)); 
		gridPane.setMargin(P1STzone4, new Insets(15,20,10,20)); gridPane.setMargin(P1Mzone4, new Insets(20)); 
		gridPane.setMargin(P1STzone5, new Insets(15,20,10,20)); gridPane.setMargin(P1Mzone5, new Insets(20)); 
		
		gridPane.setMargin(P1cemetery, new Insets(10)); gridPane.setMargin(P1Lzone, new Insets(10));
		
		gridPane.add(P1STzone1, 1, 1); gridPane.add(P1Mzone1, 1, 2);
		gridPane.add(P1STzone2, 2, 1); gridPane.add(P1Mzone2, 2, 2);
		gridPane.add(P1STzone3, 3, 1); gridPane.add(P1Mzone3, 3, 2);
		gridPane.add(P1STzone4, 4, 1); gridPane.add(P1Mzone4, 4, 2);
		gridPane.add(P1STzone5, 5, 1); gridPane.add(P1Mzone5, 5, 2);
		
		gridPane.add(P1cemetery, 0, 3); gridPane.add(P1Lzone, 6, 3);
			
		P1STZone.add(P1STzone1); P1MZone.add(P1Mzone1);
		P1STZone.add(P1STzone2); P1MZone.add(P1Mzone2);
		P1STZone.add(P1STzone3); P1MZone.add(P1Mzone3);
		P1STZone.add(P1STzone4); P1MZone.add(P1Mzone4);
		P1STZone.add(P1STzone5); P1MZone.add(P1Mzone5);
		
		/*---------------------------Campo de juego jugadorDos-------------------------------------*/
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
		
		GridPane.setMargin(P2STzone1, new Insets(0,20,10,20)); GridPane.setMargin(P2Mzone1, new Insets(20)); 
		GridPane.setMargin(P2STzone2, new Insets(0,20,10,20)); GridPane.setMargin(P2Mzone2, new Insets(20)); 
		GridPane.setMargin(P2STzone3, new Insets(0,20,10,20)); GridPane.setMargin(P2Mzone3, new Insets(20)); 
		GridPane.setMargin(P2STzone4, new Insets(0,20,10,20)); GridPane.setMargin(P2Mzone4, new Insets(20)); 
		GridPane.setMargin(P2STzone5, new Insets(0,20,10,20)); GridPane.setMargin(P2Mzone5, new Insets(20)); 
		
		GridPane.setMargin(P2cemetery, new Insets(10)); GridPane.setMargin(P2Lzone, new Insets(10));
		
		gridPane.add(P2STzone1, 1, 6); gridPane.add(P2Mzone1, 1, 5);
		gridPane.add(P2STzone2, 2, 6); gridPane.add(P2Mzone2, 2, 5);
		gridPane.add(P2STzone3, 3, 6); gridPane.add(P2Mzone3, 3, 5);
		gridPane.add(P2STzone4, 4, 6); gridPane.add(P2Mzone4, 4, 5);
		gridPane.add(P2STzone5, 5, 6); gridPane.add(P2Mzone5, 5, 5);
		
		gridPane.add(P2Lzone, 0, 4); gridPane.add(P2cemetery, 6, 4);
		
		P2STZone.add(P2STzone1); P2MZone.add(P2Mzone1);
		P2STZone.add(P2STzone2); P2MZone.add(P2Mzone2);
		P2STZone.add(P2STzone3); P2MZone.add(P2Mzone3);
		P2STZone.add(P2STzone4); P2MZone.add(P2Mzone4);
		P2STZone.add(P2STzone5); P2MZone.add(P2Mzone5);
		
		return gridPane;
	}
	
	
	private GridPane createRightGridPane() {
		GridPane gridPane = new GridPane();
		gridPane.setPrefWidth(600);
		
		//CornerRadii cornerProperties = new CornerRadii(15, 0, 0, 15, false);
		BackgroundFill fill = new BackgroundFill(Color.DARKRED, null, null);
		Background background =  new Background(fill);
		gridPane.setBackground(background);
		
		gridPane.setHgap(0);
		gridPane.setVgap(14);
		
		double with = 70; double height = 100;		
		
		this.scrollPlayer1 = new ScrollPane(); 
		this.scrollPlayer2 = new ScrollPane(); 

		this.gridScrollPlayer1 = new GridPane();
		this.gridScrollPlayer2 = new GridPane();
		
		gridPane.add(scrollPlayer1, 1, 0);
		gridPane.add(scrollPlayer2, 1, 26);
		
//		Rectangle P1card1 = new Rectangle(with, height, Color.DARKCYAN); 
//		Rectangle P1card2 = new Rectangle(with, height, Color.DARKCYAN); 
//		Rectangle P1card3 = new Rectangle(with, height, Color.DARKCYAN); 
//		Rectangle P1card4 = new Rectangle(with, height, Color.DARKCYAN); 
//		Rectangle P1card5 = new Rectangle(with, height, Color.DARKCYAN);  
//		
//		Rectangle P2card1 = new Rectangle(with, height, Color.DARKCYAN); 
//		Rectangle P2card2 = new Rectangle(with, height, Color.DARKCYAN); 
//		Rectangle P2card3 = new Rectangle(with, height, Color.DARKCYAN); 
//		Rectangle P2card4 = new Rectangle(with, height, Color.DARKCYAN); 
//		Rectangle P2card5 = new Rectangle(with, height, Color.DARKCYAN); 
//		
//		handOne.add(P1card1); handTwo.add(P2card1); 
//		handOne.add(P1card2); handTwo.add(P2card2); 
//		handOne.add(P1card3); handTwo.add(P2card3); 
//		handOne.add(P1card4); handTwo.add(P2card4); 
//		handOne.add(P1card5); handTwo.add(P2card5); 

//		gridPane.setMargin(P1card1, new Insets(30,10,10,10)); gridPane.setMargin(P2card1, new Insets(10)); 
//		gridPane.setMargin(P1card2, new Insets(30,10,10,10)); gridPane.setMargin(P2card2, new Insets(10)); 
//		gridPane.setMargin(P1card3, new Insets(30,10,10,10)); gridPane.setMargin(P2card3, new Insets(10)); 
//		gridPane.setMargin(P1card4, new Insets(30,10,10,10)); gridPane.setMargin(P2card4, new Insets(10)); 
//		gridPane.setMargin(P1card5, new Insets(30,10,10,10)); gridPane.setMargin(P2card5, new Insets(10)); 
//		
//		gridScroll1.add(P2card1, 1, 34); gridScroll2.add(P1card1, 1, 0); 
//		gridScroll1.add(P2card2, 2, 34); gridScroll2.add(P1card2, 2, 0);
//		gridScroll1.add(P2card3, 3, 34); gridScroll2.add(P1card3, 3, 0);
//		gridScroll1.add(P2card4, 4, 34); gridScroll2.add(P1card4, 4, 0);
//		gridScroll1.add(P2card5, 5, 34); gridScroll2.add(P1card5, 5, 0);
//		
		
		//gridPane.setGridLinesVisible(true); //->para prueba
		return gridPane;
	}
	
	
	private void setActionToHand() {
		
		Iterator<Rectangle> iterador = handOne.iterator();
		
		int i=0;
		while (i < handOne.size()) {
			CartasManoHandler handler = new CartasManoHandler (this,manoJugadorUno,playerOne,i,iterador.next());
			handOne.get(i).setOnContextMenuRequested(handler);
			i++;
		}
		
		iterador = handTwo.iterator();
		
		i=0;
		while (i < handTwo.size()) {
			CartasManoHandler handler = new CartasManoHandler (this,manoJugadorDos,playerTwo,i,iterador.next());
			handTwo.get(i).setOnContextMenuRequested(handler);
			i++;
		}

	}
	
}
