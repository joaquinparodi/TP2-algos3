package eventos;

import jugabilidad.Jugador;

public class TextFieldNombreHandler {

	Jugador jugador;
	
	public TextFieldNombreHandler(Jugador jugador) {
		this.jugador = jugador;
	}
	
	public void asignarNombreObtenido(String nombreJugador) {
		this.jugador.asignarNombre(nombreJugador);
	}
	
}
