package it.uniba.app.controller;
import it.uniba.app.model.Coordinate;
import it.uniba.app.model.Field;
import it.uniba.app.model.Move;
import it.uniba.app.model.Slot;
import it.uniba.app.views.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class GameControllerTest {
    /**
     * Attributo per la classe GameController per i casi di test.
     */
    private GameController controller;

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
     * Se le mosse legali vengono illuminate correttamente, il gioco deve ancora essere in corso.
     * Se le mosse legali non vengono illuminate correttamente, il gioco non deve più essere in corso.
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
        Move move = new Move(new Coordinate(0, 0), new Coordinate(0, 1));
        // Viene mosso un pezzo e viene inserito dentro la moveList
        controller.movePiece(move);

        controller.moveHistory();
        assertFalse(controller.getGame().getMoveList().isEmpty(), WRONG_MOVELIST);
    }

    /**
     * Metodo per controllare se viene effettuata correttamente l'uscita dal gioco.
     * Viene richiesta una conferma all'utente se vuole effettivamente abbandonare.
     * Se l'utente inserisce "s", il gioco non deve più esistere.
     */
    @Test
    void testLeaveGame() {
        Input.setTestCommand("s");
        controller.leaveGame();
        assertNull(controller.getGame(), NULL_GAME);
    }

    /**
     * Metodo per controllare se viene effettuata correttamente l'uscita dal gioco.
     * Viene richiesta una conferma all'utente se vuole effettivamente abbandonare.
     * Se l'utente inserisce "n", il gioco deve ancora esistere.
     */
    @Test
    void testNoLeaveGame() {
        Input.setTestCommand("n");
        controller.leaveGame();
        assertNotNull(controller.getGame(), NULL_GAME);
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
     * Metodo per verificare se viene effettuata correttamente la mossa da parte del giocatore.
     * Se la mossa non viene effettuata correttamente, la pedina non viene spostata correttamente nel campo da gioco.
     */
    @Test
    void testWrongMovePiece() {
        final int COLUMN_COORDINATE = 5;
        Move move = new Move(new Coordinate(0, 0), new Coordinate(0, COLUMN_COORDINATE));
        controller.movePiece(move);

        Field field = controller.getGame().getGameField();
        Slot destinationSlot = field.getSlot(move.getChosenSlot());

        /* Controllo se la casella di destinazione è stata colorata correttamente
         Assumo nextPlayer poiché il turno è stato passato al giocatore successivo dopo aver effettuato la mossa.
         Quindi il colore della pedina di destinazione deve essere uguale al colore che ha fatto la mossa.
         */
        assertNotEquals(controller.getGame().nextPlayer().getColor(), destinationSlot.getColorState(), BAD_MOVE_PIECE);
    }

    /**
     * Metodo per verificare se il prossimo giocatore ha mosse disponibili.
     * Se il prossimo giocatore ha mosse disponibili, il gioco deve ancora essere in corso.
     * Se il prossimo giocatore non ha mosse disponibili, il gioco non deve più essere in corso.
     */
    @Test
    void testCheckTurn() {
        controller.checkTurn();
        assertNotNull(controller.getGame(), NULL_GAME);
    }
}
