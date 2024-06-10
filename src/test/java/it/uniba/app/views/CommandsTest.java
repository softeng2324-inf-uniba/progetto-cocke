package it.uniba.app.views;

import it.uniba.app.model.Coordinate;

import it.uniba.app.model.Field;
import it.uniba.app.utils.Message;
import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Vector;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.*;

public class CommandsTest {

    /**
     * Variabile di tipo vettore di coordinate per i casi di test.
     */
    static Vector<Coordinate> CoordsToLoco;

    /**
     *
     */
    static Coordinate tempCord;

    /**
     *
     */
    static Coordinate tempOutCord;

    /**
     *
     */
    static int pos;

    /**
     *
     */
    static int outPos;

    /**
     *
     */
    final String IN_CTL = "L'oggetto si trova in CoordsToLoco";

    /**
     *
     */
    final String NOT_IN_CTL_ATPOS = "L'oggetto non si trova nella posizione 0 di CoordsToLoco";

    /**
     *
     */
    final String INVALID_POS = "Posizione di CoordsToLoco non accessibile";

    /**
     *
     */
    final String SIZE_ERR = "Dimensione di CoordsToLoco errata";

    /**
     *
     */
    @BeforeAll
    static void setUpCoordsToLoco() {
        CoordsToLoco = new Vector<>();
        tempCord = new Coordinate(3,2);
        tempOutCord = new Coordinate(9,6);
        pos = 0;
        outPos = 7;
        CoordsToLoco.add(tempCord);
    }

    /**
     *
     */
    @Test
    void isInCoordsToLockTest(){
        assertEquals(tempCord, CoordsToLoco.get(pos), NOT_IN_CTL_ATPOS);
    }

    /**
     *
     */
    @Test
    void isNotInCoordsToLockTest(){
        assertNotEquals(tempOutCord, CoordsToLoco.get(pos), IN_CTL);
    }

    /**
     *
     */
    @Test
    void getOutOfCoordToLockTest() {
        try {
            Commands.getCoordToLock(outPos);
            fail("Eccezione non raggiunta");
        } catch (Exception e) {
            System.out.println(INVALID_POS);
        }
    }

    /**
     *
     */
    @Test
    void getCoordsToLockSizeTest() {
        assertEquals(1, CoordsToLoco.size(), SIZE_ERR);
    }

    /**
     *
     */
    final String[] args = new String[]{"-i", "CONSOLE"};

    /**
     *
     */
    ByteArrayOutputStream byteOut;

    /**
     *
     */
    PrintStream defaultPS;

    /**
     *
     */
    String str;

    /**
     *
     */
    final String UNEXPECTED_MSG = "L'output è differente da quello previsto.";

    /**
     *
     */
    final String FILE_ERR = "Il file help non è stato letto correttamente.";

    /**
     *
     */
    final String FIELD_ERR = "Il campo stampato è differente da quello previsto.";

    /**
     *
     * @return
     */
    boolean fieldPrintCheck() {
        boolean check = byteOut.toString().contains(" ");
        char a = 'A';
        char uno = '1';
        for (int i = 0; i < Field.DEFAULT_DIM - 1; i++) {
            check = check && byteOut.toString().contains("" + a);
            check = check && byteOut.toString().contains("" + uno);
            a++;
            uno++;
            if (check == false) return check;
        }
        return check;
    }

    /**
     *
     */
    @BeforeEach
    void setUpAtaxxCommandsTest() {
        byteOut = new ByteArrayOutputStream();
        PrintStream myPS = new PrintStream(byteOut);
        defaultPS = System.out;
        System.setOut(myPS);
    }

    /**
     *
     */
    @AfterEach
    void afterAtaxxCommandsTest() {
        assertTrue(byteOut.toString().contains(Message.INSERT_COMMAND.getMessageText()), UNEXPECTED_MSG);
        System.out.flush();
        System.setOut(defaultPS);
    }

    /**
     *
     */
    @Test
    void notAnAtaxxCommandTest() {
        str = "not_a_command\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        assertTrue(byteOut.toString().contains(Message.UNKNOWN_COMMAND.getMessageText()), UNEXPECTED_MSG);
    }

    /**
     *
     * @throws IOException
     */
    @Test
    void ataxxHelpCommandTest() throws IOException {
        str = "/help\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        String helpToString = Files.readString(Path.of("./src/main/java/it/uniba/app/help.txt"));
        assertTrue(byteOut.toString().contains(helpToString), FILE_ERR);
    }

    /**
     *
     */
    @Test
    void ataxxGiocaCommandTest() {
        str = "/gioca\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        assertTrue(fieldPrintCheck(), FIELD_ERR);
    }

