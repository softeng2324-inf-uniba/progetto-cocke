package it.uniba.app.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MoveTest {

    private final Coordinate starting = new Coordinate(0,0);
    private final Coordinate destination = new Coordinate(1,1);
    private Move testMove;

    final static String WRONG_START = "Lo slot di partenza non coincide con quello inizializzato nella mossa.";
    final static String WRONG_DESTINATION = "Lo slot di arrivo non coincide con quello inizializzato nella mossa.";
    final static String WRONG_DISTANCE = "La distanza calcolata tra le due coordinate non coincide con quella attesa.";
    final static String WRONG_STRING = "La stringa corrispondente alla mossa in analisi, non coincide con quella attesa.";

    @BeforeEach
    void setUp() {
        testMove = new Move(starting, destination);
    }

    @Test
    void testGetStartingSlot() {
        assertEquals(starting, testMove.getStartingSlot(), WRONG_START);
    }

    @Test
    void testGetChosenSlot() {
        assertEquals(destination, testMove.getChosenSlot(), WRONG_DESTINATION);
    }

    @Test
    void testGetDistance() {
        assertEquals(1, testMove.getDistance(), WRONG_DISTANCE);
    }

    @Test
    void testToString() {
        assertEquals("a1-b2", testMove.toString(), WRONG_STRING);
    }

}
