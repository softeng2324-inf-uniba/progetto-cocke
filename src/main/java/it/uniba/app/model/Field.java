package it.uniba.app.model;

import java.util.Arrays;

/**
 * {@literal <<Entity>>}
 * Classe che rappresenta il campo da gioco.
 */
public class Field {
    /**
     * Contiene le caselle del campo da gioco.
     */
    private final Slot[][] gameboard;
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
     * Costruttore di copia della classe <code>Field</code>: crea una copia del campo da gioco in ingresso.
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
        try {
            return gameboard[coordinate.getRow()][coordinate.getColumn()];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Restituisce la lunghezza del lato del campo da gioco.
     * @return lunghezza del lato del campo da gioco.
     */
    public int length() {
        return gameboard.length;
    }

    /**
     * Verifica, se l'oggetto in entrata è un <code>Field</code>, se è uguale all'oggetto su cui
     * il metodo è stato invocato.
     * @param obj oggetto secondo membro dell'uguaglianza.
     * @return <code>true</code> se i due campi da gioco hanno le medesime caselle nello stesso stato,
     * <code>false</code> in tutti gli altri casi.
     */
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Field castObj) {
            return Arrays.deepEquals(gameboard, castObj.gameboard);
        }
        return false;
    }

    /**
     * Calcola il valore hash relativo alle caselle presenti nel campo da gioco su cui il metodo
     * è invocato.
     * @return il valore hash del <code>Field</code>.
     */
    public int hashCode() {
        return Arrays.deepToString(gameboard).hashCode();
    }

}
