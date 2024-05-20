package it.uniba.app.model;

import it.uniba.app.utils.Color;

/**
 * <code>Player</code> rappresenta il singolo giocatore con il suo colore e il suo nome in partita.
 */
public class Player {
    /**
     * Rappresenta il colore del giocatore.
     */
    private Color playerColor;

    /**
     * Rappresenta il nome del giocatore.
     */
    private String playerName;

    /**
     * Costruttore della classe <code>Player</code> che inizializza nome e colore delle pedine di un giocatore.
     */
    public Player() {
        playerColor = null;
        playerName = "_";
    }

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
     * Inizializza il colore del giocatore.
     * @param color colore del giocatore da inizializzare.
     */
    public void setColor(final Color color) {
        playerColor = color;
    }

    /**
     * Restituisce il nome del giocatore.
     * @return nome del giocatore.
     */
    public String getName() {
        return playerName;
    }

    /**
     * Inizializza il nome del giocatore.
     * @param name nome del giocatoreda inizializzare.
     */
    public void setName(final String name) {
        playerName = name;
    }
}

