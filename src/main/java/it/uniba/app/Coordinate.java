package it.uniba.app;

/**
 * Coordinate e la classe che serve ad indicare le coordinate di gioco.
 */
public class Coordinate {
    /**
     * x è l'indice di riga.
     */
    private int x;

    /**
     * y è l'indice di colonna.
     */
    private int y;

    /**
     * Costruisce le coordinate.
     * @param newX è l'indice di riga.
     * @param newY è l'indice di colonna.
     */
    public Coordinate(final int newX, final int newY) {
        x = newX;
        y = newY;
    }

    /**
     * Prende il valore x.
     * @return l'indice di riga.
     */
    public int getX() {
        return x;
    }

    /**
     * Prende il valore y.
     * @return y l'indice di colonna.
     */
    public int getY() {
        return y;
    }

    /**
     * Imposta il valore x.
     */
    public void setX(final int newX) {
        x = newX;
    }

    /**
     * Imposta il valore y.
     */
    public void setY(final int newY) {
        y = newY;
    }
}
