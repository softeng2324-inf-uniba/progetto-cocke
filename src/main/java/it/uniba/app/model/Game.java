package it.uniba.app.model;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import it.uniba.app.utils.Color;
import it.uniba.app.views.Output;

/**
 * {@literal <<Entity>>}
 * <code>Game</code> contiene gli elementi che costituiscono una partita del gioco.
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
     * L'istante di tempo in cui è iniziato il gioco.
     */
    private ZonedDateTime startTime;

    /**
     * Costruttore della classe <code>Game</code> privo di parametri formali, che inizializza tutti i rispettivi
     * attributi, inizializzando i giocatori con dei nomi di default (es. Giocatore 1).
     */
    public Game() {
        gameField = new Field();
        moveList = new ArrayList<>();

        players = new Player[2];
        players[0] = new Player(Color.BLACK, "Giocatore 1");
        players[1] = new Player(Color.WHITE, "Giocatore 2");

        startTime = ZonedDateTime.now();
    }

    /**
     * Costruttore della classe <code>Game</code> che inizializza i rispettivi attributi, usando gli oggetti di tipo
     * <code>Player</code> passati come dati in entrata.
     * @param newPlayers Array contenente i giocatori, con i rispettivi campi inizializzati.
     */
    public Game(final Player[] newPlayers) {
        gameField = new Field();
        moveList = new ArrayList<>();
        players = new Player[2];
        System.arraycopy(newPlayers, 0, players, 0, newPlayers.length);
        startTime = ZonedDateTime.now();
    }

    /**
     * Costruttore di copia della classe <code>Game</code>, che restituisce una copia della partita passata in ingresso.
     * @param srcGame partita in ingresso da copiare.
     */
    public Game(final Game srcGame) {
        gameField = srcGame.getGameField();
        moveList = srcGame.getMoveList();
        players = srcGame.getPlayers();
        startTime = srcGame.getStartTime();
    }

    /**
     * Restituisce un elenco dei giocatori presenti nella partita attuale.
     * @return Copia dell'elenco dei giocatori in partita.
     */
    public Player[] getPlayers() {
        Player[] playersTemp = new Player[players.length];
        System.arraycopy(players, 0, playersTemp, 0, playersTemp.length);
        return playersTemp;
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
     * @param srcField campo da gioco da inserire all'interno di una partita.
     */
    public void setGameField(final Field srcField) {
        gameField = new Field(srcField);
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
     * @param srcMoves lista di <code>Move</code> da inserire come log della partita corrente.
     */
    public void setMoveList(final ArrayList<Move> srcMoves) {
        moveList = new ArrayList<>(srcMoves);
    }

    /**
     * Attraverso un indice in entrata, restituisce il giocatore in quella data posizione.
     * @param index indica la posizione del giocatore desiderato nella partita corrente.
     * @return <code>Player</code> nella posizione richiesta.
     */
    public Player getPlayer(final int index) {
        return new Player(players[index]);
    }

    /**
     * Inserisce un'istanza di <code>Player</code> come giocatore della partita su cui tale metodo è invocato.
     * @param srcPlayer giocatore da inserire nella posizione <code>index</code> richiesta nella partita.
     * @param index indice della posizione in cui inserire il giocatore <code>srcPlayer</code>.
     */
    public void setPlayer(final Player srcPlayer, final int index) {
        players[index] = new Player(srcPlayer);
    }

    /**
     * Restituisce l'istante di tempo in cui è iniziato il gioco.
     * @return l'istante di tempo in cui è iniziato il gioco.
     */
    public ZonedDateTime getStartTime() {
        return startTime;
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
     * Conta le pedine di un giocatore il cui colore è dato in ingresso.
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

    /**
     * Stampa il tempo trascorso dall'inizio del gioco.
     */
    public void elapsedTime() {
        ZonedDateTime currentTime = ZonedDateTime.now();
        Duration elapsedTime = Duration.between(getStartTime(), currentTime);
        Output.printElapsedTime(elapsedTime);
    }
}
