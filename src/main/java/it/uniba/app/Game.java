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
     * Costruttore della classe <code>Game</code> privo di parametri formali, che inizializza tutti i rispettivi
     * attributi, inizializzando i giocatori con dei nomi di default (es. Giocatore 1).
     */
    Game() {
        gameField = new Field();
        moveList = new ArrayList<Move>();

        players = new Player[2];
        players[0] = new Player(Color.NERO, "Giocatore 1");
        players[1] = new Player(Color.BIANCO, "Giocatore 2");
    }

    /**
     * Costruttore della classe <code>Game</code> che inizializza i rispettivi attributi, usando gli oggetti di tipo
     * <code>Player</code> passati come dati in entrata.
     * @param newPlayers Array contenente i giocatori, con i rispettivi campi inizializzati.
     */
    Game(final Player[] newPlayers) {
        gameField = new Field();
        moveList = new ArrayList<Move>();
        players = newPlayers;
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
    void setGameField(final Field f) {
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
    void setMoveList(final ArrayList<Move> moves) {
        this.moveList = moves;
    }

    /**
     * Attraverso un indice in entrata, restituisce il giocatore in quella data posizione.
     * @param index indica la posizione del giocatore desiderato nella partita corrente.
     * @return <code>Player</code> nella posizione richiesta.
     */
    Player getPlayer(final int index) {
        return players[index];
    }

    /**
     * Inserisce un'istanza di <code>Player</code> come giocatore della partita su cui tale metodo è invocato.
     * @param p giocatore da inserire nella posizione <code>index</code> richiesta nella partita.
     * @param index indice della posizione in cui inserire il giocatore <code>p</code>.
     */
    void setPlayer(final Player p, final int index) {
        players[index] = p;
    }

    /**
     * Restituisce il giocatore di turno.
     * @return giocatore del turno appena iniziato/in corso.
     */
    Player whoIsPlaying() {
        return getPlayer(getMoveList().size() % 2);
    }

    /**
     * Inizializza la posizione iniziale delle pedine sul campo da gioco a inizio partita.
     */
    void setStartingPosition() {
        int[] tempXY = new int[2];
        tempXY[0] = 0;
        tempXY[1] = Field.DEFAULT_DIM - 1;

        Field tempField = getGameField();

        Slot tempSlot = new Slot();

        for (int x = 0; x < tempXY.length; x++) {

            for (int y = 0; y < tempXY.length; y++) {

                Coordinate tempCoordinate = new Coordinate(tempXY[x], tempXY[y]);

                if ((x + y) % 2 != 0) {
                    tempSlot.setColorState(Color.BIANCO);
                } else {
                    tempSlot.setColorState(Color.NERO);
                }

                tempField.setSlot(tempCoordinate, tempSlot);

            }

        }

    }
    
    /**
     * Stampa il campo con le mosse consentite per il giocatore di turno.
     * <p>Il metodo stampa:
     * <ul>
     *     <li>in giallo le caselle in cui è possibile effettuare duplicarsi;</li>
     *     <li>in arancione le caselle in cui è possibile spostarsi;</li>
     *     <li>in rosa le caselle in cui è possibile effettuare entrambe le azioni.</li>
     * </ul>
     */
    void legalMoves() {
        Field legalMovesField = new Field(getGameField());
        convertField(legalMovesField, whoIsPlaying().getColor());
        //Output.printField(legalMovesField);
    }

    /**
     * Converte il campo per mostrare le mosse consentite al giocatore di turno.
     * <p>Il <code>field</code> passato come parametro
     * è convertito in un campo con le caselle colorate a seconda delle mosse consentite.
     * @param field il campo da convertire.
     * @param playerColor il colore del giocatore di cui mostrare le mosse.
     */
    void convertField(final Field field, final Color playerColor) {
        Coordinate coordinate = new Coordinate(0, 0);
        Slot currentSlot = null;
        for (int x = 0; x < field.length(); x++) {
            for (int y = 0; y < field.length(); y++) {
                coordinate.setX(x);
                coordinate.setY(y);
                currentSlot = field.getSlot(coordinate);
                if (currentSlot.getColorState() == playerColor) {
                    markNeighboringSlot(field, coordinate);
                }
            }
        }
    }

    /**
     * Evidenzia le caselle adiacenti alla pedina.
     * @param field il campo in cui evidenziare le caselle.
     * @param coordinate la posizione della casella da cui evidenziare le caselle.
     */
    void markNeighboringSlot(final Field field, final Coordinate coordinate) {
        Coordinate markCoordinate = new Coordinate(0, 0);
        for (int distance = 1; distance <= 2; distance++) {
            for (int row = (coordinate.getX() - distance); row <= (coordinate.getX() + distance); row++) {
                if ((row == (coordinate.getX() - distance)) || (row == (coordinate.getX() + distance))) {
                    for (int column = (coordinate.getY() - distance); column <= (coordinate.getY() + distance);
                         column++) {
                        if ((((row >= 0) && (row < field.length())) && ((column >= 0) && (column < field.length())))) {
                            markCoordinate.setX(row);
                            markCoordinate.setY(column);
                            field.getSlot(markCoordinate).markSlot(distance);
                        }
                    }
                } else {
                    int column = coordinate.getY() - distance;
                    if ((((row >= 0) && (row < field.length())) && ((column >= 0) && (column < field.length())))) {
                        markCoordinate.setX(row);
                        markCoordinate.setY(column);
                        field.getSlot(markCoordinate).markSlot(distance);
                    }
                    column = coordinate.getY() + distance;
                    if ((((row >= 0) && (row < field.length())) && ((column >= 0) && (column < field.length())))) {
                        markCoordinate.setX(row);
                        markCoordinate.setY(column);
                        field.getSlot(markCoordinate).markSlot(distance);
                    }
                }
            }
        }
    }

}
