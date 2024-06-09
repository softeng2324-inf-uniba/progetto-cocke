package it.uniba.app.model;

import it.uniba.app.utils.Color;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SlotTest {

    /**
     * Casella utilizzata per testare la classe <code>Slot</code>, inizializzata con <code>Color.GREY</code>.
     */
    private final Slot testSlot = new Slot(Color.GREY);

    /**
     * Messaggio d'errore nel caso in cui la copia eseguita attraverso il costruttore di copia della classe
     * <code>Slot</code>, non corrisponda all'istanza di <code>Slot</code> in ingresso al costruttore stesso.
     */
    private static final String WRONG_COPY = "La copia della casella non corrisponde alla casella copiata.";

    /**
     * Messaggio d'errore nel caso in cui il colore dello <code>Slot</code> in esame non corrisponda al colore atteso.
     */
    private static final String WRONG_GET_COLOR = "Il colore della casella in esame non corrisponde con quello atteso.";

    /**
     * Messaggio d'errore nel caso in cui il colore dello <code>Slot</code> modificato attraverso il metodo
     * <code>setColorState()</code> non corrisponda al colore atteso.
     */
    private static final String WRONG_SET_COLOR = "Il colore inserito nella casella in esame non corrisponde con"
            + " quello atteso.";

    /**
     * Messaggio d'errore nel caso in cui il colore dello <code>Slot</code>, modificato attraverso il metodo
     * <code>markSlot()</code>, basato sulla distanza rispetto a una data casella, non corrisponda al colore previsto.
     */
    private static final String WRONG_MARK_SLOT = "Il colore della casella in esame, in base alla distanza prevista, "
            + "non corrisponde con quello atteso.";

    /**
     * Messaggio d'errore nel caso in cui gli <code>Slot</code> in esame, uno copia dell'altro, risultino diversi.
     */
    private static final String WRONG_EQUALS = "Le caselle in esame risultano diverse.";

    /**
     * Messaggio d'errore nel caso in cui il valore hash calcolato attraverso il metodo <code>hashCode()</code> sia
     * diverso da quello atteso.
     */
    private static final String WRONG_HASH = "Il valore hash, della casella, calcolato non corrisponde a quello "
            + "atteso.";

    /**
     * Valore hash di testSlot con colore <code>Color.GREY</code>.
     */
    private static final int HASH_GREY_SLOT = 2196191;

    /**
     * Test del costruttore di copia della classe <code>Slot</code>, per cui viene inizializzato una nuova casella con
     * il colore con cui testSlot è stata inizializzata, e su queste viene eseguito il test.
     */
    @Test
    void testCopyConstructor() {
        Slot temp = new Slot(testSlot);
        assertEquals(testSlot, temp, WRONG_COPY);
    }

    /**
     * Test del metodo <code>getColorState()</code> sulla casella testSlot, che verifica se il colore con cui questa è
     * stata inizializzata, ottenuto mediante il metodo <code>getColorState()</code>, corrisponde a quello atteso.
     */
    @Test
    void testGetColorState() {
        assertEquals(Color.GREY, testSlot.getColorState(), WRONG_GET_COLOR);
    }

    /**
     * Test del metodo <code>setColorState()</code> sulla casella testSlot, che verifica se il colore con cui questa è
     * stata modificata, corrisponde a quello atteso.
     */
    @Test
    void testSetColorState() {
        testSlot.setColorState(Color.PINK);
        assertEquals(Color.PINK, testSlot.getColorState(), WRONG_SET_COLOR);
    }

    /**
     * Test del metodo <code>markSlot</code>, che verifica se la casella modifica il proprio colore (stato), in base
     * alla distanza, data in entrata al metodo stesso (caso in cui la casella è libera).
     */
    @Test
    void testMarkSlotFree() {
        testSlot.markSlot(1);
        assertEquals(Color.YELLOW, testSlot.getColorState(), WRONG_MARK_SLOT);
    }

    /**
     * Test del metodo <code>markSlot</code>, che verifica se la casella modifica il proprio colore (stato), in base
     * alla distanza, data in entrata al metodo stesso (caso in cui la casella è occupata).
     */
    @Test
    void testMarkSlotTaken() {
        testSlot.setColorState(Color.WHITE);
        testSlot.markSlot(1);
        assertEquals(Color.WHITE, testSlot.getColorState(), WRONG_MARK_SLOT);
    }

    /**
     * Test del metodo <code>equals()</code> su testSlot e una sua copia, che ne verifica l'uguaglianza.
     */
    @Test
    void testEquals() {
        Slot temp = new Slot(testSlot);
        boolean match = temp.equals(testSlot);
        assertTrue(match, WRONG_EQUALS);
    }

    /**
     * Test del metodo <code>hashCode()</code> su testSlot, che verifica l'uguaglianza del valore hash dello stesso
     * con quello atteso.
     */
    @Test
    void testHashCode() {
        assertEquals(HASH_GREY_SLOT, testSlot.hashCode(), WRONG_HASH);
    }

}
