package it.uniba.app.views;

import it.uniba.app.model.Field;
import it.uniba.app.model.Coordinate;
import it.uniba.app.utils.Message;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Vector;

import static java.nio.charset.StandardCharsets.UTF_8;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

public class CommandsTest {

    /**
     * Classe di test per il metodo ataxxCommand di Commands.
     */
    @Nested
    class AtaxxCommandTest {

        /**
         * Argomenti di input per il test di ataxxCommand.
         */
        private String[] args;

        /**
         * Variabile buffer che contiene le stampe prodotte dai test di ataxxCommand.
         */
        private ByteArrayOutputStream byteOut;

        /**
         * Variabile utile per impostare il PrintStream allo stato di default.
         */
        private static PrintStream defaultPS;

        /**
         * Variabile utile per impostare il PrintStream in modo da poter effettuare i test.
         */
        private PrintStream myPS;

        /**
         * Stringa contenente il comando da dare in input al test di ataxxCommand.
         */
        private String str;

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
         * File contenente l'input da testare.
         */
        private static File inToTest;

        /**
         * Input stream proveniente da file di testo.
         */
        private FileOutputStream streamToFile;

        /**
         * Crea il file da cui proviene lo stream di input.
         */
        @BeforeAll
        static void setUpInputStream() {
            defaultPS = System.out;
            inToTest = new File("./src/test/java/it/uniba/app/inToTest.txt");
        }

        /**
         * Inizializza le variabili di test e imposta l'inputStream in modo che venga letto il file di inToTest.
         * @throws IOException se il percorso del file non viene trovato.
         */
        @BeforeEach
        void startAtaxxCommand() throws IOException {
            str = "";
            byteOut = new ByteArrayOutputStream();
            myPS = new PrintStream(byteOut);
            System.setOut(myPS);
            args = new String[]{"-i", "CONSOLE"};
            streamToFile = new FileOutputStream(inToTest, true);
            System.setIn(new FileInputStream(inToTest));
        }

        /**
         * Svuota il buffer di stampa e chiude il flusso di input precedente.
         * @throws IOException se il percorso del file non viene trovato.
         */
        @AfterEach
        void setUpAtaxxCommandTest() throws IOException {
            System.out.flush();
            System.setOut(defaultPS);
            streamToFile.close();
        }

        /**
         * Cancella il file degli input e reimposta l'input e l'output allo stato di default.
         */
        @AfterAll
        static void deleteFile() {
            boolean a = inToTest.delete();
            while (a) {
                a = inToTest.delete();
            }
            System.setOut(defaultPS);
            System.setIn(System.in);
        }

        /**
         * Test del metodo ataxxCommand quando viene inserito un comando non valido.
         * Modifica l'input stream, in modo che risulti letta da tastiera la stringa str.
         * Verifica che venga stampato a video il messaggio dell'inserimento di un comando e per un comando sconosciuto.
         * @throws IOException se il percorso del file non viene trovato.
         */
        @Test
        void testNotAnAtaxxCommand() throws IOException {
            str = "not_a_command\n/esci\ns\n";
            streamToFile.write(str.getBytes(UTF_8));
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
            String helpToString = Files.readString(Path.of("./src/main/java/it/uniba/app/help.txt"));
            streamToFile.write(str.getBytes(UTF_8));
            Commands.ataxxCommand(args);
            boolean inC = byteOut.toString().contains(Message.INSERT_COMMAND.getMessageText());
            assertTrue(inC && byteOut.toString().contains(helpToString), FILE_ERR);
        }

