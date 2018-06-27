package eventos;

import Vista.VentanaDeJuego;
import cartas.Monstruo;
import javafx.scene.shape.Rectangle;
import jugabilidad.Jugador;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class BotonVoltearEnZonaMonstruo implements EventHandler<ActionEvent> {

	private VentanaDeJuego ventana;
	private Jugador jugador;
	private Monstruo monstruo;
	private Object rectangulo;

	public BotonVoltearEnZonaMonstruo(VentanaDeJuego ventanaDeJuego, Jugador jugador, Monstruo monstruo,
			Rectangle rect) {
		this.ventana = ventanaDeJuego;
		this.jugador = jugador;
		this.monstruo = monstruo;
		this.rectangulo = rectangulo;
	}

	@Override
	public void handle(ActionEvent event) {
		monstruo.voltear();
		ventana.actualizarCampoDeJuego();
	}

}
