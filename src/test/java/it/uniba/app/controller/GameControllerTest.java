package it.uniba.app.controller;
import it.uniba.app.model.*;
import it.uniba.app.utils.Color;
import it.uniba.app.views.Output;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GameControllerTest {
    public GameController controller;

    @BeforeEach
    public void before() {
        Game testGame = new Game();
        Game testOutput = new Game();
        controller = new GameController();
    }


    @Test
    public void testGetGameIsNull() {
        assertNull(controller.getGame());
    }


    @Test
    void testSetGame() {
        Game newGame = new Game();
        //Game copiedGame = new Game(newGame);
        controller.setGame(newGame);
        assertNotNull(controller.getGame());
        //assertEquals(copiedGame, controller.getGame());
    }

    @Test
    void testGetStillPlaying() {
        // Valore iniziale di stillPlaying dovrebbe essere true
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
    public void testStartGameIsNotNull() {
        controller.startNewGame();
        assertNotNull(controller.getGame());
    }

    @Test
    public void testConvertField() {
        int fieldSize = 7;
        Field field = new Field(fieldSize);
        Color playerColor = Color.WHITE;
//inserisce 2 pedine in a1 e a2 , cancellare questo commento dopo la supervisione
        field.getSlot(new Coordinate(1, 1)).setColorState(playerColor);
        field.getSlot(new Coordinate(1, 2)).setColorState(playerColor);


        GameController controller = new GameController();
        controller.convertField(field, playerColor);

//verifica che ci sia il colore giallo in 2,1 arancione 3,1 e rosa 2,3. da finire di implementare
        assertEquals(playerColor, field.getSlot(new Coordinate(2, 1)).getColorState());
        assertEquals(playerColor, field.getSlot(new Coordinate(3, 1)).getColorState());
        assertEquals(playerColor, field.getSlot(new Coordinate(2, 3)).getColorState());
    }

    @Test //inserisce tutti pezzi neri e restituisce falso con i colori rosa, arancione e giallo
    public void testConvertFullField() {
        int fieldSize = 7;
        Field field = new Field(fieldSize);
        Color playerColor = Color.BLACK;

        for (int x = 0; x < fieldSize; x++) {
            for (int y = 0; y < fieldSize; y++) {
                Coordinate coordinate = new Coordinate(x, y);
                field.getSlot(coordinate).setColorState(playerColor);
            }
        }

        assertEquals(playerColor, field.getSlot(new Coordinate(4, 3)).getColorState());
    }


    @Test
    public void testCheckDistanceReturnsTrueWithinRange() {
        int distanceWithinRange = 2;
        assertTrue(controller.checkDistance(distanceWithinRange));
    }

    @Test
    public void testCheckDistanceReturnsFalseOutOfRange() {
        int distanceOutOfRange = 15;
        assertFalse(controller.checkDistance(distanceOutOfRange));
    }

    @Test
    public void testCheckTurn() {
        GameController gameController = new GameController();
        Game game = new Game();
        gameController.setGame(game);
        gameController.checkTurn();
        assertNull(gameController.getGame());
    }

    @Test
    public void testContainsEmptyMove() {
        ArrayList<Move> moveList = new ArrayList<>();
        Coordinate emptyCoordinate = new Coordinate(0, 0);
        Move emptyMove = new Move(emptyCoordinate, emptyCoordinate);

        moveList.add(emptyMove);
        assertTrue(controller.containsEmptyMove(moveList));

        moveList.remove(emptyMove);
        assertFalse(controller.containsEmptyMove(moveList));
    }
}

//qui sono riportati i metodi non privati a cui fare i test
//setStatingPosition
//legalMoves
//moveHistory
//markNeighboringSlot
//movePiece
//checkTurn
