package jugabilidad;

import Vista.ResultadoDeAtaque;
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
			double vidaAnterior1 = atacante.obtenerVida();
			double vidaAnterior2 = atacante.obtenerRival().obtenerVida();

			atacante.atacarCartaConCarta(monstruoAtacado, monstruoAtacante);

			double vidaResultante1 = atacante.obtenerVida();
			double vidaResultante2 = atacante.obtenerRival().obtenerVida();

			ReglasDeMonstruos.obtener().agregarMonstruoQueAtaco(monstruoAtacante);
			ResultadoDeAtaque resultadoMostrable = ResultadoDeAtaque.obtener();
			
			resultadoMostrable.setMonstruo1(monstruoAtacante);
			resultadoMostrable.setMonstruo2(monstruoAtacado);
			resultadoMostrable.setVidaPerdida1((int)(vidaAnterior1-vidaResultante1));
			resultadoMostrable.setVidaPerdida2((int)(vidaAnterior2-vidaResultante2));
			
			
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
