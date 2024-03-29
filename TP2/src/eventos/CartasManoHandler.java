package eventos;


import Vista.VentanaDeJuego;
import cartas.Carta;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
import jugabilidad.Baraja;
import jugabilidad.Controlador;
import jugabilidad.Jugador;
import jugabilidad.ReglasDeMonstruos;

public class CartasManoHandler implements EventHandler<ContextMenuEvent> {

	private VentanaDeJuego ventanaDeJuego;
	private Jugador jugador;
	private Node nodo;
	private Carta carta;

	public CartasManoHandler(VentanaDeJuego ventana, Baraja manoJugador, Jugador jugador, Node nodo) {
		this.jugador = jugador;
		this.nodo = nodo;
		this.ventanaDeJuego = ventana;
	}
	
	public void handle(ContextMenuEvent event) {
		if ((Controlador.obtener().esElTurnoDe(jugador)) && (Controlador.obtener().obtenerFase() == "Preparacion")) {
			ContextMenu menuDesplegable = new ContextMenu();
			
			if (carta.esDeMagia()) {
				MenuItem agregarEnCampo = new MenuItem("Agregar en campo");
				menuDesplegable.getItems().addAll(agregarEnCampo);
				BotonAgregarVolteada handlerAgregarVolteada = new BotonAgregarVolteada(ventanaDeJuego, carta);			
				agregarEnCampo.setOnAction(handlerAgregarVolteada);
			}
			
			if (carta.esMonstruo()) {
				
				MenuItem agregarEnCampo = new MenuItem("Agregar en campo");
				if (ReglasDeMonstruos.obtener().algunaCartaFueAgregadaEsteTurno()) {
					agregarEnCampo.setDisable(true);
				}

				menuDesplegable.getItems().addAll(agregarEnCampo);

				BotonAgregarEnCampoHandler handlerAgregar = new BotonAgregarEnCampoHandler(ventanaDeJuego, jugador, carta);	
				agregarEnCampo.setOnAction(handlerAgregar);

				
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
				BotonAgregarVolteada handlerAgregarVolteada = new BotonAgregarVolteada(ventanaDeJuego, carta);			
				agregarEnCampo.setOnAction(handlerAgregarVolteada);

			}
			menuDesplegable.show(nodo, event.getScreenX(), event.getScreenY());
	
		}
	}

	public void asignarCartaEnPoscion(Carta carta) {
		this.carta = carta;
	}
}
