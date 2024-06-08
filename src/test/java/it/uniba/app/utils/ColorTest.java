package it.uniba.app.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class ColorTest {
    /**
     *
     */
    Color color;

    /**
     * Test per il metodo getColorValue della classe Color.
     * Verifica che il metodo restituisca il valore corretto del colore.
     */
    @Test
    public void testColorValue() {
        color = Color.BLACK;
        assertEquals(0, color.getColorValue());
    }

    /**
     * Test per il metodo getColorValue della classe Color.
     * Verifica che il metodo restituisca il valore corretto del colore.
     */
    @Test
    public void testAnotherColorValue() {
        color = Color.WHITE;
        assertEquals(15, color.getColorValue());
    }

    /**
     * Test per il metodo getColorValue della classe Color.
     * Inserendo un valore sbagliato, il test fallisce.
     */
    @Test
    public void testWrongColorValue() {
        color = Color.BLACK;
        assertNotEquals(16, color.getColorValue());
    }

    /**
     * Test per il metodo getColorValue della classe Color.
     * Inserendo un colore inesistente, il test fallisce.
     */
    @Test
    public void testAnotherWrongColorValue() {
        color = Color.valueOf("GOLD");
        assertEquals(178, color.getColorValue());
    }

    /**
     * Test per il metodo getColorValue della classe Color.
     * Inserendo un valore non nullo, il test fallisce.
     */
    @Test
    public void testNullColorValue() {
        color = null;
        assertThrows(NullPointerException.class, () -> color.getColorValue());
    }


}
