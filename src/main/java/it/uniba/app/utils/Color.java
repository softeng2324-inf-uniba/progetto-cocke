package it.uniba.app.utils;

/**
 * {@literal <<noECB>>}
 * Color è una enumerazione per la gestione dei colori.
 * In questa enumerazione sono riportati tutti i colori utilizzati nel programma con la rispettiva codifica in 8 bit.
 */
public enum Color {
    WHITE(15),
    BLACK(0),
    ORANGE(208),
    PINK(213),
    YELLOW(226),
    GREY(244),
    DARK_GREY(240);

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
