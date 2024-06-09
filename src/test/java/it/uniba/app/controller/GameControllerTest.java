package it.uniba.app.controller;
import it.uniba.app.model.*;
import it.uniba.app.utils.Color;
import it.uniba.app.views.Output;
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
     * Messaggio di errore quando la pedina non è stata spostata correttamente.
     */
    private static final String BAD_MOVE_PIECE = "La pedina non è stata spostata correttamente.";

    /**
     * Metodo eseguito prima di ogni test (viene inizializzato il controller
     * tramite il costruttore).
     */
    @BeforeEach
    void before() {
        controller = new GameController();
        controller.startNewGame();
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
    }

    /**
     * Metodo per controllare se vengono inserite correttamente le mosse nei log.
     */
    @Test
    void testMoveHistory() {
        Move move = new Move(new Coordinate(0, 0), new Coordinate(0, 1));
        controller.movePiece(move);

        controller.moveHistory();
        assertFalse(controller.getGame().getMoveList().isEmpty());
    }

    /**
     * Metodo per controllare se viene effettuata correttamente l'uscita dal gioco.
     * Se viene effettuata l'uscita, il gioco non deve più esistere.
     */
    @Test
    void testLeaveGame() {
        controller.leaveGame();
        assertNull(controller.getGame(), NULL_GAME);
    }

    /**
     * Metodo per verificare se viene effettuata correttamente la mossa da parte del giocatore.
     * Se la mossa viene effettuata correttamente, la pedina viene spostata correttamente nel campo da gioco.
     */
    @Test
    void testMovePiece() {
        Move move = new Move(new Coordinate(0, 0), new Coordinate(0, 1));
        controller.movePiece(move);

        Field field = controller.getGame().getGameField();
        Slot destinationSlot = field.getSlot(move.getChosenSlot());

        /* Controllo se la casella di destinazione è stata colorata correttamente
         Assumo nextPlayer poiché il turno è stato passato al giocatore successivo dopo aver effettuato la mossa.
         Quindi il colore della pedina di destinazione deve essere uguale al colore che ha fatto la mossa.
         */
        assertEquals(controller.getGame().nextPlayer().getColor(), destinationSlot.getColorState(), BAD_MOVE_PIECE);
    }

    /**
     * Metodo per verificare se i due giocatori hanno mosse a disposizione.
     */
    @Test
    void testCheckTurn() {
        controller.checkTurn();

        Player currentPlayer = controller.getGame().whoIsPlaying();
        assertNotEquals(controller.getGame().nextPlayer().getColor(), currentPlayer.getColor());
    }
}
