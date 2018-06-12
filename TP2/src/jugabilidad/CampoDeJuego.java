package jugabilidad;

import java.util.ArrayList;

import cartas.Carta;
import cartas.Magica;
import cartas.Monstruo;
import cartas.PosicionMonstruo;
import eg.edu.guc.yugioh.cards.MonsterCard;


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

	public void invocarMonstruo(Monstruo unMonstruo, PosicionMonstruo posicion, ArrayList<Monstruo> monstruosASacrificar) {
		mano.remove(unMonstruo);
		unMonstruo.asignarPosicion(posicion);
		if (unMonstruo.obtenerNivel() == 5 || unMonstruo.obtenerNivel() == 6) {
			monstruosASacrificar.remove(0);
		} else if(unMonstruo.obtenerNivel() > 6) {
			monstruosASacrificar.remove(0);
			monstruosASacrificar.remove(0);
		}
		filaMonstruos.add(unMonstruo);		
	}
	
}
