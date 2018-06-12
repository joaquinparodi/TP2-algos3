package jugabilidad;

import java.util.ArrayList;

import cartas.Carta;
import cartas.Magica;
import cartas.Monstruo;
import posiciones.PosicionMonstruo;


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
		if (unMonstruo.obtenerEstrellas() == 5 || unMonstruo.obtenerEstrellas() == 6) {
			monstruosASacrificar.remove(0);
		} else if(unMonstruo.obtenerEstrellas() > 6) {
			monstruosASacrificar.remove(0);
			monstruosASacrificar.remove(0);
		}
		filaMonstruos.add(unMonstruo);		
	}
	
	public void enviarCartaACementerio(Monstruo monstruo) {
		cementerio.add(monstruo);
	}
	
	public boolean cartaPerteneceAlCementerio(Monstruo monstruo) {
		return cementerio.contains(monstruo);
	}
	
}