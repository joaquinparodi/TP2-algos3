package tests;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import org.junit.Test;
import atributos.Estrellas;
import atributos.Puntos;
import cartas.*;
import factories.FabricaDeCartas;
import jugabilidad.Baraja;
import jugabilidad.Controlador;
import jugabilidad.Jugador;

public class TestControlador {

	@Test
	public void test01ObtenerExodiaEnLaManoGanaAutomaticamenteElJuego() {
		
		/*Al crearse el controlador tiene al jugador uno como atacante*/
		Controlador controlador = Controlador.obtener();

		Jugador jugadorUno = controlador.obtenerJugadorUno();
		Jugador jugadorDos = controlador.obtenerJugadorDos();
		
		FabricaDeCartas fabrica = new FabricaDeCartas(jugadorUno);
		FabricaDeCartas fabrica2 = new FabricaDeCartas(jugadorDos);
		
		Baraja mazoUno = new Baraja();
		Baraja mazoDos = new Baraja();
		
		Puntos puntosUno = new Puntos(200, 200);
		Puntos puntosDos = new Puntos(50, 50);
		Estrellas estrellas = new Estrellas(3);
		
		//el mazo esta construido en orden para que los exodias se agreguen a la mano (sin mezclar)
		mazoUno.agregarCarta(fabrica.crearBrazoIzquierdoExodia());
		mazoUno.agregarCarta(fabrica.crearBrazoDerechoExodia());
		mazoUno.agregarCarta(fabrica.crearPiernaDerechaExodia());
		mazoUno.agregarCarta(fabrica.crearPiernaIzquierdaExodia());
		mazoUno.agregarCarta(fabrica.crearCabezaExodia());
		mazoUno.agregarCarta(fabrica.crearMonstruoPersonalizado("monstruo1", estrellas, puntosUno));
		mazoUno.agregarCarta(fabrica.crearMonstruoPersonalizado("monstruo2", estrellas, puntosUno));

		mazoDos.agregarCarta(fabrica2.crearMonstruoPersonalizado("monstruoA", estrellas, puntosDos));
		mazoDos.agregarCarta(fabrica2.crearMonstruoPersonalizado("monstruoB", estrellas, puntosDos));
		mazoDos.agregarCarta(fabrica2.crearMonstruoPersonalizado("monstruoC", estrellas, puntosDos));
		mazoDos.agregarCarta(fabrica2.crearMonstruoPersonalizado("monstruoD", estrellas, puntosDos));
		mazoDos.agregarCarta(fabrica2.crearMonstruoPersonalizado("monstruoE", estrellas, puntosDos));
		mazoDos.agregarCarta(fabrica2.crearMonstruoPersonalizado("monstruoF", estrellas, puntosDos));
		mazoDos.agregarCarta(fabrica2.crearMonstruoPersonalizado("monstruoG", estrellas, puntosDos));
		
		controlador.asignarMazos(mazoUno, mazoDos);	
		
		assert( controlador.ganoElJugadorDelTurnoActual() );
		
	} 
	
	@Test
	public void test02AtacarJugadorYDejarloSinVidaGanaElDuelo() {
		
		/*Al crearse el controlador tiene al jugador uno como atacante*/
		Controlador controlador = Controlador.obtener();

		Jugador jugadorUno = controlador.obtenerJugadorUno();
		Jugador jugadorDos = controlador.obtenerJugadorDos();
		
		FabricaDeCartas fabrica = new FabricaDeCartas(jugadorUno);
		FabricaDeCartas fabrica2 = new FabricaDeCartas(jugadorDos);
		
		Puntos puntosUno = new Puntos(8200, 200);
		Puntos puntosDos = new Puntos(50, 50);
		Estrellas estrellas = new Estrellas(3);
		
		Baraja mazoUno = new Baraja();
		Baraja mazoDos = new Baraja();
		
		Monstruo monstruo1 = fabrica.crearMonstruoPersonalizado("monstruo1", estrellas, puntosUno);
		mazoUno.agregarCarta(monstruo1);
		mazoUno.agregarCarta(fabrica.crearMonstruoPersonalizado("monstruo2", estrellas, puntosUno));
		mazoUno.agregarCarta(fabrica.crearMonstruoPersonalizado("monstruo3", estrellas, puntosUno));
		mazoUno.agregarCarta(fabrica.crearMonstruoPersonalizado("monstruo4", estrellas, puntosUno));
		mazoUno.agregarCarta(fabrica.crearMonstruoPersonalizado("monstruo5", estrellas, puntosUno));
		mazoUno.agregarCarta(fabrica.crearMonstruoPersonalizado("monstruo6", estrellas, puntosUno));
	
		Monstruo monstruoA = fabrica2.crearMonstruoPersonalizado("monstruoA", estrellas, puntosDos);
		mazoDos.agregarCarta(monstruoA);
		mazoDos.agregarCarta(fabrica2.crearMonstruoPersonalizado("monstruoB", estrellas, puntosDos));
		mazoDos.agregarCarta(fabrica2.crearMonstruoPersonalizado("monstruoD", estrellas, puntosDos));
		mazoDos.agregarCarta(fabrica2.crearMonstruoPersonalizado("monstruoE", estrellas, puntosDos));
		mazoDos.agregarCarta(fabrica2.crearMonstruoPersonalizado("monstruoF", estrellas, puntosDos));
		
		controlador.asignarMazos(mazoUno, mazoDos);	
		
		jugadorUno.agregarCartaEnCampo(monstruo1);
		jugadorDos.agregarCartaEnCampo(monstruoA);
		jugadorUno.atacarCartaConCarta(monstruoA, monstruo1);
		//El jugador atacado queda con vida negativa, por lo tanto el jugador atacante es el ganador
		assert( controlador.ganoElJugadorDelTurnoActual() );
		
	}	
	
