package it.uniba.app;

import com.sun.jdi.Field;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class Game {

    private Field gameField;
    private ArrayList<Move> moveList;
    private Player[] players;

    Game(Player[] players) {
        gameField = new Field();
        moveList = new ArrayList<Move>();
        this.players = players;
    }

    Field getGameField() {
        return gameField;
    }

    void setGameField(Field f) {
        this.gameField = f;
    }

    Queue<Move> getMoveList() {
        return moveList;
    }

    void setMoveList(Queue<Move> moves) {
        this.moveList = moves;
    }

    Player getPlayer(int index) {
        return players[index];
    }

    void setPlayer(Player p, int index) {
        players[index] = p;
    }

}
