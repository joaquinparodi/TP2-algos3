package tests;
import jugabilidad.Baraja;
import jugabilidad.Jugador;
import cartas.Efecto;
import cartas.EfectoAgujeroNegro;
import cartas.Magica;

import org.junit.jupiter.api.Test;

import atributos.Vida;
import cartas.Monstruo;
import cartas.Puntos;
import errores.ErrorSacrificiosInsuficientes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;

class TestJugador {

    @Test
    public void test01JugadorNuevoTieneXPuntos (){
        
    	Vida vida = new Vida(8000);
    	Jugador unJugador = new Jugador(vida);

        double vidaEsperada = 8000;
        double vidaObtenida = unJugador.obtenerVida().obtenerPuntosDeVida();

        assertEquals(vidaEsperada,vidaObtenida);
    }

    @Test
    public void test02JugadorRecibeDanio(){

    	Vida vida = new Vida(8000);
    	Jugador unJugador = new Jugador(vida);

        unJugador.hacerDanio(2000);

        double vidaEsperada = 6000;
        double vidaObtenida = unJugador.obtenerVida().obtenerPuntosDeVida();

        assertEquals(vidaEsperada,vidaObtenida);
    }
    
	@Test
	public void test03ColocarMosnstruoConNivelInferiorACincoYNoSacrificaANadie() {
    	
		Vida vida = new Vida(8000);
    	Jugador jugador = new Jugador(vida);
		
    	int nivel = 2;
    	Puntos puntos = new Puntos(2000, 2000);
		Monstruo monstruoUno = new Monstruo("Auriane", jugador, nivel,puntos);
		Monstruo monstruoDos = new Monstruo("Javir", jugador, nivel, puntos);
		Monstruo monstruoTres = new Monstruo("Facundo", jugador, nivel, puntos);
		
		jugador.repartirCarta(monstruoUno);
		jugador.repartirCarta(monstruoDos);
		jugador.repartirCarta(monstruoTres);
		
		jugador.agregarCartaMonstruoEnCampo("Auriane", new Baraja());
		jugador.agregarCartaMonstruoEnCampo("Javir", new Baraja());
		
		//Al agregar "Facundo", no se tendrian que borrar ninguno de los 2 mosntruos del tablero
		jugador.agregarCartaMonstruoEnCampo("Facundo", new Baraja());
		
		assertFalse( jugador.monstruoEstaMuerto("Javir") );
		assertFalse( jugador.monstruoEstaMuerto("Auriane") );
	}
	
	
	@Test
	public void test04ColocarMonstruoConNivel7Requiere2Sacrificios() {
 
		Vida vida = new Vida(8000);
    	Jugador jugador = new Jugador(vida);
		
		int nivel = 7;
		Puntos puntos = new Puntos(2000, 2000);
		Monstruo monstruoUno = new Monstruo("joaco", jugador, nivel, puntos);
		Monstruo monstruoDos = new Monstruo("javi", jugador, nivel, puntos);
		Monstruo monstruoTres = new Monstruo("facu", jugador, nivel, puntos);
		
		jugador.repartirCarta(monstruoUno);
		jugador.repartirCarta(monstruoDos);
		jugador.repartirCarta(monstruoTres);
		
		Baraja monstruosASacrificar = new Baraja();
		monstruosASacrificar.agregarCarta(monstruoDos);
		monstruosASacrificar.agregarCarta(monstruoTres);
		
		jugador.agregarCartaMonstruoEnCampo("joaco", monstruosASacrificar);
		
		assert( jugador.monstruoEstaMuerto("javi") );
		assert( jugador.monstruoEstaMuerto("facu") );
	}
	
	@Test
	public void test05ColocarMonstruoConNivel5Requiere1Sacrificio() {
    
		Vida vida = new Vida(8000);
    	Jugador jugador = new Jugador(vida);
		
		int nivel = 5;
		Puntos puntos = new Puntos(2000, 2000);
		Monstruo monstruoUno = new Monstruo("joaco", jugador, nivel, puntos);
		Monstruo monstruoDos = new Monstruo("javi", jugador, nivel, puntos);
		
		jugador.repartirCarta(monstruoUno);
		jugador.repartirCarta(monstruoDos);
		
		Baraja monstruosASacrificar = new Baraja();
		monstruosASacrificar.agregarCarta(monstruoDos);
		
		jugador.agregarCartaMonstruoEnCampo("joaco", monstruosASacrificar);
		
		assert( jugador.monstruoEstaMuerto("javi") );
	}
	
	@Test
	public void test06VerificarSiElMonstroColocadoSeEncuentraEnElCampoLuegoDeSacrificio() {

    	Vida vida = new Vida(8000);
    	Jugador jugador = new Jugador(vida);
		
		int nivel = 5;
		Puntos puntos = new Puntos(2000, 2000);
		Monstruo monstruoUno = new Monstruo("joaco", jugador, nivel, puntos);
		Monstruo monstruoDos = new Monstruo("javi", jugador, nivel, puntos);
		
		jugador.repartirCarta(monstruoUno);
		jugador.repartirCarta(monstruoDos);
		
		Baraja monstruosASacrificar = new Baraja();
		monstruosASacrificar.agregarCarta(monstruoDos);
		
		jugador.agregarCartaMonstruoEnCampo("joaco", monstruosASacrificar);
		
		assert ( ((jugador.obtenerCampo()).obtenerMonstruos()).pertenece("joaco") );
	}
	
