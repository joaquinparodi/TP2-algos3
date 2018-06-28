package eventos;

import Vista.VentanaDeJuego;
import cartas.Carta;
import cartas.Monstruo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import jugabilidad.AreaDeSacrificios;

public class BotonNoSacrificar implements EventHandler<ActionEvent> {

	private Monstruo sacrificado;
	private VentanaDeJuego ventana;
	
	public BotonNoSacrificar(Carta carta, VentanaDeJuego ventana) {
		this.sacrificado = (Monstruo)carta;
		this.ventana = ventana;
	}

	public void handle(ActionEvent event) {
		AreaDeSacrificios.obtener().cancelarSacrificio(sacrificado);
		this.ventana.actualizarCampoDeJuego();
	}
}
