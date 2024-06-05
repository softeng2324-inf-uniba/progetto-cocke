package it.uniba.app.utils;

/**
 * {@literal <<noECB>>}
 * Message è una enumerazione per la gestione dei messaggi.
 * <p>In questa enumerazione sono riportati tutti i messaggi principali del programma.</p>
 */
public enum Message {
    NO_GAME("Non è stata avviata alcuna partita. '/gioca' per avviare una nuova partita."),
    CONFIRM_EXIT("Sicuro di voler uscire? (s/n)"),
    UNKNOWN_FLAG("Flag non riconosciuta: "),
    UNKNOWN_COMMAND("Comando sconosciuto. '/help' per mostrare la lista dei comandi."),
    INSERT_PLAYER_NAME("Inserire il nome del giocatore "),
    INSERT_ROW("Inserire la riga "),
    INSERT_COLUMN("Inserire la colonna "),
    INSERT_COMMAND("Inserire un comando: "),
    BAD_CONFIRMATION_EXIT("Errore! Inserire 's' per uscire, 'n' per annullare."),
    CONFIRM_ABANDONMENT("Sei sicuro di voler abbandonare la partita? (s/n)"),
    BAD_CONFIRMATION_LEAVE("Errore, inserire 's' per abbandonare o 'n' per annullare."),
    FILE_NOT_FOUND("File non trovato"),
    START_SLOT("della pedina da muovere"),
    ARRIVAL_SLOT("dello slot di arrivo"),
    WINNER_PLAYER("Il giocatore "),
    PLAYER_WON(" ha vinto per abbandono dell'avversario, il punteggio è "),
    NO_MOVES("Non è stata giocata nessuna mossa."),
    MOVE_LIST("Ecco la lista delle mosse: "),
    SCORE_0(" a 0."),
    ILLEGAL_MOVE("Mossa non valida, riprova.");
    /**
     * Il testo del messaggio.
     */
    private final String messageText;

    /**
     * Costruisce un messaggio con il testo specificato.
     * @param text il testo del messaggio.
     */
    Message(final String text) {
        messageText = text;
    }

    /**
     * Restituisce il testo del messaggio.
     * @return il testo del messaggio.
     */
    public String getMessageText() {
        return messageText;
    }
}
