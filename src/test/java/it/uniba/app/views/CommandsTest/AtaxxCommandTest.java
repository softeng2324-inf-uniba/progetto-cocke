package it.uniba.app.views.CommandsTest;

import it.uniba.app.model.Field;
import it.uniba.app.utils.Message;
import it.uniba.app.views.Commands;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.PrintStream;
import java.io.InputStream;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.charset.StandardCharsets.UTF_8;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe di test per il metodo ataxxCommand di Commands.
 */
public class AtaxxCommandTest {

    /**
     * Argomenti di input per il test di ataxxCommand.
     */
    private final String[] args = new String[]{"-i", "CONSOLE"};

    /**
     * Variabile buffer che contiene le stampe prodotte dai test di ataxxCommand.
     */
    private ByteArrayOutputStream byteOut;

    /**
     *  Variabile utile per impostare il PrintStream allo stato di default.
     */
    private PrintStream defaultPS = System.out;

    /**
     * Stringa contenente il comando da dare in input al test di ataxxCommand.
     */
    private String str = "";

    /**
     * Messaggio di errore relativo ai test di ataxxCommand.
     */
    static final String UNEXPECTED_MSG = "L'output è differente da quello previsto.";

    /**
     * Messaggio di errore relativo ai test di ataxxCommand.
     */
    static final String FILE_ERR = "Il file help non è stato letto correttamente.";

    /**
     * Messaggio di errore relativo ai test di ataxxCommand.
     */
    static final String FIELD_ERR = "Il campo stampato è differente da quello previsto.";

    /**
     * Imposta il flusso di stampa a video nel buffer byteOut, in modo da poter eseguire i test su ataxxCommand.
     */
    @BeforeEach
    void setUpAtaxxCommandTest() {
        byteOut = new ByteArrayOutputStream();
        PrintStream myPS = new PrintStream(byteOut);
        System.setOut(myPS);
    }

    /**
     * Reimposta il flusso di stampa a video.
     */
    @AfterEach
    void afterAtaxxCommandTest() {
        System.out.flush();
        System.setOut(defaultPS);
    }

    /**
     * Test del metodo ataxxCommand quando viene inserito un comando non valido.
     * Modifica l'input stream, in modo che risulti letta da tastiera la stringa str.
     * Verifica che venga stampato a video il messaggio dell'inserimento di un comando e per un comando sconosciuto.
     */
    @Test
    void testNotAnAtaxxCommand() {
        str = "not_a_command\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        boolean inC = byteOut.toString().contains(Message.INSERT_COMMAND.getMessageText());
        assertTrue(inC && byteOut.toString().contains(Message.UNKNOWN_COMMAND.getMessageText()), UNEXPECTED_MSG);
    }

    /**
     * Test del metodo ataxxCommand per il comando /help.
     * Modifica l'input stream, in modo che risulti letta da tastiera la stringa str.
     * Verifica che venga visualizzato a video il messaggio relativo all'inserimento di un comando.
     * Verifica che venga visualizzato a video il contenuto del file help.txt.
     * @throws IOException se il percorso del file non viene trovato.
     */
    @Test
    void testAtaxxHelpCommand() throws IOException {
        str = "/help\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        String helpToString = Files.readString(Path.of("./src/main/java/it/uniba/app/help.txt"));
        boolean inC = byteOut.toString().contains(Message.INSERT_COMMAND.getMessageText());
        assertTrue(inC && byteOut.toString().contains(helpToString), FILE_ERR);
    }

    /**
     * Test del metodo ataxxCommand per il comando /gioca.
     * Modifica l'input stream, in modo che risulti letta da tastiera la stringa str.
     * Verifica che venga visualizzato a video il messaggio relativo all'inserimento di un comando.
     * Verifica che venga stampato a video il campo di gioco.
     */
    @Test
    void testAtaxxGiocaCommand() {
        str = "/gioca\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        boolean inC = byteOut.toString().contains(Message.INSERT_COMMAND.getMessageText());
        boolean check = byteOut.toString().contains(" ");
        char a = 'A';
        char uno = '1';
        for (int i = 0; i < Field.DEFAULT_DIM - 1; i++) {
            check = check && byteOut.toString().contains("" + a);
            check = check && byteOut.toString().contains("" + uno);
            a++;
            uno++;
        }
        assertTrue(inC && check, FIELD_ERR);
    }

