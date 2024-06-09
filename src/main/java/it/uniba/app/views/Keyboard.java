package it.uniba.app.views;

import it.uniba.app.utils.Message;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

/**
 * {@literal <<Boundary>>}
 * Utility class per la lettura di diversi tipi di dati da tastiera.
 */
final class Keyboard {
    /**
     * Costruttore per la classe Keyboard.
     */
    private Keyboard() { }

    /*
     *   Sezione per la gestione degli errori.
     */


    /**
     * Stampa il messaggio di errore se appropriato.
     */
    private static void error() {
        String str = "Errore nel leggere la stringa. Riprovare.";
        Output.printMessages(Message.UNKNOWN_COMMAND, str);
    }

    // Sezione per la lettura di input da tastiera.

    /**
     * Contiene il token corrente.
     */
    private static String currentToken = null;

    /**
     * Contiene i token da leggere.
     */
    private static StringTokenizer reader;

    /**
     * Contiene l'input da tastiera.
     */
    private static final BufferedReader IN = new BufferedReader(
            new InputStreamReader(System.in, StandardCharsets.UTF_8));


    /**
     * Restituisce il prossimo token di input derivato dalla funzione getNextInputToken.
     *
     * @return il prossimo token di input.
     */
    private static String getNextToken() {
        String token;
        if (currentToken == null) {
            token = getNextInputToken();
        } else {
            token = currentToken;
            currentToken = null;
       }
       return token;
    }

    /**
     * Restituisce il prossimo token di input.
     *
     * @return il prossimo token di input.
     */
    private static String getNextInputToken() {
        final String delimiters = " \t\n\r\f";
        String token = null;
        String text;
        try {
            if (reader == null) {
                text = IN.readLine();
                if (text != null) {
                    reader = new StringTokenizer(text, delimiters, true);
                }
            }
            while (token == null || delimiters.contains(token)) {
                while (!reader.hasMoreTokens()) {
                    text = IN.readLine();
                    if (text != null) {
                        reader = new StringTokenizer(text, delimiters, true);
                    }
                }
                token = reader.nextToken();
            }
        } catch (Exception exception) {
            token = null;
        }

        return token;
    }

    /**
     * Metodo per trovare il fine linea.
     * @return (true) se ci troviamo a fine linea, (false) altrimenti
     */
    public static boolean endOfLine() {
        return !reader.hasMoreTokens();
    }

    /**
     * Legge e restituisce una stringa da tastiera.
     * @return str la stringa letta da tastiera.
     */
    public static String readString() {
        StringBuilder str;

        try {
            str = new StringBuilder(getNextToken());
            while (!endOfLine()) {
                str.append(getNextToken());
            }
        } catch (Exception exception) {
            error();
            str = null;
        }
        assert str != null;
        return str.toString();
    }
}
