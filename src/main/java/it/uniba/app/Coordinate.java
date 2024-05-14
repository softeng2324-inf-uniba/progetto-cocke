package it.uniba.app;

/**
 * Coordinate e la classe che serve ad indicare le coordinate di gioco
 */
public class Coordinate{
    /**
     * x è la coordinata x
     */
    private int x;
    /**
     * y è la coordinata y
     */
    private int y;


    /**
     * Costruisce le coordinate
     * @param x è la coordinata ascisse
     * @param y è la coordinata ordinate
     */
    public Coordinate(int x , int y) {
        this.x=x;
        this.y=y;
    }

    /**
     * Prende il valore x
     * @return il valore delle ascisse
     */
    public int getX(){
        return x;
    }

    /**
     * Prende il valore y
     * @return il valore delle ordinate
     */
    public int getY(){
        return y;
    }
    /**
     * Imposta il valore x
     * @return il valore delle ascisse
     */
    public void setX(int x){
        this.x=x;
    }
    /**
     * Imposta il valore y
     * @return il valore delle ordinate
     */
    public void setY(int y){
        this.y=y;
    }
}