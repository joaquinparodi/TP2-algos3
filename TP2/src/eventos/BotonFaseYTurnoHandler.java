package eventos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import jugabilidad.Controlador;

public class BotonFaseYTurnoHandler implements EventHandler<ActionEvent> {
	
	Button boton;
	
	public BotonFaseYTurnoHandler(Button boton) {
		this.boton = boton;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		
		Controlador.obtener().avanzarFase();
		
		String fase = Controlador.obtener().obtenerFase();
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
