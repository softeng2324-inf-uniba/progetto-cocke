package it.uniba.app.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {

    /**
     * Coordinate temporanee per i casi di test.
     */
    Coordinate tempCoordinate;

    /**
     * Messaggio di errore quando la riga non è corretta.
     */
    final static String WRONG_ROW = "La riga restituita non coincide con quella attesa";

    /**
     * Messaggio di errore quando la colonna non è corretta.
     */
    final static String WRONG_COLUMN = "La colonna restituita non coincide con quella attesa";

    /**
     * Messaggio di errore quando la riga non è stata modificata correttamente.
     */
    final static String BAD_ROW = "La riga non è stata modificata correttamente";

    /**
     * Messaggio di errore quando la colonna non è stata modificata correttamente.
     */
    final static String BAD_COLUMN = "La colonna non è stata modificata correttamente";

    /**
     * Messaggio di errore quando le coordinate risultano diverse.
     */
    final static String BAD_EQUALS = "Le coordinate non risultano uguali";

    /**
     * Messaggio di errore quando la stampa della coordinata non è corretta.
     */
    final static String BAD_TO_STRING = "La stampa della coordinata non è corretta";

    /**
     * Imposta le coordinate temporanee di default (5,6) per i test.
     */
    @BeforeEach
    void setUp() {
        tempCoordinate = new Coordinate(5, 6);
    }

    /**
     * Test per il metodo setRow.
     * Viene testato il caso in cui la riga viene modificata correttamente.
     */
    @Test
    void testSetRow() {
        tempCoordinate.setRow(10);
        assertEquals(10, tempCoordinate.getRow(), BAD_ROW);
    }

    /**
     * Test per il metodo getRow.
     * Viene testato il caso in cui la riga viene restituita correttamente.
     */
    @Test
    void testGetRow() {
        assertEquals(5, tempCoordinate.getRow(), WRONG_ROW);
    }

    /**
     * Test per il metodo setColumn.
     * Viene testato il caso in cui la colonna viene modificata correttamente.
     */
    @Test
    void testSetColumn() {
        tempCoordinate.setColumn(10);
        assertEquals(10, tempCoordinate.getColumn(), BAD_COLUMN);
    }

    /**
     * Test per il metodo getColumn.
     * Viene testato il caso in cui la colonna viene restituita correttamente.
     */
    @Test
    void testGetColumn() {
        assertEquals(6, tempCoordinate.getColumn(), WRONG_COLUMN);
    }

    /**
     * Test per il costruttore di copia.
     * Viene testato il caso in cui una nuova coordinata viene creata correttamente.
     */
    @Test
    void testCopyConstructor() {
        Coordinate copy = new Coordinate(tempCoordinate);
        assertAll("Costruttore di copia",
                () -> assertEquals(5, copy.getRow(), WRONG_ROW),
                () -> assertEquals(6, copy.getColumn(), WRONG_COLUMN)
        );
    }

    /**
     * Test per il metodo equalsCoordinate.
     * Viene testato il caso in cui due coordinate sono uguali.
     */
    @Test
    void testEqualsCoordinate() {
        Coordinate equalCoordinate = new Coordinate(5, 6);
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
