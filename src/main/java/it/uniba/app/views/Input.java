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
     * Costanti per le righe del tavoliere.
     */
    static final int ROW_A = 0;
    static final int ROW_B = 1;
    static final int ROW_C = 2;
    static final int ROW_D = 3;
    static final int ROW_E = 4;
    static final int ROW_F = 5;
    static final int ROW_G = 6;

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
     * Controlla l'indice della colonna e lo trasforma in un intero.
     * @param row la riga da controllare.
     * @return l'indice della riga.
     */
    private static int checkColsWord(final String row) {
        switch (row.charAt(0)) {
            case 'a':
                return ROW_A;
            case 'b':
                return ROW_B;
            case 'c':
                return ROW_C;
            case 'd':
                return ROW_D;
            case 'e':
                return ROW_E;
            case 'f':
                return ROW_F;
            case 'g':
                return ROW_G;
            default:
                return -1;
        }
    }


    /**
     * Metodo per l'acquisizione della prossima mossa dove il pattern è casella di partenza - casella di arrivo.
     * @param command il comando da analizzare.
     * @return un array di stringhe contenente la casella di partenza e la casella di arrivo, null altrimenti.
     */
    public static String[] getNextMove(final String command) {
        if (command.length() == MAX_COMMAND_LENGTH) {
            String[] nextMoveArray = command.split("-");
            if (nextMoveArray.length == MAX_COORDINATE_LENGTH)  {
                int startRow = Integer.parseInt(nextMoveArray[0].substring(1));
                int startCol = checkColsWord(nextMoveArray[0].substring(0, 1));
                int destRow = Integer.parseInt(nextMoveArray[1].substring(1));
                int destCol = checkColsWord(nextMoveArray[1].substring(0, 1));
                if (startRow != -1 && startCol != -1 && destRow != -1 && destCol != -1) {
                    return new String[]{nextMoveArray[0], nextMoveArray[1]};
                }
            }
        }
        Output.printMessages(Message.ILLEGAL_MOVE);
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
