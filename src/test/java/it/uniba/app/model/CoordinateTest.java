package it.uniba.app.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {

    Coordinate tempCoordinate;

    @BeforeEach
    void setUp() {
        tempCoordinate = new Coordinate(5, 6);
    }

    @Test
    void testSetRow() {
        tempCoordinate.setRow(10);
        assertEquals(10, tempCoordinate.getRow(), "La riga non è stata modificata correttamente");
    }

    @Test
    void testGetRow() {
        assertEquals(5, tempCoordinate.getRow(), "La riga restituita non coincide con quella attesa");
    }

    @Test
    void testSetColumn() {
        tempCoordinate.setColumn(10);
        assertEquals(10, tempCoordinate.getColumn(), "La colonna non è stata modificata correttamente");
    }

    @Test
    void testGetColumn() {
        assertEquals(6, tempCoordinate.getColumn(), "La colonna restituita non coincide con quella attesa");
    }

    @Test
    void testCopyConstructor() {
        Coordinate copy = new Coordinate(tempCoordinate);
        assertAll("Costruttore di copia",
                () -> assertEquals(5, copy.getRow(), "La riga restituita non coincide con quella attesa"),
                () -> assertEquals(6, copy.getColumn(), "La colonna restituita non coincide con quella attesa")
        );
    }

    @Test
    void testEqualsCoordinate() {
        Coordinate equalCoordinate = new Coordinate(5, 6);
        assertTrue(equalCoordinate.equalsCoordinate(tempCoordinate), "Le coordinate non risultano uguali");
    }

    @Test
    void testToString() {
        assertEquals("g6", tempCoordinate.toString(), "La stampa della coordinata non è corretta");
    }

}
