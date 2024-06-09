package it.uniba.app.model;

import it.uniba.app.utils.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import static it.uniba.app.model.FieldTest.BAD_RETURN_SLOT;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    /**
     * Gioco temporaneo per i casi di test.
     */
    Game tempGame;

    /**
     * Messaggio di errore restituito quando il gioco copiato non coincide con quello atteso.
     */
    final static String BAD_COPY_GAME = "Il gioco copiato non coincide con quello atteso";

    /**
     * Messaggio di errore restituito quando uno slot viene catturato senza motivo.
     */
    final static String BAD_CAPTURE = "È stato modificato il colore dello slot impropriamente";

    /**
     * Messaggio di errore restituito quando viene restituito un campo non corrispondente a quello atteso.
     */
    final static String WRONG_FIELD = "Il campo restituito non coincide con quello atteso";

    /**
     * Messaggio di errore restituito quando viene restituito un'array di giocatori non corrispondente a quello atteso .
     */
    final static String WRONG_PLAYERS = "L'array di giocatori restituito non coincide con quello atteso";

    /**
     * Messaggio di errore restituito quando viene assegnato un campo diverso da quello previsto.
     */
    final static String BAD_FIELD_SET = "Il campo non è stato assegnato correttamente";

    /**
     * Messaggio di errore restituito quando viene restituita una lista di mosse diversa da quella attesa.
     */
    final static String BAD_MOVE_LIST = "La lista di mosse restituita non coincide con quella attesa";

    /**
     * Messaggio di errore restituito quando viene assegnata una lista di mosse diversa da quella prevista.
     */
    final static String BAD_MOVE_LIST_SET = "La lista di mosse non è stata assegnata correttamente";

    /**
     * Messaggio di errore restituito quando viene restituito un giocatore diverso da quello atteso.
     */
    final static String WRONG_PLAYER = "Il giocatore restituito non coincide con quello atteso";

    /**
     * Messaggio di errore restituito quando viene restituito un numero di pedine diverso da quello atteso.
     */
    final static String WRONG_PIECES_NUMBER = "Il numero di pedine restituito non coincide con quello atteso";

    /**
     * Messaggio di errore restituito quando viene restituito un intervallo di tempo diverso da quello atteso.
     */
    final static String WRONG_DURATION = "L'intervallo di tempo restituito non coincide con quello atteso";

    /**
     * Messaggio di errore restituito quando viene restituito un intervallo di tempo diverso da quello atteso.
     */
    final static String WRONG_COLOR = "Il colore restituito non coincide con quello atteso";

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
        testField.setSlot(new Coordinate(2, 4), tempSlot);

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
        testMoveList.add(new Move(new Coordinate(2, 1), new Coordinate(4, 3)));
        testMoveList.add(new Move(new Coordinate(0, 5), new Coordinate(7, 2)));

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
        testList.add(new Move(new Coordinate(0, 0), new Coordinate(0, 0)));
        testList.add(new Move(new Coordinate(0, 0), new Coordinate(0, 0)));
        testList.add(new Move(new Coordinate(0, 0), new Coordinate(0, 0)));
        testList.add(new Move(new Coordinate(0, 0), new Coordinate(0, 0)));
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
        testList.add(new Move(new Coordinate(0, 0), new Coordinate(0, 0)));
        testList.add(new Move(new Coordinate(0, 0), new Coordinate(0, 0)));
        testList.add(new Move(new Coordinate(0, 0), new Coordinate(0, 0)));
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
        testList.add(new Move(new Coordinate(0, 0), new Coordinate(0, 0)));
        testList.add(new Move(new Coordinate(0, 0), new Coordinate(0, 0)));
        testList.add(new Move(new Coordinate(0, 0), new Coordinate(0, 0)));
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
        testList.add(new Move(new Coordinate(0, 0), new Coordinate(0, 0)));
        testList.add(new Move(new Coordinate(0, 0), new Coordinate(0, 0)));
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
        testField.setSlot(new Coordinate(0,0), tempSlot);
        testField.setSlot(new Coordinate(0,1), tempSlot);
        testField.setSlot(new Coordinate(0,5), tempSlot);
        testField.setSlot(new Coordinate(6,6), tempSlot);
        testField.setSlot(new Coordinate(5,5), tempSlot);
        tempSlot = new Slot();
        tempSlot.setColorState(Color.BLACK);
        testField.setSlot(new Coordinate(4,4), tempSlot);
        tempGame.setGameField(testField);

        assertEquals(5, tempGame.countPieces(Color.WHITE), WRONG_PIECES_NUMBER);
    }

    /**
     * Test del metodo getElapsedTime.
     * Verifica che l'intervallo di tempo restituito coincida con quello effettivamente trascorso.
     */
    @Test
    void testGetElapsedTime() throws InterruptedException {
        Thread.sleep(2000);

        assertEquals(2, tempGame.getElapsedTime().getSeconds(), WRONG_DURATION);
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
        testField.setSlot(new Coordinate(0,0), tempSlot);
        testField.setSlot(new Coordinate(0,1), tempSlot);
        testField.setSlot(new Coordinate(0,5), tempSlot);
        testField.setSlot(new Coordinate(6,6), tempSlot);
        testField.setSlot(new Coordinate(5,5), tempSlot);
        tempSlot.setColorState(Color.WHITE);
        testField.setSlot(new Coordinate(4,4), tempSlot);
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
        testField.setSlot(new Coordinate(0,0), tempSlot);
        testField.setSlot(new Coordinate(0,1), tempSlot);
        testField.setSlot(new Coordinate(0,2), tempSlot);
        testField.setSlot(new Coordinate(1,0), tempSlot);
        testField.setSlot(new Coordinate(1,2), tempSlot);
        testField.setSlot(new Coordinate(2,0), tempSlot);
        testField.setSlot(new Coordinate(2,1), tempSlot);
        testField.setSlot(new Coordinate(2,2), tempSlot);
        testField.setSlot(new Coordinate(2,3), tempSlot);
        testField.setSlot(new Coordinate(3,1), tempSlot);
        tempSlot.setColorState(Color.WHITE);
        testField.setSlot(new Coordinate(1,1), tempSlot);
        tempGame.setGameField(testField);

        Field testFieldResult = testField;
        tempSlot.setColorState(Color.WHITE);
        testFieldResult.setSlot(new Coordinate(0,0), tempSlot);
        testFieldResult.setSlot(new Coordinate(0,1), tempSlot);
        testFieldResult.setSlot(new Coordinate(0,2), tempSlot);
        testFieldResult.setSlot(new Coordinate(1,0), tempSlot);
        testFieldResult.setSlot(new Coordinate(1,2), tempSlot);
        testFieldResult.setSlot(new Coordinate(2,0), tempSlot);
        testFieldResult.setSlot(new Coordinate(2,1), tempSlot);
        testFieldResult.setSlot(new Coordinate(2,2), tempSlot);

        tempGame.captureSlot(new Coordinate(1, 1));
        assertEquals(testFieldResult, tempGame.getGameField(), BAD_CAPTURE);
    }
}

