package eventos;

import Vista.VentanaDeJuego;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.shape.Rectangle;
import jugabilidad.Jugador;

public class BotonRotarEnZonaMonstruo implements EventHandler<ActionEvent> {

	VentanaDeJuego ventana;
	Jugador jugador;
	Rectangle rect;
	int index;
	
	public BotonRotarEnZonaMonstruo(VentanaDeJuego ventana, Jugador jugador, int index, Rectangle rect) {
		this.jugador = jugador;
		this.index = index;
		this.ventana = ventana;
		this.rect = rect;
	}

	public void handle(ActionEvent event) {
		jugador.rotarCartaMonstruoEnCampo(index);		
		ventana.actualizarCampoDeJuego();
	}
	
	 
}
