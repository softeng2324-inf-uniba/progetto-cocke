package it.uniba.app.model;

/**
 * {@literal <<Entity>>}
 * Coordinate è la classe che serve ad indicare le coordinate di gioco.
 */
public class Coordinate {
    /**
     * Rappresenta la riga della coordinata.
     */
    private int row;

    /**
     * Rappresenta la colonna della coordinata.
     */
    private int column;

    /**
     * Costruttore della classe <code>Coordinate</code> con ordinata e ascissa in entrata.
     * @param newRow valore dell'indice di riga.
     * @param newColumn valore dell'indice di colonna.
     */
    public Coordinate(final int newRow, final int newColumn) {
        row = newRow;
        column = newColumn;
    }

    /**
     * Costruttore di copia della classe <code>Coordinate</code> con la coordinata da copiare in entrata.
     * @param coordinate coordinata da copiare.
     */
    public Coordinate(final Coordinate coordinate) {
        row = coordinate.getRow();
        column = coordinate.getColumn();
    }

    /**
     * Restituisce il valore dell'indice di riga della coordinata.
     * @return valore dell'indice di riga della coordinata su cui è invocato.
     */
    public int getRow() {
        return row;
    }

    /**
     * Restituisce il valore dell'indice di colonna della coordinata.
     * @return valore dell'indice di colonna della coordinata su cui è invocato.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Inizializza il valore della riga della coordinata su cui è invocato.
     * @param newRow valore da inserire, come indice di riga, nella coordinata su cui il metodo è invocato.
     */
    public void setRow(final int newRow) {
        row = newRow;
    }

    /**
     * Inizializza il valore della colonna della coordinata su cui è invocato.
     * @param newCol valore da inserire, come indice di riga, nella coordinata su cui il metodo è invocato.
     */
    public void setColumn(final int newCol) {
        column = newCol;
    }

    /**
     * Stampa la singola coordinata.
     * @return la coordinata in formato stringa.
     */
    @Override
    public String toString() {
        char columnChar = (char) ('a' + getColumn());
        int row1 = getRow() + 1;
        return columnChar + "" + row1;
    }

    /**
     * Verifica, se l'oggetto in entrata è una coordinata, se è uguale all'oggetto su cui
     * il metodo è stato invocato.
     * @param obj oggetto secondo membro dell'uguaglianza.
     * @return <code>true</code> se le due coordinate hanno stessa ascissa e ordinata, <code>false</code>
     * in tutti gli altri casi.
     */
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Coordinate temp) {
            return Integer.valueOf(getRow()).equals(temp.getRow())
                    && Integer.valueOf(getColumn()).equals(temp.getColumn());
        }
        return false;
    }

    /**
     * Calcola il valore hash relativo all'ascissa e ordinata della coordinata sulla quale
     * il metodo è invocato.
     * @return il valore hash della coordinata.
     */
    public int hashCode() {
        String temp = String.valueOf(getRow());
        temp += String.valueOf(getColumn());
        return temp.hashCode();
    }
}

