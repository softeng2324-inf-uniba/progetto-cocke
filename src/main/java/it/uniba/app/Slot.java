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
        colorState = Color.GREY;
    }

    /**
     * Crea un nuovo <code>Slot</code> che è la copia di quello passato come argomento.
     * @param slot lo slot da copiare.
     */
    Slot(final Slot slot) {
        colorState = slot.getColorState();
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

    /**
     * Evidenzia lo slot selezionato in base alla distanza selezionata.
     * @param distance la distanza per cui evidenziare.
     */
    void markSlot(final int distance) {
        if ((getColorState() != Color.WHITE) && (getColorState() != Color.BLACK)) {
            switch (distance) {
                case 1:
                    if (getColorState() == Color.ORANGE) {
                        setColorState(Color.PINK);
                    } else {
                        setColorState(Color.YELLOW);
                    }
                    break;
                case 2:
                    if (getColorState() == Color.YELLOW) {
                        setColorState(Color.PINK);
                    } else {
                        setColorState(Color.ORANGE);
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
