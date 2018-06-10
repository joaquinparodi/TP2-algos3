package cartas;

public class Magica implements Clase {
	
	public Posicion obtenerPosicionInicial() {
		return new PosicionArriba();
	}
	
	public Posicion cambiarPosicion( Posicion posicion ) {
		return posicion.cambiarPosicion();
	}

	@Override
	public double atacar(Posicion posicionActual, Carta otraCarta) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double recibirAtaque(Posicion posicionActual, Atributos atributosAtacante) {
		// TODO Auto-generated method stub
		return 0;
	}

}
