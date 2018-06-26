package eventos;

import Vista.VentanaDeJuego;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import jugabilidad.Jugador;

public class BotonAgregarVolteada implements EventHandler<ActionEvent> {
		
	private Jugador jugador;
	private VentanaDeJuego ventanaDeJuego;
	private int index;

		
	public BotonAgregarVolteada(VentanaDeJuego ventana, Jugador jugador, int index) {
		this.jugador = jugador;
		this.index = index;
		this.ventanaDeJuego = ventana;
	}
		
	public void handle(ActionEvent event) {
		jugador.voltearCartaEnMano(index);
		jugador.agregarCartaDeManoEnCampo(index);
		ventanaDeJuego.actualizarCampoDeJuego();
	}
	
}
