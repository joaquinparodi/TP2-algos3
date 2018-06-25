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
import jugabilidad.Baraja;
import jugabilidad.Jugador;

public class CartasManoHandler implements EventHandler<ContextMenuEvent> {

	private VentanaDeJuego ventanaDeJuego;
	private Jugador jugador;
	private Node nodo;
	private int index;
	
	public CartasManoHandler(VentanaDeJuego ventana, Baraja manoJugador, Jugador jugador, int index, Node nodo) {
		this.jugador = jugador;
		this.nodo = nodo;
		this.index = index;
		this.ventanaDeJuego = ventana;
	}
	
	public void handle(ContextMenuEvent event) {
		ContextMenu menuDesplegable = new ContextMenu();
		
		MenuItem agregarEnCampo = new MenuItem("Agregar en campo");
		MenuItem rotar = new MenuItem("Rotar");
		MenuItem voltear = new MenuItem("Voltear");
		menuDesplegable.getItems().addAll(agregarEnCampo, rotar, voltear);

		BotonAgregarEnCampoHandler handlerAgregar = new BotonAgregarEnCampoHandler(ventanaDeJuego, jugador, index);
		agregarEnCampo.setOnAction(handlerAgregar);
		
		menuDesplegable.show(nodo, event.getScreenX(), event.getScreenY());
	}

}
