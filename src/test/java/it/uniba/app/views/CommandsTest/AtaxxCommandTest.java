package it.uniba.app.views.CommandsTest;

import it.uniba.app.model.Field;
import it.uniba.app.utils.Message;
import it.uniba.app.views.Commands;

import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.*;

public class AtaxxCommandTest {

    /**
     * Argomenti di input per il test di ataxxCommand.
     */
    final String[] args = new String[]{"-i", "CONSOLE"};

    /**
     * Variabile buffer che contiene le stampe prodotte dai test di ataxxCommand.
     */
    ByteArrayOutputStream byteOut;

    /**
     *  Variabile utile per impostare il PrintStream allo stato di default.
     */
    PrintStream defaultPS = System.out;

    /**
     * Stringa contenente il comando da dare in input al test di ataxxCommand.
     */
    String str;

    /**
     * Messaggio di errore relativo ai test di ataxxCommand.
     */
    final String UNEXPECTED_MSG = "L'output è differente da quello previsto.";

    /**
     * Messaggio di errore relativo ai test di ataxxCommand.
     */
    final String FILE_ERR = "Il file help non è stato letto correttamente.";

    /**
     * Messaggio di errore relativo ai test di ataxxCommand.
     */
    final String FIELD_ERR = "Il campo stampato è differente da quello previsto.";

    /**
     * Funzione interna che restituisce vero se il buffer contiene gli elementi che compongono la stampa del campo di
     * gioco
     * @return vero o falso a seconda dell' esito dell'operazione.
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
     * Imposta per ogni test di ataxxCommand lo stream di input, in modo che venga simulata la lettura da tastiera
     * della stringa str.
     */
    void setUpInputStream(final String str) {
        InputStream stream = new ByteArrayInputStream(str.getBytes(UTF_8));
        System.setIn(stream);
    }

    /**
     * Imposta il flusso di stampa a video, in modo da poter eseguire i test su ataxxCommand.
     */
    @BeforeEach
    void setUpAtaxxCommandTest() {
        byteOut = new ByteArrayOutputStream();
        PrintStream myPS = new PrintStream(byteOut);
        System.setOut(myPS);
    }

    /**
     * Controlla un' asserzione in comune a tutti i test su ataxxCommand e reimposta il flusso di stampa a video.
     */
    @AfterEach
    void afterAtaxxCommandTest() {
        assertTrue(byteOut.toString().contains(Message.INSERT_COMMAND.getMessageText()), UNEXPECTED_MSG);
        System.out.flush();
        System.setOut(defaultPS);
    }

    /**
     * Test del metodo ataxxCommand quando viene inserito un comando non valido.
     * Verifica che venga stampato a video il messaggio per un comando sconosciuto.
     */
    @Test
    void testNotAnAtaxxCommand() {
        str = "not_a_command\n/esci\ns\n";
        setUpInputStream(str);
        Commands.ataxxCommand(args);
        assertTrue(byteOut.toString().contains(Message.UNKNOWN_COMMAND.getMessageText()), UNEXPECTED_MSG);
    }

    /**
     * Test del metodo ataxxCommand per il comando /help.
     * Verifica che venga visualizzato a video il contenuto del file help.txt.
     * @throws IOException se il percorso del file non viene trovato.
     */
    @Test
    void testAtaxxHelpCommand() throws IOException {
        str = "/help\n/esci\ns\n";
        setUpInputStream(str);
        Commands.ataxxCommand(args);
        String helpToString = Files.readString(Path.of("./src/main/java/it/uniba/app/help.txt"));
        assertTrue(byteOut.toString().contains(helpToString), FILE_ERR);
    }

    /**
     * Test del metodo ataxxCommand per il comando /gioca.
     * Verifica che venga stampato a video il campo di gioco.
     */
    @Test
    void testAtaxxGiocaCommand() {
        str = "/gioca\n/esci\ns\n";
        setUpInputStream(str);
        Commands.ataxxCommand(args);
        assertTrue(fieldPrintCheck(), FIELD_ERR);
    }

    /**
     * Test del metodo ataxxCommand per il comando /vuoto.
     * Verifica che venga stampato a video il campo di gioco.
     */
    @Test
    void testAtaxxVuotoCommand() {
        str = "/vuoto\n/esci\ns\n";
        setUpInputStream(str);
        Commands.ataxxCommand(args);
        assertTrue(fieldPrintCheck(), FIELD_ERR);
    }