    /**
     * Test del metodo ataxxCommand per il comando /vuoto.
     * Modifica l'input stream, in modo che risulti letta da tastiera la stringa str.
     * Verifica che venga visualizzato a video il messaggio relativo all'inserimento di un comando.
     * Verifica che venga stampato a video il campo di gioco.
     */
    @Test
    void testAtaxxVuotoCommand() {
        str = "/vuoto\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        boolean inC = byteOut.toString().contains(Message.INSERT_COMMAND.getMessageText());
        boolean check = byteOut.toString().contains(" ");
        char a = 'A';
        char uno = '1';
        for (int i = 0; i < Field.DEFAULT_DIM - 1; i++) {
            check = check && byteOut.toString().contains("" + a);
            check = check && byteOut.toString().contains("" + uno);
            a++;
            uno++;
        }
        assertTrue(inC && check, FIELD_ERR);
    }

    /**
     * Test del metodo ataxxCommand per il comando /tavoliere.
     * Modifica l'input stream, in modo che risulti letta da tastiera la stringa str.
     * Verifica che venga visualizzato a video il messaggio relativo all'inserimento di un comando.
     * Verifica che venga stampato a video il campo di gioco.
     */
    @Test
    void testAtaxxTavoliereCommand() {
        str = "/gioca\n/tavoliere\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        boolean inC = byteOut.toString().contains(Message.INSERT_COMMAND.getMessageText());
        boolean check = byteOut.toString().contains(" ");
        char a = 'A';
        char uno = '1';
        for (int i = 0; i < Field.DEFAULT_DIM - 1; i++) {
            check = check && byteOut.toString().contains("" + a);
            check = check && byteOut.toString().contains("" + uno);
            a++;
            uno++;
        }
        assertTrue(inC && check, FIELD_ERR);
    }

    /**
     * Test del metodo ataxxCommand per il comando /tavoliere quando non è in corso alcuna partita.
     * Modifica l'input stream, in modo che risulti letta da tastiera la stringa str.
     * Verifica che venga visualizzato a video il messaggio relativo all'inserimento di un comando.
     * Verifica che venga stampato a video il messaggio per una partita non cominciata.
     */
    @Test
    void testAtaxxTavoliereNoGameCommand() {
        str = "/tavoliere\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        boolean inC = byteOut.toString().contains(Message.INSERT_COMMAND.getMessageText());
        assertTrue(inC && byteOut.toString().contains(Message.NO_GAME.getMessageText()), UNEXPECTED_MSG);
    }

    /**
     * Test del metodo ataxxCommand per il comando /qualimosse.
     * Modifica l'input stream, in modo che risulti letta da tastiera la stringa str.
     * Verifica che venga visualizzato a video il messaggio relativo all'inserimento di un comando.
     * Verifica che venga stampato a video il campo di gioco.
     */
    @Test
    void testAtaxxQualimosseCommand() {
        str = "/gioca\n/qualimosse\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        boolean inC = byteOut.toString().contains(Message.INSERT_COMMAND.getMessageText());
        boolean check = byteOut.toString().contains(" ");
        char a = 'A';
        char uno = '1';
        for (int i = 0; i < Field.DEFAULT_DIM - 1; i++) {
            check = check && byteOut.toString().contains("" + a);
            check = check && byteOut.toString().contains("" + uno);
            a++;
            uno++;
        }
        assertTrue(inC && check, FIELD_ERR);
    }

