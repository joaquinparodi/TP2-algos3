package atributos;

import cartas.Atacable;
import cartas.Monstruo;
import cartas.Trampa;
import jugabilidad.Jugador;

public class EfectoCilindroMagico extends EfectoDeTrampa {

	public void aplicar(Atacable atacante, Atacable atacado, Jugador jugadorRival, Trampa trampaDuenia) {
		jugadorRival.hacerDanio(atacante.obtenerPuntos().obtenerPuntosDeAtaque());	
		atacante.enviarAlCementerio();
		trampaDuenia.enviarAlCementerio();
	}

}