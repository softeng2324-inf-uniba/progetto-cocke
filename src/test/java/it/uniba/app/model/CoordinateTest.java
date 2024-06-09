package it.uniba.app.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CoordinateTest {

    /**
     * Coordinate temporanee per i casi di test.
     */
    private Coordinate tempCoordinate;

    /**
     * Messaggio di errore restituito quando la riga restituita non è corretta.
     */
    static final String WRONG_ROW = "La riga restituita non coincide con quella attesa";

    /**
     * Messaggio di errore restituito quando la colonna restituita non è corretta.
     */
    static final String WRONG_COLUMN = "La colonna restituita non coincide con quella attesa";

    /**
     * Messaggio di errore restituito quando la riga non è stata modificata correttamente.
     */
    static final String BAD_ROW = "La riga non è stata modificata correttamente";

    /**
     * Messaggio di errore restituito quando la colonna non è stata modificata correttamente.
     */
    static final String BAD_COLUMN = "La colonna non è stata modificata correttamente";

    /**
     * Messaggio di errore restituito quando la stampa della coordinata non è corretta.
     */
    static final String BAD_TO_STRING = "La stampa della coordinata non è corretta";

    /**
     * Messaggio di errore restituito quando la coordinata non è stata copiata correttamente.
     */
    static final String WRONG_COPY = "La coordinata non è stata copiata correttamante";

    /**
     * Messaggio di errore restituito quando due coordinate risultano diverse tra loro.
     */
    static final String BAD_MATCH = "Le coordinate non risultano uguali";

    /**
     * Messaggio di errore restituito quando l'hash della coordinata non coincide con quello atteso.
     */
    static final String WRONG_HASH = "L'hash della coordinata non corrisponde con quello atteso";

    /**
     * L'hash calcolato su tempCoordinate.
     */
    static final int HASH_TEMP_COORDINATE = 1697;

    /**
     * Riga della coordinata di test.
     */
    static final int COORDINATE_ROW = 5;

    /**
     * Colonna della coordinata di test.
     */
    static final int COORDINATE_COLUMN = 6;

    /**
     * Riga di test.
     */
    static final int TEST_ROW = 10;

    /**
     * Colonna di test.
     */
    static final int TEST_COLUMN = 10;

    /**
     * Imposta le coordinate temporanee di default (5,6) per i test.
     */
    @BeforeEach
    void setUp() {
        tempCoordinate = new Coordinate(COORDINATE_ROW, COORDINATE_COLUMN);
    }

    /**
     * Test per il metodo setRow.
     * Viene testato il caso in cui la riga viene modificata correttamente.
     */
    @Test
    void testSetRow() {
        tempCoordinate.setRow(TEST_ROW);
        assertEquals(TEST_ROW, tempCoordinate.getRow(), BAD_ROW);
    }

    /**
     * Test per il metodo getRow.
     * Viene testato il caso in cui la riga viene restituita correttamente.
     */
    @Test
    void testGetRow() {
        assertEquals(COORDINATE_ROW, tempCoordinate.getRow(), WRONG_ROW);
    }

    /**
     * Test per il metodo setColumn.
     * Viene testato il caso in cui la colonna viene modificata correttamente.
     */
    @Test
    void testSetColumn() {
        tempCoordinate.setColumn(TEST_COLUMN);
        assertEquals(TEST_COLUMN, tempCoordinate.getColumn(), BAD_COLUMN);
    }

    /**
     * Test per il metodo getColumn.
     * Viene testato il caso in cui la colonna viene restituita correttamente.
     */
    @Test
    void testGetColumn() {
        assertEquals(COORDINATE_COLUMN, tempCoordinate.getColumn(), WRONG_COLUMN);
    }

    /**
     * Test per il costruttore di copia.
     * Viene testato il caso in cui una nuova coordinata viene creata correttamente.
     */
    @Test
    void testCopyConstructor() {
        Coordinate copy = new Coordinate(tempCoordinate);

        assertEquals(tempCoordinate, copy, WRONG_COPY);
    }

    /**
     * Test per il metodo toString.
     * Viene testato il caso in cui la stampa della coordinata è corretta.
     */
    @Test
    void testToString() {
        assertEquals("g6", tempCoordinate.toString(), BAD_TO_STRING);
    }

    /**
     * Test per il metodo equals.
     * Verifica l'uguaglianza tra una copia della coordinata e la coordinata effettiva.
     */
    @Test
    void testEquals() {
        Coordinate copy = new Coordinate(tempCoordinate);

        boolean match = tempCoordinate.equals(copy);
        assertTrue(match, BAD_MATCH);
    }

    /**
     * Test per il metodo hashCode.
     * Verifica che l'hash restituito corrisponda a quello della coordinata di test.
     */
    @Test
    void testHashCode() {
        assertEquals(HASH_TEMP_COORDINATE, tempCoordinate.hashCode(), WRONG_HASH);
    }

}
