package Vista;

import java.util.HashMap;

public class BaseDeDatosDeCartas {

	HashMap<String, String> URL;
	
	private final String devoradorDeNiveles = "file:images/Devorador_de_niveles.jpg";
	private final String gusanoAguja = "file:images/Gusano_aguja.jpg";
	private final String jiraiGumo = "file:images/Jirai_gumo.jpg";
	private final String parasitoParacida = "file:images/Parasito_paracida.jpg";
	private final String sangan = "file:images/Sangan.jpg";
	private final String capulloEvolutivo = "file:images/Capullo_evolutivo.jpg";
	private final String gigobyte = "file:images/gigobytekq9.jpg";
	private final String agujeroNegro = "file:images/agujero.jpg";
	private final String cilindroMagico = "file:images/cilindro.jpg";
	private final String fisura = "file:images/fisura.jpg";
	private final String ollaDeLaCodicia = "file:images/olla.jpg";
	private final String reinforcement = "file:images/rein.jpg";
	private final String sogen = "file:images/sogen.jpg";
	private final String wasteland = "file:images/wasteland.jpg";
	private final String dragonDefinitivo = "file:images/dragonDefinitivo.jpg";
	private final String dragonBlancoDeOjosAzules = "file:images/dragonBlanco.jpg";
	private final String cabezaDeExodia = "file:images/cabeza.jpg";
	private final String brazoDerechoDeExodia = "file:images/brazoDerecho.jpg";
	private final String brazoizquierdoDeExodia = "file:images/brazoIzquierdo.jpg";
	private final String piernaIzquierdaDeExodia = "file:images/piernaIzquierda.jpg";
	private final String piernaDerechaDeExodia = "file:images/piernaDerecha.jpg";
	private final String insectoComeHombres = "file:images/insecto.png";
	private final String jinzo7 = "file:images/jinzo.jpg";
	
	public BaseDeDatosDeCartas() {
		URL = new HashMap<String, String>();
		this.cargarHashMap();
	}
	
	public String getURL(String nombreMonstruo) {
		return URL.get(nombreMonstruo);
	}
	
	private void cargarHashMap() {
		URL.put("Devorador De Niveles", devoradorDeNiveles);
		URL.put("Gusano Aguja", gusanoAguja);
		URL.put("Jirai Gumo", jiraiGumo);
		URL.put("Parasito Paracida", parasitoParacida);
		URL.put("Sangan", sangan);
		URL.put("Capullo Evolutivo", capulloEvolutivo);
		URL.put("Gigobyte", gigobyte);
		URL.put("Agujero Negro", agujeroNegro);
		URL.put("Cilindro Magico", cilindroMagico);
		URL.put("Fisura", fisura);
		URL.put("Olla De La Codicia", ollaDeLaCodicia);
		URL.put("Reinforcements", reinforcement);
		URL.put("Sogen", sogen);
		URL.put("Wasteland", wasteland);
		URL.put("Dragon Definitivo", dragonDefinitivo);
		URL.put("Dragon Blanco Ojos Azules", dragonBlancoDeOjosAzules);
		URL.put("Cabeza Exodia", cabezaDeExodia);
		URL.put("Brazo Derecho Exodia", brazoDerechoDeExodia);
		URL.put("Brazo Izquierdo Exodia", brazoizquierdoDeExodia);
		URL.put("Pierna Derecha Exodia", piernaDerechaDeExodia);
		URL.put("Pierna Izquierda Exodia", piernaIzquierdaDeExodia);
		URL.put("Insecto Come-Hombres", insectoComeHombres);
		URL.put("Jinzo 7", jinzo7);
	}
	
	
	
}
