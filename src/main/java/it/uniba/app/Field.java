package it.uniba.app;

/**
 * Classe che rappresenta il campo di gioco.
 */

public class Field {
    /**
     * Matrice di slot che rappresenta il campo di gioco.
     */
    private Slot[][] gameboard;
    /**
     * Dimensione di default del campo di gioco.
     */
    static final int DEFAULT_DIM = 7;

    /**
     * Costruisce un oggetto Field.
     */
    Field() {
        gameboard = new Slot[DEFAULT_DIM][DEFAULT_DIM];
    }

    /**
     * Costruisce un oggetto Field.
     * @param dim dimensione del campo di gioco.
     */
    Field(final int dim) {
        gameboard = new Slot[dim][dim];
    }

    /**
     * Imposta uno slot nella coordinata c.
     * @param c Coordinate x e y.
     * @param s Slot da inserire.
     */
    public void setSlot(final Coordinate c, final Slot s) {
        gameboard[c.getX()][c.getY()] = s;
    }

    /**
     * Restituisce uno slot del campo.
     * @param c Coordinate x e y.
     * @return Slot nella posizione c.
     */
    public Slot getSlot(final Coordinate c) {
        return gameboard[c.getX()][c.getY()];
    }

    /**
     * Restituisce la lunghezza del campo.
     * @return la lunghezza del campo.
     */
    public int length() {
        return gameboard.length;
    }
}