	@Test 
	public void test03QuedarseSinCartasEnElMasoHaceQueElOponenteGaneElDuelo() {
			
		Controlador controlador = Controlador.obtener();
		
		Jugador jugadorUno = controlador.obtenerJugadorUno();
		Jugador jugadorDos = controlador.obtenerJugadorDos();
		
		FabricaDeCartas fabrica = new FabricaDeCartas(jugadorUno);
		FabricaDeCartas fabrica2 = new FabricaDeCartas(jugadorDos);
		
		Puntos puntos = new Puntos(200, 200);
		Estrellas estrellas = new Estrellas(3);
		
		Baraja mazoUno = new Baraja();
		Baraja mazoDos = new Baraja();
		
		mazoUno.agregarCarta(fabrica.crearMonstruoPersonalizado("monstruo1", estrellas, puntos));
		mazoUno.agregarCarta(fabrica.crearMonstruoPersonalizado("monstruo2", estrellas, puntos));
		mazoUno.agregarCarta(fabrica.crearMonstruoPersonalizado("monstruo3", estrellas, puntos));
		mazoUno.agregarCarta(fabrica.crearMonstruoPersonalizado("monstruo4", estrellas, puntos));
		mazoUno.agregarCarta(fabrica.crearMonstruoPersonalizado("monstruo5", estrellas, puntos));
	
		mazoDos.agregarCarta(fabrica2.crearMonstruoPersonalizado("monstruoA", estrellas, puntos));
		mazoDos.agregarCarta(fabrica2.crearMonstruoPersonalizado("monstruoB", estrellas, puntos));
		mazoDos.agregarCarta(fabrica2.crearMonstruoPersonalizado("monstruoC", estrellas, puntos));
		mazoDos.agregarCarta(fabrica2.crearMonstruoPersonalizado("monstruoD", estrellas, puntos));
		mazoDos.agregarCarta(fabrica2.crearMonstruoPersonalizado("monstruoE", estrellas, puntos));
		
		controlador.asignarMazos(mazoUno, mazoDos);	
		
		//Saco las cartas del mazo del atacante
		
		Iterator<Carta> iter = mazoUno.obtenerIteradorDeBaraja();
		while(mazoUno.tieneCartas()) {
			iter.next().agregarseEnCampo();
		}
		
		//Ahora el atacante deberia sacar una carta, como no tiene perdio el duelo
		assert( controlador.ganoElJugadorDelTurnoActual() );
		
	}
	
	@Test
	public void test04AsignarRival() {
		Controlador controlador = Controlador.obtener();
		Jugador jugadorAtacante = controlador.obtenerJugadorAtacante();
		Jugador jugadorDefensor = controlador.obtenerJugadorDefensor();
		assertEquals(jugadorAtacante.obtenerRival(), jugadorDefensor);
	}
	
	@Test
	public void test05AvanzarDeFasePreparacionAAtaque() {
		Controlador controlador = Controlador.obtener();
		controlador.avanzarFase();
		assert(controlador.partidaEstaEnFase("Ataque"));
	}
	
	@Test
	public void test06AvanzarDeFaseAtaqueAFinal() {
		Controlador controlador = Controlador.obtener();
		controlador.avanzarFase();
		controlador.avanzarFase();
		controlador.avanzarFase();
		controlador.avanzarFase();
		assert(controlador.partidaEstaEnFase("Final"));
	}
	
	@Test
	public void test07AvanzarDeFaseFinalAPreparacion() {
		Controlador controlador = Controlador.obtener();
		controlador.avanzarFase();
		assert(controlador.partidaEstaEnFase("Preparacion"));
	}
	
	@Test
	public void test08CambiarTurnos() {
		Controlador controlador = Controlador.obtener();
		Jugador jugadorDef = controlador.obtenerJugadorDefensor();
		controlador.cambiarTurno();
		assert(controlador.esElTurnoDe(jugadorDef));
	}
	
	@Test
	public void test09JugadorAtacanteEsElCorrecto() {
		Controlador controlador = Controlador.obtener();
		Jugador jugadorAtacante = controlador.obtenerJugadorAtacante();
		
		assert(controlador.esElTurnoDe(jugadorAtacante));
	}
}
