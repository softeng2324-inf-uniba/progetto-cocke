package it.uniba.app.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoveTest {

    private final Coordinate starting = new Coordinate(0,0);
    private final Coordinate destination = new Coordinate(1,1);
    private Move testMove;

    @BeforeEach
    void setUp() {
        testMove = new Move(starting, destination);
    }

    @Test
    void testGetStartingSlot() {
        assertEquals(starting, testMove.getStartingSlot());
    }

    @Test
    void testGetChosenSlot() {
        assertEquals(destination, testMove.getChosenSlot());
    }

    @Test
    void testGetDistance() {
        assertEquals(1, testMove.getDistance());
    }

    @Test
    void testToString() {
        assertEquals("a1-b2", testMove.toString());
    }

}
