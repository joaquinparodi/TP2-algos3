package eventos;

import java.util.ArrayList;

import Vista.BaseDeDatosDeCartas;
import Vista.VentanaDeJuego;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import jugabilidad.Baraja;
import jugabilidad.Jugador;

public class BotonAgregarEnCampoHandler implements EventHandler<ActionEvent> {
	
	private BaseDeDatosDeCartas database = new BaseDeDatosDeCartas();
	
	private Jugador jugador;
	private Baraja manoJugador;
	private VentanaDeJuego ventanaDeJuego;
	private int index;

	
	public BotonAgregarEnCampoHandler(VentanaDeJuego ventana, Baraja manoJugador, Jugador jugador, int index) {
		this.jugador = jugador;
		this.index = index;
		this.ventanaDeJuego = ventana;
		this.manoJugador = manoJugador;
	}
	
	public void handle(ActionEvent event) {
		jugador.agregarCartaDeManoEnCampo(index);
		ventanaDeJuego.actualizarCampoDeJuego();
	}

}
