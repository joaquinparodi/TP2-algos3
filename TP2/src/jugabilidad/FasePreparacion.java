package jugabilidad;

public class FasePreparacion implements Fase {

	@Override
	public Fase avanzarFase() {
		return new FaseAtaque();
	}

	@Override
	public String obtenerNombre() {
		return "Preparacion";
	}
	
}
