package eventos;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ActivarBotonHandler implements EventHandler<KeyEvent> {
	
	private Button botonAActivar;
	private TextField esteCampo;
	private TextField elOtroCampo;
	
	public ActivarBotonHandler (Button boton, TextField campo1, TextField campo2) {
		this.botonAActivar = boton;
		this.esteCampo = campo1;
		this.elOtroCampo = campo2;
	}
	
	@Override
	public void handle(KeyEvent event) {
		
		if ((esteCampo.getText().length() != 0) && (elOtroCampo.getText().length() !=0 )) {
				botonAActivar.setDisable(false);
		}
		else {	
			botonAActivar.setDisable(true);
		}
	}

}