        /**
         * Test del metodo ataxxCommand per il comando /gioca.
         * Modifica l'input stream, in modo che risulti letta da tastiera la stringa str.
         * Verifica che venga visualizzato a video il messaggio relativo all'inserimento di un comando.
         * Verifica che venga stampato a video il campo di gioco.
         * @throws IOException se il percorso del file non viene trovato.
         */
        @Test
        void testAtaxxGiocaCommand() throws IOException {
            str = "/gioca\n/esci\ns\n";
            streamToFile.write(str.getBytes(UTF_8));
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
         * @throws IOException se il percorso del file non viene trovato.
         */
        @Test
        void testAtaxxVuotoCommand() throws IOException {
            str = "/vuoto\n/esci\ns\n";
            streamToFile.write(str.getBytes(UTF_8));
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
         * @throws IOException se il percorso del file non viene trovato.
         */
        @Test
        void testAtaxxTavoliereCommand() throws IOException {
            str = "/gioca\n/tavoliere\n/esci\ns\n";
            streamToFile.write(str.getBytes(UTF_8));
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
         * @throws IOException se il percorso del file non viene trovato.
         */
        @Test
        void testAtaxxTavoliereNoGameCommand() throws IOException {
            str = "/tavoliere\n/esci\ns\n";
            streamToFile.write(str.getBytes(UTF_8));
            Commands.ataxxCommand(args);
            boolean inC = byteOut.toString().contains(Message.INSERT_COMMAND.getMessageText());
            assertTrue(inC && byteOut.toString().contains(Message.NO_GAME.getMessageText()), UNEXPECTED_MSG);
        }

        /**
         * Test del metodo ataxxCommand per il comando /qualimosse.
         * Modifica l'input stream, in modo che risulti letta da tastiera la stringa str.
         * Verifica che venga visualizzato a video il messaggio relativo all'inserimento di un comando.
         * Verifica che venga stampato a video il campo di gioco.
         * @throws IOException se il percorso del file non viene trovato.
         */
        @Test
        void testAtaxxQualimosseCommand() throws IOException {
            str = "/gioca\n/qualimosse\n/esci\ns\n";
            streamToFile.write(str.getBytes(UTF_8));
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
         * @throws IOException se il percorso del file non viene trovato.
         */
        @Test
        void testAtaxxQualimosseNoGameCommand() throws IOException {
            str = "/qualimosse\n/esci\ns\n";
            streamToFile.write(str.getBytes(UTF_8));
            Commands.ataxxCommand(args);
            boolean inC = byteOut.toString().contains(Message.INSERT_COMMAND.getMessageText());
            assertTrue(inC && byteOut.toString().contains(Message.NO_GAME.getMessageText()), UNEXPECTED_MSG);
        }

        /**
         * Test del metodo ataxxCommand per il comando /mosse.
         * Modifica l'input stream, in modo che risulti letta da tastiera la stringa str.
         * Verifica che venga visualizzato a video il messaggio relativo all'inserimento di un comando.
         * Verifica che venga stampato lo storico delle mosse.
         * @throws IOException se il percorso del file non viene trovato.
         */
        @Test
        void testAtaxxMosseCommand() throws IOException {
            str = "/gioca\na1-a2\n/mosse\n/esci\ns\n";
            streamToFile.write(str.getBytes(UTF_8));
            Commands.ataxxCommand(args);
            boolean inC = byteOut.toString().contains(Message.INSERT_COMMAND.getMessageText());
            assertTrue(inC && byteOut.toString().contains(Message.MOVE_LIST.getMessageText()), UNEXPECTED_MSG);
        }

        /**
         * Test del metodo ataxxCommand per il comando /mosse quando non sono state effettuate mosse.
         * Modifica l'input stream, in modo che risulti letta da tastiera la stringa str.
         * Verifica che venga visualizzato a video il messaggio relativo all'inserimento di un comando.
         * Verifica che venga stampato il corretto messaggio di errore.
         * @throws IOException se il percorso del file non viene trovato.
         */
        @Test
        void testAtaxxEmptyMosseCommand() throws IOException {
            str = "/gioca\n/mosse\n/esci\ns\n";
            streamToFile.write(str.getBytes(UTF_8));
            Commands.ataxxCommand(args);
            boolean inC = byteOut.toString().contains(Message.INSERT_COMMAND.getMessageText());
            assertTrue(inC && byteOut.toString().contains(Message.NO_MOVES.getMessageText()), UNEXPECTED_MSG);
        }

        /**
         * Test del metodo ataxxCommand per il comando /mosse quando non ci sono partite in corso.
         * Modifica l'input stream, in modo che risulti letta da tastiera la stringa str.
         * Verifica che venga visualizzato a video il messaggio relativo all'inserimento di un comando.
         * Verifica che venga stampato il corretto messaggio di errore.
         * @throws IOException se il percorso del file non viene trovato.
         */
        @Test
        void testAtaxxMosseNoGameCommand() throws IOException {
            str = "/mosse\n/esci\ns\n";
            streamToFile.write(str.getBytes(UTF_8));
            Commands.ataxxCommand(args);
            boolean inC = byteOut.toString().contains(Message.INSERT_COMMAND.getMessageText());
            assertTrue(inC && byteOut.toString().contains(Message.NO_GAME.getMessageText()), UNEXPECTED_MSG);
        }

        /**
         * Test del metodo ataxxCommand per il comando /abbandona.
         * Modifica l'input stream, in modo che risulti letta da tastiera la stringa str.
         * Verifica che venga visualizzato a video il messaggio relativo all'inserimento di un comando.
         * Verifica che vengano stampati i messaggi relativi al termine della partita.
         * @throws IOException se il percorso del file non viene trovato.
         */
        @Test
        void testAtaxxAbbandonaCommand() throws IOException {
            str = "/gioca\n/abbandona\ns\n/esci\ns\n";
            streamToFile.write(str.getBytes(UTF_8));
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
         * @throws IOException se il percorso del file non viene trovato.
         */
        @Test
        void testAtaxxAbbandonaNoGameCommand() throws IOException {
            str = "/abbandona\n/esci\ns\n";
            streamToFile.write(str.getBytes(UTF_8));
            Commands.ataxxCommand(args);
            boolean inC = byteOut.toString().contains(Message.INSERT_COMMAND.getMessageText());
            assertTrue(inC && byteOut.toString().contains(Message.NO_GAME.getMessageText()), UNEXPECTED_MSG);
        }

        /**
         * Test del metodo ataxxCommand per il comando /tempo.
         * Modifica l'input stream, in modo che risulti letta da tastiera la stringa str.
         * Verifica che venga visualizzato a video il messaggio relativo all'inserimento di un comando.
         * Verifica che venga effettuata la stampa del tempo trascorso in partita.
         * @throws IOException se il percorso del file non viene trovato.
         */
        @Test
        void testAtaxxTempoCommand() throws IOException {
            str = "/gioca\n/tempo\n/esci\ns\n";
            streamToFile.write(str.getBytes(UTF_8));
            Commands.ataxxCommand(args);
            boolean inC = byteOut.toString().contains(Message.INSERT_COMMAND.getMessageText());
            assertTrue(inC && byteOut.toString().contains(Message.ELAPSED_TIME.getMessageText()), UNEXPECTED_MSG);
        }

        /**
         * Test del metodo ataxxCommand per il comando /tempo quando non ci sono partite in corso.
         * Modifica l'input stream, in modo che risulti letta da tastiera la stringa str.
         * Verifica che venga visualizzato a video il messaggio relativo all'inserimento di un comando.
         * Verifica che venga stampato il corretto messaggio di errore.
         * @throws IOException se il percorso del file non viene trovato.
         */
        @Test
        void testAtaxxTempoNoGameCommand() throws IOException {
            str = "/tempo\n/esci\ns\n";
            streamToFile.write(str.getBytes(UTF_8));
            Commands.ataxxCommand(args);
            boolean inC = byteOut.toString().contains(Message.INSERT_COMMAND.getMessageText());
            assertTrue(inC && byteOut.toString().contains(Message.NO_GAME.getMessageText()), UNEXPECTED_MSG);
        }

        /**
         * Test del metodo ataxxCommand per il comando /esci.
         * Modifica l'input stream, in modo che risulti letta da tastiera la stringa str.
         * Verifica che venga visualizzato a video il messaggio relativo all'inserimento di un comando.
         * Verifica che vengano stampati i messaggi relativi al comando in oggetto.
         * @throws IOException se il percorso del file non viene trovato.
         */
        @Test
        void testAtaxxEsciCommand() throws IOException {
            str = "/esci\ns\n";
            streamToFile.write(str.getBytes(UTF_8));
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
         * @throws IOException se il percorso del file non viene trovato.
         */
        @Test
        void testAtaxxBloccaCommand() throws IOException {
            str = "/blocca d3\n/gioca\n/esci\ns\n";
            streamToFile.write(str.getBytes(UTF_8));
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
         * @throws IOException se il percorso del file non viene trovato.
         */
        @Test
        void testAtaxxBloccaNonLockableCommand() throws IOException {
            str = "/blocca a1\n/esci\ns\n";
            streamToFile.write(str.getBytes(UTF_8));
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
         * @throws IOException se il percorso del file non viene trovato.
         */
        @Test
        void testAtaxxBloccaOutCommand() throws IOException {
            str = "/blocca m9\n/esci\ns\n";
            streamToFile.write(str.getBytes(UTF_8));
            Commands.ataxxCommand(args);
            boolean inC = byteOut.toString().contains(Message.INSERT_COMMAND.getMessageText());
            assertTrue(inC && byteOut.toString().contains(Message.COORD_ERR.getMessageText()), UNEXPECTED_MSG);
        }

        /**
         * Test del metodo ataxxCommand per il comando /blocca quando è in corso una partita.
         * Modifica l'input stream, in modo che risulti letta da tastiera la stringa str.
         * Verifica che venga visualizzato a video il messaggio relativo all'inserimento di un comando.
         * Verifica che venga stampato il corretto messaggio di errore.
         * @throws IOException se il percorso del file non viene trovato.
         */
        @Test
        void testAtaxxBloccaInvalidCommand() throws IOException {
            str = "/gioca\n/blocca a1\n/esci\ns\n";
            streamToFile.write(str.getBytes(UTF_8));
            Commands.ataxxCommand(args);
            boolean inC = byteOut.toString().contains(Message.INSERT_COMMAND.getMessageText());
            assertTrue(inC && byteOut.toString().contains(Message.GAME_IS_PLAYING.getMessageText()), UNEXPECTED_MSG);
        }

        /**
         * Test del metodo ataxxCommand per il comando /blocca quando vengono bloccate più di 9 caselle.
         * Modifica l'input stream, in modo che risulti letta da tastiera la stringa str.
         * Verifica che venga visualizzato a video il messaggio relativo all'inserimento di un comando.
         * Verifica che venga stampato il corretto messaggio di errore.
         * @throws IOException se il percorso del file non viene trovato.
         */
        @Test
        void testAtaxxBloccaAllCommand() throws IOException {
            str = "/gioca\na1-a6\n/esci\ns\n";
            streamToFile.write(str.getBytes(UTF_8));
            Commands.ataxxCommand(args);
            boolean inC = byteOut.toString().contains(Message.INSERT_COMMAND.getMessageText());
            assertTrue(inC && byteOut.toString().contains(Message.ILLEGAL_MOVE.getMessageText()), UNEXPECTED_MSG);
        }

        /**
         * Test del metodo ataxxCommand nel momento in cui viene immessa una mossa senza che sia in corso una partita.
         * Modifica l'input stream, in modo che risulti letta da tastiera la stringa str.
         * Verifica che venga visualizzato a video il messaggio relativo all'inserimento di un comando.
         * Verifica che venga stampato il corretto messaggio di errore.
         * @throws IOException se il percorso del file non viene trovato.
         */
        @Test
        void testAtaxxMoveNoGameCommand() throws IOException {
            str = "a1-a2\n/esci\ns\n";
            streamToFile.write(str.getBytes(UTF_8));
            Commands.ataxxCommand(args);
            boolean inC = byteOut.toString().contains(Message.INSERT_COMMAND.getMessageText());
            assertTrue(inC && byteOut.toString().contains(Message.NO_GAME.getMessageText()), UNEXPECTED_MSG);
        }

    }

    /**
     * Classe di test per i metodi relativi a COORDS_TO_LOCK in Commands.
     */
    @Nested
    class CoordsToLockTest {

        /**
         * Coordinate di cui verificare la presenza nel test di COORDS_TO_LOCK.
         */
        private static Coordinate tempCord;

        /**
         * Coordinate errate di cui verificare la presenza nel test di COORDS_TO_LOCK.
         */
        private static Coordinate tempOutCord;

        /**
         * Posizione di COORDS_TO_LOCK da cui recuperare il contenuto.
         */
        private static int pos;

        /**
         * Posizione errata di COORDS_TO_LOCK da cui recuperare il contenuto.
         */
        private static int outPos;

        /**
         * Dimensione corrente attesa per COORDS_TO_LOCK.
         */
        private static int size;

        /**
         * Costante utile a risolvere gli errori checkstyle relativi ai numeri magici.
         */
        private static final int ANTI_MAGIC_NUM = 3;

        /**
         * Messaggio di errore dovuto al fallimento di un test su COORDS_TO_LOCK.
         */
        static final String IN_CTL = "L'oggetto si trova in COORDS_TO_LOCK";

        /**
         * Messaggio di errore dovuto al fallimento di un test su COORDS_TO_LOCK.
         */
        static final String NOT_IN_CTL = "L'oggetto non si trova in COORDS_TO_LOCK";

        /**
         * Messaggio di errore dovuto al fallimento di un test su COORDS_TO_LOCK.
         */
        static final String NOT_IN_CTL_ATPOS = "L'oggetto non si trova nella posizione 0 di COORDS_TO_LOCK";

        /**
         * Messaggio di errore dovuto al fallimento di un test su COORDS_TO_LOCK.
         */
        static final String INVALID_POS = "Posizione di COORDS_TO_LOCK non accessibile";

        /**
         * Messaggio di errore dovuto al fallimento di un test su COORDS_TO_LOCK.
         */
        static final String SIZE_ERR = "Dimensione corrente di COORDS_TO_LOCK non corretta";

        /**
         * Imposta uno specchio per il vettore COORDS_TO_LOCK in commands, in modo da poterne testare i metodi.
         * Imposta, inoltre, gli altri campi utili ai fini del test.
         * @throws NoSuchFieldException viene lanciata se il campo che si vuole specchiare non esiste.
         * @throws IllegalAccessException viene lanciata se il campo che si vuole specchiare non è accessibile.
         */
        @BeforeAll
        static void setUpCoordsToLockTest() throws NoSuchFieldException, IllegalAccessException {
            Commands com = new Commands();
            java.lang.reflect.Field nameField = com.getClass().getDeclaredField("COORDS_TO_LOCK");
            nameField.setAccessible(true);
            Vector<Coordinate> tempCTL = (Vector<Coordinate>) nameField.get(com);
            tempCTL.clear();
            pos = 0;
            outPos = ANTI_MAGIC_NUM;
            size++;
            tempCord = new Coordinate(ANTI_MAGIC_NUM, ANTI_MAGIC_NUM - size);
            tempOutCord = new Coordinate(ANTI_MAGIC_NUM * ANTI_MAGIC_NUM, ANTI_MAGIC_NUM + ANTI_MAGIC_NUM - size);
            tempCTL.add(tempCord);
        }

        /**
         * Test del metodo isInCoordsToLock.
         * Verifica se tempCord, precedentemente inserito, è presente in COORDS_TO_LOCK.
         */
        @Test
        void testIsInCoordsToLock() {
            assertTrue(Commands.isInCoordsToLock(tempCord), NOT_IN_CTL);
        }

        /**
         * Test del metodo isInCoordsToLock.
         * Verifica se tempOutCord non è presente in COORDS_TO_LOCK.
         */
        @Test
        void testIsNotInCoordsToLock() {
            assertFalse(Commands.isInCoordsToLock(tempOutCord), IN_CTL);
        }

        /**
         * Test del metodo getCoordToLock.
         * Verifica che tempCord sia in posizione pos di COORDS_TO_LOCK.
         */
        @Test
        void testGetCoordToLock() {
            assertEquals(tempCord, Commands.getCoordToLock(pos), NOT_IN_CTL_ATPOS);
        }

        /**
         * Test del metodo getCoordToLock.
         * Verifica se la funzione in oggetto solleva eccezioni se si accede ad una posizione fuori da COORDS_TO_LOCK.
         */
        @Test
        void testGetOutOfCoordToLock() {
            try {
                Commands.getCoordToLock(outPos);
                fail("Eccezione non raggiunta");
            } catch (Exception e) {
                System.out.println(INVALID_POS);
            }
        }

        /**
         * Test del metodo getCoordToLockSize.
         * Verifica che venga restituita la dimensione corrente corretta di COORDS_TO_LOCK.
         */
        @Test
        void testGetCoordsToLockSize() {
            assertEquals(size, Commands.getCoordsToLockSize(), SIZE_ERR);
        }

    }

}
