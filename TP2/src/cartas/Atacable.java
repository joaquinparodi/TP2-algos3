package cartas;

import atributos.Puntos;
import jugabilidad.Jugador;
import resultados.Resultado;

public interface Atacable {
	
	void enviarAlCementerio();
	
	void atacar(Atacable otroAtacable);
	
	void cambiarPosicion();
	
	public Resultado recibirAtaque(Puntos puntosAtacante);

	boolean lePerteneceA(Jugador jugador);
	
	public void aplicarAtaque (Atacable otroMonstruo);
	
	public void atacarConEfecto (Atacable otroMonstruo);
	
	public void atacarSinEfecto (Atacable otroMonstruo);

	public Resultado recibirAtaqueBocaArriba(Puntos puntosAtacante);

	public Resultado recibirAtaqueBocaAbajo(Puntos puntosAtacante);
	
	public Puntos obtenerPuntos();
	
	public void anularPuntos();

	public void restaurarPuntos();
	
	public void incrementarPuntosDeAtaque(double incremento);
	
	public void incrementarPuntosDeDefensa(double incremento);
			
}
