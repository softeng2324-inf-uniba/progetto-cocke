package it.uniba.app.model;

import it.uniba.app.utils.Color;

/**
 * Player è la classe che rappresenta il giocatore con il suo colore e il suo nome.
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
     * Costruttore vuoto della classe Player.
     */
    public Player() {
        playerColor = null;
        playerName = "_";
    }
    /**
     * Costruttore della classe Player che inizializza il giocatore con il suo colore e il suo nome.
     * @param color colore del giocatore.
     * @param name nome del giocatore.
     */
    public Player(final Color color, final String name) {
        playerColor = color;
        playerName = name;
    }

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
     * Imposta il colore del giocatore.
     * @param color colore del giocatore
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
     * Imposta il nome del giocatore.
     * @param name nome del giocatore.
     */
    public void setName(final String name) {
        playerName = name;
    }
}

