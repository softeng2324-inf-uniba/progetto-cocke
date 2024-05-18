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

    /**
     * Stampa le linee interne del campo di gioco.
     * @param dim dimensione del campo di gioco
     */
    private static void printCrossedHoLine(final int dim) {
        for (int i = 0; i < dim - 1; i++) {
            System.out.print("═════╬");
        }
        System.out.print("═════");
    }

    /**
     * Metodo che stampa la riga contenente le lettere che identificano le colonne.
     * @param dim dimensione del campo.
     */
    private static void printLetters(final int dim) {
        System.out.print("    ║");
        char c = 'A';
        for (int i = 0; i < dim; i++) {
            System.out.print("  " + c + "  ║");
            c++;
        }
        System.out.print("\n");
    }

    /**
     * Metodo che stampa la riga num del campo di gioco.
     * @param dim dimensione del campo.
     * @param num riga da stampare.
     */
    private static void printStuffedLine(final int dim, final int num) {
        final int limitDim = 10;
        if (num < limitDim) {
            System.out.print("║ " + num + " ║");
        } else {
            System.out.print("║" + "\u2009" + "\u200a" + num + "\u200a" + "\u2009" + "║");
        }
        for (int i = 0; i < dim; i++) {
            System.out.print("     ║");
        }
        if (num < limitDim) {
            System.out.print(" " + num + " ║");
            System.out.print("\n");
        } else {
            System.out.print("\u2009" + "\u200a" + num + "\u200a" + "\u2009" + "║");
            System.out.print("\n");
        }
    }

    /**
     * Metodo che mostra a video il campo vuoto su cui giocare.
     */
    public static void printEmptyField() {
        final int dim = Field.DEFAULT_DIM; //da sostituire con Field.length successivamente.
        System.out.print("    ╔");
        for (int i = 0; i < dim - 1; i++) {
            System.out.print("═════╦");
        }
        System.out.print("═════╗");
        System.out.print("\n");
        printLetters(dim);

        System.out.print("╔═══╬");
        printCrossedHoLine(dim);
        System.out.print("╬═══╗");
        System.out.print("\n");

        int i;
        for (i = 0; i < dim - 1; i++) {
            printStuffedLine(dim, i + 1);
            System.out.print("╠═══╬");
            printCrossedHoLine(dim);
            System.out.print("╬═══╣");
            System.out.print("\n");
        }
        printStuffedLine(dim, i + 1);
        System.out.print("╚═══╬");
        printCrossedHoLine(dim);
        System.out.print("╬═══╝");
        System.out.print("\n");
        printLetters(dim);
        System.out.print("    ╚");
        for (i = 0; i < dim - 1; i++) {
            System.out.print("═════╩");
        }
        System.out.print("═════╝");
    }

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
