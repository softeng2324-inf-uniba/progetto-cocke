package it.uniba.app.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


class ColorTest {
    /**
     * Variabile di istanza di tipo Color per i casi di test.
     */
    private Color color;

    /**
     * Variabile di istanza che rappresenta il messaggio di errore
     * quando un test fallisce.
     */
    private static final String TEST_FAILED = "Test fallito, il messaggio passato non è corretto.";

    /**
     * Test per il metodo getColorValue della classe Color.
     * Verifica che il metodo restituisca il valore corretto del colore.
     */
    @Test
    void testColorValue() {
        final int blackValue = 0;
        color = Color.BLACK;
        assertEquals(blackValue, color.getColorValue(), TEST_FAILED);
    }

    /**
     * Test per il metodo getColorValue della classe Color.
     * Verifica che il metodo restituisca il valore corretto del colore.
     */
    @Test
    void testAnotherColorValue() {
        final int whiteValue = 15;
        color = Color.WHITE;
        assertEquals(whiteValue, color.getColorValue(), TEST_FAILED);
    }

    /**
     * Test per il metodo getColorValue della classe Color.
     * Verifica che un colore esistente è diverso rispetto a un valore sbagliato.
     */
    @Test
    void testWrongColorValue() {
        final int notBlackValue = 16;
        color = Color.BLACK;
        assertNotEquals(notBlackValue, color.getColorValue());
    }


    /**
     * Test per il metodo getColorValue della classe Color.
     * Inserendo un colore inesistente, il test fallisce.
     */
    @Test
    void testAnotherWrongColorValue() {
        assertThrows(IllegalArgumentException.class, () -> Color.valueOf("GOLDEN"));
    }
}
