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
        gameboard[0][0].setColorState(Color.BIANCO);
        gameboard[DEFAULT_DIM - 1][DEFAULT_DIM - 1].setColorState(Color.BIANCO);
        gameboard[0][DEFAULT_DIM - 1].setColorState(Color.NERO);
        gameboard[DEFAULT_DIM - 1][0].setColorState(Color.NERO);
    }

    /**
     * Costruisce un oggetto Field.
     * @param dim dimensione del campo di gioco.
     */
    Field(final int dim) {
        gameboard = new Slot[dim][dim];
        gameboard[0][0].setColorState(Color.BIANCO);
        gameboard[dim - 1][dim - 1].setColorState(Color.BIANCO);
        gameboard[0][dim - 1].setColorState(Color.NERO);
        gameboard[dim - 1][0].setColorState(Color.NERO);
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
}
