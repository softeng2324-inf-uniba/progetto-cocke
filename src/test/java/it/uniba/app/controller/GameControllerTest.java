package it.uniba.app.controller;
import it.uniba.app.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GameControllerTest {
    public GameController controller;

    @BeforeEach
    public void before() {
        controller = new GameController();
    }


    @Test
    public void testGetGameIsNull() {
        assertNull(controller.getGame());
    }


    @Test
    void testSetGame() {
        Game newGame = new Game();
        controller.setGame(newGame);
        assertNotNull(controller.getGame());
    }

    @Test
    void testGetStillPlaying() {
        assertTrue(controller.getStillPlaying());
    }

    @Test
    void testSetStillPlaying() {
        // Imposta stillPlaying a false e verifica
        controller.setStillPlaying(false);
        assertFalse(controller.getStillPlaying());

        // Imposta stillPlaying a true e verifica
        controller.setStillPlaying(true);
        assertTrue(controller.getStillPlaying());
    }

    @Test
    public void testCheckTurn() {
        GameController gameController = new GameController();
        Game game = new Game();
        gameController.setGame(game);
        gameController.checkTurn();
        assertNull(gameController.getGame());
    }
}
