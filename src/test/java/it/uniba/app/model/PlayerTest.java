package it.uniba.app.model;

import it.uniba.app.utils.Color;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerTest {

    /**
     * <Code>Player</Code> test utilizzato all'interno dei test della classe, inizializzato con il colore <Code>Color.BLACK</Code>
     * e il nome "Giocatore 1".
     */
    private final Player testPlayer = new Player(Color.BLACK, "Giocatore 1");

    /**
     * Messaggio d'errore nel caso in cui la copia eseguita attraverso il costruttore di copia della classe <code>Player</code>,
     * non corrisponda all'istanza di <code>Player</code> in ingresso al costruttore stesso.
     */
    private final static String WRONG_COPY = "La copia del giocatore eseguita non corrisponde al giocatore copiato.";

    /**
     * Messaggio d'errore nel caso in cui il colore del <code>Player</code> in esame non corrisponda a quello previsto.
     */
    private final static String WRONG_COLOR = "Il colore del giocatore non corrisponde a quello atteso.";

    /**
     * Messaggio d'errore nel caso in cui il nome del <code>Player</code> in esame non corrisponda al nome atteso.
     */
    private static final String WRONG_NAME = "Il nome del giocatore non corrisponde a quello previsto.";

    /**
     * Messaggio d'errore nel caso in cui i <code>Player</code> in esame, uno copia dell'altro, risultino diversi.
     */
    private static final String WRONG_EQUALS = "I giocatori in esame risultano diversi.";

    /**
     * Messaggio d'errore nel caso in cui il valore hash calcolato attraverso il metodo <code>hashCode()</code> sia
     * diverso da quello atteso.
     */
    private static final String WRONG_HASH = "Il valore hash del giocatore calcolato non corrisponde a quello atteso.";


    /**
     * Test del costruttore di copia della classe <Code>Player</Code>, per cui viene inizializzato un nuovo giocatore con
     * gli stessi valori degli attributi contenuti all'interno di testPlayer, e su questi viene eseguito il test.
     */
    @Test
    void testCopyConstructor() {
        Player copyTest = new Player(testPlayer);
        assertEquals(testPlayer, copyTest, WRONG_COPY);
    }

    /**
     * Test del metodo <code>getColor()</code> sul testPlayer a disposizione, che verifica se il colore con cui questo
     * è stato inizializzato, corrisponde a quello atteso.
     */
    @Test
    void testGetColor() {
        assertEquals(Color.BLACK, testPlayer.getColor(), WRONG_COLOR);
    }

    /**
     * Test del metodo <code>getName()</code> sul testPlayer a disposizione, che verifica se il nome con cui questo
     * è stato inizializzato, corrisponde a quello atteso.
     */
    @Test
    void testGetName() {
        assertEquals("Giocatore 1", testPlayer.getName(), WRONG_NAME);
    }

    /**
     * Test del metodo <code>equals()</code> su testPlayer e una sua copia, che verifica l'uguaglianza viene verificata.
     */
    @Test
    void testEquals() {
        Player temp = new Player(testPlayer);
        boolean match = temp.equals(testPlayer);
        assertTrue(match, WRONG_EQUALS);
    }

    /**
     * Test del metodo <code>hashCode()</code> su testPlayer, che verifica l'uguaglianza del valore hash dello stesso
     * con quello atteso.
     */
    @Test
    void testHashCode() {
        assertEquals(-628335851, testPlayer.hashCode(), WRONG_HASH);
    }

}
