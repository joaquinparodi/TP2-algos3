package eventos;

import Vista.VentanaDeJuego;
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
		if (!(Controlador.obtener().partidaEstaEnFase("Preparacion")) || !(Controlador.obtener().esElTurnoDe(jugador))) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(ventana.getStage());
			alert.setTitle("Error");
			alert.setHeaderText("No puede repartir carta");
			alert.setContentText("No se puede repartir carta en este fase del juego");
			alert.showAndWait();
		} else if(Controlador.obtener().jugadorYaRepartiCarta()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(ventana.getStage());
			alert.setTitle("Error");
			alert.setHeaderText("No puede repartir carta");
			alert.setContentText("No se puede repartir mas de una carta en el turno");
			alert.showAndWait();
		}else{
			Controlador.obtener().repartirCartaAJugador();
		}
		ventana.actualizarCampoDeJuego();
	}
	
	
	
}
