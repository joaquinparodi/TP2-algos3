package eventos;

import Vista.VentanaDeJuego;
import cartas.Monstruo;
import errores.ErrorAtaqueDesdePosicionInvalida;
import errores.ErrorIntentaAtacarACartaPropia;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import jugabilidad.ArenaDeCombate;

public class BotonAtacar implements EventHandler<ActionEvent> {

	private ArenaDeCombate arena;
	private Monstruo monstruo;
	private VentanaDeJuego ventana;
	
	public BotonAtacar(Monstruo monstruo, VentanaDeJuego ventana) {
		this.arena = ArenaDeCombate.obtener();
		this.monstruo = monstruo;
		this.ventana = ventana;
	}
	
	public void handle(ActionEvent event) {
		try {
			arena.agregarMonstruoAtacado(this.monstruo);			
		}catch(ErrorIntentaAtacarACartaPropia error) {
			
		}catch(ErrorAtaqueDesdePosicionInvalida error) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(ventana.getStage());
			alert.setTitle("Error");
			alert.setHeaderText("Ataque desde posicion invalida");
			alert.setContentText("No se puede atacar con un monstruo que esta en posicion de defensa");
			alert.showAndWait();
		}
		this.ventana.actualizarCampoDeJuego();
	}
	
	
}
