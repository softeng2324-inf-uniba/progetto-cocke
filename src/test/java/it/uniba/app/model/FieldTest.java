package it.uniba.app.model;

import it.uniba.app.utils.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FieldTest {

    Field tempField;
    Slot tempSlot;

    @BeforeEach
    void setUp() {
        tempField = new Field();
        tempSlot = new Slot(Color.GOLD);
        tempField.setSlot(new Coordinate(5, 5), tempSlot);
    }

    @Test
    void testCopyConstructor() {
        Field copy = new Field(tempField);
        assertAll("Costruttore di copia",
                () -> assertEquals(Color.GOLD, copy.getSlot(new Coordinate(5, 5)).getColorState()),
                () -> assertEquals(Color.GREY, copy.getSlot(new Coordinate(4, 6)).getColorState())
        );
    }

    @Test
    void testLengthConstructor() {
        Field copy = new Field(15);
        assertAll("Costruttore con lunghezza campo",
                () -> assertEquals(Color.GREY, copy.getSlot(new Coordinate(14, 14)).getColorState()),
                () -> assertEquals(15, copy.length())
        );
    }

    @Test
    void testSetSlot() {
        tempField.setSlot(new Coordinate(3, 3), tempSlot);
        assertEquals(tempSlot.getColorState(), tempField.getSlot(new Coordinate(3, 3)).getColorState(), "Lo slot non è stato modificato correttamente");
    }

    @Test
    void testGetSlot() {
        assertAll("Getter di uno slot del campo",
                () -> assertEquals(Color.GOLD, tempField.getSlot(new Coordinate(5, 5)).getColorState(), "Lo slot restituito non coincide con quello atteso"),
                () -> assertEquals(Color.GREY, tempField.getSlot(new Coordinate(1, 2)).getColorState(), "Lo slot restituito non coincide con quello atteso")
        );
    }

    @Test
    void testLength() {
        assertAll("Lunghezza di un campo",
                () -> assertEquals(Field.DEFAULT_DIM, tempField.length(), "La dimensione restituita non coincide con quella attesa"),
                () -> assertEquals(13, new Field(13).length(), "La dimensione restituita non coincide con quella attesa")
        );
    }

}
