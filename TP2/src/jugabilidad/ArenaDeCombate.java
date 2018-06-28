package jugabilidad;

import cartas.Monstruo;

public class ArenaDeCombate {

	/*hacer pruebas*/
	
	private static ArenaDeCombate instancia;
	private Monstruo monstruoAtacado;
	private Monstruo monstruoAtacante;
	
	private ArenaDeCombate() {
		
	}
	
	public static ArenaDeCombate obtener() {
		if(instancia == null) instancia = new ArenaDeCombate();
		return instancia;
	}

	public void agregarMonstruoAtacado(Monstruo atacado) {
		this.monstruoAtacado = atacado;
		this.realizarAtaqueSiPosible();
	}
	
	public void agregarMonstruoAtacante(Monstruo atacante) {
		this.monstruoAtacante = atacante;
		this.realizarAtaqueSiPosible();
	}
	
	private void realizarAtaqueSiPosible() {
		if(this.monstruoAtacado != null && this.monstruoAtacante != null ) {
			Jugador atacante = monstruoAtacante.obtenerDuenio();
			atacante.atacarCartaConCarta(monstruoAtacado, monstruoAtacante);
			ReglasDeMonstruos.obtener().agregarMonstruoQueAtaco(monstruoAtacante);
			/*seteo los mosntruos a null nuevamente*/
			this.monstruoAtacado = null;
			this.monstruoAtacante = null;
		}
	}

	public boolean tieneMonstruoAtacante() {
		return monstruoAtacante != null;
	}
	
	public boolean tieneMonstruoAtacado() {
		return monstruoAtacado != null;
	}
	
	public String obtenerNombreMonstruoAtacado() {
		return monstruoAtacado.obtenerNombre();
	}
	
	public String obtenerNombreMonstruoAtacante() {
		return monstruoAtacante.obtenerNombre();
	}

	public void reiniciarCombatientes() {
		this.monstruoAtacante = null;
		this.monstruoAtacado = null;
		
	}
	
}
