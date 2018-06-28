package eventos;

import Vista.VentanaDeJuego;
import error.ErrorNoPuedeRepartirCartas;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import jugabilidad.Controlador;
import jugabilidad.Jugador;

public class MazoHandler implements EventHandler<MouseEvent> {

	private Jugador jugador;
	private VentanaDeJuego ventana;
	
	public MazoHandler(VentanaDeJuego ventana, Jugador jugador) {
		this.jugador = jugador;
		this.ventana = ventana;
	}
	
	public void handle(MouseEvent event) {
		if (Controlador.obtener().obtenerFase()!="Preparacion") {
			//throw new ErrorNoPuedeRepartirCartas();
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(ventana.getStage());
			alert.setTitle("Error");
			alert.setHeaderText("No puede repartir carta");
			alert.setContentText("No se puede repartir carta en este fase del juego");
			alert.showAndWait();
		}
		jugador.repartirCarta();
		ventana.actualizarCampoDeJuego();
	}
	
	
	
}
