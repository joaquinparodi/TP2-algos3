package jugabilidad;

public class ArenaDeCombate {

	/*hacer pruebas*/
	
	private static ArenaDeCombate instancia;
	
	private ArenaDeCombate() {
		
	}
	
	public static ArenaDeCombate obtener() {
		if(instancia == null) instancia = new ArenaDeCombate();
		return instancia;
	}
}
