package it.uniba.app.model;
import java.util.ArrayList;
import it.uniba.app.utils.Color;

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
    public Game() {
        gameField = new Field();
        moveList = new ArrayList<Move>();

        players = new Player[2];
        players[0] = new Player(Color.BLACK, "Giocatore 1");
        players[1] = new Player(Color.WHITE, "Giocatore 2");
    }

    /**
     * Costruttore della classe <code>Game</code> che inizializza i rispettivi attributi, usando gli oggetti di tipo
     * <code>Player</code> passati come dati in entrata.
     * @param newPlayers Array contenente i giocatori, con i rispettivi campi inizializzati.
     */
    public Game(final Player[] newPlayers) {
        gameField = new Field();
        moveList = new ArrayList<Move>();
        for (int n = 0; n < newPlayers.length; n++){
            players[n] = newPlayers[n];
        }
    }

    public Game(Game gioco) {
        gameField = gioco.getGameField();
        moveList = gioco.getMoveList();
        players = gioco.getPlayers();
    }

    public Player[] getPlayers() {
        Player[] newPlayers = new Player[players.length];
        for (int n = 0; n < newPlayers.length; n++){
            newPlayers[n] = players[n];
        }
        return newPlayers;
    }

    /**
     * Metodo che restituisce il campo da gioco proprio della partita su cui è invocato.
     * @return oggetto di tipo <code>Field</code>
     */
    public Field getGameField() {
        return new Field(gameField);
    }

    /**
     * Inizializza il campo da gioco della partita su cui è invocato, usando il campo dato in entrata al metodo.
     * @param f campo da gioco da inserire all'interno di una partita.
     */
    public void setGameField(final Field f) {
        this.gameField = new Field(f);
    }

    /**
     * Restituisce una lista delle mosse giocate fino a questo momento, nella partita su cui è invocato.
     * @return una lista di <code>Move</code>.
     */
    public ArrayList<Move> getMoveList() {
        return new ArrayList<>(moveList);
    }

    /**
     * Inserisce una lista di mosse in entrata, come log della partita su cui tale metodo è invocato.
     * @param moves lista di <code>Move</code> da inserire come log della partita corrente.
     */
    public void setMoveList(final ArrayList<Move> moves) {
        this.moveList = new ArrayList<>(moves);
    }

    /**
     * Attraverso un indice in entrata, restituisce il giocatore in quella data posizione.
     * @param index indica la posizione del giocatore desiderato nella partita corrente.
     * @return <code>Player</code> nella posizione richiesta.
     */
    public Player getPlayer(final int index) {
        return players[index];
    }

    /**
     * Inserisce un'istanza di <code>Player</code> come giocatore della partita su cui tale metodo è invocato.
     * @param p giocatore da inserire nella posizione <code>index</code> richiesta nella partita.
     * @param index indice della posizione in cui inserire il giocatore <code>p</code>.
     */
    public void setPlayer(final Player p, final int index) {
        players[index] = p;
    }

    /**
     * Restituisce il giocatore di turno.
     * @return giocatore del turno appena iniziato/in corso.
     */
    public Player whoIsPlaying() {
        return getPlayer(getMoveList().size() % 2);
    }


    /**
     * Restituisce il giocatore successivo al giocatore di turno.
     * @return <code>Player</code> successivo a quello attuale.
     */
    public Player nextPlayer() {
        int turnNumber = getMoveList().size();
        return getPlayer((turnNumber + 1) % 2);
    }

    /**
     * Conta le pedine di un giocatore.
     * @param color colore del giocatore selezionato.
     * @return numero delle pedine del giocatore.
     */
    public int countPieces(final Color color) {
        int count = 0;
        Coordinate coordinata = new Coordinate(0, 0);
        for (int row = 0; row < getGameField().length(); row++) {
            coordinata.setRow(row);
            for (int column = 0; column < getGameField().length(); column++) {
                coordinata.setColumn(column);
                if (getGameField().getSlot(coordinata).getColorState() == color) {
                    count++;
                }
            }
        }
        return count;
    }
}
