package jugabilidad;

import java.util.ArrayList;

import cartas.Monstruo;

public class ReglasDeMonstruos {
	
	private ArrayList<Monstruo> monstruosQueAtacaron;
	private ArrayList<Monstruo> monstruosQueSeAgregaron;
	private static ReglasDeMonstruos instancia;
	
	private ReglasDeMonstruos () {
		monstruosQueAtacaron = new ArrayList<Monstruo>();
		monstruosQueSeAgregaron = new ArrayList<Monstruo>();
	}
	
	public static ReglasDeMonstruos obtener() {
		if (instancia == null) {	
			instancia = new ReglasDeMonstruos();
		}
		return instancia;
	}
	
	public void reiniciar() {
		monstruosQueAtacaron.clear();
		monstruosQueSeAgregaron.clear();
	}
	
	public boolean monstruoAtaco(Monstruo monstruo) {
		return monstruosQueAtacaron.contains(monstruo);
	}
	
	public boolean monstruoFueAgregadoEnEsteTurno(Monstruo monstruo) {
		return monstruosQueSeAgregaron.contains(monstruo);
	}
	
	public boolean agregarMonstruoQueAtaco(Monstruo monstruo) {
		return monstruosQueAtacaron.add(monstruo);
	}
	
	public boolean agregarMonstruoQueFueAgregadoEnEsteTurno(Monstruo monstruo) {
		return monstruosQueSeAgregaron.add(monstruo);
	}
	
	public boolean algunaCartaFueAgregadaEsteTurno() {
		return (this.monstruosQueSeAgregaron.size() != 0);
	}

}
