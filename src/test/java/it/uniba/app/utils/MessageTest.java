package it.uniba.app.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MessageTest {


    /**
     * Test per il metodo getMessageText della classe Message.
     * Verifica che il metodo restituisca il testo del messaggio corretto.
     */
    @Test
    public void testMessageText() {
        assertEquals("Mossa non valida, riprova.", Message.ILLEGAL_MOVE.getMessageText());
    }

    /**
     * Test per il metodo getMessageText della classe Message.
     * Verifica che il metodo restituisca il testo del messaggio corretto.
     */
    @Test
    public void testAnotherMessageText() {
        assertEquals("Nessuna mossa disponibile per ", Message.UNAVAILABLE_MOVE.getMessageText());
    }

    /**
     * Test per il metodo getMessageText della classe Message.
     * Verifica che il metodo restituisca il testo del messaggio corretto.
     */
    @Test
    public void testWrongMessageText() {
        assertNotEquals("Nessuna mossa disponibile per ", Message.ILLEGAL_MOVE.getMessageText());
    }

    /**
     * Test per il metodo getMessageText della classe Message.
     * Verifica che il metodo restituisca il testo del messaggio corretto.
     */
    @Test
    public void testAnotherWrongMessageText() {
        assertThrows(IllegalArgumentException.class, () -> Message.valueOf("IPPEGAL_MOVE"));
    }
}
