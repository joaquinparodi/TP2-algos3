package eventos;

import Vista.VentanaDeJuego;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import jugabilidad.Jugador;

public class MazoHandler implements EventHandler<MouseEvent> {

	private Jugador jugador;
	private VentanaDeJuego ventana;
	
	public MazoHandler(VentanaDeJuego ventana, Jugador jugador) {
		this.jugador = jugador;
		this.ventana = ventana;
	}
	
	public void handle(MouseEvent event) {
		jugador.repartirCarta();
		ventana.actualizarCampoDeJuego();
	}
	
	
	
}