    /**
     * Test del metodo ataxxCommand per il comando /qualimosse quando non è in corso alcuna partita.
     * Modifica l'input stream, in modo che risulti letta da tastiera la stringa str.
     * Verifica che venga visualizzato a video il messaggio relativo all'inserimento di un comando.
     * Verifica che venga stampato a video il messaggio per una partita non cominciata.
     */
    @Test
    void testAtaxxQualimosseNoGameCommand() {
        str = "/qualimosse\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        boolean inC = byteOut.toString().contains(Message.INSERT_COMMAND.getMessageText());
        assertTrue(inC && byteOut.toString().contains(Message.NO_GAME.getMessageText()), UNEXPECTED_MSG);
    }

    /**
     * Test del metodo ataxxCommand per il comando /mosse.
     * Modifica l'input stream, in modo che risulti letta da tastiera la stringa str.
     * Verifica che venga visualizzato a video il messaggio relativo all'inserimento di un comando.
     * Verifica che venga stampato lo storico delle mosse.
     */
    @Test
    void testAtaxxMosseCommand() {
        str = "/gioca\na1-a2\n/mosse\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        boolean inC = byteOut.toString().contains(Message.INSERT_COMMAND.getMessageText());
        assertTrue(inC && byteOut.toString().contains(Message.MOVE_LIST.getMessageText()), UNEXPECTED_MSG);
    }

    /**
     * Test del metodo ataxxCommand per il comando /mosse quando non sono state effettuate mosse.
     * Modifica l'input stream, in modo che risulti letta da tastiera la stringa str.
     * Verifica che venga visualizzato a video il messaggio relativo all'inserimento di un comando.
     * Verifica che venga stampato il corretto messaggio di errore.
     */
    @Test
    void testAtaxxEmptyMosseCommand() {
        str = "/gioca\n/mosse\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        boolean inC = byteOut.toString().contains(Message.INSERT_COMMAND.getMessageText());
        assertTrue(inC && byteOut.toString().contains(Message.NO_MOVES.getMessageText()), UNEXPECTED_MSG);
    }

    /**
     * Test del metodo ataxxCommand per il comando /mosse quando non ci sono partite in corso.
     * Modifica l'input stream, in modo che risulti letta da tastiera la stringa str.
     * Verifica che venga visualizzato a video il messaggio relativo all'inserimento di un comando.
     * Verifica che venga stampato il corretto messaggio di errore.
     */
    @Test
    void testAtaxxMosseNoGameCommand() {
        str = "/mosse\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        boolean inC = byteOut.toString().contains(Message.INSERT_COMMAND.getMessageText());
        assertTrue(inC && byteOut.toString().contains(Message.NO_GAME.getMessageText()), UNEXPECTED_MSG);
    }

    /**
     * Test del metodo ataxxCommand per il comando /abbandona.
     * Modifica l'input stream, in modo che risulti letta da tastiera la stringa str.
     * Verifica che venga visualizzato a video il messaggio relativo all'inserimento di un comando.
     * Verifica che vengano stampati i messaggi relativi al termine della partita.
     */
    @Test
    void testAtaxxAbbandonaCommand() {
        str = "/gioca\n/abbandona\ns\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        boolean a = byteOut.toString().contains(Message.CONFIRM_ABANDONMENT.getMessageText());
        boolean b = byteOut.toString().contains(Message.WINNER_PLAYER.getMessageText());
        boolean inC = byteOut.toString().contains(Message.INSERT_COMMAND.getMessageText());
        assertTrue(inC && a && b, UNEXPECTED_MSG);
    }

    /**
     * Test del metodo ataxxCommand per il comando /abbandona quando non ci sono partite in corso.
     * Modifica l'input stream, in modo che risulti letta da tastiera la stringa str.
     * Verifica che venga visualizzato a video il messaggio relativo all'inserimento di un comando.
     * Verifica che venga stampato il corretto messaggio di errore.
     */
    @Test
    void testAtaxxAbbandonaNoGameCommand() {
        str = "/abbandona\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        boolean inC = byteOut.toString().contains(Message.INSERT_COMMAND.getMessageText());
        assertTrue(inC && byteOut.toString().contains(Message.NO_GAME.getMessageText()), UNEXPECTED_MSG);
    }

