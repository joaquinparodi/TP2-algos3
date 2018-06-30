package eventos;

import Vista.VentanaDeJuego;
import cartas.Carta;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Window;

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
		Alert alerta = new Alert(AlertType.INFORMATION);
		alerta.initOwner(ventana.getStage());
		alerta.setTitle("Resultado");
		alerta.setHeaderText("");
		String texto;
		
		texto = carta.obtenerDuenio().obtenerNombre()+" ha usado la carta magica "+carta.obtenerNombre();
		
		alerta.setContentText(texto);
		
		alerta.showAndWait();

	}
	
	 
}