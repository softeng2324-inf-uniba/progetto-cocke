package it.uniba.app.views;

import it.uniba.app.model.Coordinate;

import it.uniba.app.utils.Message;
import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Vector;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.*;

public class CommandsTest {

    static Vector<Coordinate> CoordsToLoco;

    static Coordinate tempCord;
    static Coordinate tempOutCord;
    static int pos;
    static int outPos;

    final String IN_CTL = "L'oggetto si trova in CoordsToLoco";

    final String NOT_IN_CTL_ATPOS = "L'oggetto non si trova nella posizione 0 di CoordsToLoco";

    final String INVALID_POS = "Posizione di CoordsToLoco non accessibile";

    final String SIZE_ERR = "Dimensione di CoordsToLoco errata";

    @BeforeAll
    static void setUpCoordsToLoco() {
        CoordsToLoco = new Vector<>();
        tempCord = new Coordinate(3,2);
        tempOutCord = new Coordinate(9,6);
        pos = 0;
        outPos = 7;
        CoordsToLoco.add(tempCord);
    }

    @Test
    void isInCoordsToLockTest(){
        assertAll(
                () -> assertEquals(tempCord.getRow(), CoordsToLoco.get(pos).getRow(), NOT_IN_CTL_ATPOS),
                () -> assertEquals(tempCord.getColumn(), CoordsToLoco.get(pos).getColumn(), NOT_IN_CTL_ATPOS)
        );
    }

    @Test
    void isNotInCoordsToLockTest(){
        assertAll(
                () -> assertNotEquals(tempOutCord.getRow(), CoordsToLoco.get(pos).getRow(), IN_CTL),
                () -> assertNotEquals(tempOutCord.getColumn(), CoordsToLoco.get(pos).getColumn(), IN_CTL)
        );
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

    String[] args = new String[2];

    ByteArrayOutputStream byteOut;
    PrintStream defaultPS;

    static String str;

    final String UNEXPECTED_MSG = "L'output è differente da quello previsto.";
    final String FILE_ERR = "Il file help non è stato letto correttamente.";
    final String FIELD_ERR = "Il campo stampato è differente da quello previsto.";

    @BeforeEach
    void setUp() {
        args = new String[] { "-i", "CONSOLE" };
        byteOut = new ByteArrayOutputStream();
        PrintStream myPS = new PrintStream(byteOut);
        defaultPS = System.out;
        System.setOut(myPS);
    }

    @AfterEach
    void systemOutToDefault() {
        System.out.flush();
        System.setOut(defaultPS);
    }

    @Test
    void notAnAtaxxCommandTest() {
        str = "not_a_command";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        assertTrue(byteOut.toString().contains(Message.UNKNOWN_COMMAND.getMessageText()), UNEXPECTED_MSG);
    }

    @Test
    void ataxxHelpCommandTest() throws IOException {
        str = "/help";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        assertTrue(byteOut.toString().contains(Files.readString(Path.of("src/main/java/it/uniba/app/help.txt"))), FILE_ERR);
    }

    @Test
    void ataxxGiocaCommandTest() {
        str = "/gioca";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);

    }

    @Test
    void ataxxVuotoCommandTest() {
        str = "/vuoto";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);

    }

    @Test
    void ataxxTavoliereCommandTest() {
        str = "/tavoliere";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);

    }

    @Test
    void ataxxQualimosseCommandTest() {
        str = "/qualimosse";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
    }

    @Test
    void ataxxMosseCommandTest() {
        str = "/mosse";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);

    }

    @Test
    void ataxxAbbandonaCommandTest() {
        str = "/abbandona";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
    }

    @Test
    void ataxxTempoCommandTest() {
        str = "/tempo";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
    }

    @Test
    void ataxxEsciCommandTest() {
        str = "/esci";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
    }

    @Test
    void ataxxNoGameCommandTest() {

    }

    @Test
    void ataxxBloccaCommandTest() {
        str = "/blocca d3";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
    }

    @Test
    void ataxxBloccaNonLockableCommandTest() {
        str = "/blocca a1";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
    }

    @Test
    void ataxxBloccaOutCommandTest() {
        str = "/blocca m9";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
    }

    @Test
    void ataxxBloccaInvalidCommandTest() {
        str = "/blocca ff";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
    }

}
