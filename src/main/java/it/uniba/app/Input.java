package it.uniba.app;

import java.util.Scanner;

/**
 * Classe Input utile all'acquisizione degli input da tastiera.
 */
public final class Input {
    /**
     * Costruttore per la classe Input
     */
    private Input(){}

    /**
     * Metodo per l'acquisizione del nome di un giocatore
     * @param playerIndex id del nuovo giocatore
     * @return il nuovo oggetto giocatore
     */
    public static Player getPlayersName(final int playerIndex){
        Player p = null;
        System.out.println("Enter player " + playerIndex+1 + " name");
        if(playerIndex==1){
            p = new Player(Color.BIANCO, Keyboard.readString());
        } else {
            p = new Player(Color.NERO, Keyboard.readString());
        }
        return p;
    }

    /**
     * Metodo per l'acquisizione di una mossa da effettuare
     * @return la mossa letta da tastiera
     */
    public static Move getMove(){
        char row = ' ';
        int col = -1;
        System.out.println("Enter the row of the draught to move");
        row = Keyboard.readChar();
        System.out.println("Enter the column of the draught to move");
        col = Keyboard.readInt();
        Coordinate start = new Coordinate(row, col);
        System.out.println("Enter the row of the designated slot");
        row = Keyboard.readChar();
        System.out.println("Enter the column of the designated slot");
        col = Keyboard.readInt();
        Coordinate choice = new Coordinate(row, col);
        return new Move(start, choice);
    }

    /**
     * Metodo per l'acquisizione di una coordinata
     * @return le coordinate lette da tastiera
     */
    private static Coordinate getCoordinate(){
        char row = ' ';
        int col = -1;
        Coordinate c = null;
        System.out.println("Enter row");
        c.setRow(row);
        System.out.println("Enter column");
        c.setCol(col);
        return c;
    }

    /**
     * Metodo per l'acquisizione di un comando
     * @return il comando letto da tastiera
     */
    public static String getCommand(){
        System.out.println("Enter a command");
        return Keyboard.readString();
    }
}
