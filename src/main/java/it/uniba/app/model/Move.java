package it.uniba.app.model;

/**
 * {@literal <<Entity>>}
 * Contiene, per ogni mossa, un riferimento alla casella di partenza e di arrivo di una singola mossa.
 */
public class Move {
    /**
     * Distanza massima tra le coordinate delle caselle coinvolte in una mossa.
     */
    public static final int MAX_DISTANCE = 2;

    /**
     * startingSlot rappresenta la casella iniziale.
     */
    private final Coordinate startingSlot;
    /**
     * chosenSlot rappresenta la casella d'arrivo.
     */
    private final Coordinate chosenSlot;

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
     * Restituisce la coordinata della casella di arrivo.
     * @return la coordinata della casella di arrivo.
     */
    public Coordinate getChosenSlot() {
        return new Coordinate(chosenSlot);
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
     * Verifica la correttezza della distanza tra le coordinate delle caselle coinvolte in una mossa.
     * @param distance distanza tra le caselle sul campo da gioco.
     * @return (true) se la distanza è consentita, (false) in caso contrario.
     */
    public static boolean checkDistance(final int distance) {
        return distance > 0 && distance <= MAX_DISTANCE;
    }

    /**
     * Restituisce la mossa nel formato per la stampa.
     * @return una stringa in formato "casella di partenza-casella di arrivo".
     */
    @Override
    public String toString() {
        Coordinate sSlot = getStartingSlot();
        Coordinate cSlot = getChosenSlot();
        return sSlot + "-" + cSlot;
    }
}
