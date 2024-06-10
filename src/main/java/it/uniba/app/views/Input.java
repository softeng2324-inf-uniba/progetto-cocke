package it.uniba.app.views;
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
     * Attributo di istanza per i casi di test per l'input.
     */
    private static String testCommand = null;

    /**
     * Costruttore per la classe Input.
     */
    private Input() { }


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
        if (testCommand != null) {
            String command = testCommand;
            testCommand = null;
            return command;
        } else {
            Output.printMessages(Message.INSERT_COMMAND);
            return Keyboard.readString();
        }
    }

    /**
     * Metodo per testare un comando preso input evitando di leggere da tastiera.
     */
    public static void setTestCommand(final String command) {
        testCommand = command;
    }
}