    /**
     * Test del metodo ataxxCommand per il comando /tempo.
     * Modifica l'input stream, in modo che risulti letta da tastiera la stringa str.
     * Verifica che venga visualizzato a video il messaggio relativo all'inserimento di un comando.
     * Verifica che venga effettuata la stampa del tempo trascorso in partita.
     */
    @Test
    void testAtaxxTempoCommand() {
        str = "/gioca\n/tempo\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        boolean inC = byteOut.toString().contains(Message.INSERT_COMMAND.getMessageText());
        assertTrue(inC && byteOut.toString().contains(Message.ELAPSED_TIME.getMessageText()), UNEXPECTED_MSG);
    }

    /**
     * Test del metodo ataxxCommand per il comando /tempo quando non ci sono partite in corso.
     * Modifica l'input stream, in modo che risulti letta da tastiera la stringa str.
     * Verifica che venga visualizzato a video il messaggio relativo all'inserimento di un comando.
     * Verifica che venga stampato il corretto messaggio di errore.
     */
    @Test
    void testAtaxxTempoNoGameCommand() {
        str = "/tempo\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        boolean inC = byteOut.toString().contains(Message.INSERT_COMMAND.getMessageText());
        assertTrue(inC && byteOut.toString().contains(Message.NO_GAME.getMessageText()), UNEXPECTED_MSG);
    }

    /**
     * Test del metodo ataxxCommand per il comando /esci.
     * Modifica l'input stream, in modo che risulti letta da tastiera la stringa str.
     * Verifica che venga visualizzato a video il messaggio relativo all'inserimento di un comando.
     * Verifica che vengano stampati i messaggi relativi al comando in oggetto.
     */
    @Test
    void testAtaxxEsciCommand() {
        str = "/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        boolean a = byteOut.toString().contains(Message.CONFIRM_EXIT.getMessageText());
        boolean b = byteOut.toString().contains(Message.BAD_CONFIRMATION_EXIT.getMessageText());
        boolean inC = byteOut.toString().contains(Message.INSERT_COMMAND.getMessageText());
        assertTrue(inC && a && !b, UNEXPECTED_MSG);
    }

    /**
     * Test del metodo ataxxCommand per il comando /blocca.
     * Modifica l'input stream, in modo che risulti letta da tastiera la stringa str.
     * Verifica che venga visualizzato a video il messaggio relativo all'inserimento di un comando.
     * Verifica che venga eseguito /gioca dopo aver bloccato una casella e che venga stampato il campo di gioco.
     */
    @Test
    void testAtaxxBloccaCommand() {
        str = "/blocca d3\n/gioca\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        boolean inC = byteOut.toString().contains(Message.INSERT_COMMAND.getMessageText());
        boolean check = byteOut.toString().contains(" ");
        char a = 'A';
        char uno = '1';
        for (int i = 0; i < Field.DEFAULT_DIM - 1; i++) {
            check = check && byteOut.toString().contains("" + a);
            check = check && byteOut.toString().contains("" + uno);
            a++;
            uno++;
        }
        assertTrue(inC && check, FIELD_ERR);
    }

    /**
     * Test del metodo ataxxCommand per il comando /blocca quando viene inserita una coordinata non bloccabile.
     * Modifica l'input stream, in modo che risulti letta da tastiera la stringa str.
     * Verifica che venga visualizzato a video il messaggio relativo all'inserimento di un comando.
     * Verifica che venga visualizzato il corretto messaggio.
     */
    @Test
    void testAtaxxBloccaNonLockableCommand() {
        str = "/blocca a1\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        boolean inC = byteOut.toString().contains(Message.INSERT_COMMAND.getMessageText());
        assertTrue(inC && byteOut.toString().contains(Message.CANTDO.getMessageText()), UNEXPECTED_MSG);
    }

