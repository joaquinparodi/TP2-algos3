package eventos;

import Vista.VentanaDeJuego;
import cartas.Monstruo;
import javafx.scene.shape.Rectangle;
import jugabilidad.Jugador;
import jugabilidad.ReglasDeMonstruos;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class BotonVoltearEnZonaMonstruo implements EventHandler<ActionEvent> {

	private VentanaDeJuego ventana;
	private Monstruo monstruo;

	public BotonVoltearEnZonaMonstruo(VentanaDeJuego ventanaDeJuego, Jugador jugador, Monstruo monstruo,
			Rectangle rect) {
		this.ventana = ventanaDeJuego;
		this.monstruo = monstruo;
	}

	@Override
	public void handle(ActionEvent event) {
		monstruo.voltear();
		ReglasDeMonstruos.obtener().agregarMonstruoQueFueVolteadoEnEsteTurno(monstruo);
		ventana.actualizarCampoDeJuego();
	}

}
