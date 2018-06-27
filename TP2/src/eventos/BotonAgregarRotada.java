package eventos;

import Vista.BaseDeDatosDeCartas;
import Vista.VentanaDeJuego;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import jugabilidad.Jugador;

public class BotonAgregarRotada implements EventHandler<ActionEvent> {

	private BaseDeDatosDeCartas database = new BaseDeDatosDeCartas();
	
	private Jugador jugador;
	private VentanaDeJuego ventanaDeJuego;
	private int index;

	
	public BotonAgregarRotada(VentanaDeJuego ventana, Jugador jugador, int index) {
		this.jugador = jugador;
		this.index = index;
		this.ventanaDeJuego = ventana;
	}
	
	public void handle(ActionEvent event) {
		jugador.rotarCartaEnMano(index);
		jugador.agregarCartaDeManoEnCampo(index);
		ventanaDeJuego.actualizarCampoDeJuego();
	}
	
}