    /**
     * Test del metodo ataxxCommand per il comando /blocca quando viene inserita una coordinata non appartenente al
     * campo di gioco.
     * Modifica l'input stream, in modo che risulti letta da tastiera la stringa str.
     * Verifica che venga visualizzato a video il messaggio relativo all'inserimento di un comando.
     * Verifica che venga stampato il messaggio di errore per le coordinate non ammesse.
     */
    @Test
    void testAtaxxBloccaOutCommand() {
        str = "/blocca m9\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        boolean inC = byteOut.toString().contains(Message.INSERT_COMMAND.getMessageText());
        assertTrue(inC && byteOut.toString().contains(Message.COORD_ERR.getMessageText()), UNEXPECTED_MSG);
    }

    /**
     * Test del metodo ataxxCommand per il comando /blocca quando è in corso una partita.
     * Modifica l'input stream, in modo che risulti letta da tastiera la stringa str.
     * Verifica che venga visualizzato a video il messaggio relativo all'inserimento di un comando.
     * Verifica che venga stampato il corretto messaggio di errore.
     */
    @Test
    void testAtaxxBloccaInvalidCommand() {
        str = "/gioca\n/blocca a1\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        boolean inC = byteOut.toString().contains(Message.INSERT_COMMAND.getMessageText());
        assertTrue(inC && byteOut.toString().contains(Message.GAME_IS_PLAYING.getMessageText()), UNEXPECTED_MSG);
    }

    /**
     * Test del metodo ataxxCommand per il comando /blocca quando vengono bloccate più di 9 caselle.
     * Modifica l'input stream, in modo che risulti letta da tastiera la stringa str.
     * Verifica che venga visualizzato a video il messaggio relativo all'inserimento di un comando.
     * Verifica che venga stampato il corretto messaggio di errore.
     */
    @Test
    void testAtaxxBloccaAllCommand() {
        for (int i = 1; i <= Field.DEFAULT_DIM; i++) {
            str = str + "/blocca d" + i + "\n";
        }
        str = str + "/blocca a4\n/blocca b4\n/blocca c4\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        boolean inC = byteOut.toString().contains(Message.INSERT_COMMAND.getMessageText());
        assertTrue(inC && byteOut.toString().contains(Message.CANTDO.getMessageText()), UNEXPECTED_MSG);
    }

    /**
     * Test del metodo ataxxCommand nel momento in cui viene richiesta una mossa non valida.
     * Modifica l'input stream, in modo che risulti letta da tastiera la stringa str.
     * Verifica che venga visualizzato a video il messaggio relativo all'inserimento di un comando.
     * Verifica che venga stampato a video che la mossa non è realizzabile.
     */
    @Test
    void testAtaxxMoveCommand() {
        str = "/gioca\na1-a6\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        boolean inC = byteOut.toString().contains(Message.INSERT_COMMAND.getMessageText());
        assertTrue(inC && byteOut.toString().contains(Message.ILLEGAL_MOVE.getMessageText()), UNEXPECTED_MSG);
    }

    /**
     * Test del metodo ataxxCommand nel momento in cui viene immessa una mossa senza che sia in corso una partita.
     * Modifica l'input stream, in modo che risulti letta da tastiera la stringa str.
     * Verifica che venga visualizzato a video il messaggio relativo all'inserimento di un comando.
     * Verifica che venga stampato il corretto messaggio di errore.
     */
    @Test
    void testAtaxxMoveNoGameCommand() {
        str = "a1-a2\n/esci\ns\n";
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
        Commands.ataxxCommand(args);
        boolean inC = byteOut.toString().contains(Message.INSERT_COMMAND.getMessageText());
        assertTrue(inC && byteOut.toString().contains(Message.NO_GAME.getMessageText()), UNEXPECTED_MSG);
    }

}
