package it.uniba.app.views;
import it.uniba.app.model.Coordinate;
import it.uniba.app.utils.Color;
import it.uniba.app.model.Move;
import it.uniba.app.model.Player;
import it.uniba.app.utils.Messages;

/**
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
        Output.printMessages(Messages.INSERIRE_NOME_GIOCATORE, String.valueOf(playerIndex + 1));
        if (playerIndex == 1) {
            p = new Player(Color.WHITE, Keyboard.readString());
        } else {
            p = new Player(Color.BLACK, Keyboard.readString());
        }
        return p;
    }

    /**
     * Metodo per l'acquisizione di una mossa da effettuare.
     * @return la mossa letta da tastiera.
     */
    public static Move getMove() {
        char row = ' ';
        int col = -1;
        Output.printMessages(Messages.INSERIRE_RIGA, "della pedina da muovere");
        row = Keyboard.readChar();
        Output.printMessages(Messages.INSERIRE_COLONNA, "della pedina da muovere");
        col = Keyboard.readInt();
        Coordinate start = new Coordinate(row, col);
        Output.printMessages(Messages.INSERIRE_RIGA, "dello slot di arrivo");
        row = Keyboard.readChar();
        Output.printMessages(Messages.INSERIRE_COLONNA, "dello slot di arrivo");
        col = Keyboard.readInt();
        Coordinate choice = new Coordinate(row, col);
        return new Move(start, choice);
    }

    /**
     * Metodo per l'acquisizione di una coordinata.
     * @return le coordinate lette da tastiera.
     */
    private static Coordinate getCoordinate() {
        char row = ' ';
        int col = -1;
        Coordinate c = new Coordinate(row, col);
        Output.printMessages(Messages.INSERIRE_RIGA);

        c.setRow(row);
        Output.printMessages(Messages.INSERIRE_COLONNA);
        c.setColumn(col);
        return c;
    }

    /**
     * Metodo per l'acquisizione di un comando.
     * @return il comando letto da tastiera.
     */
    public static String getCommand() {
        Output.printMessages(Messages.INSERIRE_COMANDO);
        return Keyboard.readString();
    }
}