	@Test
	public void test07InvocarMonstruoNivel5SinEnviarMonstruosASacrificarLanzaError() {
    	Vida vida = new Vida(8000);
    	Jugador jugador = new Jugador(vida);
		
		int nivel = 5;
		Puntos puntos = new Puntos(2000, 2000);
		Monstruo monstruoUno = new Monstruo("joaco", jugador, nivel, puntos);
		
		jugador.repartirCarta(monstruoUno);
		
		Baraja monstruosASacrificar = new Baraja();
		
		Assertions.assertThrows(ErrorSacrificiosInsuficientes.class, () -> jugador.agregarCartaMonstruoEnCampo("joaco", monstruosASacrificar));
	}
	
	@Test
	public void test08ColocarMonstruoConNivel3NoRequiereSacrificio() {
    	Vida vida = new Vida(8000);
    	Jugador jugador = new Jugador(vida);
		
		int nivel = 3;
		Puntos puntos = new Puntos(2000, 2000);
		Monstruo monstruoUno = new Monstruo("joaco", jugador, nivel, puntos);
		Monstruo monstruoDos = new Monstruo("javi", jugador, nivel, puntos);
		
		jugador.repartirCarta(monstruoUno);
		jugador.repartirCarta(monstruoDos);
		
		Baraja monstruosASacrificar = new Baraja();
		monstruosASacrificar.agregarCarta(monstruoDos);
		
		jugador.agregarCartaMonstruoEnCampo("joaco", monstruosASacrificar);
		
		assertFalse( jugador.monstruoEstaMuerto("javi") );
	}
	
	@Test
	public void test09AgregarAgujeroNegroEliminaTodasLasCartas () {
		
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntos = new Puntos(2000, 2000);
		Monstruo monstruoUno = new Monstruo("facu", jugadorUno, 1, puntos);
		Monstruo monstruoDos = new Monstruo("javi", jugadorDos, 1, puntos);
		
		Efecto efecto = new EfectoAgujeroNegro ();
		Magica agujeroNegro = new Magica ("Agujero Negro", jugadorUno, efecto);
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorUno.repartirCarta(agujeroNegro);
		jugadorDos.repartirCarta(monstruoDos);
		
		jugadorUno.agregarCartaMonstruoEnCampo("facu", new Baraja());
		jugadorDos.agregarCartaMonstruoEnCampo("javi", new Baraja());
		
		jugadorUno.agregarCartaMagicaEnCampo("Agujero Negro");
		
		assertTrue(jugadorUno.monstruoEstaMuerto("facu"));
		assertTrue(jugadorDos.monstruoEstaMuerto("javi"));
	}
	
	@Test
	public void test10AgregarAgujeroNegroNoQuitaVidaAJugadores () {
			
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Efecto efecto = new EfectoAgujeroNegro ();
		Magica agujeroNegro = new Magica ("Agujero Negro", jugadorUno, efecto);
		
		jugadorUno.repartirCarta(agujeroNegro);
		jugadorUno.agregarCartaMagicaEnCampo("Agujero Negro");
		
		assertTrue(jugadorUno.obtenerVida().obtenerPuntosDeVida() == 8000);
		assertTrue(jugadorDos.obtenerVida().obtenerPuntosDeVida() == 8000);
		
	}
	
	@Test
	public void test11AgujeroNegroBocaAbajoNoEnviaMonstruosAlCementerio () {
				
		Vida vidaJugadorUno = new Vida (8000);
		Vida vidaJugadorDos = new Vida (8000);
		Jugador jugadorUno = new Jugador (vidaJugadorUno);
		Jugador jugadorDos = new Jugador (vidaJugadorDos);
		
		jugadorUno.asignarRival(jugadorDos);
		jugadorDos.asignarRival(jugadorUno);
		
		Puntos puntos = new Puntos(2000, 2000);
		Monstruo monstruoUno = new Monstruo("facu", jugadorUno, 1, puntos);
		Monstruo monstruoDos = new Monstruo("javi", jugadorDos, 1, puntos);
		
		Efecto efecto = new EfectoAgujeroNegro ();
		Magica agujeroNegro = new Magica ("Agujero Negro", jugadorUno, efecto);
		
		jugadorUno.repartirCarta(monstruoUno);
		jugadorUno.repartirCarta(agujeroNegro);
		jugadorDos.repartirCarta(monstruoDos);
		
		jugadorUno.voltearCartaDeMano("Agujero Negro");
		
		jugadorUno.agregarCartaMonstruoEnCampo("facu", new Baraja());
		jugadorDos.agregarCartaMonstruoEnCampo("javi", new Baraja());
		
		jugadorUno.agregarCartaMagicaEnCampo("Agujero Negro");
		
		assertFalse(jugadorUno.monstruoEstaMuerto("facu"));
		assertFalse(jugadorDos.monstruoEstaMuerto("javi"));
		
	}
}