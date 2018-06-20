package atributos;

import cartas.Atacable;
import cartas.Monstruo;
import jugabilidad.Jugador;

public class EfectoCilindro extends EfectoDeTrampa {

	public void aplicar(Atacable monstruoAtacante, Jugador jugadorRival) {
		jugadorRival.hacerDanio(monstruoAtacante.obtenerPuntos().obtenerPuntosDeAtaque());	
	}
}
