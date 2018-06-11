package jugabilidad;
import java.util.*;
import cartas.Carta;

public class Cementerio{
	
	private static Cementerio instanciaDeCementerio;
	private LinkedList<Carta> listaDeCartas;
	
	private Cementerio () {
		
	}
	
	public static Cementerio obtenerCementerio () {
		if (instanciaDeCementerio == null) {
			instanciaDeCementerio = new Cementerio ();
		}
		return instanciaDeCementerio;
	}
	
	public void agregarCarta (Carta unaCarta) {
		listaDeCartas.add(unaCarta);
	}
}
