package eventos;

import Vista.VentanaDeJuego;
import cartas.Carta;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BotonVoltearEnZonaMagica implements EventHandler<ActionEvent> {

	private VentanaDeJuego ventana;
	private Carta carta;
	
	public BotonVoltearEnZonaMagica(VentanaDeJuego ventana, Carta carta) {
		this.carta = carta;
		this.ventana = ventana;
	}

	public void handle(ActionEvent event) {		
		carta.voltear();
		ventana.actualizarCampoDeJuego();
	}
	
	 
}