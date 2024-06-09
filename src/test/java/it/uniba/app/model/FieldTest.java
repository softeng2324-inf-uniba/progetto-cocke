package it.uniba.app.model;

import it.uniba.app.utils.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FieldTest {

    /**
     * Campo temporaneo per i casi di test.
     */
    Field tempField;

    /**
     * Slot temporaneo per i casi di test.
     */
    Slot tempSlot;

    /**
     * Messaggio di errore quando lo slot copiato non coincide con quello atteso.
     */
    final static String BAD_COPY_SLOT = "Lo slot copiato non coincide con quello atteso";

    /**
     * Messaggio di errore quando lo slot restituito non coincide con quello atteso.
     */
    final static String BAD_RETURN_SLOT = "Lo slot restituito non coincide con quello atteso";

    /**
     * Messaggio di errore quando lo slot non è stato modificato correttamente.
     */
    final static String BAD_SLOT = "Lo slot non è stato modificato correttamente";

    /**
     * Messaggio di errore quando la dimensione del campo non coincide con quella attesa.
     */
    final static String BAD_FIELD_LENGTH = "La dimensione del campo non coincide con quella attesa";

    /**
     * Messaggio di errore quando la dimensione restituita non coincide con quella attesa.
     */
    final static String BAD_LENGTH = "La dimensione restituita non coincide con quella attesa";

    /**
     * Imposta il campo temporaneo di default per i test.
     * Imposta uno slot di colore GOLD nella posizione (5,5).
     */
    @BeforeEach
    void setUp() {
        tempField = new Field();
        tempSlot = new Slot(Color.GOLD);
        tempField.setSlot(new Coordinate(5, 5), tempSlot);
    }

    /**
     * Test per il costruttore di copia.
     * Viene testato il caso in cui il campo viene copiato correttamente.
     */
    @Test
    void testCopyConstructor() {
        Field copy = new Field(tempField);
        assertAll("Costruttore di copia",
                () -> assertEquals(Color.GOLD, copy.getSlot(new Coordinate(5, 5)).getColorState(), BAD_COPY_SLOT),
                () -> assertEquals(Color.GREY, copy.getSlot(new Coordinate(4, 6)).getColorState(), BAD_COPY_SLOT)
        );
    }

    /**
     * Test per il metodo setSlot.
     * Viene testato il caso in cui lo slot viene modificato correttamente.
     */
    @Test
    void testSetSlot() {
        tempField.setSlot(new Coordinate(3, 3), tempSlot);
        assertEquals(tempSlot.getColorState(), tempField.getSlot(new Coordinate(3, 3)).getColorState(), BAD_SLOT);
    }

    /**
     * Test per il metodo getSlot.
     * Viene testato il caso in cui lo slot viene restituito correttamente.
     */
    @Test
    void testGetSlot() {
        assertAll("Getter di uno slot del campo",
                () -> assertEquals(Color.GOLD, tempField.getSlot(new Coordinate(5, 5)).getColorState(), BAD_RETURN_SLOT),
                () -> assertEquals(Color.GREY, tempField.getSlot(new Coordinate(1, 2)).getColorState(), BAD_RETURN_SLOT)
        );
    }

    /**
     * Test per il metodo length.
     * Viene testato il caso in cui la lunghezza del campo viene restituita correttamente.
     */
    @Test
    void testLength() {
        assertEquals(Field.DEFAULT_DIM, tempField.length(), BAD_LENGTH);
    }

}
