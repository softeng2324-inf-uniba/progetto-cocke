package it.uniba.app.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
     * Inserendo un messaggio sbagliato e confrontandolo con un messaggio corretto, il test fallisce.
     */
    @Test
    public void testWrongMessageText() {
        assertEquals("Nessuna mossa disponibile per ", Message.ILLEGAL_MOVE.getMessageText());
    }

    /**
     * Test per il metodo getMessageText della classe Message.
     * Inserendo un messaggio inesistente, il test fallisce.
     */
    @Test
    public void testAnotherWrongMessageText() {
        assertEquals("Nessuna mossa disponibile per ", Message.valueOf("IPPEGAL_MOVE").getMessageText());
    }
}
