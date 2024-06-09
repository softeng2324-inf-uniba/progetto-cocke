package it.uniba.app.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
     * Messaggio di errore restituito quando le coordinate risultano diverse.
     */
    static final String BAD_EQUALS = "Le coordinate non risultano uguali";

    /**
     * Messaggio di errore restituito quando la stampa della coordinata non è corretta.
     */
    static final String BAD_TO_STRING = "La stampa della coordinata non è corretta";

    /**
     * Messaggio di errore restituito quando la coordinata non è stata copiata correttamente.
     */
    static final String WRONG_COPY = "La coordinata non è stata copiata correttamante";

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
     * Test per il metodo equalsCoordinate.
     * Viene testato il caso in cui due coordinate sono uguali.
     */
    @Test
    void testEqualsCoordinate() {
        Coordinate equalCoordinate = new Coordinate(COORDINATE_ROW, COORDINATE_COLUMN);

        assertEquals(equalCoordinate, tempCoordinate, BAD_EQUALS);
    }

    /**
     * Test per il metodo toString.
     * Viene testato il caso in cui la stampa della coordinata è corretta.
     */
    @Test
    void testToString() {
        assertEquals("g6", tempCoordinate.toString(), BAD_TO_STRING);
    }

}
