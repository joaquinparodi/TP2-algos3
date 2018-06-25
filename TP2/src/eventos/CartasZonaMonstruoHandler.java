package eventos;

import Vista.VentanaDeJuego;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.shape.Rectangle;
import jugabilidad.Baraja;
import jugabilidad.Jugador;

public class CartasZonaMonstruoHandler implements EventHandler<ContextMenuEvent> {

	private VentanaDeJuego ventanaDeJuego;
	private Jugador jugador;
	private Baraja manoJugador;
	private Rectangle rect;
	private int index;
	
	public CartasZonaMonstruoHandler(VentanaDeJuego ventana, Jugador jugador, int index, Rectangle rect) {
		this.jugador = jugador;
		this.rect = rect;
		this.index = index;
		this.ventanaDeJuego = ventana;
	}
	
	public void handle(ContextMenuEvent event) {
		ContextMenu menuDesplegable = new ContextMenu();
	
		MenuItem rotar = new MenuItem("rotar");
		menuDesplegable.getItems().addAll(rotar);

		BotonRotarEnZonaMonstruo handler = new BotonRotarEnZonaMonstruo(ventanaDeJuego, jugador, index, rect);
		rotar.setOnAction(handler);
		
		menuDesplegable.show(rect, event.getScreenX(), event.getScreenY());
	}

	
}
