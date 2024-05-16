package it.uniba.app;


import java.util.ArrayList;

/**
 * Game è la classe che contiene gli elementi che costituiscono una partita del gioco presente
 * in <code>Ataxx</code>.
 * <p>Questa classe permette di inizializzare il campo da gioco, salvare una
 * cronologia delle mosse della partita e inizializzare entrambi i giocatori.</p>
 */
public class Game {

    /**
     * Contiene il campo da gioco formato da <code>Slot</code>.
     */
    private Field gameField;
    /**
     * Contiene una lista di <code>Move</code> che costituiscono il log della partita cui appartengono.
     */
    private ArrayList<Move> moveList;
    /**
     * Array di <code>Player</code>, contenente i giocatori inizializzati con il loro nome e colore.
     */
    private Player[] players;

    /**
     * Costruttore della classe <code>Game</code> che inizializza i rispettivi attributi, usando gli oggetti di tipo
     * <code>Player</code> passati come dati in entrata.
     * @param players Array contenente i giocatori, con i rispettivi campi inizializzati.
     */
    Game(Player[] players) {
        gameField = new Field();
        moveList = new ArrayList<Move>();
        this.players = players;
    }

    /**
     * Metodo che restituisce il campo da gioco proprio della partita su cui è invocato.
     * @return oggetto di tipo <code>Field</code>
     */
    Field getGameField() {
        return gameField;
    }

    /**
     * Inizializza il campo da gioco della partita su cui è invocato, usando il campo dato in entrata al metodo.
     * @param f campo da gioco da inserire all'interno di una partita.
     */
    void setGameField(Field f) {
        this.gameField = f;
    }

    /**
     * Restituisce una lista delle mosse giocate fino a questo momento, nella partita su cui è invocato.
     * @return una lista di <code>Move</code>.
     */
    ArrayList<Move> getMoveList() {
        return moveList;
    }

    /**
     * Inserisce una lista di mosse in entrata, come log della partita su cui tale metodo è invocato.
     * @param moves lista di <code>Move</code> da inserire come log della partita corrente.
     */
    void setMoveList(ArrayList<Move> moves) {
        this.moveList = moves;
    }

    /**
     * Attraverso un indice in entrata, restituisce il giocatore in quella data posizione.
     * @param index indica la posizione del giocatore desiderato nella partita corrente.
     * @return <code>Player</code> nella posizione richiesta.
     */
    Player getPlayer(int index) {
        return players[index];
    }

    /**
     * Inserisce un'istanza di <code>Player</code> come giocatore della partita su cui tale metodo è invocato.
     * @param p giocatore da inserire nella posizione <code>index</code> richiesta nella partita.
     * @param index indice della posizione in cui inserire il giocatore <code>p</code>.
     */
    void setPlayer(Player p, int index) {
        players[index] = p;
    }

    void legalMoves(){
        Field legalMovesField = getGameField();
        convertField(legalMovesField, whoIsPlaying().getColor());
        printMoveField(legalMovesField);
    }

    void convertField(Field field, Color playerColor){
        Coordinate coordinate = new Coordinate(0, 0);
        Slot currentSlot = null;
        for (int x = 0; x < field.length(); x++){
            for (int y = 0; y < field.length(); y++){
                coordinate.setX(x);
                coordinate.setY(y);
                currentSlot = field.getSlot(coordinate);
                if (currentSlot.getColorState() == playerColor){
                    markNeighboringSlot(field, coordinate);
                }
            }
        }
    }

    void markNeighboringSlot(Field field, Coordinate coordinate) {
        Coordinate markCoordinate = new Coordinate(0, 0);
        for (int distance = 1; distance <= 2; distance++) {
            for (int row = coordinate.getX() - distance; row < coordinate.getX() + distance; row++) {
                if ((row == 0) || (row == field.length() - 1)) {
                    for (int column = coordinate.getY()-distance; column < coordinate.getY()+distance; column++) {
                        if (!(((row < 0) || (row >= field.length())) || ((column < 0) || (column > field.length())))) {
                            markCoordinate.setX(row);
                            markCoordinate.setY(column);
                            markSlot(field.getSlot(markCoordinate), distance);
                        }
                    }
                }else {
                    markCoordinate.setX(row);
                    markCoordinate.setY(0);
                    markSlot(field.getSlot(markCoordinate), distance);
                    markCoordinate.setX(row);
                    markCoordinate.setY(field.length() - 1);
                    markSlot(field.getSlot(markCoordinate), distance);
                }
            }
        }
    }

    void markSlot(Slot slot, int distance) {
        if ((slot.getColorState() != Color.BIANCO) && (slot.getColorState() != Color.NERO)) {
            switch (distance) {
                case 1:
                    if (slot.getColorState() == Color.ARANCIONE) {
                        slot.setColorState(Color.ROSA);
                    }else {
                        slot.setColorState(Color.GIALLO);
                    }
                    break;
                case 2:
                    if (slot.getColorState() == Color.GIALLO) {
                        slot.setColorState(Color.ROSA);
                    }else {
                        slot.setColorState(Color.ARANCIONE);
                    }
                    break;
            }
        }
    }

}
