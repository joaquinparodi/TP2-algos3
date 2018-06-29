package jugabilidad;

import cartas.Monstruo;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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

			Alert alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Resultado");
			alerta.setHeaderText("Resultado del ataque:");
			String texto = "";
			
			if (atacante.cartaEstaMuerta(monstruoAtacante)) {
				texto = texto+monstruoAtacante.obtenerNombre()+" ha muerto\n";
			} 
			
			if (atacante.obtenerRival().cartaEstaMuerta(monstruoAtacado)) {
				texto = texto+monstruoAtacado.obtenerNombre()+" ha muerto\n";
			} 
			
			if ((!atacante.cartaEstaMuerta(monstruoAtacante)) && (!atacante.obtenerRival().cartaEstaMuerta(monstruoAtacado))){
				texto = "Ningun monstruo ha muerto\n";
			}
			
			
			texto = texto+(atacante.obtenerNombre()+" ha perdido "+((int)vidaAnterior1-(int)vidaResultante1)+" puntos de vida\n");
			texto = texto+(atacante.obtenerRival().obtenerNombre()+" ha perdido "+((int)vidaAnterior2-(int)vidaResultante2)+" puntos de vida");
			
			alerta.setContentText(texto);
			
			alerta.showAndWait();
			
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
