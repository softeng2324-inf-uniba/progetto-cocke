package it.uniba.app.controller;
import it.uniba.app.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {
    /**
     * Attributo per la classe GameController per i casi di test.
     */
    GameController controller;

    /**
     * Attributo per la classe Game per i casi di test.
     */
    Game game;

    /**
     * Messaggio di errore quando il gioco non esiste.
     */
    private static final String NULL_GAME = "Il gioco non esiste.";

    /**
     * Messaggio di errore quando il gioco è ancora in corso.
     */
    private static final String STILL_PLAYING = "Il gioco è ancora in corso.";

    /**
     * Messaggio di errore quando il gioco non è più in corso.
     */
    private static final String WRONG_MOVELIST = "Le mosse non sono state inserite correttamente.";

    /**
     * Metodo eseguito prima di ogni test (viene inizializzato il controller
     * tramite il costruttore).
     */
    @BeforeEach
    void before() {
        game = new Game();
        controller = new GameController();
    }


    /**
     * Metodo per controllare se il gioco è null.
     */
    @Test
    void testGetGameIsNull() {
        assertNull(controller.getGame(), NULL_GAME);
    }

    /**
     * Metodo per controllare se il gioco non è null.
     */
    @Test
    void testGetGame() {
        assertNotNull(controller.getGame(), NULL_GAME);
    }

    /**
     * Metodo per controllare se il gioco è stato impostato correttamente.
     */
    @Test
    void testSetGame() {
        assertNotNull(controller.getGame(), NULL_GAME);
    }

    /**
     * Metodo per controllare se il gioco è ancora in corso.
     */
    @Test
    void testGetStillPlaying() {
        assertTrue(controller.getStillPlaying(), STILL_PLAYING);
    }

    /**
     * Metodo per controllare se il gioco non è più in corso.
     */
    @Test
    void testSetStillPlayingFalse() {
        controller.setStillPlaying(false);
        assertFalse(controller.getStillPlaying(), STILL_PLAYING);
    }

    /**
     * Metodo per controllare se vengono illuminate correttamente le mosse legali.
     */
    @Test
    void testLegalMoves() {
        controller.legalMoves();
        assertNotNull(controller.getGame(), NULL_GAME);
    }

    /**
     * Metodo per controllare se vengono inserite correttamente le mosse nei log.
     */
    @Test
    void testMoveHistory() {
        ArrayList<Move> tempMoveList = game.getMoveList();
        tempMoveList.add(new Move(new Coordinate(0, 0), new Coordinate(1, 1)));
        game.setMoveList(tempMoveList);
        controller.setGame(game);
        assertEquals(tempMoveList, controller.getGame().getMoveList(), WRONG_MOVELIST);
    }

    /**
     * Metodo per controllare se viene effettuata correttamente l'uscita dal gioco.
     */
    @Test
    void testLeaveGame() {
        controller.leaveGame();
        assertNull(controller.getGame(), NULL_GAME);
    }

    /**
     * Metodo per verificare se viene effettuata correttamente la mossa da parte del giocatore.
     */
    @Test
    void testMovePiece() {

    }

    /**
     * Metodo per verificare se i due giocatori hanno mosse a disposizione.
     */
    @Test
    void testCheckTurn() {

    }
}
