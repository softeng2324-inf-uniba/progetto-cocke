package it.uniba.app.utils;

/**
 * Color è una enumerazione per la gestione dei colori.
 * In questa enumerazione sono riportati tutti i colori utilizzati nel programma con la rispettiva codifica in 8 bit.
 */
public enum Color {
    BIANCO(15),
    NERO(0),
    ARANCIONE(208),
    ROSA(213),
    GIALLO(226),
    GRIGIO(244);

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
