package tests;

import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Test;
import atributos.Estrellas;
import atributos.Puntos;
import atributos.Vida;
import cartas.*;
import factories.FabricaDeCartas;
import factories.FabricaDeMonstruosEspeciales;
import jugabilidad.Baraja;
import jugabilidad.Controlador;
import jugabilidad.Jugador;

public class TestControlador {

	@Test
	public void test01ObtenerExodiaEnLaManoGanaAutomaticamenteElJuego() {
		
		Vida vidaUno = new Vida(8000);
		Vida vidaDos = new Vida(8000);
		
		Jugador jugadorUno = new Jugador(vidaUno);
		Jugador jugadorDos = new Jugador(vidaDos);
				
		Controlador controlador = new Controlador(jugadorUno, jugadorDos);
		
		FabricaDeMonstruosEspeciales fabricaEspecial = new FabricaDeMonstruosEspeciales();
		FabricaDeCartas fabrica = new FabricaDeCartas();
		
		Baraja mazoUno = new Baraja();
		Baraja mazoDos = new Baraja();
		
		Puntos puntosUno = new Puntos(200, 200);
		Puntos puntosDos = new Puntos(50, 50);
		Estrellas estrellas = new Estrellas(3);
		
		//el mazo esta construido en orden para que los exodias se agreguen a la mano (sin mezclar)
		mazoUno.agregarCarta(fabricaEspecial.crearBrazoIzquierdoExodia(jugadorUno));
		mazoUno.agregarCarta(fabricaEspecial.crearBrazoDerechoExodia(jugadorUno));
		mazoUno.agregarCarta(fabricaEspecial.crearPiernaDerechaExodia(jugadorUno));
		mazoUno.agregarCarta(fabricaEspecial.crearPiernaIzquierdaExodia(jugadorUno));
		mazoUno.agregarCarta(fabricaEspecial.crearCabezaExodia(jugadorUno));
		mazoUno.agregarCarta(fabrica.crearCarta("monstruo1", jugadorUno, estrellas, puntosDos));
		mazoUno.agregarCarta(fabrica.crearCarta("monstruo2", jugadorUno, estrellas, puntosDos));

		mazoDos.agregarCarta(fabrica.crearCarta("monstruoA", jugadorDos, estrellas, puntosDos));
		mazoDos.agregarCarta(fabrica.crearCarta("monstruoB", jugadorDos, estrellas, puntosDos));
		mazoDos.agregarCarta(fabrica.crearCarta("monstruoC", jugadorDos, estrellas, puntosDos));
		mazoDos.agregarCarta(fabrica.crearCarta("monstruoD", jugadorDos, estrellas, puntosDos));
		mazoDos.agregarCarta(fabrica.crearCarta("monstruoE", jugadorDos, estrellas, puntosDos));
		mazoDos.agregarCarta(fabrica.crearCarta("monstruoF", jugadorDos, estrellas, puntosDos));
		mazoDos.agregarCarta(fabrica.crearCarta("monstruoG", jugadorDos, estrellas, puntosDos));
		
		controlador.asignarMazos(mazoUno, mazoDos);	
		
		assertTrue( controlador.hayGanador() );
		
	} 
	
