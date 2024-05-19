package it.uniba.app.model;

/**
 * Coordinate e la classe che serve ad indicare le coordinate di gioco.
 */
public class Coordinate {
    /**
     * row è l'indice di riga.
     */
    private int row;

    /**
     * col è l'indice di colonna.
     */
    private int col;

    /**
     * Costruisce le coordinate.
     * @param inRow è l'indice di riga.
     * @param inCol è l'indice di colonna.
     */
    public Coordinate(final int inRow, final int inCol) {
        row = inRow;
        col = inCol;
    }

    public Coordinate(Coordinate coordinate) {
        row = coordinate.getRow();
        col = coordinate.getCol();
    }

    /**
     * Restituisce il valore row.
     * @return l'indice di riga.
     */
    public int getRow() {
        return row;
    }

    /**
     * Restituisce il valore y.
     * @return y l'indice di colonna.
     */
    public int getCol() {
        return col;
    }

    /**
     * Imposta il valore dell'indice di riga.
     */
    public void setRow(final int inRow) {
        row = inRow;
    }

    /**
     * Imposta il valore dell'indice di colonna.
     */
    public void setCol(final int inCol) {
        col = inCol;
    }
}
