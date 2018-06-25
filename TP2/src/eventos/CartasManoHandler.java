package eventos;

import java.util.ArrayList;

import Vista.VentanaDeJuego;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import jugabilidad.Jugador;

public class CartasManoHandler implements EventHandler<ContextMenuEvent> {

	private ArrayList<Rectangle> STZone;
	private ArrayList<Rectangle> MZone;
	private Jugador jugador;
	private Node duenio;
	private VentanaDeJuego ventanaDeJuego;
	private int index;
	
	public CartasManoHandler(VentanaDeJuego juego, ArrayList<Rectangle> STZone, ArrayList<Rectangle> MZone, Node duenio, Jugador jugador, int index ) {
		this.STZone = STZone;
		this.MZone = MZone;		
		this.jugador = jugador;
		this.duenio = duenio;
		this.index = index;
		this.ventanaDeJuego = juego;
	}
	
	public void handle(ContextMenuEvent event) {
		ContextMenu menuDesplegable = new ContextMenu();
		
		MenuItem agregarEnCampo = new MenuItem("Agregar en campo");
		MenuItem rotar = new MenuItem("Rotar");
		MenuItem voltear = new MenuItem("Voltear");
		menuDesplegable.getItems().addAll(agregarEnCampo, rotar, voltear);

		BotonAgregarEnCampoHandler handlerAgregar = new BotonAgregarEnCampoHandler(ventanaDeJuego ,STZone, MZone, jugador, index);
		agregarEnCampo.setOnAction(handlerAgregar);
		
		menuDesplegable.show(duenio, event.getScreenX(), event.getScreenY());
	}

}
