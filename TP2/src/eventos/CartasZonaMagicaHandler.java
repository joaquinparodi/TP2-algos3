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

public class CartasZonaMagicaHandler implements EventHandler<ContextMenuEvent> {

	private VentanaDeJuego ventanaDeJuego;
	private Jugador jugador;
	private Baraja manoJugador;
	private Rectangle rect;
	private int index;
	
	public CartasZonaMagicaHandler(VentanaDeJuego ventana, Jugador jugador, int index, Rectangle rect) {
		this.jugador = jugador;
		this.rect = rect;
		this.index = index;
		this.ventanaDeJuego = ventana;
	}
	
	public void handle(ContextMenuEvent event) {
			ContextMenu menuDesplegable = new ContextMenu();
	
			MenuItem voltear = new MenuItem("voltear");
			menuDesplegable.getItems().addAll(voltear);

			BotonVoltearEnZonaMagica handler = new BotonVoltearEnZonaMagica(ventanaDeJuego, jugador, index, rect);
			voltear.setOnAction(handler);
		
			menuDesplegable.show(rect, event.getScreenX(), event.getScreenY());
	}

	
}