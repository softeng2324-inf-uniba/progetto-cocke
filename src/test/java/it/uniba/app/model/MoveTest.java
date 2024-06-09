package it.uniba.app.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MoveTest {

    /**
     * Coordinate del campo da gioco corrispondenti a una casella di partenza di una mossa.
     */
    private final Coordinate starting = new Coordinate(0,0);

    /**
     * Coordinate del campo da gioco corrispondenti a una casella di arrivo di una mossa.
     */
    private final Coordinate destination = new Coordinate(1,1);

    /**
     * Mossa temporanea, usata durante i test, inizializzata nel costruttore <code>setUp()</code>.
     */
    private Move testMove;

    /**
     * Messaggio di errore relativo alla non corrispondenza della casella di partenza utilizzata per l'inizializzazione
     * della mossa e la stessa ottenuta dalla relativa mossa attraverso il metodo <code>getStartingSlot()</code>.
     */
    final static String WRONG_START = "Lo slot di partenza non coincide con quello inizializzato nella mossa.";

    /**
     * Messaggio di errore relativo alla non corrispondenza della casella di arrivo utilizzata per l'inizializzazione
     * della mossa e la stessa ottenuta dalla relativa mossa attraverso il metodo <code>getChosenSlot()</code>.
     */
    final static String WRONG_DESTINATION = "Lo slot di arrivo non coincide con quello inizializzato nella mossa.";

    /**
     * Messaggio di errore nel caso in cui la distanza ipotizzata tra le due caselle, coinvolte nella mossa, non
     * corrisponda a quella calcolata attraverso il metodo <code>getDistance()</code>.
     */
    final static String WRONG_DISTANCE = "La distanza calcolata tra le due coordinate non coincide con quella attesa.";

    /**
     * Messaggio di errore corrispondente alla disuguaglianza tra la stringa calcolata attraverso il metodo <code>toString()</code>
     * della mossa su cui il metodo è invocato, e quella ipotizzata.
     */
    final static String WRONG_STRING = "La stringa corrispondente alla mossa in analisi, non coincide con quella attesa.";


    /**
     * Inizializza, prima di ogni test, la mossa di default utilizzata per ognuno di essi.
     */
    @BeforeEach
    void setUp() {
        testMove = new Move(starting, destination);
    }

    /**
     * Test per il metodo <code>getStartingSlot()</code>, nel caso in cui la casella di partenza della mossa esaminata e
     * quella confrontata coincidono.
     */
    @Test
    void testGetStartingSlot() {
        assertEquals(starting, testMove.getStartingSlot(), WRONG_START);
    }

    /**
     * Test per il metodo <code>getChosenSlot()</code>, nel caso in cui la casella d'arrivo della mossa esaminata e
     * quella confrontata coincidono.
     */
    @Test
    void testGetChosenSlot() {
        assertEquals(destination, testMove.getChosenSlot(), WRONG_DESTINATION);
    }

    /**
     * Test per il metodo <code>getDistance()</code>, nel caso in cui la distanza, tra le due caselle coinvolte nella
     * mossa in esame, coincida con quella attesa (1).
     */
    @Test
    void testGetDistance() {
        assertEquals(1, testMove.getDistance(), WRONG_DISTANCE);
    }

    /**
     * Test per il metodo <code>toString()</code> invocato sulla mossa in esame, nel caso in cui la stringa ottenuta
     * in uscita corrisponda a quella prevista per la mossa.
     */
    @Test
    void testToString() {
        assertEquals("a1-b2", testMove.toString(), WRONG_STRING);
    }

}
