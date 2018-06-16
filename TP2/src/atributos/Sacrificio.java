package atributos;

import java.util.Iterator;
import cartas.Carta;
import cartas.Monstruo;
import jugabilidad.Baraja;
import jugabilidad.CampoDeJuego;

public class Sacrificio extends Baraja {
	
	public Sacrificio() {
		super();
	}
	
	public void enviarSacrificiosAlCementerio( CampoDeJuego campo ) {
		Iterator<Carta> iter = contenedor.iterator();
		while(iter.hasNext()) {
		    	Monstruo monstruo = (Monstruo) iter.next();
		    	campo.enviarAlCementerio(monstruo);
		}
	}

}