    /**
     *
     */
    @Test
    void ataxxVuotoCommandTest() {
        str = "/vuoto\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        assertTrue(fieldPrintCheck(), FIELD_ERR);
    }

    /**
     *
     */
    @Test
    void ataxxTavoliereCommandTest() {
        str = "/gioca\n/tavoliere\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        assertTrue(fieldPrintCheck(), FIELD_ERR);
    }

    /**
     *
     */
    @Test
    void ataxxTavoliereNoGameCommandTest() {
        str = "/tavoliere\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        assertTrue(byteOut.toString().contains(Message.NO_GAME.getMessageText()), UNEXPECTED_MSG);
    }

    /**
     *
     */
    @Test
    void ataxxQualimosseCommandTest() {
        str = "/gioca\n/qualimosse\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        assertTrue(fieldPrintCheck(), FIELD_ERR);
    }

    /**
     *
     */
    @Test
    void ataxxQualimosseNoGameCommandTest() {
        str = "/qualimosse\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        assertTrue(byteOut.toString().contains(Message.NO_GAME.getMessageText()), UNEXPECTED_MSG);
    }

    /**
     *
     */
    @Test
    void ataxxMosseCommandTest() {
        str = "/gioca\n/mosse\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        assertTrue(fieldPrintCheck(), FIELD_ERR);
    }

    /**
     *
     */
    @Test
    void ataxxMosseNoGameCommandTest() {
        str = "/mosse\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        assertTrue(byteOut.toString().contains(Message.NO_GAME.getMessageText()), UNEXPECTED_MSG);
    }

    /**
     *
     */
    @Test
    void ataxxAbbandonaCommandTest() {
        str = "/gioca\n/abbandona\ns\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        boolean a = byteOut.toString().contains(Message.CONFIRM_ABANDONMENT.getMessageText());
        boolean b = byteOut.toString().contains(Message.WINNER_PLAYER.getMessageText());
        assertTrue(a && b, UNEXPECTED_MSG);
    }

    /**
     *
     */
    @Test
    void ataxxAbbandonaNoGameCommandTest() {
        str = "/abbandona\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        assertTrue(byteOut.toString().contains(Message.NO_GAME.getMessageText()), UNEXPECTED_MSG);
    }

    /**
     *
     */
    @Test
    void ataxxTempoCommandTest() {
        str = "/gioca\n/tempo\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        assertTrue(byteOut.toString().contains(Message.ELAPSED_TIME.getMessageText()), UNEXPECTED_MSG);
    }

    /**
     *
     */
    @Test
    void ataxxTempoNoGameCommandTest() {
        str = "/tempo\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        assertTrue(byteOut.toString().contains(Message.NO_GAME.getMessageText()), UNEXPECTED_MSG);
    }

    /**
     *
     */
    @Test
    void ataxxEsciCommandTest() {
        str = "/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        boolean a = byteOut.toString().contains(Message.CONFIRM_EXIT.getMessageText());
        boolean b = byteOut.toString().contains(Message.BAD_CONFIRMATION_EXIT.getMessageText());
        assertTrue(a && !b, UNEXPECTED_MSG);
    }

    /**
     *
     */
    @Test
    void ataxxBloccaCommandTest() {
        str = "/blocca d3\n/gioca\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        assertTrue(fieldPrintCheck(), FIELD_ERR);
    }

    /**
     *
     */
    @Test
    void ataxxBloccaNonLockableCommandTest() {
        str = "/blocca a1\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        assertTrue(byteOut.toString().contains(Message.CANTDO.getMessageText()), UNEXPECTED_MSG);
    }

    /**
     *
     */
    @Test
    void ataxxBloccaOutCommandTest() {
        str = "/blocca m9\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        assertTrue(byteOut.toString().contains(Message.COORD_ERR.getMessageText()), UNEXPECTED_MSG);
    }

    /**
     *
     */
    @Test
    void ataxxBloccaInvalidCommandTest() {
        str = "/gioca\n/blocca a1\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        assertTrue(byteOut.toString().contains(Message.GAME_IS_PLAYING.getMessageText()), UNEXPECTED_MSG);
    }

    /**
     *
     */
    @Test
    void ataxxMoveTest() {
        str = "/gioca\na1-a6\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        assertTrue(byteOut.toString().contains(Message.ILLEGAL_MOVE.getMessageText()), UNEXPECTED_MSG);
    }

    /**
     *
     */
    @Test
    void ataxxMoveNoGameTest() {
        str = "a1-a2\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        assertTrue(byteOut.toString().contains(Message.NO_GAME.getMessageText()), UNEXPECTED_MSG);
    }

}
