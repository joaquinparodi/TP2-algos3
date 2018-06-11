package jugabilidad;
import java.util.*;
import cartas.Carta;

public class Cementerio{
	
	private static Cementerio instanciaDeCementerio = null;
	private LinkedList<Carta> listaDeCartas;
	
	private Cementerio () {
		listaDeCartas = new LinkedList<Carta> ();
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
	
	public boolean tieneCarta (Carta unaCarta) {
		return listaDeCartas.contains(unaCarta);
	}
}
