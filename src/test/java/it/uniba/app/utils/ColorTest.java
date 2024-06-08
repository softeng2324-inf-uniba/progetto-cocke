package it.uniba.app.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ColorTest {

    /**
     * Test per il metodo getColorValue della classe Color.
     * Verifica che il metodo restituisca il valore corretto del colore.
     */
    @Test
    public void testColorValue() {
        Color color = Color.BLACK;
        assertEquals(0, color.getColorValue());
    }

    /**
     * Test per il metodo getColorValue della classe Color.
     * Verifica che il metodo restituisca il valore corretto del colore.
     */
    @Test
    public void testAnotherColorValue() {
        Color color = Color.WHITE;
        assertEquals(15, color.getColorValue());
    }

    /**
     * Test per il metodo getColorValue della classe Color.
     * Inserendo un valore sbagliato, il test fallisce.
     */
    @Test
    public void testWrongColorValue() {
        Color color = Color.WHITE;
        assertEquals(16, color.getColorValue());
    }

    /**
     * Test per il metodo getColorValue della classe Color.
     * Inserendo un colore inesistente, il test fallisce.
     */
    @Test
    public void testAnotherWrongColorValue() {
        Color color = Color.valueOf("RED");
        assertEquals(15, color.getColorValue());
    }
}
