package factories;

import cartas.Carta;

public class FabricaCartas {

		private CreadorDeCartas creadorDeCartas;
		
		public void asignarCreadorDeCartas(CreadorDeCartas creador) {
			creadorDeCartas = creador;
		}
		
		public Carta obtenerCarta() {
			return creadorDeCartas.obtenerCarta();
		}
		
		public void construirCarta() {
			creadorDeCartas.crearCarta();
		}
}
