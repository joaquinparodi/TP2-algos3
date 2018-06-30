package Vista;

import cartas.Monstruo;
import cartas.Trampa;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import jugabilidad.Jugador;

public class ResultadoDeAtaque {
	
	private int vida1;
	private int vida2;
	private Monstruo monstruo1;
	private Monstruo monstruo2;
	private Jugador jugador1;
	private Jugador jugador2;
	private Trampa trampa;
	private Stage stage;
	private static ResultadoDeAtaque instancia = null;
	
	private ResultadoDeAtaque () {
		
	}
	
	public static ResultadoDeAtaque obtener () {
		if (instancia == null) {
			instancia = new ResultadoDeAtaque();
		}
		return instancia;
	}
	
	public void setVidaPerdida1 (int vida) {
		vida1 = vida;
	}
	
	public void setVidaPerdida2 (int vida) {
		vida2 = vida;
	}
	
	public void setMonstruo1 (Monstruo monstruo) {
		monstruo1 = monstruo;
		jugador1 = monstruo.obtenerDuenio();
	}
	
	public void setMonstruo2 (Monstruo monstruo) {
		monstruo2 = monstruo;
		jugador2 = monstruo.obtenerDuenio();	
	}
	
	public void reiniciar () {
		vida1 = 0;
		vida2 = 0;
		monstruo1 = null;
		monstruo2 = null;
		jugador1 = null;
		jugador2 = null;
		trampa = null;
	}
	
	public void mostrar () {
		if ( (monstruo1 != null) && (monstruo2 != null) ) {
			Alert alerta = new Alert(AlertType.INFORMATION);
			alerta.initOwner(null);
			alerta.setTitle("Resultado");
			alerta.setHeaderText("Resultado del ataque:");
			String texto = "";
			
			if (jugador1.cartaEstaMuerta(monstruo1)) {
				texto = texto+monstruo1.obtenerNombre()+" de "+jugador1.obtenerNombre()+" ha muerto\n";
			} 
			
			if (jugador2.cartaEstaMuerta(monstruo2)) {
				texto = texto+monstruo2.obtenerNombre()+" de "+jugador2.obtenerNombre()+" ha muerto\n";
			} 
			
			if ((!jugador1.cartaEstaMuerta(monstruo1)) && (!jugador2.cartaEstaMuerta(monstruo2))){
				texto = "Ningun monstruo ha muerto\n";
			}
			
			
			if (trampa != null) {
				texto = texto+jugador2.obtenerNombre()+" ha usado la carta trampa "+trampa.obtenerNombre()+"\n";
			}
			
			texto = texto+(jugador1.obtenerNombre()+" ha perdido "+(vida1+" puntos de vida\n"));
			texto = texto+(jugador2.obtenerNombre()+" ha perdido "+(vida2+" puntos de vida"));
			
			alerta.setContentText(texto);
			
			alerta.showAndWait();
			
			this.reiniciar();
		}	
	}

	public void setTrampaUsada(Trampa trampa) {
		this.trampa = trampa;
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
