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
     * Costruisce un oggetto Field
     */
    Field () {
        gameboard = new Slot [7][7];
    }

    /**
     * Costruisce un oggetto Field
     * @param dim dimensione del campo di gioco
     */
    Field (int dim) {
        gameboard = new Slot [dim][dim];
    }

    /**
     * Imposta uno slot nella coordinata c
     * @param c Coordinate x e y
     * @param s Slot da inserire
     */
    public void setSlot (Coordinate c,Slot s) {
        gameboard[c.getX()][c.getY()] = s;
    }

    /**
     * Restituisce uno slot del campo
     * @param c Coordinate x e y
     * @return Slot nella posizione c
     */
    public Slot getSlot (Coordinate c) {
        return gameboard[c.getX()][c.getY()];
    }
    
}