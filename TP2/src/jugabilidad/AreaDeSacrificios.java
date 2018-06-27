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

	public void reiniciarSacrificios() {
		this.sacrificio = new Sacrificio();
	}
	
	
	
}
