package it.uniba.app.model;

import it.uniba.app.utils.Color;

/**
 * {@literal <<Entity>>}
 * <code>Player</code> rappresenta il singolo giocatore con il suo colore e il suo nome in partita.
 */
public class Player {
    /**
     * Rappresenta il colore del giocatore.
     */
    private final Color playerColor;

    /**
     * Rappresenta il nome del giocatore.
     */
    private final String playerName;

    /**
     * Costruttore della classe Player che inizializza il giocatore con il suo colore e il suo nome.
     * @param color colore del giocatore da inizializzare.
     * @param name nome del giocatore da inizializzare.
     */
    public Player(final Color color, final String name) {
        playerColor = color;
        playerName = name;
    }

    /**
     * Costruttore di copia della classe <code>Player</code> che inizializza una copia del giocatore in ingresso.
     * @param srcPlayer istanza del giocatore da copiare.
     */
    public Player(final Player srcPlayer) {
        playerColor = srcPlayer.getColor();
        playerName = srcPlayer.getName();
    }

    /**
     * Restituisce il colore del giocatore.
     * @return colore del giocatore.
     */
    public Color getColor() {
        return playerColor;
    }

    /**
     * Restituisce il nome del giocatore.
     * @return nome del giocatore.
     */
    public String getName() {
        return playerName;
    }

}

