package it.uniba.app.model;

/**
 * Classe che rappresenta il campo da gioco.
 */

public class Field {
    /**
     * Contiene le caselle del campo da gioco.
     */
    private Slot[][] gameboard;
    /**
     * Dimensione del campo da gioco di default.
     */
    public static final int DEFAULT_DIM = 7;

    /**
     * Costruttore della classe <code>Field</code> che utilizza la dimensione di default del campo.
     */
    public Field() {
        gameboard = new Slot[DEFAULT_DIM][DEFAULT_DIM];
        for (int row = 0; row < DEFAULT_DIM; row++) {
            for (int column = 0; column < DEFAULT_DIM; column++) {
                setSlot(new Coordinate(row, column), new Slot());
            }
        }
    }

    /**
     * Crea un nuovo campo che è la copia di quello passato come argomento.
     * @param field il campo da copiare.
     */
    public Field(final Field field) {
        gameboard = new Slot[field.length()][field.length()];
        Coordinate coordinate = new Coordinate(0, 0);
        for (int row = 0; row  < length(); row++) {
            for (int column = 0; column < length(); column++) {
                coordinate.setRow(row);
                coordinate.setColumn(column);
                setSlot(coordinate, new Slot(field.getSlot(coordinate)));
            }
        }
    }

    /**
     * Costruttore della classe <code>Field</code> che utilizza il dato in entrata come dimensione del campo da gioco.
     * @param dim dimensione del campo da gioco da inizializzare.
     */
    public Field(final int dim) {
        gameboard = new Slot[dim][dim];
        for (int row = 0; row < dim; row++) {
            for (int column = 0; column < dim; column++) {
                setSlot(new Coordinate(row, column), new Slot());
            }
        }
    }

    /**
     * Inserisce una casella, in entrata, all'interno del campo da gioco, nella posizione data in ingresso.
     * @param coordinate coordinata all'interno del campo da gioco, in cui inserire la casella.
     * @param slot casella da inserire nel campo da gioco su cui il metodo è invocato.
     */
    public void setSlot(final Coordinate coordinate, final Slot slot) {
        gameboard[coordinate.getRow()][coordinate.getColumn()] = slot;
    }

    /**
     * Restituisce la casella del campo da gioco nella posizione in ingresso.
     * @param coordinate coordinata della casella del campo da gioco richiesta.
     * @return casella presente nel campo da gioco nella posizione inserita.
     */
    public Slot getSlot(final Coordinate coordinate) {
        return gameboard[coordinate.getRow()][coordinate.getColumn()];
    }

    /**
     * Restituisce la lunghezza del lato del campo da gioco.
     * @return lunghezza del lato del campo da gioco.
     */
    public int length() {
        return gameboard.length;
    }
}
