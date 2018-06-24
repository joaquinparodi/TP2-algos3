package Vista;

import java.util.HashMap;

public class BaseDeDatosDeCartas {

	HashMap<String, String> URL;
	
	private final String devoradorDeNiveles = "file:images/Devorador_de_niveles.jpg";
	private final String gusanoAguja = "file:images/Gusano_aguja.jpg";
	private final String jiraiGumo = "file:images/Jirai_gumo.jpg";
	private final String parasitoParacida = "file:images/Parasito_paracida.jpg";
	private final String sangan = "file:images/Sangan.jpg";
	
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
	}
	
	
	
}
