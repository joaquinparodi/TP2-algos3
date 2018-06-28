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
		botonAActivar.setDisable(true);
		String text1 = esteCampo.getText();
		String text2 = elOtroCampo.getText();
		if ((text1.length() != 0) && (text2.length() != 0) && (!text1.equalsIgnoreCase(text2))) {
				botonAActivar.setDisable(false);
				if (event.getCode() == KeyCode.ENTER) {
					botonAActivar.fire();
				}
		}

	}

}
