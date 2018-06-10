import org.junit.jupiter.api.Test;
import jugabilidad.Jugador;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestJugador{

    @Test
    public void testJugadorNuevoTieneXPuntos (){
        Jugador unJugador = new Jugador(8000);

        double vidaEsperada = 8000;
        double vidaObtenida = unJugador.obtenerVida();

        assertEquals(vidaEsperada,vidaObtenida);
    }

    @Test
    public void testJugadorRecibeDanio(){

        Jugador unJugador = new Jugador(8000);

        unJugador.hacerDanio(2000);

        double vidaEsperada = 6000;
        double vidaObtenida = unJugador.obtenerVida();

        assertEquals(vidaEsperada,vidaObtenida);
    }
}