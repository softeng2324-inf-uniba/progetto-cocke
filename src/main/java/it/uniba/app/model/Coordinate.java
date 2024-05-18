package it.uniba.app.model;

/**
 * Coordinate e la classe che serve ad indicare le coordinate di gioco.
 */
public class Coordinate {
    /**
     * x è la coordinata x.
     */
    private int x;
    /**
     * y è la coordinata y.
     */
    private int y;


    /**
     * Costruisce le coordinate.
     * @param newX è la coordinata ascisse.
     * @param newY è la coordinata ordinate.
     */
    public Coordinate(final int newX, final int newY) {
        x = newX;
        y = newY;
    }

    /**
     * Prende il valore x.
     * @return il valore delle ascisse.
     */
    public int getX() {
        return x;
    }

    /**
     * Prende il valore y.
     * @return y il valore delle ordinate.
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
