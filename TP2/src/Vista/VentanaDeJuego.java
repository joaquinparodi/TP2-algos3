package Vista;

import java.util.ArrayList;
import java.util.Iterator;
import cartas.Carta;
import cartas.Monstruo;
import eventos.BotonFaseYTurnoHandler;
import eventos.CartasManoHandler;
import eventos.CartasZonaMagicaHandler;
import eventos.CartasZonaMonstruoHandler;
import eventos.MazoHandler;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import jugabilidad.Baraja;
import jugabilidad.CampoDeJuego;
import jugabilidad.Jugador;		


public class VentanaDeJuego {
	
	private BaseDeDatosDeCartas database;
	
	private Jugador playerOne;
	private Jugador playerTwo;
	private CampoDeJuego campoDeJuegoUno;
	private CampoDeJuego campoDeJuegoDos;
	private Baraja manoJugadorUno;
	private Baraja manoJugadorDos;
	
	private Text playerOneName;
	private Text playerTwoName;
	private Text playerOneLife;
	private Text playerTwoLife;
	
	private ArrayList<Rectangle> handOne;
	private ArrayList<Rectangle> handTwo;
	
	private ArrayList<Rectangle> P1STZone;
	private ArrayList<Rectangle> P2STZone;
	
	private ArrayList<Rectangle> P1MZone;
	private ArrayList<Rectangle> P2MZone;
	
	private GridPane gridScrollPlayer1;
	private GridPane gridScrollPlayer2;
	private ScrollPane scrollPlayer1;
	private ScrollPane scrollPlayer2;
	private Rectangle cementerioJugadorUno;
	private Rectangle cementerioJugadorDos;
	private Rectangle cartaDeCampoUno;
	private Rectangle cartaDeCampoDos;
	private Rectangle mazoJugadorUno;
	private Rectangle mazoJugadorDos;
	
	private Text faseYTurno;

	private Stage stage;
	
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
		this.actualizarCementerio();
		this.actualizarMazos();
		this.actualizarFilaMonstruosJugadorUno();
		this.actualizarFilaMonstruosJugadorDos();
		this.actualizarFilaMagicasYTrampasJugadorUno();
		this.actualizarFilaMagicasYTrampasJugadorDos();
		this.actualizarZonaDeCartasDeCampo();
		this.actualizarManos();
		this.actualizarOpcionesDeManejo();
		this.actualizarVidaJugadores();
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
		
