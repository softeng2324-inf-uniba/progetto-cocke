package it.uniba.app.model;

import it.uniba.app.utils.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    /**
     * Gioco temporaneo per i casi di test.
     */
    private Game tempGame;

    /**
     * Messaggio di errore restituito quando il gioco copiato non coincide con quello atteso.
     */
    static final String BAD_COPY_GAME = "Il gioco copiato non coincide con quello atteso";

    /**
     * Messaggio di errore restituito quando uno slot viene catturato senza motivo.
     */
    static final String BAD_CAPTURE = "È stato modificato il colore dello slot impropriamente";

    /**
     * Messaggio di errore restituito quando viene restituito un campo non corrispondente a quello atteso.
     */
    static final String WRONG_FIELD = "Il campo restituito non coincide con quello atteso";

    /**
     * Messaggio di errore restituito quando viene restituito un'array di giocatori non corrispondente a quello atteso .
     */
    static final String WRONG_PLAYERS = "L'array di giocatori restituito non coincide con quello atteso";

    /**
     * Messaggio di errore restituito quando viene assegnato un campo diverso da quello previsto.
     */
    static final String BAD_FIELD_SET = "Il campo non è stato assegnato correttamente";

    /**
     * Messaggio di errore restituito quando viene restituita una lista di mosse diversa da quella attesa.
     */
    static final String BAD_MOVE_LIST = "La lista di mosse restituita non coincide con quella attesa";

    /**
     * Messaggio di errore restituito quando viene assegnata una lista di mosse diversa da quella prevista.
     */
    static final String BAD_MOVE_LIST_SET = "La lista di mosse non è stata assegnata correttamente";

    /**
     * Messaggio di errore restituito quando viene restituito un giocatore diverso da quello atteso.
     */
    static final String WRONG_PLAYER = "Il giocatore restituito non coincide con quello atteso";

    /**
     * Messaggio di errore restituito quando viene restituito un numero di pedine diverso da quello atteso.
     */
    static final String WRONG_PIECES_NUMBER = "Il numero di pedine restituito non coincide con quello atteso";

    /**
     * Messaggio di errore restituito quando viene restituito un intervallo di tempo diverso da quello atteso.
     */
    static final String WRONG_DURATION = "L'intervallo di tempo restituito non coincide con quello atteso";

    /**
     * Messaggio di errore restituito quando viene restituito un intervallo di tempo diverso da quello atteso.
     */
    static final String WRONG_COLOR = "Il colore restituito non coincide con quello atteso";

    /**
     * Messaggio di errore restituito quando due partite risultano diverse tra loro.
     */
    static final String BAD_MATCH = "Le partite non risultano uguali";

    /**
     * Messaggio di errore restituito quando l'hash della partita non coincide con quello atteso.
     */
    static final String WRONG_HASH = "L'hash della partita non corrisponde con quello atteso";

    /**
     * L'hash calcolato su tempGame.
     */
    static final int HASH_TEMP_GAME = 839998248;

    /**
     * La coordinata utilizzata durante i test.
     */
    static final Coordinate TEST_COORDINATE = new Coordinate(2, 4);

    /**
     * Coordinate circostanti la coordinata di test.
     */
    static final Coordinate[] COORDINATES_CAPTURE = {
            new Coordinate(2, 3),
            new Coordinate(2, 5),
            new Coordinate(1, 3),
            new Coordinate(1, 4),
            new Coordinate(1, 5),
            new Coordinate(3, 3),
            new Coordinate(3, 4),
            new Coordinate(3, 5)
    };

    /**
     * Coordinata a distanza 2 rispetto a quella di test.
     */
    static final Coordinate NEAR_COORDINATE = new Coordinate(4, 4);

    /**
     * Millisecondi da attendere per il test del tempo trascorso.
     */
    static final int WAIT_MILLIS = 2000;

    /**
     * Secondi trascorsi dall'inizio del gioco.
     */
    static final int SECOND_WAITED = 2;

    /**
     * Numero di pedine sul campo.
     */
    static final int SLOT_ON_FIELD = 5;

    /**
     * Imposta il gioco temporaneo di default per i test.
     */
    @BeforeEach
    void setUp() {
        tempGame = new Game();
    }

    /**
     * Test per il costruttore di copia.
     * Viene testato il caso in cui il gioco viene copiato correttamente.
     */
    @Test
    void testCopyConstructor() {
        Game copy = new Game(tempGame);
        assertEquals(tempGame, copy, BAD_COPY_GAME);
    }

    /**
     * Test per il metodo getPlayers.
     * Viene testato il caso in cui l'array di giocatore viene restituito correttamente.
     */
    @Test
    void testGetPlayers() {
        Player[] defaultPlayers = {new Player(Color.BLACK, "Giocatore 1"), new Player(Color.WHITE, "Giocatore 2")};

        assertArrayEquals(defaultPlayers, tempGame.getPlayers(), WRONG_PLAYERS);
    }

    /**
     * Test per il metodo getGameField.
     * Viene testato il caso in cui il campo di gioco venga restituito correttamente.
     */
    @Test
    void testGetGameField() {
        assertEquals(new Field(), tempGame.getGameField(), WRONG_FIELD);
    }

    /**
     * Test per il metodo setGameField.
     * Viene testato il caso in cui il campo di gioco venga assegnato correttamente.
     */
    @Test
    void testSetGameField() {
        Field testField = new Field();
        Slot tempSlot = new Slot();
        tempSlot.setColorState(Color.ORANGE);
        testField.setSlot(TEST_COORDINATE, tempSlot);

        tempGame.setGameField(testField);
        assertEquals(testField, tempGame.getGameField(), BAD_FIELD_SET);
    }

    /**
     * Test per il metodo getMoveList.
     * Verifica che venga restituita la lista delle mosse del gioco.
     */
    @Test
    void testGetMoveList() {
        assertNotNull(tempGame.getMoveList(), BAD_MOVE_LIST);
    }

    /**
     * Test per il metodo setMoveList.
     * Verifica che venga modificata la lista delle mosse del gioco.
     */
    @Test
    void testSetMoveList() {
        ArrayList<Move> testMoveList = new ArrayList<>();
        testMoveList.add(new Move(COORDINATES_CAPTURE[0], COORDINATES_CAPTURE[1]));
        testMoveList.add(new Move(COORDINATES_CAPTURE[1], COORDINATES_CAPTURE[0]));

        tempGame.setMoveList(testMoveList);
        assertEquals(testMoveList, tempGame.getMoveList(), BAD_MOVE_LIST_SET);
    }

    /**
     * Test del metodo getPlayer.
     * Verifica che il giocatore restituito corrisponda con quello atteso.
     */
    @Test
    void testGetPlayer() {
        Player testPlayer = new Player(Color.WHITE, "Giocatore 2");

        assertEquals(testPlayer, tempGame.getPlayer(1), WRONG_PLAYER);
    }

    /**
     * Test del metodo whoIsPlaying per il primo giocatore.
     * Verifica che venga restituito il primo giocatore.
     */
    @Test
    void testWhoIsPlayingFirstPlayer() {
        ArrayList<Move> testList = tempGame.getMoveList();
        testList.add(new Move(TEST_COORDINATE, TEST_COORDINATE));
        testList.add(new Move(TEST_COORDINATE, TEST_COORDINATE));
        testList.add(new Move(TEST_COORDINATE, TEST_COORDINATE));
        testList.add(new Move(TEST_COORDINATE, TEST_COORDINATE));
        tempGame.setMoveList(testList);

        assertEquals(tempGame.getPlayer(0), tempGame.whoIsPlaying(), WRONG_PLAYER);
    }

    /**
     * Test del metodo whoIsPlaying per il secondo giocatore.
     * Verifica che venga restituito il secondo giocatore.
     */
    @Test
    void testWhoIsPlayingSecondPlayer() {
        ArrayList<Move> testList = tempGame.getMoveList();
        testList.add(new Move(TEST_COORDINATE, TEST_COORDINATE));
        testList.add(new Move(TEST_COORDINATE, TEST_COORDINATE));
        testList.add(new Move(TEST_COORDINATE, TEST_COORDINATE));
        tempGame.setMoveList(testList);

        assertEquals(tempGame.getPlayer(1), tempGame.whoIsPlaying(), WRONG_PLAYER);
    }

    /**
     * Test del metodo nextPlayer per la lista di mosse vuota.
     * Verifica che venga restituito il secondo giocatore.
     */
    @Test
    void testWhoIsPlayingEmpty() {
        ArrayList<Move> testList = tempGame.getMoveList();
        tempGame.setMoveList(testList);

        assertEquals(tempGame.getPlayer(0), tempGame.whoIsPlaying(), WRONG_PLAYER);
    }

    /**
     * Test del metodo nextPlayer per il primo giocatore.
     * Verifica che venga restituito il primo giocatore.
     */
    @Test
    void testNextPlayerFirstPlayer() {
        ArrayList<Move> testList = tempGame.getMoveList();
        testList.add(new Move(TEST_COORDINATE, TEST_COORDINATE));
        testList.add(new Move(TEST_COORDINATE, TEST_COORDINATE));
        testList.add(new Move(TEST_COORDINATE, TEST_COORDINATE));
        tempGame.setMoveList(testList);

        assertEquals(tempGame.getPlayer(0), tempGame.nextPlayer(), WRONG_PLAYER);
    }

    /**
     * Test del metodo nextPlayer per il secondo giocatore.
     * Verifica che venga restituito il secondo giocatore.
     */
    @Test
    void testNextPlayerSecondPlayer() {
        ArrayList<Move> testList = tempGame.getMoveList();
        testList.add(new Move(TEST_COORDINATE, TEST_COORDINATE));
        testList.add(new Move(TEST_COORDINATE, TEST_COORDINATE));
        tempGame.setMoveList(testList);

        assertEquals(tempGame.getPlayer(1), tempGame.nextPlayer(), WRONG_PLAYER);
    }

    /**
     * Test del metodo nextPlayer per la lista di mosse vuota.
     * Verifica che venga restituito il secondo giocatore.
     */
    @Test
    void testNextPlayerEmpty() {
        ArrayList<Move> testList = tempGame.getMoveList();
        tempGame.setMoveList(testList);

        assertEquals(tempGame.getPlayer(1), tempGame.nextPlayer(), WRONG_PLAYER);
    }

    /**
     * Test del metodo countPieces.
     * Verifica che il numero di pedine contato corrisponda a quello effettivo.
     */
    @Test
    void testCountPieces() {
        Field testField = new Field();
        Slot tempSlot = new Slot();
        tempSlot.setColorState(Color.WHITE);
        for (int i = 0; i < SLOT_ON_FIELD; i++) {
            testField.setSlot(COORDINATES_CAPTURE[i], tempSlot);
        }
        tempSlot = new Slot();
        tempSlot.setColorState(Color.BLACK);
        testField.setSlot(NEAR_COORDINATE, tempSlot);
        tempGame.setGameField(testField);

        assertEquals(SLOT_ON_FIELD, tempGame.countPieces(Color.WHITE), WRONG_PIECES_NUMBER);
    }

    /**
     * Test del metodo getElapsedTime.
     * Verifica che l'intervallo di tempo restituito coincida con quello effettivamente trascorso.
     */
    @Test
    void testGetElapsedTime() throws InterruptedException {
        Thread.sleep(WAIT_MILLIS);

        assertEquals(SECOND_WAITED, tempGame.getElapsedTime().getSeconds(), WRONG_DURATION);
    }

    /**
     * Test del metodo colorWinner.
     * Verifica che il colore restituito sia quello del giocatore con più pedine.
     */
    @Test
    void testColorWinner() {
        Field testField = tempGame.getGameField();
        Slot tempSlot = new Slot();
        tempSlot.setColorState(Color.BLACK);
        for (Coordinate coordinate : COORDINATES_CAPTURE) {
            testField.setSlot(coordinate, tempSlot);
        }
        tempSlot = new Slot();
        tempSlot.setColorState(Color.WHITE);
        testField.setSlot(NEAR_COORDINATE, tempSlot);
        tempGame.setGameField(testField);

        assertEquals(Color.BLACK, tempGame.colorWinner(), WRONG_COLOR);
    }

    /**
     * Test del metodo captureSlot.
     * Verifica che il metodo capture slot cambi colore alle celle adiacenti.
     */
    @Test
    void testCaptureSlot() {
        Field testField = tempGame.getGameField();
        Slot tempSlot = new Slot();
        tempSlot.setColorState(Color.BLACK);
        for (Coordinate coordinate : COORDINATES_CAPTURE) {
            testField.setSlot(coordinate, tempSlot);
        }
        tempSlot = new Slot();
        tempSlot.setColorState(Color.WHITE);
        testField.setSlot(TEST_COORDINATE, tempSlot);
        tempGame.setGameField(testField);

        Field testFieldResult = new Field(testField);
        for (Coordinate coordinate : COORDINATES_CAPTURE) {
            testFieldResult.setSlot(coordinate, tempSlot);
        }

        tempGame.captureSlot(TEST_COORDINATE);
        assertEquals(testFieldResult, tempGame.getGameField(), BAD_CAPTURE);
    }

    /**
     * Test del metodo captureSlot.
     * Verifica che il metodo capture slot non cambi colore alle caselle a distanza 2.
     */
    @Test
    void testCaptureSlotNearSlot() {
        Field testField = tempGame.getGameField();
        Slot tempSlot = new Slot();
        tempSlot.setColorState(Color.BLACK);
        testField.setSlot(NEAR_COORDINATE, tempSlot);
        tempGame.setGameField(testField);

        tempGame.captureSlot(TEST_COORDINATE);
        assertEquals(testField, tempGame.getGameField(), BAD_CAPTURE);
    }

    /**
     * Test per il metodo equals.
     * Verifica l'uguaglianza tra una copia della partita e la partita effettiva.
     */
    @Test
    void testEquals() {
        Game copy = new Game(tempGame);

        boolean match = tempGame.equals(copy);
        assertTrue(match, BAD_MATCH);
    }

    /**
     * Test per il metodo hashCode.
     * Verifica che l'hash restituito corrisponda a quello della partita di test.
     */
    @Test
    void testHashCode() {
        assertEquals(HASH_TEMP_GAME, tempGame.hashCode(), WRONG_HASH);
    }

}

