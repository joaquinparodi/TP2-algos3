package eventos;

import cartas.Monstruo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import jugabilidad.AreaDeSacrificios;

public class BotonSacrificar implements EventHandler<ActionEvent> {

	private Monstruo monstruo;
	
	public BotonSacrificar(Monstruo monstruo) {
		this.monstruo = monstruo;
	}
	
	public void handle(ActionEvent event) {
		AreaDeSacrificios.obtener().agregarMonstruo(this.monstruo);
	}

}
