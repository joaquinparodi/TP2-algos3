package eventos;

import Vista.VentanaDeJuego;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
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
			if (Controlador.obtener().hayGanador()) {
				//ACA TERMINA EL JUEGO
			}else {
				Controlador.obtener().repartirCartaAJugador();
			}		
		}
	}
}
