package atributos;

import java.util.Iterator;
import cartas.Carta;
import cartas.Monstruo;
import errores.ErrorMonstruoYaSacrificado;
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

	public Iterator<Carta> iterator() {
		return contenedor.iterator();
	}
	
	public boolean hayUnSacrificio() {
		return !contenedor.isEmpty();
	}

	public boolean hayMasDeUnSacrificio() {
		return contenedor.size() > 2;
	}
	
	public boolean hayMasDeDosSacrificios() {
		return contenedor.size() > 3;
	}
	
	public String obtenerNombrePrimerSacrificio() {
		return contenedor.get(0).obtenerNombre();
	}
	
	public String obtenerNombreSegundoSacrificio() {
		return contenedor.get(1).obtenerNombre();
	}
	
	public String obtenerNombreTercerSacrificio() {
		return contenedor.get(2).obtenerNombre();
	}
	
	@Override
	public void agregarCarta(Carta carta) {
		this.controlarSacrificioAgregado(carta);
		contenedor.add(carta);
	}
	
	private void controlarSacrificioAgregado(Carta carta) {
		if(contenedor.contains(carta)) throw new ErrorMonstruoYaSacrificado();
	}
}
