package it.uniba.app.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import it.uniba.app.model.Field;

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
     * Metodo che stampa a video la riga num del campo di gioco, eventualmente con le pedine nelle caselle occupate.
     * @param dim dimensione del campo di gioco.
     * @param num riga da stampare a video.
     * @param field1 campo di gioco da stampare a video.
     */
    private static void printStuffedLine(final int dim, final int num, final Field field1) {
        final int limitDim = 10;
        if (num < limitDim) {
            System.out.print("║ " + num + " ║");
        } else {
            System.out.print("║" + "\u2009" + "\u200a" + num + "\u200a" + "\u2009" + "║");
        }
        for (int i = 0; i < dim; i++) {
            Coordinate c = new Coordinate(num, i);
            if (field1.getSlot(c).getColorState() == Color.WHITE || field1.getSlot(c).getColorState() == Color.BLACK) {
                switchCharColor(field1.getSlot(c).getColorState());
                System.out.print(" " + "\u200a" + "\u200a" + "\u200a" + "\u200a" + "⛂");
                System.out.print("\u200a" + "\u200a" + "\u200a");
                switchCharColor(Color.WHITE); //colore di default dei caratteri del terminale
                System.out.print(" ║");
            } else {
                switchBackgroundColor(field1.getSlot(c).getColorState());
                System.out.print("     ");
                switchBackgroundColor(Color.GREY); //da cambiare con variabile globale del colore in ataxx.java
                System.out.print("║");
            }
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
     * Metodo che stampa la riga num del campo di gioco.
     * @param dim dimensione del campo.
     * @param num riga da stampare.
     */
    private static void printNumLine(final int dim, final int num) {
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
    static void printEmptyField() {
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
            printNumLine(dim, i + 1);
            System.out.print("╠═══╬");
            printCrossedHoLine(dim);
            System.out.print("╬═══╣");
            System.out.print("\n");
        }
        printNumLine(dim, i + 1);
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

    /**
     * Metodo che stampa a video il campo di gioco con le pedine nella situazione attuale.
     * @param f campo di gioco da visualizzare.
     */
    public static void printField(final Field f) {
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
            printStuffedLine(dim, i + 1, f);
            System.out.print("╠═══╬");
            printCrossedHoLine(dim);
            System.out.print("╬═══╣");
            System.out.print("\n");
        }
        printStuffedLine(dim, i + 1, f);
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

    /**
     * Metodo che gestisce le stampe dei messaggi di errore.
     * @param id messaggio che si vuole mostrare.
     * @param extra stringa da aggiungere al messaggio standard.
     */
    public static void printMessages(final int id, final String extra) {
        switch (id) {
            case 1:
                System.out.println("Non è stata avviata alcuna partita. '/gioca' per avviare una nuova partita.");
                break;
            case 2:
                System.out.println("Sicuro di voler uscire? (s/n)");
                break;
            case 3:
                System.out.println("Flag non riconosciuta: " + extra);
                break;
            case 4:
                System.out.println("Comando sconosciuto");
                break;
            case 5:
                System.out.println("Inserire il nome del giocatore " + extra);
                break;
            case 6:
                System.out.println("Inserire la riga " + extra);
                break;
            case 7:
                System.out.println("Inserire la colonna " + extra);
                break;
            case 8:
                System.out.println("Inserire un comando");
                break;
            case 9:
                System.out.println(extra);
                break;
            default:
                System.out.println("ID messaggio sconosciuto");
                break;
        }
    }
}
