package eventos;

import Vista.VentanaDeJuego;
import cartas.Monstruo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
		arena.agregarMonstruoAtacado(this.monstruo);
		this.ventana.actualizarCampoDeJuego();
	}
	
	
}
