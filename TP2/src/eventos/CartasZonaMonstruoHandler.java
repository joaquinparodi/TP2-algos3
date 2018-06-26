package eventos;

import Vista.VentanaDeJuego;
import cartas.Monstruo;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.shape.Rectangle;
import jugabilidad.Controlador;
import jugabilidad.Jugador;

public class CartasZonaMonstruoHandler implements EventHandler<ContextMenuEvent> {

	private VentanaDeJuego ventanaDeJuego;
	private Jugador jugador;
	private Rectangle rect;
	private Monstruo monstruo;
	private int index;
	
	public CartasZonaMonstruoHandler(VentanaDeJuego ventana, Jugador jugador, int index, Rectangle rect) {
		this.jugador = jugador;
		this.rect = rect;
		this.index = index;
		this.ventanaDeJuego = ventana;
	}
	
	public void handle(ContextMenuEvent event) {
		Controlador controlador = Controlador.obtener();
		
		if(controlador.partidaEstaEnFase("Preparacion") && controlador.esElTurnoDe(jugador)) {
			ContextMenu menuDesplegable = new ContextMenu();
	
			MenuItem rotar = new MenuItem("rotar");
			menuDesplegable.getItems().addAll(rotar);

			BotonRotarEnZonaMonstruo handler = new BotonRotarEnZonaMonstruo(ventanaDeJuego, jugador, index, rect);
			rotar.setOnAction(handler);
		
			menuDesplegable.show(rect, event.getScreenX(), event.getScreenY());
		}
		
		if(Controlador.obtener().partidaEstaEnFase("Ataque") && !controlador.esElTurnoDe(jugador)) {
			ContextMenu menuDesplegable = new ContextMenu();
	
			MenuItem atacar = new MenuItem("Atacar");
			menuDesplegable.getItems().addAll(atacar);
			BotonAtacar handler = new BotonAtacar(monstruo, ventanaDeJuego);
			atacar.setOnAction(handler);
		
			menuDesplegable.show(rect, event.getScreenX(), event.getScreenY());
		}
		
		if(Controlador.obtener().partidaEstaEnFase("Ataque") && controlador.esElTurnoDe(jugador)) {
			ContextMenu menuDesplegable = new ContextMenu();
			
			MenuItem seleccionarAtacante = new MenuItem("Seleccionar como atacante");
			menuDesplegable.getItems().addAll(seleccionarAtacante);
			BotonSeleccionarAtacante handler = new BotonSeleccionarAtacante(monstruo, ventanaDeJuego);
			seleccionarAtacante.setOnAction(handler);
			
			menuDesplegable.show(rect, event.getScreenX(), event.getScreenY());
		}
	}


	public void asignarMonstruoEnPosicion(Monstruo monstruo) {
		this.monstruo = monstruo;
	}

}
