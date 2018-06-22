package jugabilidad;

import atributos.Efecto;
import atributos.EfectoAgujeroNegro;
import atributos.EfectoCilindroMagico;
import atributos.EfectoDeTrampa;
import atributos.EfectoFisura;
import atributos.EfectoOllaDeLaCodicia;
import atributos.EfectoReinforcements;
import atributos.EfectoSogen;
import atributos.EfectoWasteland;
import cartas.Carta;
import factories.FabricaDeCartas;
import factories.FabricaDeMonstruosEspeciales;

public class Randomizador {
	
	public Jugador queJugadorEmpieza(Jugador jugador1, Jugador jugador2) {
		int numeroAlAzar = (int) (Math.random() * 2) + 1;
			if(numeroAlAzar == 1) {
				return jugador1;
			}else {
				return jugador2;
			}
	}
	
	//HABRIA MODIFICAR LA CREACION DE CARTAS PARA QUE NO SE LE TENGA QUE PASAR AL JUGADOR COMO PARAMETRO
	//ASI NO SE VIOLA EL ENCAPSULAMIENTO
	public void cargarMazo(Jugador jugador) {
		
		FabricaDeMonstruosEspeciales fabricaDeMonstruosEspeciales = new FabricaDeMonstruosEspeciales();
		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas();
		Baraja mazo = jugador.obtenerCampo().obtenerMazo();
		Carta cartaAAgregar;
		Efecto efecto;

		//primero cargo 20 monstruos para asegurarme de que no hallan muchas magicas
		for(int i = 0; i < 20; i++) {
			int numeroAlAzar = (int) (Math.random() * 9) + 1;
			switch (numeroAlAzar) {
            case 1: cartaAAgregar = fabricaDeMonstruosEspeciales.crearJinzo7(jugador);
                    break;
            case 2: cartaAAgregar = fabricaDeMonstruosEspeciales.crearDragonBlanco(jugador);
            		break;
            case 3: cartaAAgregar = fabricaDeMonstruosEspeciales.crearDragonDefinitivo(jugador);
            		break;
            case 4: cartaAAgregar = fabricaDeMonstruosEspeciales.crearInsectoComeHombres(jugador);
            		break;
            case 5: cartaAAgregar = fabricaDeMonstruosEspeciales.crearCabezaExodia(jugador);
            		break;
            case 6: cartaAAgregar = fabricaDeMonstruosEspeciales.crearBrazoIzquierdoExodia(jugador);
    				break;
            case 7: cartaAAgregar = fabricaDeMonstruosEspeciales.crearBrazoDerechoExodia(jugador);
					break;
            case 8: cartaAAgregar = fabricaDeMonstruosEspeciales.crearPiernaDerechaExodia(jugador);
					break;
            default: cartaAAgregar = fabricaDeMonstruosEspeciales.crearPiernaIzquierdaExodia(jugador);
					break;			
			}
            mazo.agregarCarta(cartaAAgregar);          
		}
		
		//ahora cargo 20 cartas cualquiera
		for(int i = 0; i < 20; i++) {
			int numeroAlAzar = (int) (Math.random() * 16) + 1;
			switch (numeroAlAzar) {
			case 1: cartaAAgregar = fabricaDeMonstruosEspeciales.crearJinzo7(jugador);
                    break;
            case 2: cartaAAgregar = fabricaDeMonstruosEspeciales.crearDragonBlanco(jugador);
            		break;
            case 3: cartaAAgregar = fabricaDeMonstruosEspeciales.crearDragonDefinitivo(jugador);
            		break;
            case 4: cartaAAgregar = fabricaDeMonstruosEspeciales.crearInsectoComeHombres(jugador);
            		break;
            case 5: cartaAAgregar = fabricaDeMonstruosEspeciales.crearCabezaExodia(jugador);
            		break;
            case 6: cartaAAgregar = fabricaDeMonstruosEspeciales.crearBrazoIzquierdoExodia(jugador);
    				break;
            case 7: cartaAAgregar = fabricaDeMonstruosEspeciales.crearBrazoDerechoExodia(jugador);
					break;
            case 8: cartaAAgregar = fabricaDeMonstruosEspeciales.crearPiernaDerechaExodia(jugador);
					break;
            case 9: cartaAAgregar = fabricaDeMonstruosEspeciales.crearPiernaIzquierdaExodia(jugador);
					break;			
            case 10: efecto = new EfectoWasteland();
    				cartaAAgregar = fabricaDeCartas.crearCarta("Wasteland", jugador, efecto);
					break;
            case 11: efecto = new EfectoAgujeroNegro ();
            		cartaAAgregar = fabricaDeCartas.crearCarta("Agujero Negro", jugador, efecto);
			 		break;
            case 12: EfectoDeTrampa efectoTrampa = new EfectoCilindroMagico ();
    				cartaAAgregar = fabricaDeCartas.crearCarta("Cilindro Magico", jugador, efectoTrampa);
	 				break;
            case 13: efecto = new EfectoFisura ();
    				cartaAAgregar = fabricaDeCartas.crearCarta("Fisura", jugador, efecto);
    				break;
            case 14: efecto = new EfectoOllaDeLaCodicia ();
    				cartaAAgregar = fabricaDeCartas.crearCarta("Olla De La Codicia", jugador, efecto);
    				break;
            case 15: efectoTrampa = new EfectoReinforcements ();
    				cartaAAgregar = fabricaDeCartas.crearCarta("Reinforcements", jugador, efectoTrampa);
    				break;
            default: efecto = new EfectoSogen ();
    				cartaAAgregar = fabricaDeCartas.crearCarta("Sogen", jugador, efecto);
    				break;
			}
            mazo.agregarCarta(cartaAAgregar);          
		}
		mazo.mezclarBaraja();
	}
}
