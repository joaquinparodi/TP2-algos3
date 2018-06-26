package eventos;

import java.util.ArrayList;

import Vista.VentanaDeJuego;
import cartas.Carta;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import jugabilidad.Baraja;
import jugabilidad.Controlador;
import jugabilidad.Jugador;

public class CartasManoHandler implements EventHandler<ContextMenuEvent> {

	private VentanaDeJuego ventanaDeJuego;
	private Jugador jugador;
	private Node nodo;
	private Carta carta;
	private int index;
	
	
	public CartasManoHandler(VentanaDeJuego ventana, Baraja manoJugador, Jugador jugador, int index, Node nodo) {
		this.jugador = jugador;
		this.nodo = nodo;
		this.index = index;
		this.ventanaDeJuego = ventana;
	}
	
	public void handle(ContextMenuEvent event) {
		if ((Controlador.obtener().esElTurnoDe(jugador)) && (Controlador.obtener().obtenerFase() == "Preparacion")) {
			ContextMenu menuDesplegable = new ContextMenu();
			Rectangle rect = (Rectangle)event.getSource();
			
			if (carta.esDeMagia()) {
				MenuItem agregarEnCampo = new MenuItem("Agregar en campo");
				menuDesplegable.getItems().addAll(agregarEnCampo);
				BotonAgregarVolteada handlerAgregarVolteada = new BotonAgregarVolteada(ventanaDeJuego, jugador, index);			
				agregarEnCampo.setOnAction(handlerAgregarVolteada);
			}
			
			if (carta.esMonstruo()) {
				
				MenuItem agregarEnCampo = new MenuItem("Agregar en campo");
				MenuItem agregarRotada = new MenuItem("Agregar rotada");
				MenuItem agregarVolteada = new MenuItem("Agregar volteada");
				menuDesplegable.getItems().addAll(agregarEnCampo,agregarRotada,agregarVolteada);

				BotonAgregarEnCampoHandler handlerAgregar = new BotonAgregarEnCampoHandler(ventanaDeJuego, jugador, carta);
				BotonAgregarRotada handlerAgregarRotada = new BotonAgregarRotada(ventanaDeJuego, jugador, index);
				BotonAgregarVolteada handlerAgregarVolteada = new BotonAgregarVolteada(ventanaDeJuego, jugador, index);			
				
				agregarEnCampo.setOnAction(handlerAgregar);
				agregarRotada.setOnAction(handlerAgregarRotada);
				agregarVolteada.setOnAction(handlerAgregarVolteada);			
			}
			
			if (carta.esDeCampo()) {
				MenuItem agregarEnCampo = new MenuItem("Agregar en campo");
				menuDesplegable.getItems().addAll(agregarEnCampo);

				BotonAgregarEnCampoHandler handlerAgregar = new BotonAgregarEnCampoHandler(ventanaDeJuego, jugador, carta);
				agregarEnCampo.setOnAction(handlerAgregar);
			}
			
			if (carta.esDeTrampa()) {
				MenuItem agregarEnCampo = new MenuItem("Agregar en campo");
				menuDesplegable.getItems().addAll(agregarEnCampo);
				BotonAgregarVolteada handlerAgregarVolteada = new BotonAgregarVolteada(ventanaDeJuego, jugador, index);			
				agregarEnCampo.setOnAction(handlerAgregarVolteada);

			}
			menuDesplegable.show(nodo, event.getScreenX(), event.getScreenY());
	
		}
	}

	public void asignarCartaEnPoscion(Carta carta) {
		this.carta = carta;
	}
}
