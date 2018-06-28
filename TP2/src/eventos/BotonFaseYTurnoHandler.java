package eventos;

import Vista.VentanaDeJuego;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import jugabilidad.AreaDeSacrificios;
import jugabilidad.ArenaDeCombate;
import jugabilidad.Controlador;

public class BotonFaseYTurnoHandler implements EventHandler<ActionEvent> {
	
	private Button boton;
	private VentanaDeJuego ventana;
	
	public BotonFaseYTurnoHandler(VentanaDeJuego ventana, Button boton) {
		this.boton = boton;
		this.ventana = ventana;
	}
	
	public void handle(ActionEvent arg0) {
		
		Controlador controlador = Controlador.obtener();
		controlador.avanzarFase();
		
		String fase = controlador.obtenerFase();
		
		ventana.cambiarFase(fase, controlador.obtenerNumeroJugadorAtacante());
		if (fase == "Final") {
			boton.setText("Finalizar Turno");
		}else {
			boton.setText("Avanzar Fase");
		}
		
		if (fase == "Preparacion") {
			if (controlador.hayGanador()) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.initOwner(ventana.getStage());
				alert.setTitle("Fin del juego");
				if (controlador.ganoElJugadorDelTurnoActual()) {
					alert.setHeaderText("Ganador: " + controlador.obtenerJugadorAtacante().obtenerNombre());	
				}else {
					alert.setHeaderText("Ganador: " + controlador.obtenerJugadorDefensor().obtenerNombre());	
				}
				alert.setContentText("Gracias por jugar");
				EventoCerrarJuego evento = new EventoCerrarJuego(ventana.getStage());
				alert.setOnCloseRequest(evento);
				alert.show();
				
			}else {
				Controlador.obtener().repartirCartaAJugador();
			}
		}
		ArenaDeCombate.obtener().reiniciarCombatientes();
		AreaDeSacrificios.obtener().reiniciarSacrificios();
		ventana.actualizarCampoDeJuego();
	}
}
