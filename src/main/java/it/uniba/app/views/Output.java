package it.uniba.app.views;

import it.uniba.app.controller.GameController;
import it.uniba.app.model.Field;
import it.uniba.app.model.Game;
import it.uniba.app.utils.Color;
import it.uniba.app.model.Coordinate;
import it.uniba.app.utils.Message;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.time.Duration;


/**
 * {@literal <<Boundary>>}
 * Output è la classe che si occupa delle stampe a video.
 * <p>Questa classe gestisce la stampa degli elementi del gioco Ataxx,
 * come la stampa del campo da gioco e dell'interfaccia grafica.</p>
 */
public final class Output {

    /**
     * Numero di righe presenti nel file Winner.txt.
     */
    private static int WINNER_LINE = 36;

    /**
     * Stringa contenente il percorso relativo del file Winner.txt.
     */
    private static String relativeWinnerPath = "/src/main/java/it/uniba/app/Winner.txt";
    /**
     * Workspace attuale.
     */
    private static String winnerPath = Paths.get(System.getProperty("user.dir"), relativeWinnerPath).toString();

    /**
     * Costruttore privato per evitare che la classe Output venga istanziata.
     */
    private Output() { };

    /**
     * Colore di background di default.
     */
    public static final Color DEFAULT_BACKGROUND = Color.GREY;

    /**
     * Colore di background di default.
     */
    public static final Color DEFAULT_CHAR = Color.WHITE;

    /**
     * Stampa le linee interne del campo di gioco.
     * @param dim dimensione del campo di gioco
     */
    private static void printCrossedHoLine(final int dim) {
        for (int i = 0; i < dim - 1; i++) {
            System.out.print("══                                                                    ═══╬");
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
            System.out.print("  " + c);
            Output.switchCharColor(DEFAULT_BACKGROUND);
            System.out.print("⛂ ");
            Output.switchCharColor(DEFAULT_CHAR);
            System.out.print("║");
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
            Coordinate c = new Coordinate(num - 1, i);
            if (field1.getSlot(c).getColorState() == Color.WHITE || field1.getSlot(c).getColorState() == Color.BLACK) {
                switchCharColor(field1.getSlot(c).getColorState());
                System.out.print("  " + "⛂");
                System.out.print(" ");
                switchCharColor(DEFAULT_CHAR);
                System.out.print(" ║");
            } else {
                switchBackgroundColor(field1.getSlot(c).getColorState());
                switchCharColor(field1.getSlot(c).getColorState());
                System.out.print("  ⛂  ");
                switchBackgroundColor(DEFAULT_BACKGROUND);
                switchCharColor(DEFAULT_CHAR);
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
        System.out.println("═════╝");
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
        System.out.println("═════╝");
    }

    /** Prende in input un percorso di un file e ne stampa il suo contenuto.
     * @param filePath percorso del file da stampare,
     */
    public static void printFile(final String filePath) {
        if (new File(filePath).exists()) {
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(filePath), "UTF-8"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            Output.printMessages(Message.FILE_NOT_FOUND);
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
     * Stampa i messaggi assieme ad eventuali parametri extra.
     * @param message messaggio che si vuole mostrare.
     * @param extra stringhe da aggiungere al messaggio standard.
     */
    public static void printMessages(final Message message, final String... extra) {
        System.out.print(message.getMessageText());
        for (String string:extra) {
            System.out.print(string);
        }
        System.out.print("\n");
    }

    /**
     * Stampa il file Winner.txt contenente la schermata di vittoria nel fine partita.
     * @param game la partita in corso.
     */
    public static void printWinner(final Game game) {
        if (new File(winnerPath).exists()) {
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(winnerPath), "UTF-8"))) {
                StringBuilder completeTextBuilder = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    completeTextBuilder.append(line).append('\n');
                }
                String completeText = completeTextBuilder.toString();
                Character actualCharacter;
                Output.switchCharColor(game.whoIsPlaying().getColor());
                    for (int character = 0; character < completeText.length(); character++) {
                        actualCharacter = completeText.charAt(character);
                        switch (actualCharacter.toString()) {
                            case "#":
                                System.out.print(String.format("%2s" , game.countPieces(Color.BLACK)).replace(' ', '0'));
                                break;
                            case "*":
                                System.out.print(String.format("%2s" , game.countPieces(Color.WHITE)).replace(' ', '0'));
                                break;
                            case "@":
                                Duration duration = game.getElapsedTime();
                                StringBuilder output = new StringBuilder();
                                output.append(String.format("%2s" , duration.toHours()).replace(' ', '0')).append(":");
                                output.append(String.format("%2s" , duration.minusHours(duration.toHours()).toMinutes()).replace(' ', '0')).append(":");
                                output.append(String.format("%2s" , duration.minusMinutes(duration.toMinutes()).toSeconds()).replace(' ', '0'));
                                System.out.print(output);
                                break;
                            case "§":
                                Output.switchCharColor(Color.BLACK);
                                break;
                            case "^":
                                Output.switchCharColor(Color.WHITE);
                                break;
                            case "ò":
                                Output.switchCharColor(game.whoIsPlaying().getColor());
                                break;
                            default:
                                System.out.print(actualCharacter);
                                break;
                        }
                    }
                    Output.switchCharColor(DEFAULT_CHAR);
                    System.out.println();
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            Output.printMessages(Message.FILE_NOT_FOUND);
        }
    }

    /**
     * Stampa nel formato ore:minuti:secondi un dato arco temporale.
     * @param elapsedTime l'arco temporale da stampare.
     */
    public static void printElapsedTime(final Duration elapsedTime) {
        Duration duration = elapsedTime;
        StringBuilder output = new StringBuilder();
        output.append(duration.toHours()).append(":");
        output.append(duration.minusHours(duration.toHours()).toMinutes()).append(":");
        output.append(duration.minusMinutes(duration.toMinutes()).toSeconds());
        Output.printMessages(Message.ELAPSED_TIME, output.toString());
    }
}

