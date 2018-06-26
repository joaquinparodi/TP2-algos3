package eventos;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import jugabilidad.ArenaDeCombate;

public class BotonAtacar implements EventHandler<ActionEvent> {

	private ArenaDeCombate arena;
	private int index;
	
	public BotonAtacar(int index) {
		this.arena = ArenaDeCombate.obtener();
		this.index = index;
	}
	
	public void handle(ActionEvent event) {
		
		
	}
	
	
}
