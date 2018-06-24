package jugabilidad;

import java.util.Iterator;

import cartas.BrazoDerechoExodia;
import cartas.BrazoIzquierdoExodia;
import cartas.CabezaExodia;
import cartas.Carta;
import cartas.PiernaDerechaExodia;
import cartas.PiernaIzquierdaExodia;
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
			int numeroAlAzar = (int) (Math.random() * 7) + 1;
			switch (numeroAlAzar) {
            case 1: cartaAAgregar = fabricaDeCartas.crearSangan();
                    break;
            case 2: cartaAAgregar = fabricaDeCartas.crearParasitoParacida();
            		break;
            case 3: cartaAAgregar = fabricaDeCartas.crearGusanoAguja();
            		break;
            case 4: cartaAAgregar = fabricaDeCartas.crearDevoradorDeNiveles();
            		break;
            case 5: cartaAAgregar = fabricaDeCartas.crearCapulloEvolutivo();
            		break;
            case 6: cartaAAgregar = fabricaDeCartas.crearGigobyte();
    				break;
            default: cartaAAgregar = fabricaDeCartas.crearJiraiGumo();
					break;	
			}
            mazo.agregarCarta(cartaAAgregar);          
		}
		
		Iterator<Carta> iteradorMazo = mazo.obtenerIteradorDeBaraja();
		
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
		    case 5: while(iteradorMazo.hasNext()) {
		    			Carta carta = iteradorMazo.next();
		    			if(carta instanceof CabezaExodia) continue;
		    		}
		    		cartaAAgregar = fabricaDeCartas.crearCabezaExodia();
		    		break;
		    case 6: while(iteradorMazo.hasNext()) {
    					Carta carta = iteradorMazo.next();
    					if(carta instanceof BrazoIzquierdoExodia) continue;
    				}
		    		cartaAAgregar = fabricaDeCartas.crearBrazoIzquierdoExodia();
					break;
		    case 7: while(iteradorMazo.hasNext()) {
    					Carta carta = iteradorMazo.next();
    					if(carta instanceof BrazoDerechoExodia) continue;
    				}
		    		cartaAAgregar = fabricaDeCartas.crearBrazoDerechoExodia();
					break;
		    case 8: while(iteradorMazo.hasNext()) {
    					Carta carta = iteradorMazo.next();
    					if(carta instanceof PiernaDerechaExodia) continue;
    				}
		    		cartaAAgregar = fabricaDeCartas.crearPiernaDerechaExodia();
					break;
            case 9: while(iteradorMazo.hasNext()) {
    					Carta carta = iteradorMazo.next();
    					if(carta instanceof PiernaIzquierdaExodia) continue;
    				}
            		cartaAAgregar = fabricaDeCartas.crearPiernaIzquierdaExodia();
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
