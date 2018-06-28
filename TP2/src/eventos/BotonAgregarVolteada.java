package eventos;

import Vista.VentanaDeJuego;
import cartas.Carta;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class BotonAgregarVolteada implements EventHandler<ActionEvent> {
		

	private VentanaDeJuego ventanaDeJuego;
	private Carta carta;

		
	public BotonAgregarVolteada(VentanaDeJuego ventana, Carta carta) {
		this.carta = carta;
		this.ventanaDeJuego = ventana;
	}
		
	public void handle(ActionEvent event) {
		carta.voltear();
		carta.agregarseEnCampo();
		ventanaDeJuego.actualizarCampoDeJuego();
	}
	
}
