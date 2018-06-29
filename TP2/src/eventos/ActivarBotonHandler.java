package eventos;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

public class ActivarBotonHandler implements EventHandler<KeyEvent> {
	
	private Button botonAActivar;
	private TextField esteCampo;
	private TextField elOtroCampo;
	private Text help1;
	private Text help2;
	
	public ActivarBotonHandler (Button boton, TextField campo1, TextField campo2, Text helpText1, Text helpText2) {
		this.botonAActivar = boton;
		this.esteCampo = campo1;
		this.elOtroCampo = campo2;
		this.help1 = helpText1;
		this.help2 = helpText2;
	}
	
	@Override
	public void handle(KeyEvent event) {
		botonAActivar.setDisable(true);
		String text1 = esteCampo.getText();
		String text2 = elOtroCampo.getText();
		
		help2.setFill(Color.WHITE);
		
		
		if (text2.equalsIgnoreCase(text1)) {
			help1.setText("Los nombres no pueden ser iguales");
			help2.setText("");
			help1.setFill(Color.RED);
			help2.setFill(Color.RED);
		}else {
			help1.setText("");
			help2.setText("");
		}
		
		if (text1.length() > 8) {
			help1.setText("El nombre no puede contener más de 8 caracteres");
			help1.setFill(Color.RED);
		}
		
		if (text2.length() > 8) {
			help2.setText("El nombre no puede contener más de 8 caracteres");
			help2.setFill(Color.RED);

		}
		
		if (text1.length() == 0) {
			help1.setText("Ingrese un nombre");
			help1.setFill(Color.WHITE);
		}
		
		if (text2.length() == 0) {
			help2.setText("Ingrese un nombre");
			help2.setFill(Color.WHITE);
		}

		
		if ((text1.length() != 0) && (text2.length() != 0) && (!text1.equalsIgnoreCase(text2) && (text1.length() < 9 ) && (text2.length() < 9)))  {
				botonAActivar.setDisable(false);
				help1.setText("");
				help2.setText("");
				if (event.getCode() == KeyCode.ENTER) {
					botonAActivar.fire();
				}
		}

	}

}
