package jugabilidad;

import cartas.Carta;
import factories.FabricaDeCartas;

public class Randomizador {
	
	public Jugador queJugadorEmpieza(Jugador jugador1, Jugador jugador2) {
		int numeroAlAzar = (int) (Math.random() * 2) + 1;
			if(numeroAlAzar == 1) {
				return jugador1;
			}else {
				return jugador2;
			}
	}
	
	public void cargarMazo(Jugador jugador) {

		FabricaDeCartas fabricaDeCartas = new FabricaDeCartas(jugador);
		Baraja mazo = jugador.obtenerCampo().obtenerMazo();
		Carta cartaAAgregar;

		//primero cargo 20 monstruos para asegurarme de que no hallan muchas magicas
		for(int i = 0; i < 20; i++) {
			int numeroAlAzar = (int) (Math.random() * 9) + 1;
			switch (numeroAlAzar) {
            case 1: cartaAAgregar = fabricaDeCartas.crearJinzo7();
                    break;
            case 2: cartaAAgregar = fabricaDeCartas.crearDragonBlancoDeOjosAzules();
            		break;
            case 3: cartaAAgregar = fabricaDeCartas.crearDragonDefinitivo();
            		break;
            case 4: cartaAAgregar = fabricaDeCartas.crearInsectoComeHombres();
            		break;
            case 5: cartaAAgregar = fabricaDeCartas.crearCabezaExodia();
            		break;
            case 6: cartaAAgregar = fabricaDeCartas.crearBrazoIzquierdoExodia();
    				break;
            case 7: cartaAAgregar = fabricaDeCartas.crearBrazoDerechoExodia();
					break;
            case 8: cartaAAgregar = fabricaDeCartas.crearPiernaDerechaExodia();
					break;
            default: cartaAAgregar = fabricaDeCartas.crearPiernaIzquierdaExodia();
					break;			
			}
            mazo.agregarCarta(cartaAAgregar);          
		}
		
		//ahora cargo 20 cartas cualquiera
		for(int i = 0; i < 20; i++) {
			int numeroAlAzar = (int) (Math.random() * 16) + 1;
			switch (numeroAlAzar) {
			case 1: cartaAAgregar = fabricaDeCartas.crearJinzo7();
		            break;
		    case 2: cartaAAgregar = fabricaDeCartas.crearDragonBlancoDeOjosAzules();
		    		break;
		    case 3: cartaAAgregar = fabricaDeCartas.crearDragonDefinitivo();
		    		break;
		    case 4: cartaAAgregar = fabricaDeCartas.crearInsectoComeHombres();
		    		break;
		    case 5: cartaAAgregar = fabricaDeCartas.crearCabezaExodia();
		    		break;
		    case 6: cartaAAgregar = fabricaDeCartas.crearBrazoIzquierdoExodia();
					break;
		    case 7: cartaAAgregar = fabricaDeCartas.crearBrazoDerechoExodia();
					break;
		    case 8: cartaAAgregar = fabricaDeCartas.crearPiernaDerechaExodia();
					break;
            case 9: cartaAAgregar = fabricaDeCartas.crearPiernaIzquierdaExodia();
					break;			
            case 10: cartaAAgregar = fabricaDeCartas.crearWasteland();
					break;
            case 11: cartaAAgregar = fabricaDeCartas.crearAgujeroNegro();
			 		break;
            case 12: cartaAAgregar = fabricaDeCartas.crearCilindroMagico();
	 				break;
            case 13: cartaAAgregar = fabricaDeCartas.crearFisura();
    				break;
            case 14: cartaAAgregar = fabricaDeCartas.crearOllaDeLaCodicia();
    				break;
            case 15: cartaAAgregar = fabricaDeCartas.crearReinforcements();
    				break;
            default: cartaAAgregar = fabricaDeCartas.crearSogen();
    				break;
			}
            mazo.agregarCarta(cartaAAgregar);          
		}
		mazo.mezclarBaraja();
	}
}
