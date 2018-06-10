package cartas;

public class Trampa implements Clase {
	
	public Posicion obtenerPosicionInicial() {
		return new PosicionAbajo();
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

