package it.uniba.app.views;
import it.uniba.app.model.Coordinate;
import it.uniba.app.model.Move;
import it.uniba.app.utils.Color;
import it.uniba.app.model.Player;
import it.uniba.app.utils.Message;

/**
 * {@literal <<Boundary>>}
 * Classe Input utile all'acquisizione degli input da tastiera.
 */
public final class Input {
    /**
     * Costruttore per la classe Input.
     */
    private Input() { }

    /**
     * Metodo per l'acquisizione del nome di un giocatore.
     * @param playerIndex id del nuovo giocatore.
     * @return il nuovo oggetto giocatore.
     */
    public static Player getPlayersName(final int playerIndex) {
        Player p = null;
        Output.printMessages(Message.INSERT_PLAYER_NAME, String.valueOf(playerIndex + 1));
        if (playerIndex == 1) {
            p = new Player(Color.WHITE, Keyboard.readString());
        } else {
            p = new Player(Color.BLACK, Keyboard.readString());
        }
        return p;
    }

    /**
     * Metodo per l'acquisizione della prossima mossa dove il pattern è casella di partenza - casella di arrivo.
     */
    public static String[] getNextMove() {
        String nextMove = Keyboard.readString();
        String[] nextMoveArray = nextMove.split("-");
        return nextMoveArray;
    }

    /**
     * Metodo per l'acquisizione di un comando.
     * @return il comando letto da tastiera.
     */
    public static String getCommand() {
        Output.printMessages(Message.INSERT_COMMAND);
        return Keyboard.readString();
    }
}
