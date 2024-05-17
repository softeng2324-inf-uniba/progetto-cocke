package it.uniba.app;

/**
 * Slot è la classe che rappresenta la casella
 */
public class Slot {
    /**
     * Rappresenta lo stato della casella se piena(di che colore) o vuota
     */
    private Color colorState;

    /**
     * Costruttore della classe slot
     * @param c il colore della casella
     */
    public Slot (Color c){
        colorState = c;
    }

    /**
     * Restituisce lo stato della casella
     * @return lo stato della casella
     */
    public Color getColorState() {
        return colorState;
    }

    /**
     * Modifica lo stato della casella
     * @param c il colore della casella
     */
    public void setColorState (Color c) {
        colorState = c;
    }

    /**
     * Evidenzia lo slot selezionato in base alla distanza selezionata.
     * @param distance la distanza per cui evidenziare.
     */
    void markSlot(final int distance) {
        if ((getColorState() != Color.BIANCO) && (getColorState() != Color.NERO)) {
            switch (distance) {
                case 1:
                    if (getColorState() == Color.ARANCIONE) {
                        setColorState(Color.ROSA);
                    } else {
                        setColorState(Color.GIALLO);
                    }
                    break;
                case 2:
                    if (getColorState() == Color.GIALLO) {
                        setColorState(Color.ROSA);
                    } else {
                        setColorState(Color.ARANCIONE);
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
