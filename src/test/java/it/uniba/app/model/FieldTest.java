package it.uniba.app.model;

import it.uniba.app.utils.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FieldTest {

    /**
     * Campo temporaneo per i casi di test.
     */
    private Field tempField;

    /**
     * Slot temporaneo per i casi di test.
     */
    private Slot tempSlot;

    /**
     * Messaggio di errore quando il campo copiato non coincide con quello atteso.
     */
    static final String WRONG_COPY = "Il campo copiato non coincide con quello atteso";

    /**
     * Messaggio di errore quando lo slot restituito non coincide con quello atteso.
     */
    static final String BAD_RETURN_SLOT = "Lo slot restituito non coincide con quello atteso";

    /**
     * Messaggio di errore quando lo slot non è stato modificato correttamente.
     */
    static final String BAD_SLOT = "Lo slot non è stato modificato correttamente";

    /**
     * Messaggio di errore quando la dimensione restituita non coincide con quella attesa.
     */
    static final String BAD_LENGTH = "La dimensione restituita non coincide con quella attesa";

    /**
     * Messaggio di errore restituito quando due campi risultano diversi tra loro.
     */
    static final String BAD_MATCH = "I campi non risultano uguali";

    /**
     * Messaggio di errore restituito quando l'hash del campo non coincide con quello atteso.
     */
    static final String WRONG_HASH = "L'hash del campo non corrisponde con quello atteso";

    /**
     * L'hash calcolato su tempField.
     */
    static final int HASH_TEMP_FIELD = 941589020;

    /**
     * Coordinata pre impostata prima di ogni test.
     */
    static final Coordinate TEMP_COORDINATE = new Coordinate(5, 5);

    /**
     * Coordinata utilizzata per effettuare test.
     */
    static final Coordinate TEST_COORDINATE = new Coordinate(3, 3);

    /**
     * Imposta il campo temporaneo di default per i test.
     * Imposta uno slot di colore GOLD nella posizione (5,5).
     */
    @BeforeEach
    void setUp() {
        tempField = new Field();
        tempSlot = new Slot();
        tempSlot.setColorState(Color.GOLD);
        tempField.setSlot(TEMP_COORDINATE, tempSlot);
    }

    /**
     * Test per il costruttore di copia.
     * Viene testato il caso in cui il campo viene copiato correttamente.
     */
    @Test
    void testCopyConstructor() {
        Field copy = new Field(tempField);
        assertEquals(tempField, copy, WRONG_COPY);
    }

    /**
     * Test per il metodo setSlot.
     * Viene testato il caso in cui lo slot viene modificato correttamente.
     */
    @Test
    void testSetSlot() {
        tempField.setSlot(TEST_COORDINATE, tempSlot);
        assertEquals(tempSlot.getColorState(), tempField.getSlot(TEST_COORDINATE).getColorState(), BAD_SLOT);
    }

    /**
     * Test per il metodo getSlot.
     * Viene testato il caso in cui lo slot viene restituito correttamente.
     */
    @Test
    void testGetSlot() {
        assertEquals(Color.GOLD, tempField.getSlot(TEMP_COORDINATE).getColorState(), BAD_RETURN_SLOT);
    }

    /**
     * Test per il metodo length.
     * Viene testato il caso in cui la lunghezza del campo viene restituita correttamente.
     */
    @Test
    void testLength() {
        assertEquals(Field.DEFAULT_DIM, tempField.length(), BAD_LENGTH);
    }

    /**
     * Test per il metodo equals.
     * Verifica l'uguaglianza tra una copia del campo e il campo effettivo.
     */
    @Test
    void testEquals() {
        Field copy = new Field(tempField);

        boolean match = tempField.equals(copy);
        assertTrue(match, BAD_MATCH);
    }

    /**
     * Test per il metodo hashCode.
     * Verifica che l'hash restituito corrisponda a quello del campo di test.
     */
    @Test
    void testHashCode() {
        assertEquals(HASH_TEMP_FIELD, tempField.hashCode(), WRONG_HASH);
    }

}
