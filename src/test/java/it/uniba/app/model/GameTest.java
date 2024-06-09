package it.uniba.app.model;

import it.uniba.app.utils.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;

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
     * Messaggio di errore restituito quando il campo restituito non è null.
     */
    final static String NOT_NULL_FIELD = "Il campo restituito non è null";


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
        assertAll("Costruttore di copia",
                () -> assertNotNull(copy, BAD_COPY_GAME),
                () -> assertNotNull(copy.getGameField(), BAD_COPY_GAME),
                () -> assertNotNull(copy.getMoveList(), BAD_COPY_GAME),
                () -> assertNotNull(copy.getPlayers(), BAD_COPY_GAME),
                () -> assertEquals(Color.BLACK, copy.getPlayer(0).getColor(), BAD_COPY_GAME)
        );
    }

    /**
     * Test per il metodo getPlayers.
     * Viene testato il caso in cui l'array di giocatore viene restituito correttamente.
     */
    @Test
    void testGetPlayers() {
        Player[] defaultPlayers = {new Player(Color.BLACK, "Giocatore 1"), new Player(Color.WHITE, "Giocatore 2")};

        assertArrayEquals(defaultPlayers, tempGame.getPlayers());
    }

    /**
     * Test per il metodo getGameField.
     * Viene testato il caso in cui il campo di gioco venga restituito correttamente.
     */
    @Test
    void testGetGameField() {
        assertAll("Getter del campo da gioco",
                () -> assertEquals(Field.DEFAULT_DIM, tempGame.getGameField().length()),
                () -> assertEquals(Color.GREY, tempGame.getGameField().getSlot(new Coordinate(4, 3)).getColorState())
        );
    }

    /**
     * Test per il metodo setGameField.
     * Viene testato il caso in cui il campo di gioco venga assegnato correttamente.
     */
    @Test
    void testSetGameField() {
        Field testField = new Field();
        testField.setSlot(new Coordinate(2, 4), new Slot(Color.ORANGE));
        tempGame.setGameField(testField);
        assertAll("Setter del campo da gioco",
                () -> assertEquals(Field.DEFAULT_DIM, tempGame.getGameField().length()),
                () -> assertEquals(Color.GREY, tempGame.getGameField().getSlot(new Coordinate(4, 3)).getColorState()),
                () -> assertEquals(Color.ORANGE, tempGame.getGameField().getSlot(new Coordinate(2, 4)).getColorState())
        );
    }

    /**
     * Test per il metodo getMoveList.
     * Verifica che venga restituita la lista delle mosse del gioco.
     */
    @Test
    void testGetGameList() {
        assertNotNull(tempGame.getMoveList());
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
        assertAll("Getter della lista di mosse effettuate",
                () -> assertEquals(testMoveList.get(0).getStartingSlot(), tempGame.getMoveList().get(0).getStartingSlot()),
                () -> assertEquals(testMoveList.get(0).getChosenSlot(), tempGame.getMoveList().get(0).getChosenSlot()),
                () -> assertEquals(testMoveList.get(1).getStartingSlot(), tempGame.getMoveList().get(1).getStartingSlot()),
                () -> assertEquals(testMoveList.get(1).getChosenSlot(), tempGame.getMoveList().get(1).getChosenSlot())
        );
    }

    /**
     * Test del metodo getPlayer.
     * Verifica che il giocatore restituito corrisponda con quello atteso.
     */
    @Test
    void testGetPlayer() {
        Player testPlayer = new Player(Color.WHITE, "Giocatore 2");

        assertEquals(testPlayer, tempGame.getPlayer(1));
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

        assertEquals(tempGame.getPlayer(0), tempGame.whoIsPlaying());
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

        assertEquals(tempGame.getPlayer(1), tempGame.whoIsPlaying());
    }

    /**
     * Test del metodo nextPlayer per la lista di mosse vuota.
     * Verifica che venga restituito il secondo giocatore.
     */
    @Test
    void testWhoIsPlayingEmpty() {
        ArrayList<Move> testList = tempGame.getMoveList();
        tempGame.setMoveList(testList);

        assertEquals(tempGame.getPlayer(0), tempGame.whoIsPlaying());
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

        assertEquals(tempGame.getPlayer(0), tempGame.nextPlayer());
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

        assertEquals(tempGame.getPlayer(1), tempGame.nextPlayer());
    }

    /**
     * Test del metodo nextPlayer per la lista di mosse vuota.
     * Verifica che venga restituito il secondo giocatore.
     */
    @Test
    void testNextPlayerEmpty() {
        ArrayList<Move> testList = tempGame.getMoveList();
        tempGame.setMoveList(testList);

        assertEquals(tempGame.getPlayer(1), tempGame.nextPlayer());
    }

    /**
     * Test del metodo countPieces.
     * Verifica che il numero di pedine contato corrisponda a quello effettivo.
     */
    @Test
    void testCountPieces() {
        Field testField = tempGame.getGameField();
        Slot tempSlot = new Slot();
        tempSlot.setColorState(Color.WHITE);
        testField.setSlot(new Coordinate(0,0), tempSlot);
        testField.setSlot(new Coordinate(0,1), tempSlot);
        testField.setSlot(new Coordinate(0,5), tempSlot);
        testField.setSlot(new Coordinate(6,6), tempSlot);
        testField.setSlot(new Coordinate(5,5), tempSlot);
        tempSlot.setColorState(Color.BLACK);
        testField.setSlot(new Coordinate(4,4), tempSlot);
        tempGame.setGameField(testField);

        assertEquals(5, tempGame.countPieces(Color.WHITE));
    }

    /**
     * Test del metodo getElapsedTime.
     * Verifica che l'intervallo di tempo restituito coincida con quello effettivamente trascorso.
     */
    @Test
    void testGetElapsedTime() throws InterruptedException {
        Thread.sleep(2000);

        assertEquals(2, tempGame.getElapsedTime().getSeconds());
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

        assertEquals(Color.BLACK, tempGame.colorWinner());
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

        tempGame.captureSlot(new Coordinate(1, 1));
        assertAll("Cattura caselle adiacenti",
                () -> assertEquals(tempGame.getGameField().getSlot(new Coordinate(0, 0)).getColorState(), Color.WHITE),
                () -> assertEquals(tempGame.getGameField().getSlot(new Coordinate(0, 1)).getColorState(), Color.WHITE),
                () -> assertEquals(tempGame.getGameField().getSlot(new Coordinate(0, 2)).getColorState(), Color.WHITE),
                () -> assertEquals(tempGame.getGameField().getSlot(new Coordinate(1, 0)).getColorState(), Color.WHITE),
                () -> assertEquals(tempGame.getGameField().getSlot(new Coordinate(1, 1)).getColorState(), Color.WHITE),
                () -> assertEquals(tempGame.getGameField().getSlot(new Coordinate(1, 2)).getColorState(), Color.WHITE),
                () -> assertEquals(tempGame.getGameField().getSlot(new Coordinate(2, 0)).getColorState(), Color.WHITE),
                () -> assertEquals(tempGame.getGameField().getSlot(new Coordinate(2, 1)).getColorState(), Color.WHITE),
                () -> assertEquals(tempGame.getGameField().getSlot(new Coordinate(2, 2)).getColorState(), Color.WHITE),
                () -> assertNotEquals(tempGame.getGameField().getSlot(new Coordinate(2, 3)).getColorState(), Color.WHITE),
                () -> assertNotEquals(tempGame.getGameField().getSlot(new Coordinate(3, 1)).getColorState(), Color.WHITE),
                () -> assertNotEquals(tempGame.getGameField().getSlot(new Coordinate(3, 0)).getColorState(), Color.WHITE)
        );
    }
}

