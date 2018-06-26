package jugabilidad;

public class FaseFinal implements Fase {

	@Override
	public Fase avanzarFase() {
		Controlador.obtener().cambiarTurno();
		return new FasePreparacion();
	}

	@Override
	public String obtenerNombre() {
		return "Final";
	}

}
