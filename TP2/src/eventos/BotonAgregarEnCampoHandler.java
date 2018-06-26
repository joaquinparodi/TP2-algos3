package eventos;

import java.util.ArrayList;

import Vista.BaseDeDatosDeCartas;
import Vista.VentanaDeJuego;
import errores.ErrorSacrificiosInsuficientes;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import jugabilidad.Baraja;
import jugabilidad.Jugador;

public class BotonAgregarEnCampoHandler implements EventHandler<ActionEvent> {
	
	private BaseDeDatosDeCartas database = new BaseDeDatosDeCartas();
	
	private Jugador jugador;
	private VentanaDeJuego ventanaDeJuego;
	private int index;

	
	public BotonAgregarEnCampoHandler(VentanaDeJuego ventana, Jugador jugador, int index) {
		this.jugador = jugador;
		this.index = index;
		this.ventanaDeJuego = ventana;
	}
	
	public void handle(ActionEvent event) {
		try {
			jugador.agregarCartaDeManoEnCampo(index);
		}catch (ErrorSacrificiosInsuficientes error) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Sacrificios incompletos");
			alert.setContentText("No se han seleccionado sacrificios suficientes para invocar este monstruo");
			alert.showAndWait();
		}
		ventanaDeJuego.actualizarCampoDeJuego();
	}

}
