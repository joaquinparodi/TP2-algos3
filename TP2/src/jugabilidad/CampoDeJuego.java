package jugabilidad;

import java.util.ArrayList;

import cartas.Carta;
import cartas.Magica;
import cartas.Monstruo;
import cartas.PosicionMonstruo;


public class CampoDeJuego {

	private ArrayList<Monstruo> filaMonstruos = new ArrayList<Monstruo>();
	private ArrayList<Magica> filaMagicas = new ArrayList<Magica>();
	private ArrayList<Carta> mano = new ArrayList<Carta>();
	private ArrayList<Carta> cementerio = new ArrayList<Carta>();
	private Mazo mazo = new Mazo();

	public void invocarMonstruo(Monstruo unMonstruo, PosicionMonstruo posicion) {
		mano.remove(unMonstruo);
		unMonstruo.asignarPosicion(posicion);
		filaMonstruos.add(unMonstruo);		
	}
}
