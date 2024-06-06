package it.uniba.app.views;
import it.uniba.app.utils.Color;
import it.uniba.app.model.Player;
import it.uniba.app.utils.Message;

/**
 * {@literal <<Boundary>>}
 * Classe Input utile all'acquisizione degli input da tastiera.
 */
public final class Input {
    /**
     * Lunghezza massima del comando per gestire le coordinate.
     */
    static final int MAX_COMMAND_LENGTH = 5;

    /**
     * Lunghezza massima di una singola coordinata.
     */
    static final int MAX_COORDINATE_LENGTH = 2;

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
        Player p;
        Output.printMessages(Message.INSERT_PLAYER_NAME, String.valueOf(playerIndex + 1));
        if (playerIndex == 1) {
            p = new Player(Color.WHITE, Keyboard.readString());
        } else {
            p = new Player(Color.BLACK, Keyboard.readString());
        }
        return p;
    }

    /**
     * Prende in input il command e lo gestisce per ottenere le mosse desiderate dall'utente.
     * @param command comando inserito da tastiera dall'utente.
     * @return mossa inserita dall'utente, null altrimenti.
     */
    public static String[] getNextMove(final String command) {
        if (command.length() == MAX_COMMAND_LENGTH) {
            String[] nextMoveArray = command.split("-");
            try {
                if (nextMoveArray.length == MAX_COORDINATE_LENGTH) {
                    int startRow = Integer.parseInt(nextMoveArray[0].substring(1));
                    String startCol = nextMoveArray[0].substring(0, 1);
                    int destRow = Integer.parseInt(nextMoveArray[1].substring(1));
                    String destCol = nextMoveArray[1].substring(0, 1);
                    if (startRow != -1 && !startCol.equals(" ") && destRow != -1 && !destCol.equals(" ")) {
                        return new String[]{nextMoveArray[0], nextMoveArray[1]};
                    }  else {
                        Output.printMessages(Message.ILLEGAL_MOVE);
                    }
                }
            } catch (NumberFormatException e) {
                Output.printMessages(Message.ILLEGAL_MOVE);
            }
        }
        return null;
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
