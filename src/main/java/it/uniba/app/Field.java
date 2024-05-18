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
    public static final int DEFAULT_DIM = 7;

    /**
     * Costruisce un oggetto Field.
     */
    Field() {
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
    Field(final Field field) {
        gameboard = new Slot[field.length()][field.length()];
        Coordinate coordinate = new Coordinate(0, 0);
        for (int row = 0; row  < length(); row++) {
            for (int column = 0; column < length(); column++) {
                coordinate.setX(column);
                coordinate.setY(row);
                setSlot(coordinate, new Slot(getSlot(coordinate)));
            }
        }
    }

    /**
     * Costruisce un oggetto Field.
     * @param dim dimensione del campo di gioco.
     */
    Field(final int dim) {
        gameboard = new Slot[dim][dim];

        for (int row = 0; row < dim; row++) {
            for (int column = 0; column < dim; column++) {
                setSlot(new Coordinate(row, column), new Slot());
            }
        }
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
