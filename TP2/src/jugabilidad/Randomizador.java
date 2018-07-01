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
		Baraja mazo = jugador.obtenerMazo();
	
		
		for(int i = 0; i < 20; i++) {
	
			int numeroAlAzar = (int) (Math.random() * 10) + 1;
			
			switch (numeroAlAzar) {
            
				case 1: mazo.agregarCarta( fabricaDeCartas.crearSangan() );
                    break;
				case 2: mazo.agregarCarta( fabricaDeCartas.crearParasitoParacida() );
            		break;
				case 3: mazo.agregarCarta( fabricaDeCartas.crearGusanoAguja() );
            		break;
				case 4: mazo.agregarCarta( fabricaDeCartas.crearDevoradorDeNiveles() );
					break;
				case 5: mazo.agregarCarta( fabricaDeCartas.crearCapulloEvolutivo() );
            		break;
				case 6: mazo.agregarCarta( fabricaDeCartas.crearGigobyte() );
    				break;
				case 7: mazo.agregarCarta( fabricaDeCartas.crearBestiaAnfibia() );
					break;
				case 8: mazo.agregarCarta( fabricaDeCartas.crearMagoOscuro() );
					break;
				case 10: mazo.agregarCarta( fabricaDeCartas.crearBaronDeLaEspadaDemoniaca() );
					break;
				default:mazo.agregarCarta( fabricaDeCartas.crearJiraiGumo() );
					
			}       
		}
	
		for(int i = 0; i < 20; i++) {
			
			int numeroAlAzar = (int) (Math.random() * 16) + 1;
			
			switch (numeroAlAzar) {
			
			case 1: mazo.agregarCarta( fabricaDeCartas.crearJinzo7() );
		            break;
		    case 2: mazo.agregarCarta( fabricaDeCartas.crearDragonBlancoDeOjosAzules() );
		    		break;
		    case 3: mazo.agregarCarta( fabricaDeCartas.crearDragonDefinitivo() );
		    		break;
		    case 4: mazo.agregarCarta( fabricaDeCartas.crearInsectoComeHombres() );
		    		break;
		    		
		    case 5: Iterator<Carta> iteradorMazo = mazo.obtenerIteradorDeBaraja();
		    		boolean encontroCabezaDeExodia = false;
		    		while(iteradorMazo.hasNext()) {
		    			Carta carta = iteradorMazo.next();
		    			if(carta instanceof CabezaExodia)
		    				encontroCabezaDeExodia = true;
		    		}
		    		if(encontroCabezaDeExodia)
		    			mazo.agregarCarta( fabricaDeCartas.crearJinzo7() );
		    		else
		    			mazo.agregarCarta( fabricaDeCartas.crearCabezaExodia() );
		    		break;
		    		
		    case 6: Iterator<Carta> iteradorMazo2 = mazo.obtenerIteradorDeBaraja();
		    		boolean encontroBrazoIzquierdoDeExodia = false;
		    		while(iteradorMazo2.hasNext()) {
    					Carta carta = iteradorMazo2.next();
    					if(carta instanceof BrazoIzquierdoExodia)
    						encontroBrazoIzquierdoDeExodia = true;
    				}
		    		if(encontroBrazoIzquierdoDeExodia)
		    			mazo.agregarCarta( fabricaDeCartas.crearDragonBlancoDeOjosAzules() );
		    		else
		    			mazo.agregarCarta( fabricaDeCartas.crearBrazoIzquierdoExodia() );
					break;
					
		    case 7: Iterator<Carta> iteradorMazo3 = mazo.obtenerIteradorDeBaraja();
		    		boolean encontroBrazoDerechoDeExodia = false;
		    		while(iteradorMazo3.hasNext()) {
    					Carta carta = iteradorMazo3.next();
    					if(carta instanceof BrazoDerechoExodia)
    						encontroBrazoDerechoDeExodia = true;
    				}
		    		if(encontroBrazoDerechoDeExodia)
		    			mazo.agregarCarta( fabricaDeCartas.crearDragonDefinitivo() );
		    		else
		    			mazo.agregarCarta( fabricaDeCartas.crearBrazoDerechoExodia() );
					break;
					
		    case 8: Iterator<Carta> iteradorMazo4 = mazo.obtenerIteradorDeBaraja();
		    		boolean encontroPiernaDerechaDeExodia = false;
		    		while(iteradorMazo4.hasNext()) {
    					Carta carta = iteradorMazo4.next();
    					if(carta instanceof PiernaDerechaExodia)
    						encontroPiernaDerechaDeExodia = true;
    				}
		    		if(encontroPiernaDerechaDeExodia)
		    			mazo.agregarCarta( fabricaDeCartas.crearInsectoComeHombres() );
		    		else
		    			mazo.agregarCarta( fabricaDeCartas.crearPiernaDerechaExodia() );
					break;
					
            case 9: Iterator<Carta> iteradorMazo5 = mazo.obtenerIteradorDeBaraja();
            		boolean encontroPiernaIzquierdaDeExodia = false;
            		while(iteradorMazo5.hasNext()) {
    					Carta carta = iteradorMazo5.next();
    					if(carta instanceof PiernaIzquierdaExodia)
    						encontroPiernaIzquierdaDeExodia = true;
    				}
            		if(encontroPiernaIzquierdaDeExodia)
            			mazo.agregarCarta( fabricaDeCartas.crearWasteland() );
            		else
            			mazo.agregarCarta( fabricaDeCartas.crearPiernaIzquierdaExodia() );
					break;	
					
            case 10:mazo.agregarCarta( fabricaDeCartas.crearWasteland() );
					break;
            case 11:mazo.agregarCarta( fabricaDeCartas.crearAgujeroNegro() );
			 		break;
            case 12:mazo.agregarCarta( fabricaDeCartas.crearCilindroMagico() );
	 				break;
            case 13:mazo.agregarCarta( fabricaDeCartas.crearFisura() );
    				break;
            case 14:mazo.agregarCarta( fabricaDeCartas.crearOllaDeLaCodicia() );
    				break;
            case 15:mazo.agregarCarta( fabricaDeCartas.crearReinforcements() );
    				break;
            default:mazo.agregarCarta( fabricaDeCartas.crearSogen() );
    				break;
			}
		}  
		
		mazo.mezclarBaraja();
	}

}
