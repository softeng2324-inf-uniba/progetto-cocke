package it.uniba.app;


/**
 * Slot è la classe che rappresenta la casella.
 */
public class Slot {
    /**
     * Rappresenta lo stato della casella se piena(di che colore) o vuota.
     */
    private Color colorState;

    /**
     * Costruttore della classe slot che inizializza una casella libera, indicata con il colore
     * <code>Color.VERDE</code>.
     */
    Slot() {
        colorState = Color.VERDE;
    }

    /**
     * Costruttore della classe slot che inizializza il colore della stessa con quello passato
     * come parametro attuale.
     * @param c il colore della casella.
     */
    public Slot(final Color c) {
        colorState = c;
    }

    /**
     * Restituisce lo stato della casella.
     * @return lo stato della casella.
     */
    public Color getColorState() {
        return colorState;
    }

    /**
     * Modifica lo stato della casella.
     * @param c il colore della casella.
     */
    public void setColorState(final Color c) {
        colorState = c;
    }
}
