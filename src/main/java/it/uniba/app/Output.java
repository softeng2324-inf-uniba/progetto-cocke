package it.uniba.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Output è la classe che si occupa delle stampe a video.
 * <p>Questa classe gestisce la stampa degli elementi del gioco Ataxx,
 * come la stampa del campo da gioco e dell'interfaccia grafica.</p>
 */
public final class Output {

    /**
     * Costruttore privato per evitare che la classe Output venga istanziata.
     */
    private Output() { };

    /** Prende in input un percorso di un file e ne stampa il suo contenuto.
     * @param filePath percorso del file da stampare,
     */
    public static void printFile(final String filePath) {
        if (new File(filePath).exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.out.println("Errore durante la lettura del file: " + e.getMessage());
            }
        } else {
            System.out.println("File non trovato");
        }
    }

    /**
     * Imposta il colore dei caratteri del terminale.
     * @param color il colore da impostare.
     */
    public static void switchCharColor(final Color color) {
        System.out.print(String.format("\033[38:5:%dm", color.getColorValue()));
    }

    /**
     * Imposta il colore di sfondo del terminale.
     * @param color il colore da impostare.
     */
    public static void switchBackgroundColor(final Color color) {
        System.out.print(String.format("\033[48:5:%dm", color.getColorValue()));
    }
}
