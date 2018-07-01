package eventos;

import java.util.Optional;

import Vista.Main;
import Vista.VentanaDeJuego;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.Window;
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
				Alert alert = new Alert(AlertType.NONE);
				alert.initOwner(ventana.getStage());
				alert.setTitle("Fin del juego");
				if (controlador.ganoElJugadorDelTurnoActual()) {
					alert.setHeaderText("Ganador: " + controlador.obtenerJugadorAtacante().obtenerNombre());	
				}else {
					alert.setHeaderText("Ganador: " + controlador.obtenerJugadorDefensor().obtenerNombre());	
				}
				alert.setContentText("Gracias por jugar");
				EventoCerrarJuego eventoSalir = new EventoCerrarJuego(ventana.getStage());
				ButtonType salir =   new ButtonType("Salir");
				ButtonType reiniciar =   new ButtonType("Nueva Partida");
				alert.getButtonTypes().setAll(salir, reiniciar);
				Optional<ButtonType> respuesta = alert.showAndWait();
				if(respuesta.get() == salir) {
					Window stage = this.ventana.getStage();
					stage.hide();
				}
				if(respuesta.get() == reiniciar) {
					Window stageAnterior = ventana.getStage();
					ventana.pararMusica();
					stageAnterior.hide();
					Stage stage = new Stage();
					Main main = new Main();	
					stage.setFullScreen(true);
					stage.setFullScreenExitHint("");
					main.reiniciarJuego(stage);
				}
			}
		}
		ArenaDeCombate.obtener().reiniciarCombatientes();
		AreaDeSacrificios.obtener().reiniciarSacrificios();
		ventana.actualizarCampoDeJuego();
	}
	
}
