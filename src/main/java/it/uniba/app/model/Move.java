package it.uniba.app.model;

/**
 * Move è la classe che serve a conservare lo slot iniziale e lo slot finale.
 */

public class Move {
    /**
     * startingSlot è lo slot iniziale.
     */
    private Coordinate startingSlot;
    /**
     * chosenSlot è lo slot finale.
     */
    private Coordinate chosenSlot;

    /**
     * Costruisce un oggetto Move.
     * @param starting  la coordinata di partenza.
     * @param chosen  la coordinata di arrivo.
     */
    public Move(final Coordinate starting, final Coordinate chosen) {
        startingSlot = new Coordinate(starting);
        chosenSlot = new Coordinate(chosen);
    }

    /**
     * Restituisce la coordinata dello slot di partenza.
     * @return la coordinata dello slot di partenza.
     */
    public Coordinate getStartingSlot() {
        return new Coordinate(startingSlot);
    }

    /**
     * Imposta la coordinata dello slot di partenza.
     * @param starting la coordinata di partenza.
     */
    public void setStartingSlot(final Coordinate starting) {
        startingSlot = new Coordinate(starting);
    }

    /**
     * Restituisce la cooridnata dello slot di arrivo.
     * @return la coordinata dello slot di arrivo.
     */
    public Coordinate getChosenSlot() {
        return new Coordinate(chosenSlot);
    }

    /**
     * Imposta la coordinata dello slot di arrivo.
     * @param chosen la coordinata di arrivo.
     */
    public void setChosenSlot(final Coordinate chosen) {
        chosenSlot = new Coordinate(chosen);
    }
}
