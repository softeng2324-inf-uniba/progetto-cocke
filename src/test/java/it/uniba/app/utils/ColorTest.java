package it.uniba.app.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class ColorTest {
    /**
     * Variabile di istanza di tipo Color per i casi di test.
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
     * Verifica che un colore esistente è diverso rispetto a un valore sbagliato.
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
        assertThrows(IllegalArgumentException.class, () -> Color.valueOf("GOLDEN"));
    }
}
