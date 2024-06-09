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

    /**
     * Verifica, se l'oggetto in entrata è un <code>Player</code>, se è uguale all'oggetto su cui
     * il metodo è stato invocato.
     * @param obj oggetto secondo membro dell'uguaglianza.
     * @return <code>true</code> se i due giocatori hanno stesso colore e nome, <code>false</code>
     * in tutti gli altri casi.
     */
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Player castObj) {
            return getColor().equals(castObj.getColor()) && getName().equals(castObj.getName());
        }
        return false;
    }

    /**
     * Calcola il valore hash relativo al nome e al colore del <code>Player</code> su cui il metodo è invocato.
     * @return il valore hash del giocatore.
     */
    public int hashCode() {
        return (getName() + getColor().toString()).hashCode();
    }

}

