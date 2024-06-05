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
     * @return
     */
    private static int checkColsWord(final String row) {
        switch (row.charAt(0)) {
            case 'a':
                return 0;
            case 'b':
                return 1;
            case 'c':
                return 2;
            case 'd':
                return 3;
            case 'e':
                return 4;
            case 'f':
                return 5;
            case 'g':
                return 6;
            case 'h':
                return 7;
            default:
                return -1;
        }
    }

    /**
     * Controlla l'indice della riga.
     * @param col la colonna da controllare.
     */
    private static int checkRowsWord(final String col) {
        switch (col.charAt(0)) {
            case '1':
                return 0;
            case '2':
                return 1;
            case '3':
                return 2;
            case '4':
                return 3;
            case '5':
                return 4;
            case '6':
                return 5;
            case '7':
                return 6;
            case '8':
                return 7;
            default:
                return -1;
        }
    }

    /**
     * Metodo per l'acquisizione della prossima mossa dove il pattern è casella di partenza - casella di arrivo.
     */
    public static String[] getNextMove(String command) {
        if (command.length() == 5) {
            String[] nextMoveArray = command.split("-");
            if (nextMoveArray.length == 2)  {
                int startRow = checkRowsWord(nextMoveArray[0].substring(1));
                int startCol = checkColsWord(nextMoveArray[0].substring(0, 1));
                int destRow = checkRowsWord(nextMoveArray[1].substring(1));
                int destCol = checkColsWord(nextMoveArray[1].substring(0, 1));
                if (startRow != -1 || startCol != -1 || destRow != -1 || destCol != -1) {
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
