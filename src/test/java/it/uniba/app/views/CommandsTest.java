package it.uniba.app.views;

import it.uniba.app.model.Coordinate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class CommandsTest {

    static Vector<Coordinate> CoordsToLoco;

    static Coordinate tempCord;
    static Coordinate tempOutCord;
    static int pos;
    static int outPos;

    final String NOT_IN_CTL = "L'oggetto non si trova in CoordsToLoco";

    final String IN_CTL = "L'oggetto si trova in CoordsToLoco";

    //final String IN_CTL_ATPOS = "L'oggetto si trova nella posizione 0 di CoordsToLoco";

    final String NOT_IN_CTL_ATPOS = "L'oggetto non si trova nella posizione 0 di CoordsToLoco";

    final String INVALID_POS = "Posizione di CoordsToLoco non accessibile";

    final String SIZE_ERR = "Dimensione di CoordsToLoco errata";

    @BeforeAll
    static void setUpCoordsToLoco() {
        CoordsToLoco = new Vector<>();
        tempCord = new Coordinate(4,2);
        tempOutCord = new Coordinate(9,5);
        pos = 0;
        outPos = 7;
        CoordsToLoco.add(tempCord);
    }

    @Test
    void isInCoordsToLockTest(){
        assertTrue(Commands.isInCoordsToLock(tempCord), NOT_IN_CTL);
    }

    @Test
    void isNotInCoordsToLockTest(){
        assertTrue(Commands.isInCoordsToLock(tempCord), IN_CTL);
    }

    @Test
    void getCoordToLockTest() {
        assertEquals(tempCord, Commands.getCoordToLock(pos), NOT_IN_CTL_ATPOS);
    }

    @Test
    void getOutOfCoordToLockTest() {
        try {
            Commands.getCoordToLock(outPos);
            fail("Eccezione non raggiunta");
        } catch (Exception e) {
            System.out.println(INVALID_POS);
        }
    }

    @Test
    void getCoordsToLockSizeTest() {
        assertEquals(1, CoordsToLoco.size(), SIZE_ERR);
    }

    @Test
    void notAnAtaxxCommandTest() {

    }

    @Test
    void ataxxHelpCommandTest() {

    }

    @Test
    void ataxxGiocaCommandTest() {

    }

    @Test
    void ataxxVuotoCommandTest() {

    }

    @Test
    void ataxxTavoliereCommandTest() {

    }

    @Test
    void ataxxQualimosseCommandTest() {

    }

    @Test
    void ataxxMosseCommandTest() {

    }

    @Test
    void ataxxAbbandonaCommandTest() {

    }

    @Test
    void ataxxTempoCommandTest() {

    }

    @Test
    void ataxxEsciCommandTest() {

    }

    @Test
    void ataxxNoGameCommandTest() {

    }

    @Test
    void ataxxBloccaCommandTest() {

    }

}