    /**
     * Test del metodo ataxxCommand per il comando /tavoliere.
     * Verifica che venga stampato a video il campo di gioco.
     */
    @Test
    void testAtaxxTavoliereCommand() {
        str = "/gioca\n/tavoliere\n/esci\ns\n";
        setUpInputStream(str);
        Commands.ataxxCommand(args);
        assertTrue(fieldPrintCheck(), FIELD_ERR);
    }

    /**
     * Test del metodo ataxxCommand per il comando /tavoliere quando non è in corso alcuna partita.
     * Verifica che venga stampato a video il messaggio per una partita non cominciata.
     */
    @Test
    void testAtaxxTavoliereNoGameCommand() {
        str = "/tavoliere\n/esci\ns\n";
        setUpInputStream(str);
        Commands.ataxxCommand(args);
        assertTrue(byteOut.toString().contains(Message.NO_GAME.getMessageText()), UNEXPECTED_MSG);
    }

    /**
     * Test del metodo ataxxCommand per il comando /qualimosse.
     * Verifica che venga stampato a video il campo di gioco.
     */
    @Test
    void testAtaxxQualimosseCommand() {
        str = "/gioca\n/qualimosse\n/esci\ns\n";
        setUpInputStream(str);
        Commands.ataxxCommand(args);
        assertTrue(fieldPrintCheck(), FIELD_ERR);
    }

    /**
     * Test del metodo ataxxCommand per il comando /qualimosse quando non è in corso alcuna partita.
     * Verifica che venga stampato a video il messaggio per una partita non cominciata.
     */
    @Test
    void testAtaxxQualimosseNoGameCommand() {
        str = "/qualimosse\n/esci\ns\n";
        setUpInputStream(str);
        Commands.ataxxCommand(args);
        assertTrue(byteOut.toString().contains(Message.NO_GAME.getMessageText()), UNEXPECTED_MSG);
    }

    /**
     * Test del metodo ataxxCommand per il comando /mosse.
     * Verifica che venga stampato lo storico delle mosse.
     */
    @Test
    void testAtaxxMosseCommand() {
        str = "/gioca\na1-a2\n/mosse\n/esci\ns\n";
        setUpInputStream(str);
        Commands.ataxxCommand(args);
        assertTrue(byteOut.toString().contains(Message.MOVE_LIST.getMessageText()), UNEXPECTED_MSG);
    }

    /**
     * Test del metodo ataxxCommand per il comando /mosse quando non sono state effettuate mosse.
     * Verifica che venga stampato il corretto messaggio di errore.
     */
    @Test
    void testAtaxxEmptyMosseCommand() {
        str = "/gioca\n/mosse\n/esci\ns\n";
        setUpInputStream(str);
        Commands.ataxxCommand(args);
        assertTrue(byteOut.toString().contains(Message.NO_MOVES.getMessageText()), UNEXPECTED_MSG);
    }

    /**
     * Test del metodo ataxxCommand per il comando /mosse quando non ci sono partite in corso.
     * Verifica che venga stampato il corretto messaggio di errore.
     */
    @Test
    void testAtaxxMosseNoGameCommand() {
        str = "/mosse\n/esci\ns\n";
        setUpInputStream(str);
        Commands.ataxxCommand(args);
        assertTrue(byteOut.toString().contains(Message.NO_GAME.getMessageText()), UNEXPECTED_MSG);
    }

    /**
     * Test del metodo ataxxCommand per il comando /abbandona.
     * Verifica che vengano stampati i messaggi relativi al termine della partita.
     */
    @Test
    void testAtaxxAbbandonaCommand() {
        str = "/gioca\n/abbandona\ns\n/esci\ns\n";
        setUpInputStream(str);
        Commands.ataxxCommand(args);
        boolean a = byteOut.toString().contains(Message.CONFIRM_ABANDONMENT.getMessageText());
        boolean b = byteOut.toString().contains(Message.WINNER_PLAYER.getMessageText());
        assertTrue(a && b, UNEXPECTED_MSG);
    }

    /**
     * Test del metodo ataxxCommand per il comando /abbandona quando non ci sono partite in corso.
     * Verifica che venga stampato il corretto messaggio di errore.
     */
    @Test
    void testAtaxxAbbandonaNoGameCommand() {
        str = "/abbandona\n/esci\ns\n";
        setUpInputStream(str);
        Commands.ataxxCommand(args);
        assertTrue(byteOut.toString().contains(Message.NO_GAME.getMessageText()), UNEXPECTED_MSG);
    }

