package it.uniba.app;

import java.util.Scanner;
/**
 * Classe Input utile all'acquisizione degli input da tastiera
 */
public class Input {
    /**
     * Costruttore per la classe Input
     */
    private Input(){}
    /**
     * Metodo per l'acquisizione del nome di un giocatore
     * @param playerIndex id del nuovo giocatore
     * @return il nuovo oggetto giocatore
     */
    public static Player getPlayersName(int playerIndex){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter player " + playerIndex + " name");
        if(playerIndex==1){
            Player p = new Player("red", keyboard.nextLine());
        }else Player p = new Player("blue", keyboard.nextLine());
        return p;
    }
    /**
     * Metodo per l'acquisizione di una mossa da effettuare
     * @return la mossa letta da tastiera
     */
    public static Move getMove(){
        System.out.println("Enter the coordinate of the draught to move");
        Coordinate start = new Coordinate();
        start = getCoordinate();
        System.out.println("Enter the coordinate of the designated slot");
        Coordinate choice = new Coordinate();
        choice = getCoordinate();
        return new Move(start, choice);
    }
    /**
     * Metodo per l'acquisizione di una coordinata
     * @return le coordinate lette da tastiera
     */
    private static Coordinate getCoordinate(){
        Coordinate c = new Coordinate();
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter row");
        c.setX(keyboard.nextLine());
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter column");
        c.setY(keyboard.nextInt());
        return c;
    }
    /**
     * Metodo per l'acquisizione di un comando
     * @return il comando letto da tastiera
     */
    public static String getCommand(){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter a command");
        return keyboard.nextLine();
    }
}
