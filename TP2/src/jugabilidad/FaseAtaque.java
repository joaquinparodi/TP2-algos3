package jugabilidad;

public class FaseAtaque implements Fase {

	@Override
	public Fase avanzarFase() {
		return new FaseFinal();
	}

	@Override
	public String obtenerNombre() {
		return "Ataque";
	}

}
