package factories;

import cartas.Carta;

public class BuilderDeCartas {

		private CreadorDeCartas creadorDeCartas;

		public BuilderDeCartas(CreadorDeCartas creador) {
			creadorDeCartas = creador;
			creadorDeCartas.crearCarta();
		}
		
		public Carta obtenerCarta() {
			return creadorDeCartas.obtenerCarta();
		}
}
