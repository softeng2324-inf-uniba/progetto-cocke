package it.uniba.app.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MessageTest {

    /**
     * Variabile di istanza di tipo Message per i casi di test.
     */
    Message message;

    /**
     * Variabile di istanza che rappresenta il messaggio di errore
     * quando un test fallisce.
     */
    private static final String TEST_FAILED = "Test fallito, il messaggio passato non è corretto.";

    /**
     * Test per il metodo getMessageText della classe Message.
     * Verifica che il metodo restituisca il testo del messaggio corretto.
     */
    @Test
    void testMessageText() {
        message = Message.ILLEGAL_MOVE;
        assertEquals("Mossa non valida, riprova.", message.getMessageText(), TEST_FAILED);
    }

    /**
     * Test per il metodo getMessageText della classe Message.
     * Verifica che il metodo restituisca il testo del messaggio corretto.
     */
    @Test
    void testAnotherMessageText() {
        assertEquals("Nessuna mossa disponibile per ", Message.UNAVAILABLE_MOVE.getMessageText(), TEST_FAILED);
    }

    /**
     * Test per il metodo getMessageText della classe Message.
     * Verifica che il metodo restituisca il testo del messaggio corretto.
     */
    @Test
    void testWrongMessageText() {
        assertNotEquals("Nessuna mossa disponibile per ", Message.ILLEGAL_MOVE.getMessageText(), TEST_FAILED);
    }

    /**
     * Test per il metodo getMessageText della classe Message.
     * Verifica che il metodo restituisca il testo del messaggio corretto.
     */
    @Test
    void testAnotherWrongMessageText() {
        assertThrows(IllegalArgumentException.class, () -> Message.valueOf("IPPEGAL_MOVE"), TEST_FAILED);
    }
}
