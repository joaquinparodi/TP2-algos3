package eventos;

import Vista.VentanaDeJuego;
import cartas.Monstruo;
import errores.ErrorMonstruoYaSacrificado;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import jugabilidad.AreaDeSacrificios;

public class BotonSacrificar implements EventHandler<ActionEvent> {

	private Monstruo monstruo;
	private VentanaDeJuego ventana;
	
	public BotonSacrificar(Monstruo monstruo, VentanaDeJuego ventana) {
		this.monstruo = monstruo;
		this.ventana = ventana;
	}
	
	public void handle(ActionEvent event) {
		try {
			AreaDeSacrificios.obtener().agregarMonstruo(this.monstruo);
		} catch(ErrorMonstruoYaSacrificado error) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(ventana.getStage());
			alert.setTitle("Error");
			alert.setHeaderText("Monstruo ya sacrificado");
			alert.setContentText("Este monstruo ya fue anadido a sacrificios");
			alert.showAndWait();
		}
		this.ventana.actualizarCampoDeJuego();
	}
}
