package eventos;

import Vista.VentanaDeJuego;
import cartas.Monstruo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import jugabilidad.Jugador;

public class BotonRotarEnZonaMonstruo implements EventHandler<ActionEvent> {

	VentanaDeJuego ventana;
	Jugador jugador;
	Rectangle rect;
	int index;
	
	public BotonRotarEnZonaMonstruo(VentanaDeJuego ventana, Jugador jugador, int index, Rectangle rect) {
		this.jugador = jugador;
		this.index = index;
		this.ventana = ventana;
		this.rect = rect;
	}

	public void handle(ActionEvent event) {
		if( jugador.cartaMonstruoEnCampoEstaRotada(index) ) this.ponerEnPosicionDeAtaque();
		else  this.ponerEnPosicionDeDefensa();
 		
		jugador.rotarCartaMonstruoEnCampo(index);
		ventana.actualizarCampoDeJuego();
	}
	
	private void ponerEnPosicionDeAtaque() {
		double ancho = 70; double altura = 100;
		double centroDeRotacionX = (ancho) / 2;
		double centroDeRotacionY = (altura) / 2;

		rect.getTransforms().add(new Rotate(90, centroDeRotacionX, centroDeRotacionY));
	}
	
	private void ponerEnPosicionDeDefensa() {
		double ancho = 70; double altura = 100;
		double centroDeRotacionX = (ancho) / 2;
		double centroDeRotacionY = (altura) / 2;

		rect.getTransforms().add(new Rotate(-90, centroDeRotacionX, centroDeRotacionY));
	}
}
