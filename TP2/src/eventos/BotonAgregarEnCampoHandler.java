package eventos;

import java.util.ArrayList;

import Vista.BaseDeDatosDeCartas;
import Vista.VentanaDeJuego;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import jugabilidad.Jugador;

public class BotonAgregarEnCampoHandler implements EventHandler<ActionEvent> {
	
	private BaseDeDatosDeCartas database = new BaseDeDatosDeCartas();
	private Jugador jugador;
	private int index;
	ArrayList<Rectangle> STZone;
	ArrayList<Rectangle> MZone;
	
	public BotonAgregarEnCampoHandler(VentanaDeJuego vetana, ArrayList<Rectangle> STZone, ArrayList<Rectangle> MZone, Jugador jugador, int index) {
		this.jugador = jugador;
		this.index = index;
		this.STZone = STZone;
		this.MZone = MZone;
	}
	
	public void handle(ActionEvent event) {
		//Lanzar errores
		
		jugador.agregarCartaDeManoEnCampo(index);
		int tam = STZone.size(); 
		String nombreCarta = jugador.obtenerMano().obtenerCartaDePosicion(index).obtenerNombre();
		ImagePattern img = new ImagePattern( new Image( database.getURL(nombreCarta) ) );
		if(tam != 0) STZone.get(tam - 1).setFill( img );
		else STZone.get(tam).setFill(img);
	}

}
