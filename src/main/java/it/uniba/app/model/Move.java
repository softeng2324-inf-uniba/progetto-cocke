package it.uniba.app.model;

/**
 * {@literal <<Entity>>}
 * Contiene, per ogni mossa, un riferimento alla casella di partenza e di arrivo di una singola mossa.
 */
public class Move {
    /**
     * startingSlot rappresenta la casella iniziale.
     */
    private Coordinate startingSlot;
    /**
     * chosenSlot rappresenta la casella d'arrivo.
     */
    private Coordinate chosenSlot;

    /**
     * Costruttore della classe <code>Move</code> che utilizza le coordinate delle caselle di partenza e di arrivo in
     * ingresso.
     * @param starting coordinata della casella di partenza.
     * @param chosen coordinata della casella di arrivo.
     */
    public Move(final Coordinate starting, final Coordinate chosen) {
        startingSlot = new Coordinate(starting);
        chosenSlot = new Coordinate(chosen);
    }

    /**
     * Restituisce la coordinata della casella di partenza.
     * @return coordinata della casella di partenza.
     */
    public Coordinate getStartingSlot() {
        return new Coordinate(startingSlot);
    }

    /**
     * Imposta la coordinata della casella di partenza.
     * @param starting coordinata della casella di partenza.
     */
    public void setStartingSlot(final Coordinate starting) {
        startingSlot = new Coordinate(starting);
    }

    /**
     * Restituisce la coordinata della casella di arrivo.
     * @return la coordinata della casella di arrivo.
     */
    public Coordinate getChosenSlot() {
        return new Coordinate(chosenSlot);
    }

    /**
     * Inizializza la coordinata della casella di arrivo.
     * @param chosen coordinata della casella di arrivo.
     */
    public void setChosenSlot(final Coordinate chosen) {
        chosenSlot = new Coordinate(chosen);
    }

    /**
     * Data una mossa, ne calcola la distanza tra le coordinate ad'essa appartenenti.
     * @return restituisce la distanza tra le coordinate contenute nella mossa.
     */
    public int getDistance() {
        Coordinate start = getStartingSlot();
        Coordinate destination = getChosenSlot();
        return java.lang.Math.max(java.lang.Math.abs(start.getRow() - destination.getRow()),
                java.lang.Math.abs(start.getColumn() - destination.getColumn()));
    }

    /**
     * Restituisce la mossa nel formato per la stampa.
     * @return una stringa in formato "casella di partenza-casella di arrivo".
     */
    @Override
    public String toString() {
        Coordinate sSlot = getStartingSlot();
        Coordinate cSlot = getChosenSlot();
        String formatoMossa = sSlot + "-" + cSlot;
        return formatoMossa;
    }
}
