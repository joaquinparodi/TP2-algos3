package eventos;

import Vista.VentanaDeJuego;
import cartas.Carta;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class BotonAgregarRotada implements EventHandler<ActionEvent> {


	private VentanaDeJuego ventanaDeJuego;
	private Carta carta;

	
	public BotonAgregarRotada(VentanaDeJuego ventana, Carta carta) {
		this.ventanaDeJuego = ventana;
		this.carta = carta;
	}
	
	public void handle(ActionEvent event) {
		carta.cambiarPosicion();
		carta.agregarseEnCampo();
		ventanaDeJuego.actualizarCampoDeJuego();
	}
	
}