		String URL; ImagePattern image; Rectangle actualRect; Monstruo monstruoActual;
		while(iterZone.hasNext() && iterFila.hasNext()) {
			monstruoActual = (Monstruo)iterFila.next();
			URL = database.getURL(monstruoActual.obtenerNombre());
			image = new ImagePattern( new Image(URL) );
			actualRect = iterZone.next();
			actualRect.setFill(image);
			if(monstruoActual.estaRotado()) actualRect.setRotate(90);
			if(monstruoActual.estaVolteada()) this.voltearCarta(actualRect);
		}
	}
	
	private void actualizarFilaMonstruosJugadorDos() {
		Baraja filaMonstruos = campoDeJuegoDos.obtenerFilaDeMonstruos();
		Iterator<Rectangle> iterZone = P2MZone.iterator();		
		Iterator<Carta> iterFila = filaMonstruos.obtenerIteradorDeBaraja();

		String URL; ImagePattern image; Rectangle actualRect; Monstruo monstruoActual;
		while(iterZone.hasNext() && iterFila.hasNext()) {
			monstruoActual = (Monstruo)iterFila.next();
			URL = database.getURL(monstruoActual.obtenerNombre());
			image = new ImagePattern( new Image(URL) );
			actualRect = iterZone.next();
			actualRect.setFill(image);
			if(monstruoActual.estaRotado()) actualRect.setRotate(90);
			if(monstruoActual.estaVolteada()) this.voltearCarta(actualRect);
		}
	}

	private void actualizarFilaMagicasYTrampasJugadorUno() {
		Baraja filaMagicasYTrampas = campoDeJuegoUno.obtenerFilaDeMagicasYTrampas();
		Iterator<Rectangle> iterZone = P1STZone.iterator();		
		Iterator<Carta> iterFila = filaMagicasYTrampas.obtenerIteradorDeBaraja();

		String URL; ImagePattern image; Carta carta; Rectangle actualRect;
		while(iterZone.hasNext() && iterFila.hasNext()) {
			carta = iterFila.next();
			URL = database.getURL(carta.obtenerNombre());
			image = new ImagePattern( new Image(URL) );
			actualRect = iterZone.next();
			actualRect.setFill(image);
			if(carta.estaVolteada()) this.voltearCarta(actualRect);
		}
		
	}
	
	private void actualizarFilaMagicasYTrampasJugadorDos() {
		Baraja filaMagicasYTrampas = campoDeJuegoDos.obtenerFilaDeMagicasYTrampas();
		Iterator<Rectangle> iterZone = P2STZone.iterator();		
		Iterator<Carta> iterFila = filaMagicasYTrampas.obtenerIteradorDeBaraja();

		final int maxCardHand = 5;
		int actualCards = 0;
		
		String URL; ImagePattern image; Carta carta; Rectangle actualRect;
		while(iterZone.hasNext() && iterFila.hasNext()) {
			carta = iterFila.next();
			URL = database.getURL(carta.obtenerNombre());
			image = new ImagePattern( new Image(URL) );
			actualRect = iterZone.next();
			actualRect.setFill(image);
			if(carta.estaVolteada()) this.voltearCarta(actualRect);
		}
	}
	
	private void restaurarPosiciones() {
		Iterator<Rectangle> iterMZoneOne = P1MZone.iterator();	
		Iterator<Rectangle> iterMZoneTwo = P2MZone.iterator();
		Iterator<Rectangle> iterSTZoneOne = P1STZone.iterator();	
		Iterator<Rectangle> iterSTZoneTwo = P2STZone.iterator();
		Iterator<Rectangle> iterHandOne = handOne.iterator();
		Iterator<Rectangle> iterHandTwo = handTwo.iterator();

		Rectangle rect;
		while(iterMZoneOne.hasNext()) {	
			rect = iterMZoneOne.next();
			rect.setFill(Color.GOLDENROD);
			rect.setRotate(0);
		}
		
		while(iterMZoneTwo.hasNext()) {
			rect = iterMZoneTwo.next();
			rect.setFill(Color.GOLDENROD);
			rect.setRotate(0);
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
	
	private void actualizarCementerio() {
		ImagePattern img = new ImagePattern( new Image("file:images/lomo_carta.jpg") );
		if(campoDeJuegoUno.cementerioContieneCartas()) cementerioJugadorUno.setFill( img );
		if(campoDeJuegoDos.cementerioContieneCartas()) cementerioJugadorDos.setFill( img );
		
	}
	
	private void actualizarZonaDeCartasDeCampo() {
		
		if( campoDeJuegoUno.seAgregoCartaDeCampo() ) {
			String nombreCarta = campoDeJuegoUno.obtenerNombreCartaDeCampo();
			ImagePattern img = new ImagePattern( new Image( database.getURL(nombreCarta) ) );
			cartaDeCampoUno.setFill(img);
		} else cartaDeCampoUno.setFill(Color.DARKVIOLET);
	
		if( campoDeJuegoDos.seAgregoCartaDeCampo() ) {
			String nombreCarta = campoDeJuegoDos.obtenerNombreCartaDeCampo();
			ImagePattern img = new ImagePattern( new Image( database.getURL(nombreCarta) ) );
			cartaDeCampoDos.setFill(img);
		} else cartaDeCampoDos.setFill(Color.DARKVIOLET);
		
	}

	private void voltearCarta(Rectangle rect) {
		ImagePattern image = new ImagePattern( new Image("file:images/lomo_carta.jpg") );
		rect.setFill(image);
	}
	
	private void actualizarOpcionesDeManejo() {
		this.setActionToMZone();
	}
	
	private void actualizarMazos() {
		if( !playerOne.poseeCartasEnMazo() ) this.mazoJugadorUno.setFill(Color.PERU);
		if( !playerTwo.poseeCartasEnMazo() ) this.mazoJugadorDos.setFill(Color.PERU);
	}
	
	public void cambiarFase(String nuevaFase, int numeroJugador) {	
		faseYTurno.setText(nuevaFase + " Jugador" + " " + numeroJugador);
	}
	
	private void actualizarVidaJugadores() {
		double vidaUno = playerOne.obtenerVida(); double vidaDos = playerTwo.obtenerVida();
		playerOneLife.setText( String.valueOf(vidaUno) );
		playerTwoLife.setText( String.valueOf(vidaDos) );
	}
	
	/*----------------------------------Creacion de la vista inicial--------------------------------------*/
	
	public Scene createGameScene(Stage stage) {
		this.stage = stage;
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

		//CornerRadii cornerProperties = new CornerRadii(0, 15, 15, 0, false);
		BackgroundFill fill = new BackgroundFill(Color.DARKRED, null, null);
		Background background =  new Background(fill);
		gridPane.setBackground(background);
		
		Rectangle square1 = new Rectangle(100, 100, Color.DARKORANGE); //Van a ser las fotos
		Rectangle square2 = new Rectangle(100, 100, Color.DARKORANGE); //Van a ser las fotos
		
		ImagePattern img = new ImagePattern( new Image("file:images/lomo_carta.jpg") );
		this.mazoJugadorUno = new Rectangle(70, 100, img);
		this.mazoJugadorDos = new Rectangle(70, 100, img);
		
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
		
		GridPane.setMargin(square1, new Insets(10,10,10,10)); 
		GridPane.setMargin(square2, new Insets(10,10,10,10)); 
		
		GridPane.setMargin(this.mazoJugadorUno, new Insets(10,10,10,30));
		GridPane.setMargin(this.mazoJugadorDos, new Insets(40,10,10,30));
		
		GridPane.setMargin(playerOneName, new Insets(0,0,0,10));
		GridPane.setMargin(playerTwoName, new Insets(-40,0,0,10));
		GridPane.setMargin(playerOneLife, new Insets(0,0,0,-30));
		GridPane.setMargin(playerTwoLife, new Insets(-40,0,0,-20));
		
		GridPane.setMargin(P1life, new Insets(0,0,0,-10));
		GridPane.setMargin(P2life, new Insets(0,0,0,-10));
		
		gridPane.add(square1, 0, 0);
		gridPane.add(playerOneName, 0, 1);
		gridPane.add(playerOneLife, 1, 1);
		gridPane.add(this.mazoJugadorUno, 0, 5);
		gridPane.add(P1life, 1, 0);
		
		gridPane.add(this.mazoJugadorDos, 0, 15);
		gridPane.add(playerTwoLife, 1, 19);
		gridPane.add(playerTwoName, 0, 19);
		gridPane.add(square2, 0, 20);
		gridPane.add(P2life, 1, 20);
		
		Button botonCambiarTurno = new Button("Avanzar Fase");
		BotonFaseYTurnoHandler botonFaseYTurnoHandler = new BotonFaseYTurnoHandler(this ,botonCambiarTurno);
		botonCambiarTurno.setOnAction(botonFaseYTurnoHandler);
		
		gridPane.add(botonCambiarTurno, 0, 10);
		GridPane.setMargin(botonCambiarTurno, new Insets(15,0,0,20) );
		//gridPane.setGridLinesVisible(true); //->Solo para guiarse,despues lo sacamos
		this.setActionToDecks();
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
		double width = 70; double height = 100;
		
		/*---------------------------Campo de juego jugadorUno-------------------------------------*/
		Rectangle P1STzone1 = new Rectangle(width, height, Color.DARKCYAN); 
		Rectangle P1STzone2 = new Rectangle(width, height, Color.DARKCYAN); 
		Rectangle P1STzone3 = new Rectangle(width, height, Color.DARKCYAN); 
		Rectangle P1STzone4 = new Rectangle(width, height, Color.DARKCYAN); 
		Rectangle P1STzone5 = new Rectangle(width, height, Color.DARKCYAN); 
		
		Rectangle P1Mzone1 = new Rectangle(width, height, Color.GOLDENROD); 
		Rectangle P1Mzone2 = new Rectangle(width, height, Color.GOLDENROD); 
		Rectangle P1Mzone3 = new Rectangle(width, height, Color.GOLDENROD); 
		Rectangle P1Mzone4 = new Rectangle(width, height, Color.GOLDENROD); 
		Rectangle P1Mzone5 = new Rectangle(width, height, Color.GOLDENROD); 
		
		this.cementerioJugadorUno = new Rectangle(width, height, Color.DIMGRAY); 
		this.cartaDeCampoUno = new Rectangle(width, height, Color.DARKVIOLET);
		
		gridPane.setMargin(P1STzone1, new Insets(15,20,10,20)); gridPane.setMargin(P1Mzone1, new Insets(20)); 
		gridPane.setMargin(P1STzone2, new Insets(15,20,10,20)); gridPane.setMargin(P1Mzone2, new Insets(20)); 
		gridPane.setMargin(P1STzone3, new Insets(15,20,10,20)); gridPane.setMargin(P1Mzone3, new Insets(20)); 
		gridPane.setMargin(P1STzone4, new Insets(15,20,10,20)); gridPane.setMargin(P1Mzone4, new Insets(20)); 
		gridPane.setMargin(P1STzone5, new Insets(15,20,10,20)); gridPane.setMargin(P1Mzone5, new Insets(20)); 
		
		gridPane.setMargin(this.cementerioJugadorUno, new Insets(10)); 
		gridPane.setMargin(this.cartaDeCampoUno, new Insets(10));
		
		gridPane.add(P1STzone1, 1, 1); gridPane.add(P1Mzone1, 1, 2);
		gridPane.add(P1STzone2, 2, 1); gridPane.add(P1Mzone2, 2, 2);
		gridPane.add(P1STzone3, 3, 1); gridPane.add(P1Mzone3, 3, 2);
		gridPane.add(P1STzone4, 4, 1); gridPane.add(P1Mzone4, 4, 2);
		gridPane.add(P1STzone5, 5, 1); gridPane.add(P1Mzone5, 5, 2);
		
		gridPane.add(this.cementerioJugadorUno, 0, 3); 
		gridPane.add(this.cartaDeCampoUno , 6, 3);
			
		P1STZone.add(P1STzone1); P1MZone.add(P1Mzone1);
		P1STZone.add(P1STzone2); P1MZone.add(P1Mzone2);
		P1STZone.add(P1STzone3); P1MZone.add(P1Mzone3);
		P1STZone.add(P1STzone4); P1MZone.add(P1Mzone4);
		P1STZone.add(P1STzone5); P1MZone.add(P1Mzone5);
		
		/*---------------------------Campo de juego jugadorDos-------------------------------------*/
		Rectangle P2STzone1 = new Rectangle(width, height, Color.DARKCYAN); 
		Rectangle P2STzone2 = new Rectangle(width, height, Color.DARKCYAN); 
		Rectangle P2STzone3 = new Rectangle(width, height, Color.DARKCYAN); 
		Rectangle P2STzone4 = new Rectangle(width, height, Color.DARKCYAN); 
		Rectangle P2STzone5 = new Rectangle(width, height, Color.DARKCYAN); 
		
		Rectangle P2Mzone1 = new Rectangle(width, height, Color.GOLDENROD); 
		Rectangle P2Mzone2 = new Rectangle(width, height, Color.GOLDENROD); 
		Rectangle P2Mzone3 = new Rectangle(width, height, Color.GOLDENROD); 
		Rectangle P2Mzone4 = new Rectangle(width, height, Color.GOLDENROD); 
		Rectangle P2Mzone5 = new Rectangle(width, height, Color.GOLDENROD); 
		
		this.cementerioJugadorDos = new Rectangle(width, height, Color.DIMGRAY);
		this.cartaDeCampoDos = new Rectangle(width, height, Color.DARKVIOLET);
		
		GridPane.setMargin(P2STzone1, new Insets(0,20,10,20)); GridPane.setMargin(P2Mzone1, new Insets(20)); 
		GridPane.setMargin(P2STzone2, new Insets(0,20,10,20)); GridPane.setMargin(P2Mzone2, new Insets(20)); 
		GridPane.setMargin(P2STzone3, new Insets(0,20,10,20)); GridPane.setMargin(P2Mzone3, new Insets(20)); 
		GridPane.setMargin(P2STzone4, new Insets(0,20,10,20)); GridPane.setMargin(P2Mzone4, new Insets(20)); 
		GridPane.setMargin(P2STzone5, new Insets(0,20,10,20)); GridPane.setMargin(P2Mzone5, new Insets(20)); 
		
		GridPane.setMargin(this.cementerioJugadorDos, new Insets(10)); 
		GridPane.setMargin(this.cartaDeCampoDos, new Insets(10));
		
		gridPane.add(P2STzone1, 1, 6); gridPane.add(P2Mzone1, 1, 5);
		gridPane.add(P2STzone2, 2, 6); gridPane.add(P2Mzone2, 2, 5);
		gridPane.add(P2STzone3, 3, 6); gridPane.add(P2Mzone3, 3, 5);
		gridPane.add(P2STzone4, 4, 6); gridPane.add(P2Mzone4, 4, 5);
		gridPane.add(P2STzone5, 5, 6); gridPane.add(P2Mzone5, 5, 5);
		
		gridPane.add(this.cartaDeCampoDos, 0, 4); 
		gridPane.add(this.cementerioJugadorDos, 6, 4);
		
		P2STZone.add(P2STzone1); P2MZone.add(P2Mzone1);
		P2STZone.add(P2STzone2); P2MZone.add(P2Mzone2);
		P2STZone.add(P2STzone3); P2MZone.add(P2Mzone3);
		P2STZone.add(P2STzone4); P2MZone.add(P2Mzone4);
		P2STZone.add(P2STzone5); P2MZone.add(P2Mzone5);
		
		
		this.setActionToMZone();
		this.setActionToSTZone();
		/*---------------------------Zona central del tablero-------------------------------------*/
		
		this.faseYTurno = new Text();
		this.faseYTurno.setText("Preparacion Jugador 1");
		this.faseYTurno.setFont(Font.font ("Verdana", 45));
		this.faseYTurno.setFill(Color.RED);
		gridPane.add(this.faseYTurno, 3, 2, 4, 4);
		GridPane.setMargin(this.faseYTurno, new Insets(0,0,0,-175));
		
	
		//gridPane.setGridLinesVisible(true); //->para prueba
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
		
		this.scrollPlayer1 = new ScrollPane(); 
		this.scrollPlayer2 = new ScrollPane(); 
		this.gridScrollPlayer1 = new GridPane();
		this.gridScrollPlayer2 = new GridPane();
		
		gridPane.add(scrollPlayer1, 1, 0);
		gridPane.add(scrollPlayer2, 1, 26);
		
		return gridPane;
	}
		
	private void setActionToHand() {
		
		Iterator<Rectangle> iterador = handOne.iterator();
		
		int i=0;
		while (i < handOne.size()) {
			CartasManoHandler handler = new CartasManoHandler (this,manoJugadorUno,playerOne,i,iterador.next());
			handler.setCarta(manoJugadorUno.obtenerCartaDePosicion(i));
			handOne.get(i).setOnContextMenuRequested(handler);
			i++;
		}
		
		iterador = handTwo.iterator();
		
		i=0;
		while (i < handTwo.size()) {
			CartasManoHandler handler = new CartasManoHandler (this,manoJugadorDos,playerTwo,i,iterador.next());
			handler.setCarta(manoJugadorDos.obtenerCartaDePosicion(i));
			handTwo.get(i).setOnContextMenuRequested(handler);
			i++;
		}

	}
	
	private void setActionToMZone() {
		ArrayList<CartasZonaMonstruoHandler> handlersUno = new ArrayList<CartasZonaMonstruoHandler>();
		ArrayList<CartasZonaMonstruoHandler> handlersDos = new ArrayList<CartasZonaMonstruoHandler>();
		
		handlersUno.add( new CartasZonaMonstruoHandler(this, playerOne, 0, P1MZone.get(0)) );
		handlersUno.add( new CartasZonaMonstruoHandler(this, playerOne, 1, P1MZone.get(1)) );
		handlersUno.add( new CartasZonaMonstruoHandler(this, playerOne, 2, P1MZone.get(2)) );
		handlersUno.add( new CartasZonaMonstruoHandler(this, playerOne, 3, P1MZone.get(3)) );
		handlersUno.add( new CartasZonaMonstruoHandler(this, playerOne, 4, P1MZone.get(4)) );
		
		handlersDos.add( new CartasZonaMonstruoHandler(this, playerTwo, 0, P2MZone.get(0)) );
		handlersDos.add( new CartasZonaMonstruoHandler(this, playerTwo, 1, P2MZone.get(1)) );
		handlersDos.add( new CartasZonaMonstruoHandler(this, playerTwo, 2, P2MZone.get(2)) );
		handlersDos.add( new CartasZonaMonstruoHandler(this, playerTwo, 3, P2MZone.get(3)) );
		handlersDos.add( new CartasZonaMonstruoHandler(this, playerTwo, 4, P2MZone.get(4)) );
		
		for(int i = 0; i < 5; i++) {
			P1MZone.get(i).setOnContextMenuRequested(null);
			P1MZone.get(i).setOnContextMenuRequested(null);
		}
		
		int tope = campoDeJuegoUno.cantidadDeMonstruosEnFila();
		for(int i = 0; i < tope; i++) {
			handlersUno.get(i).asignarMonstruoEnPosicion(campoDeJuegoUno.obtenerMonstruoEnPosicion(i));
			P1MZone.get(i).setOnContextMenuRequested(handlersUno.get(i));
		}
		
		tope = campoDeJuegoDos.cantidadDeMonstruosEnFila();
		for(int i = 0; i < tope; i++) {
			handlersDos.get(i).asignarMonstruoEnPosicion(campoDeJuegoDos.obtenerMonstruoEnPosicion(i));
			P2MZone.get(i).setOnContextMenuRequested(handlersDos.get(i));
		}
		
	}
	
	private void setActionToDecks() {
		MazoHandler handlerMasoUno = new MazoHandler(this, playerOne);
		MazoHandler handlerMasoDos = new MazoHandler(this, playerTwo);
		
		this.mazoJugadorUno.setOnMouseClicked(handlerMasoUno);
		this.mazoJugadorDos.setOnMouseClicked(handlerMasoDos);
	}

	private void setActionToSTZone() {
		CartasZonaMagicaHandler handlerO1 = new CartasZonaMagicaHandler(this, playerOne, 0, P1STZone.get(0));
		CartasZonaMagicaHandler handlerO2 = new CartasZonaMagicaHandler(this, playerOne, 1, P1STZone.get(1));
		CartasZonaMagicaHandler handlerO3 = new CartasZonaMagicaHandler(this, playerOne, 2, P1STZone.get(2));
		CartasZonaMagicaHandler handlerO4 = new CartasZonaMagicaHandler(this, playerOne, 3, P1STZone.get(3));
		CartasZonaMagicaHandler handlerO5 = new CartasZonaMagicaHandler(this, playerOne, 4, P1STZone.get(4));
		
		CartasZonaMagicaHandler handlerO6 = new CartasZonaMagicaHandler(this, playerTwo, 0, P2STZone.get(0));
		CartasZonaMagicaHandler handlerO7 = new CartasZonaMagicaHandler(this, playerTwo, 1, P2STZone.get(1));
		CartasZonaMagicaHandler handlerO8 = new CartasZonaMagicaHandler(this, playerTwo, 2, P2STZone.get(2));
		CartasZonaMagicaHandler handlerO9 = new CartasZonaMagicaHandler(this, playerTwo, 3, P2STZone.get(3));
		CartasZonaMagicaHandler handler10 = new CartasZonaMagicaHandler(this, playerTwo, 4, P2STZone.get(4));
		
		P1STZone.get(0).setOnContextMenuRequested(handlerO1); P2STZone.get(0).setOnContextMenuRequested(handlerO6);
		P1STZone.get(1).setOnContextMenuRequested(handlerO2); P2STZone.get(1).setOnContextMenuRequested(handlerO7);
		P1STZone.get(2).setOnContextMenuRequested(handlerO3); P2STZone.get(2).setOnContextMenuRequested(handlerO8);
		P1STZone.get(3).setOnContextMenuRequested(handlerO4); P2STZone.get(3).setOnContextMenuRequested(handlerO9);
		P1STZone.get(4).setOnContextMenuRequested(handlerO5); P2STZone.get(4).setOnContextMenuRequested(handler10);
	}

	public Window getStage() {
		return stage;
	}
}
