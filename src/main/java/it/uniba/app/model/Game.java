package it.uniba.app.model;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import it.uniba.app.utils.Color;

import static it.uniba.app.model.Field.DEFAULT_DIM;

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
    private final Player[] players;
    /**
     * L'istante di tempo in cui è iniziato il gioco.
     */
    private final ZonedDateTime startTime;

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
     * Restituisce l'istante di tempo in cui è iniziato il gioco.
     * @return l'istante di tempo in cui è iniziato il gioco.
     */
    private ZonedDateTime getStartTime() {
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
    public Duration getElapsedTime() {
        ZonedDateTime currentTime = ZonedDateTime.now();
        return Duration.between(getStartTime(), currentTime);
    }

    /**
     * Decreta il vincitore in base al numero di pedine.
     * @return il colore del vincitore.
     */
    public Color colorWinner() {
        if (countPieces(Color.BLACK) > countPieces(Color.WHITE)) {
            return Color.BLACK;
        } else {
            return Color.WHITE;
        }
    }

    /**
     * Modifica lo stato delle caselle circostanti e adiacenti alla casella presente nella posizione indicata
     * dalla coordinata in ingresso, eguagliandone il colore.
     * @param coordinate posizione, sul campo da gioco, della casella da cui parte la cattura delle circostanti
     */
    public void captureSlot(final Coordinate coordinate) {
        Coordinate tempCoordinate = new Coordinate(coordinate);
        Field gameFieldCopy = getGameField();
        Color colorSlot = gameFieldCopy.getSlot(coordinate).getColorState();
        for (int rowOffset = -1; rowOffset < 2; rowOffset++) {
            int tempRow = coordinate.getRow() + rowOffset;
            if (tempRow >= 0 && tempRow < DEFAULT_DIM) {
                tempCoordinate.setRow(tempRow);
                for (int columnOffset = -1; columnOffset < 2; columnOffset++) {
                    int tempColumn = coordinate.getColumn() + columnOffset;
                    if (tempColumn >= 0 && tempColumn < DEFAULT_DIM) {
                        tempCoordinate.setColumn(tempColumn);
                        if (gameFieldCopy.getSlot(tempCoordinate).getColorState() != Color.GREY
                         && gameFieldCopy.getSlot(tempCoordinate).getColorState() != Color.DARK_GREY) {
                            gameFieldCopy.getSlot(tempCoordinate).setColorState(colorSlot);
                        }
                    }
                }
            }
        }
        setGameField(gameFieldCopy);
    }

    /**
     * Verifica, se l'oggetto in entrata è un <code>Game</code>, se è uguale all'oggetto su cui
     * il metodo è stato invocato.
     * @param obj oggetto secondo membro dell'uguaglianza.
     * @return <code>true</code> se le due partite hanno il campo da gioco, l'elenco delle mosse giocate e i giocatori
     * nel medesimo stato, <code>false</code> in tutti gli altri casi.
     */
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Game castObj) {
            return gameField.equals(castObj.gameField) && moveList.equals(castObj.getMoveList())
                    && Arrays.equals(getPlayers(), castObj.getPlayers());
        }
        return false;
    }

    /**
     * Calcola il valore hash relativo alla partita su cui il metodo è invocato.
     * @return il valore hash del <code>Game</code>.
     */
    public int hashCode() {
        String temp = getGameField().toString()
                + getMoveList().toString()
                + Arrays.toString(getPlayers())
                + getStartTime().toString();
        return temp.hashCode();
    }

}
