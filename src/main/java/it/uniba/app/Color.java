package it.uniba.app;

/**
 * Color è una enumerazione per la gestione dei colori.
 * In questa enumerazione sono riportati tutti i colori utilizzati nel programma con la rispettiva codifica in 8 bit.
 */
public enum Color {
    ROSSO(0),
    BLU(0),
    BIANCO(0),
    NERO(0),
    ARANCIONE(0),
    ROSA(0),
    GIALLO(0),
    VERDE(0);

    /**
     * Codice del colore. La codifica utilizzata è a 8 bit.
     */
    private final int colorValue;

    /**
     * Imposta il codice del colore a quello specificato.
     * @param value il valore del colore.
     */
    Color(final int value) {
        colorValue = value;
    }

    /**
     * Restituisce il codice del colore.
     * @return il codice del colore.
     */
    public int getColorValue() {
        return colorValue;
    }
}
