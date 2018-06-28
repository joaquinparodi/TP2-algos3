package eventos;

import Vista.VentanaDeJuego;
import cartas.Carta;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.shape.Rectangle;
import jugabilidad.Controlador;

public class CartasSacrificadasHandler implements EventHandler<ContextMenuEvent> {

	private Carta carta;
	private VentanaDeJuego ventana;
	
	public CartasSacrificadasHandler(Carta carta, VentanaDeJuego ventana) {
		this.carta = carta;
		this.ventana = ventana;
	}
	
	public void handle(ContextMenuEvent event) {
		if(Controlador.obtener().partidaEstaEnFase("Preparacion")) {
			ContextMenu menu = new ContextMenu();
			Rectangle rect = (Rectangle) event.getSource();
			MenuItem noSacrificar = new MenuItem("Quitar de sacrificios");
			BotonNoSacrificar handler = new BotonNoSacrificar(carta, ventana);
			noSacrificar.setOnAction(handler);
			menu.getItems().add(noSacrificar);
			menu.show(rect, event.getScreenX(), event.getScreenY());
		}
		
	}
	
}
