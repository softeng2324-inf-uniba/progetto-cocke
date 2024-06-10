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
        assertEquals(tempCord, CoordsToLoco.get(pos), NOT_IN_CTL_ATPOS);
    }

    @Test
    void isNotInCoordsToLockTest(){
        assertNotEquals(tempOutCord, CoordsToLoco.get(pos), IN_CTL);
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

    String str;

    final String UNEXPECTED_MSG = "L'output è differente da quello previsto.";
    final String FILE_ERR = "Il file help non è stato letto correttamente.";
    final String FIELD_ERR = "Il campo stampato è differente da quello previsto.";

    boolean fieldPrintCheck() {
        boolean f1 = byteOut.toString().contains("╬");
        boolean f2 = byteOut.toString().contains(" ");
        boolean f3 = byteOut.toString().contains("║");
        boolean f4 = byteOut.toString().contains("⛂");
        boolean f5 = byteOut.toString().contains("╔");
        boolean f6 = byteOut.toString().contains("╦");
        boolean f7 = byteOut.toString().contains("╗");
        boolean f8 = byteOut.toString().contains("╗");
        boolean f9 = byteOut.toString().contains("╚");
        boolean f10 = byteOut.toString().contains("╝");
        boolean f11 = byteOut.toString().contains("╚");
        boolean f12 = byteOut.toString().contains("═");
        if (f1 && f2 && f3 && f4 && f5 && f6 && f7 && f8 && f9 && f10 && f11 && f12) {
            return true;
        } else {
            return false;
        }
    }

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
        String helpToString = Files.readString(Path.of("src/main/java/it/uniba/app/help.txt"));
        assertTrue(byteOut.toString().contains(helpToString), FILE_ERR);
    }

    @Test
    void ataxxGiocaCommandTest() {
        str = "/gioca";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        assertTrue(fieldPrintCheck(), FIELD_ERR);
    }

    @Test
    void ataxxVuotoCommandTest() {
        str = "/vuoto";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        assertTrue(fieldPrintCheck(), FIELD_ERR);
    }

    @Test
    void ataxxTavoliereNoGameCommandTest() {
        str = "/tavoliere";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        assertTrue(byteOut.toString().contains(Message.NO_GAME.getMessageText()), UNEXPECTED_MSG);
    }

    @Test
    void ataxxQualimosseNoGameCommandTest() {
        str = "/qualimosse";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        assertTrue(byteOut.toString().contains(Message.NO_GAME.getMessageText()), UNEXPECTED_MSG);
    }

    @Test
    void ataxxMosseCommandTest() {
        str = "/mosse";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        assertTrue(byteOut.toString().contains(Message.NO_GAME.getMessageText()), UNEXPECTED_MSG);
    }

    @Test
    void ataxxAbbandonaCommandTest() {
        str = "/abbandona";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        assertTrue(byteOut.toString().contains(Message.NO_GAME.getMessageText()), UNEXPECTED_MSG);
    }

    @Test
    void ataxxTempoCommandTest() {
        str = "/tempo";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        assertTrue(byteOut.toString().contains(Message.NO_GAME.getMessageText()), UNEXPECTED_MSG);
    }

    @Test
    void ataxxEsciCommandTest() {
        str = "/esci";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        boolean a = byteOut.toString().contains(Message.CONFIRM_EXIT.getMessageText());
        boolean b = byteOut.toString().contains(Message.BAD_CONFIRMATION_EXIT.getMessageText());
        assertTrue(a && b, UNEXPECTED_MSG);
    }

    @Test
    void ataxxBloccaCommandTest() {
        str = "/blocca d3";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        assertTrue(fieldPrintCheck(), FIELD_ERR);
    }

    @Test
    void ataxxBloccaNonLockableCommandTest() {
        str = "/blocca a1";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        assertTrue(byteOut.toString().contains(Message.CANTDO.getMessageText()), UNEXPECTED_MSG);
    }

    @Test
    void ataxxBloccaOutCommandTest() {
        str = "/blocca m9";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        assertTrue(byteOut.toString().contains(Message.CANTDO.getMessageText()), UNEXPECTED_MSG);
    }

    @Test
    void ataxxBloccaInvalidCommandTest() {
        str = "/blocca ff";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        assertTrue(byteOut.toString().contains(Message.COORD_ERR.getMessageText()), UNEXPECTED_MSG);
    }

    @Test
    void ataxxMoveNoGameTest() {
        str = "a1-a2";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        assertTrue(byteOut.toString().contains(Message.NO_GAME.getMessageText()), UNEXPECTED_MSG);
    }

}
