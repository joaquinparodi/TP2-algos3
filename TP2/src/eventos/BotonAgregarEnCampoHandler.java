package eventos;


import Vista.BaseDeDatosDeCartas;
import Vista.VentanaDeJuego;
import cartas.Carta;
import cartas.Monstruo;
import errores.ErrorSacrificiosInsuficientes;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import jugabilidad.AreaDeSacrificios;
import jugabilidad.Jugador;

public class BotonAgregarEnCampoHandler implements EventHandler<ActionEvent> {
	
	private BaseDeDatosDeCartas database = new BaseDeDatosDeCartas();
	
	private Jugador jugador;
	private VentanaDeJuego ventanaDeJuego;
	private Carta carta;
	private AreaDeSacrificios areaDeSacrificios;

	
	public BotonAgregarEnCampoHandler(VentanaDeJuego ventana, Jugador jugador, Carta carta) {
		this.jugador = jugador;
		this.carta = carta;
		this.ventanaDeJuego = ventana;
		this.areaDeSacrificios = AreaDeSacrificios.obtener();
	}
	
	public void handle(ActionEvent event) {
		if( carta.esMonstruo() ) {
			Monstruo monstruo = (Monstruo)carta;
			Jugador jugador = monstruo.obtenerDuenio();
			if( monstruo.necesitaSacrificiosParaInvocacion() ) {
				try {
					jugador.agregarCartaEnCampo(monstruo, areaDeSacrificios.obtenerSacrificios() );
					areaDeSacrificios.reiniciarSacrificios();
				} catch ( ErrorSacrificiosInsuficientes error ) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText("Sacrificios incompletos");
					alert.setContentText("No se han seleccionado sacrificios suficientes para invocar este monstruo");
					alert.showAndWait();
				}
			} else carta.agregarseEnCampo();
		} else carta.agregarseEnCampo();
		ventanaDeJuego.actualizarCampoDeJuego();
	}

}
