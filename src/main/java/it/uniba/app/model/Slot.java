package it.uniba.app.model;

import it.uniba.app.utils.Color;
import it.uniba.app.views.Output;

/**
 * {@literal <<Entity>>}
 * Classe che rappresenta una casella del campo da gioco.
 */
public class Slot {
    /**
     * Rappresenta lo stato della casella.
     */
    private Color colorState;

    /**
     * Costruttore della classe <code>Slot</code> che inizializza una casella libera.
     */
    Slot() {
        colorState = Output.DEFAULT_BACKGROUND;
    }

    /**
     * Crea un nuovo <code>Slot</code> che è la copia di quello passato come argomento.
     * @param slot lo slot da copiare.
     */
    Slot(final Slot slot) {
        colorState = slot.getColorState();
    }

    /**
     * Restituisce lo stato della casella.
     * @return stato della casella sulla quale è stato invocato il metodo.
     */
    public Color getColorState() {
        return colorState;
    }

    /**
     * Modifica lo stato della casella attraverso il parametro in ingresso.
     * @param newColorState il colore della casella da inizializzare.
     */
    public void setColorState(final Color newColorState) {
        colorState = newColorState;
    }

    /**
     * Modifica lo stato della casella su cui il metodo è invocato, in base alla distanza selezionata.
     * @param distance la distanza per cui evidenziare.
     */
    public void markSlot(final int distance) {
        if ((getColorState() != Color.WHITE)
                && (getColorState() != Color.BLACK)
                && (getColorState() != Color.PINK)
                && (getColorState() != Color.DARK_GREY)) {
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