	@Test
	public void test03AtacarJugadorYDejarloSinVidaGanaElDuelo() {
		
		Vida vidaUno = new Vida(100);
		Vida vidaDos = new Vida(100);
		
		Jugador jugadorUno = new Jugador(vidaUno);
		Jugador jugadorDos = new Jugador(vidaDos);
			
		//El primer jugador es el atacante, segundo es el defensor, luego se van permutando
		Controlador controlador = new Controlador(jugadorUno, jugadorDos);
		
		FabricaDeCartas fabrica = new FabricaDeCartas();
		
		Puntos puntosUno = new Puntos(200, 200);
		Puntos puntosDos = new Puntos(50, 50);
		Estrellas estrellas = new Estrellas(3);
		
		Baraja mazoUno = new Baraja();
		Baraja mazoDos = new Baraja();
		
		Monstruo monstruo1 = fabrica.crearCarta("monstruo1", jugadorUno, estrellas, puntosUno);
		mazoUno.agregarCarta(monstruo1);
		mazoUno.agregarCarta(fabrica.crearCarta("monstruo2", jugadorUno, estrellas, puntosUno));
		mazoUno.agregarCarta(fabrica.crearCarta("monstruo3", jugadorUno, estrellas, puntosUno));
		mazoUno.agregarCarta(fabrica.crearCarta("monstruo4", jugadorUno, estrellas, puntosUno));
		mazoUno.agregarCarta(fabrica.crearCarta("monstruo5", jugadorUno, estrellas, puntosUno));
		mazoUno.agregarCarta(fabrica.crearCarta("monstruo6", jugadorUno, estrellas, puntosUno));
	
		Monstruo monstruoA = fabrica.crearCarta("monstruoA", jugadorDos, estrellas, puntosDos);
		mazoDos.agregarCarta(monstruoA);
		mazoDos.agregarCarta(fabrica.crearCarta("monstruoB", jugadorDos, estrellas, puntosDos));
		mazoDos.agregarCarta(fabrica.crearCarta("monstruoC", jugadorDos, estrellas, puntosDos));
		mazoDos.agregarCarta(fabrica.crearCarta("monstruoD", jugadorDos, estrellas, puntosDos));
		mazoDos.agregarCarta(fabrica.crearCarta("monstruoE", jugadorDos, estrellas, puntosDos));
		mazoDos.agregarCarta(fabrica.crearCarta("monstruoF", jugadorDos, estrellas, puntosDos));
		
		controlador.asignarMazos(mazoUno, mazoDos);	
		
		jugadorUno.agregarCartaEnCampo(monstruo1);
		jugadorDos.agregarCartaEnCampo(monstruoA);
		jugadorUno.atacarCartaConCarta(monstruoA, monstruo1);
		//El jugador atacado queda con vida negativa, por lo tanto el jugador atacante es el ganador
		assertTrue( controlador.hayGanador() );
		
	}	
	
	@Test 
	public void test04QuedarseSinCartasEnElMasoHaceQueElOponenteGaneElDuelo() {
		
		Vida vidaUno = new Vida(8000);
		Vida vidaDos = new Vida(8000);
		
		Jugador jugadorUno = new Jugador(vidaUno);
		Jugador jugadorDos = new Jugador(vidaDos);
				
		Controlador controlador = new Controlador(jugadorUno, jugadorDos);
		
		FabricaDeCartas fabrica = new FabricaDeCartas();
		
		Puntos puntos = new Puntos(200, 200);
		Estrellas estrellas = new Estrellas(3);
		
		Baraja mazoUno = new Baraja();
		Baraja mazoDos = new Baraja();
		
		mazoUno.agregarCarta(fabrica.crearCarta("monstruo1", jugadorUno, estrellas, puntos));
		mazoUno.agregarCarta(fabrica.crearCarta("monstruo2", jugadorUno, estrellas, puntos));
		mazoUno.agregarCarta(fabrica.crearCarta("monstruo3", jugadorUno, estrellas, puntos));
		mazoUno.agregarCarta(fabrica.crearCarta("monstruo4", jugadorUno, estrellas, puntos));
		mazoUno.agregarCarta(fabrica.crearCarta("monstruo5", jugadorUno, estrellas, puntos));
		mazoUno.agregarCarta(fabrica.crearCarta("monstruo6", jugadorUno, estrellas, puntos));
	
		mazoDos.agregarCarta(fabrica.crearCarta("monstruoA", jugadorDos, estrellas, puntos));
		mazoDos.agregarCarta(fabrica.crearCarta("monstruoB", jugadorDos, estrellas, puntos));
		mazoDos.agregarCarta(fabrica.crearCarta("monstruoC", jugadorDos, estrellas, puntos));
		mazoDos.agregarCarta(fabrica.crearCarta("monstruoD", jugadorDos, estrellas, puntos));
		mazoDos.agregarCarta(fabrica.crearCarta("monstruoE", jugadorDos, estrellas, puntos));
		mazoDos.agregarCarta(fabrica.crearCarta("monstruoF", jugadorDos, estrellas, puntos));	
		
		controlador.asignarMazos(mazoUno, mazoDos);	
		
		//Saco las cartas del mazo del atacante
		
		Iterator iter = mazoUno.obtenerIteradorDeBaraja();
		while(mazoUno.tieneCartas()) iter.next();
		
		//Ahora el atacante deberia sacar una carta, como no tiene perdio el duelo
		assertTrue( controlador.hayGanador() );
		
	}	
		
}
