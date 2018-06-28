package jugabilidad;

import atributos.Sacrificio;
import cartas.Monstruo;

public class AreaDeSacrificios {
	
	private static AreaDeSacrificios instancia;
	private Sacrificio sacrificio;
	
	private AreaDeSacrificios() {
		sacrificio = new Sacrificio();
	}
	
	public static AreaDeSacrificios obtener() {
		if(instancia == null) instancia = new AreaDeSacrificios();
		return instancia;
	}
	
	public void agregarMonstruo(Monstruo monstruo) {
		sacrificio.agregarCarta(monstruo);
	}

	public Sacrificio obtenerSacrificios() {
		return sacrificio;
	}

	/* utilizados parra actualizar la interfaz*/
	
	public void reiniciarSacrificios() {
		this.sacrificio = new Sacrificio();
	}
	
	public boolean haySacrificios() {
		return sacrificio.tieneCartas();
	}
	
	public boolean hayMasDeUnSacrificios() {
		return sacrificio.obtenerCantidadDeCartas() > 1;
	}
	
	public boolean hayMasDeDosSacrificios() {
		return sacrificio.obtenerCantidadDeCartas() > 2;
	}
	
	public String obtenerNombrePrimerSacrificio() {
		return sacrificio.obtenerNombrePrimerSacrificio();
	}
	
	public String obtenerNombreSegundoSacrificio() {
		return sacrificio.obtenerNombreSegundoSacrificio();
	}
	
	public String obtenerNombreTercerSacrificio() {
		return sacrificio.obtenerNombreTercerSacrificio();
	}
	
	public Monstruo obtenerPrimerSacrificio() {
		return (Monstruo) sacrificio.obtenerCartaDePosicion(0);
	}
	
	public Monstruo obtenerSegundoSacrificio() {
		return (Monstruo) sacrificio.obtenerCartaDePosicion(1);
	}
	
	public Monstruo obtenerTercerSacrificio() {
		return (Monstruo) sacrificio.obtenerCartaDePosicion(2);
	}

	public void cancelarSacrificio(Monstruo sacrificado) {
		sacrificio.eliminarCarta(sacrificado);
	}
	
	
	
}
