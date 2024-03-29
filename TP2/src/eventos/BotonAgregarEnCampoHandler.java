package eventos;


import Vista.VentanaDeJuego;
import cartas.Carta;
import cartas.Monstruo;
import errores.ErrorSacrificiosInsuficientes;
import errores.ErrorYaHayCartaDeCampoInvocada;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import jugabilidad.AreaDeSacrificios;
import jugabilidad.Jugador;
import jugabilidad.ReglasDeMonstruos;

public class BotonAgregarEnCampoHandler implements EventHandler<ActionEvent> {
		
	private VentanaDeJuego ventanaDeJuego;
	private Carta carta;
	private AreaDeSacrificios areaDeSacrificios;

	
	public BotonAgregarEnCampoHandler(VentanaDeJuego ventana, Jugador jugador, Carta carta) {
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
					int cantidadDeSacrificios = monstruo.obtenerCantidadDeSacrificiosNecesarios();
					Alert alert = new Alert(AlertType.ERROR);
					alert.initOwner(ventanaDeJuego.getStage());
					alert.setTitle("Error");
					alert.setHeaderText("Sacrificios incompletos");
					alert.setContentText("Esta carta necesita " + cantidadDeSacrificios + " sacrificios para invocarse");
					alert.showAndWait();
				}
			} else {
				carta.agregarseEnCampo(); 
				ReglasDeMonstruos.obtener().agregarMonstruoQueFueAgregadoEnEsteTurno(monstruo);
			}
			
			
		} else {
			try {
				carta.agregarseEnCampo();				
			}catch(ErrorYaHayCartaDeCampoInvocada error) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.initOwner(ventanaDeJuego.getStage());
				alert.setTitle("Error");
				alert.setHeaderText("Carta de campo");
				alert.setContentText("Ya has invocado una carta de campo");
				alert.showAndWait();				
			}
		}
		ventanaDeJuego.actualizarCampoDeJuego();
	}

}
