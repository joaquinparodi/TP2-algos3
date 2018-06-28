package eventos;

import Vista.VentanaDeJuego;
import cartas.Monstruo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.shape.Rectangle;

public class BotonRotarEnZonaMonstruo implements EventHandler<ActionEvent> {

	private VentanaDeJuego ventana;
	private Monstruo monstruo;
	
	public BotonRotarEnZonaMonstruo(VentanaDeJuego ventana, Monstruo monstruo, Rectangle rect) {
		this.ventana = ventana;
		this.monstruo = monstruo;
	}

	public void handle(ActionEvent event) {
		this.monstruo.cambiarPosicion();
		ventana.actualizarCampoDeJuego();
	}
	
	 
}