    /**
     * Test del metodo ataxxCommand per il comando /tempo.
     * Verifica che venga effettuata la stampa del tempo trascorso in partita.
     */
    @Test
    void testAtaxxTempoCommand() {
        str = "/gioca\n/tempo\n/esci\ns\n";
        setUpInputStream(str);
        Commands.ataxxCommand(args);
        assertTrue(byteOut.toString().contains(Message.ELAPSED_TIME.getMessageText()), UNEXPECTED_MSG);
    }

    /**
     * Test del metodo ataxxCommand per il comando /tempo quando non ci sono partite in corso.
     * Verifica che venga stampato il corretto messaggio di errore.
     */
    @Test
    void testAtaxxTempoNoGameCommand() {
        str = "/tempo\n/esci\ns\n";
        setUpInputStream(str);
        Commands.ataxxCommand(args);
        assertTrue(byteOut.toString().contains(Message.NO_GAME.getMessageText()), UNEXPECTED_MSG);
    }

    /**
     * Test del metodo ataxxCommand per il comando /esci.
     * Verifica che vengano stampati i messaggi relativi al comando in oggetto.
     */
    @Test
    void testAtaxxEsciCommand() {
        str = "/esci\ns\n";
        setUpInputStream(str);
        Commands.ataxxCommand(args);
        boolean a = byteOut.toString().contains(Message.CONFIRM_EXIT.getMessageText());
        boolean b = byteOut.toString().contains(Message.BAD_CONFIRMATION_EXIT.getMessageText());
        assertTrue(a && !b, UNEXPECTED_MSG);
    }

    /**
     * Test del metodo ataxxCommand per il comando /blocca.
     * Verifica che venga eseguito /gioca dopo aver bloccato una casella e che venga stampato il campo di gioco.
     */
    @Test
    void testAtaxxBloccaCommand() {
        str = "/blocca d3\n/gioca\n/esci\ns\n";
        setUpInputStream(str);
        Commands.ataxxCommand(args);
        assertTrue(fieldPrintCheck(), FIELD_ERR);
    }

    /**
     * Test del metodo ataxxCommand per il comando /blocca quando viene inserita una coordinata non bloccabile.
     * Verifica che venga visualizzato il corretto messaggio.
     */
    @Test
    void testAtaxxBloccaNonLockableCommand() {
        str = "/blocca a1\n/esci\ns\n";
        setUpInputStream(str);
        Commands.ataxxCommand(args);
        assertTrue(byteOut.toString().contains(Message.CANTDO.getMessageText()), UNEXPECTED_MSG);
    }

    /**
     * Test del metodo ataxxCommand per il comando /blocca quando viene inserita una coordinata non appartenente al
     * campo di gioco.
     * Verifica che venga stampato il messaggio di errore per le coordinate non ammesse.
     */
    @Test
    void testAtaxxBloccaOutCommand() {
        str = "/blocca m9\n/esci\ns\n";
        setUpInputStream(str);
        Commands.ataxxCommand(args);
        assertTrue(byteOut.toString().contains(Message.COORD_ERR.getMessageText()), UNEXPECTED_MSG);
    }

    /**
     * Test del metodo ataxxCommand per il comando /blocca quando è in corso una partita.
     * Verifica che venga stampato il corretto messaggio di errore.
     */
    @Test
    void testAtaxxBloccaInvalidCommand() {
        str = "/gioca\n/blocca a1\n/esci\ns\n";
        setUpInputStream(str);
        Commands.ataxxCommand(args);
        assertTrue(byteOut.toString().contains(Message.GAME_IS_PLAYING.getMessageText()), UNEXPECTED_MSG);
    }

    /**
     * Test del metodo ataxxCommand nel momento in cui viene richiesta una mossa non valida.
     * Verifica che venga stampato a video che la mossa non è realizzabile.
     */
    @Test
    void testAtaxxMoveCommand() {
        str = "/gioca\na1-a6\n/esci\ns\n";
        setUpInputStream(str);
        Commands.ataxxCommand(args);
        assertTrue(byteOut.toString().contains(Message.ILLEGAL_MOVE.getMessageText()), UNEXPECTED_MSG);
    }

    /**
     * Test del metodo ataxxCommand nel momento in cui viene immessa una mossa senza che sia in corso una partita.
     * Verifica che venga stampato il corretto messaggio di errore.
     */
    @Test
    void testAtaxxMoveNoGameCommand() {
        str = "a1-a2\n/esci\ns\n";
        setUpInputStream(str);
        Commands.ataxxCommand(args);
        assertTrue(byteOut.toString().contains(Message.NO_GAME.getMessageText()), UNEXPECTED_MSG);
    }

}
